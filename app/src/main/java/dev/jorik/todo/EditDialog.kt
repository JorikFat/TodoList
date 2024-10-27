package dev.jorik.todo

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import dev.jorik.todo.databinding.DialogEditBinding

class EditDialog(
    context : Context,
    val todo: Todo,
    val onEdit :(String)->Unit,
    val onDelete :(Todo)->Unit,
) :AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Изменить")

        val views = DialogEditBinding.inflate(LayoutInflater.from(context), null, false)
        setView(views.root)
        views.field.setText(todo.title)

        setButton(BUTTON_POSITIVE, "Изменить"){ _, _ -> onEdit(views.field.text.toString())}
        setButton(BUTTON_NEGATIVE, "Отмена") { _, _, -> /* cancel */ }
        setButton(BUTTON_NEUTRAL, "Удалить") { _, _, -> onDelete(todo)}

        super.onCreate(savedInstanceState)
    }

    override fun show() {
        super.show()
        getButton(BUTTON_NEUTRAL)?.setTextColor(Color.RED)
    }

}