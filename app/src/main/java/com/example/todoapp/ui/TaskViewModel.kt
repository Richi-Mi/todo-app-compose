package com.example.todoapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.addtask.domain.AddTaskUseCase
import com.example.todoapp.addtask.domain.DeleteTaskUseCase
import com.example.todoapp.addtask.domain.GetTasksUseCase
import com.example.todoapp.addtask.domain.UpdateTaskUseCase
import com.example.todoapp.ui.TaskUIState.Success
import com.example.todoapp.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUIState> = getTasksUseCase().map(::Success)
        .catch { TaskUIState.Error(it) }
        .stateIn( viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUIState.Loading )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    //private val _tasks = mutableStateListOf<TaskModel>()
    //val task: List<TaskModel> = _tasks
    fun closeDialog() {
        _showDialog.value = false
    }

    fun onTaskCreated( text: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase( TaskModel( task = text ) )
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        // ACTUALIZAR LAS TAREAS.
        //val index = _tasks.indexOf( taskModel )
        //_tasks[index] = _tasks[index].let {
        //    it.copy( selected = !it.selected )
        //}
        viewModelScope.launch {
            updateTaskUseCase( taskModel.copy( selected = !taskModel.selected ) )
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        // DELETE USE CASE
        //val task = _tasks.find { it.id == taskModel.id }
        //_tasks.remove( task )
        viewModelScope.launch {
            deleteTaskUseCase( taskModel )
        }
    }
}