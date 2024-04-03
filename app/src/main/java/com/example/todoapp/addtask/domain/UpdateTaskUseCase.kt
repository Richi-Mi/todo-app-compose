package com.example.todoapp.addtask.domain

import com.example.todoapp.addtask.data.TaskRepository
import com.example.todoapp.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor( private val taskRepository: TaskRepository) {
    suspend operator fun invoke( taskModel: TaskModel ) {
        taskRepository.update( taskModel )
    }
}
