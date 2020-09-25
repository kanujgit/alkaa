package com.escodro.alkaa.presentation.home

import androidx.compose.animation.animate
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.escodro.alkaa.model.HomeSection
import com.escodro.task.model.Task
import com.escodro.task.presentation.TaskSection
import com.escodro.theme.AlkaaTheme
import java.util.Calendar

/**
 * Alkaa Home screen.
 */
@Composable
fun Home() {
    val (currentSection, setCurrentSection) = savedInstanceState { HomeSection.Tasks }
    val navItems = HomeSection.values().toList()

    val taskList = listOf(
        Task(title = "Buy milk", dueDate = null),
        Task(title = "Call Mark", dueDate = Calendar.getInstance()),
        Task(title = "Watch Moonlight", dueDate = Calendar.getInstance())
    )

    Scaffold(
        topBar = {
            AlkaaTopBar(currentSection = currentSection)
        },
        bottomBar = {
            AlkaaBottomNav(
                currentSection = currentSection,
                onSectionSelected = setCurrentSection,
                items = navItems
            )
        },
        bodyContent = {
            TaskSection(taskList = taskList, onItemClicked = {})
        }
    )
}

@Composable
private fun AlkaaTopBar(currentSection: HomeSection) {
    TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
        Stack(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.gravity(Alignment.Center),
                style = MaterialTheme.typography.h5,
                text = stringResource(currentSection.title)
            )
        }
    }
}

@Composable
private fun AlkaaBottomNav(
    currentSection: HomeSection,
    onSectionSelected: (HomeSection) -> Unit,
    items: List<HomeSection>
) {
    BottomAppBar(backgroundColor = MaterialTheme.colors.background) {
        items.forEach { section ->
            val selected = section == currentSection
            val tint = animate(
                if (selected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onSecondary
                }
            )
            AlkaaBottomIcon(section = section, tint = tint, onSectionSelected = onSectionSelected)
        }
    }
}

@Composable
private fun AlkaaBottomIcon(
    section: HomeSection,
    tint: Color,
    onSectionSelected: (HomeSection) -> Unit
) {
    val title = stringResource(section.title)
    IconButton(
        onClick = { onSectionSelected(section) },
        modifier = Modifier
            .weight(1f)
            .semantics { accessibilityLabel = title }
    ) {
        Icon(section.icon, tint = tint)
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview(showBackground = true)
@Composable
fun AlkaaTopBarPreview() {
    AlkaaTheme {
        AlkaaTopBar(HomeSection.Tasks)
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview(showBackground = true)
@Composable
fun AlkaaBottomNavPreview() {
    AlkaaTheme {
        AlkaaBottomNav(
            currentSection = HomeSection.Tasks,
            onSectionSelected = {},
            items = HomeSection.values().toList()
        )
    }
}
