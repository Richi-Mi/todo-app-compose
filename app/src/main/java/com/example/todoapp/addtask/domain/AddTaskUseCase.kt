package com.example.todoapp.addtask.domain

import com.example.todoapp.addtask.data.TaskRepository
import com.example.todoapp.ui.model.TaskModel
import javax.inject.Inject

// TODO: Implementar app.
class AddTaskUseCase @Inject constructor( private val taskRepository: TaskRepository ) {
    suspend operator fun invoke( taskModel: TaskModel ) {
        taskRepository.add( taskModel )
    }
}