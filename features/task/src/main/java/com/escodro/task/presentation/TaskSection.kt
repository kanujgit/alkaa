package com.escodro.task.presentation

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.escodro.task.model.Task
import com.escodro.theme.AlkaaTheme
import java.util.Calendar

@Composable
fun TaskSection(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    onItemClicked: (Task) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            LazyColumnFor(items = taskList) { item ->
                TaskItem(item = item, onItemClicked = onItemClicked)
            }

        }
    }
}

@Composable
fun TaskItem(modifier: Modifier = Modifier, item: Task, onItemClicked: (Task) -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
            .padding(all = 8.dp)
            .preferredHeight(74.dp)
            .clickable { onItemClicked(item) })
    {
        Row(modifier = modifier.fillMaxWidth().padding(8.dp)) {
            Surface(
                modifier = modifier.fillMaxHeight().preferredWidth(4.dp),
                color = MaterialTheme.colors.secondary
            ) {}
        }
        Checkbox(
            modifier = modifier.fillMaxHeight(),
            checked = item.completed,
            onCheckedChange = {})
        Spacer(Modifier.preferredWidth(8.dp))
        Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = item.dueDate.toString(),
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
private fun AlkaaBottomNavPreview() {
    val taskList = listOf(
        Task(title = "Buy milk", dueDate = null),
        Task(title = "Call Mark", dueDate = Calendar.getInstance()),
        Task(title = "Watch Moonlight", dueDate = Calendar.getInstance())
    )

    AlkaaTheme {
        TaskSection(taskList = taskList, onItemClicked = {})
    }
}
