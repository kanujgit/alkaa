package com.escodro.alkaa.ui.task.list

import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.escodro.alkaa.R
import com.escodro.alkaa.common.databinding.BindingRecyclerAdapter
import com.escodro.alkaa.data.local.model.TaskWithCategory
import com.escodro.alkaa.databinding.ItemTaskBinding

/**
 * [RecyclerView.Adapter] to bind the [TaskWithCategory] in the [RecyclerView].
 */
class TaskListAdapter(
    private val onItemClicked: (TaskWithCategory) -> Unit,
    private val onItemLongPressed: (TaskWithCategory) -> Boolean,
    private val onItemCheckedChanged: (TaskWithCategory, Boolean) -> Unit
) :
    BindingRecyclerAdapter<TaskWithCategory, ItemTaskBinding>() {

    override val layoutResource: Int
        get() = R.layout.item_task

    override fun bindData(binding: ItemTaskBinding, data: TaskWithCategory) {
        binding.task = data.task
        binding.color = data.category?.color ?: DEFAULT_LABEL_COLOR
        binding.isAlarmVisible = data.task.dueDate != null
        binding.cardviewItemtaskBackground.setOnClickListener { onItemClicked(data) }
        binding.cardviewItemtaskBackground.setOnLongClickListener { onItemLongPressed(data) }
        binding.checkboxItemtaskCompleted.setOnClickListener { view ->
            val isChecked = (view as? CheckBox)?.isChecked ?: false
            onItemCheckedChanged(data, isChecked)
        }
    }

    companion object {

        private const val DEFAULT_LABEL_COLOR = "#FFFFFF"
    }
}
