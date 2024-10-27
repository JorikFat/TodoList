package dev.jorik.todo

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import dev.jorik.todo.databinding.DialogEditBinding

class AddDialog(
    context : Context,
    val onAdd :(String)->Unit
) :AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Добавить")
        val views = DialogEditBinding.inflate(LayoutInflater.from(context), null, false)
        setView(views.root)

        setButton(BUTTON_POSITIVE, "Добавить"){ _, _ -> onAdd(views.field.text.toString())}
        setButton(BUTTON_NEGATIVE, "Отмена") { _, _, -> /* cancel */ }
        super.onCreate(savedInstanceState)
    }
}