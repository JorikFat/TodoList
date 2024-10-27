package dev.jorik.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.jorik.todo.databinding.ItemTodoBinding

class TodoDiff(
    val name :String? = null,
    val flag :Boolean? = null,
)

private object Diff :DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Todo, newItem: Todo): TodoDiff? {
        if(oldItem == newItem) return null
        return TodoDiff(
            name = newItem.title.takeIf { oldItem.title != newItem.title },
            flag = newItem.flag.takeIf { oldItem.flag != newItem.flag }
        )
    }
}

class TodoAdapter(
    val onClick :(Todo)->Unit,
    val onHold :(Todo)->Unit,
) :ListAdapter<Todo, TodoAdapter.TodoViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding).apply {
            binding.root.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
            binding.root.setOnLongClickListener {
                onHold(getItem(adapterPosition))
                true
            }
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: TodoViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if(payloads.isEmpty()) return
        val diff = payloads.first() as TodoDiff
        holder.change(diff)
    }

    class TodoViewHolder(private val bindind :ItemTodoBinding) :ViewHolder(bindind.root) {
        fun bind(todo: Todo){
            bindind.done.isChecked = todo.flag
            bindind.name.text = todo.title
        }

        fun change(diff :TodoDiff){
            diff.name?.let(bindind.name::setText)
            diff.flag?.let(bindind.done::setChecked)
        }
    }

}