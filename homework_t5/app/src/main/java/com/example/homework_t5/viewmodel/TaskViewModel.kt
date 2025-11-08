package com.example.homework_t5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_t5.data.model.Task
import com.example.homework_t5.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiStateHolder(
    val loading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String? = null
)

class TaskViewModel(
    private val repo: TaskRepository = TaskRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHolder(loading = true))
    val uiState: StateFlow<UiStateHolder> = _uiState

    private val _taskDetailState = MutableStateFlow<Result<Task>?>(null)
    val taskDetailState: StateFlow<Result<Task>?> = _taskDetailState

    init {
        loadTasks()
    }

    /** ✅ Load danh sách Task */
    fun loadTasks() {
        _uiState.value = UiStateHolder(loading = true)
        viewModelScope.launch {
            try {
                val resp = repo.getTasks()
                if (resp.isSuccessful) {
                    val body = resp.body()
                    if (body != null && body.isSuccess) {
                        _uiState.value = UiStateHolder(tasks = body.data)
                    } else {
                        _uiState.value = UiStateHolder(
                            error = body?.message ?: "Không có dữ liệu từ server"
                        )
                    }
                } else {
                    _uiState.value = UiStateHolder(
                        error = "Server error: ${resp.code()} ${resp.message()}"
                    )
                }
            } catch (e: Exception) {
                val demo = listOf(
                    Task(1, "Demo Task A", "Fallback vì lỗi API", "Pending", "Low", "Demo", "14:00"),
                    Task(2, "Demo Task B", "Fallback", "In Progress", "High", "Work", "18:00")
                )
                _uiState.value = UiStateHolder(tasks = demo, error = e.localizedMessage)
            }
        }
    }

    /** ✅ Load chi tiết Task */
    fun loadTask(id: Int) {
        _taskDetailState.value = null
        viewModelScope.launch {
            try {
                val resp = repo.getTask(id)
                if (resp.isSuccessful) {
                    val body = resp.body()
                    if (body != null && body.isSuccess) {
                        _taskDetailState.value = Result.success(body.data)
                    } else {
                        _taskDetailState.value = Result.failure(
                            Exception(body?.message ?: "Empty response")
                        )
                    }
                } else {
                    _taskDetailState.value = Result.failure(
                        Exception("Server error ${resp.code()}")
                    )
                }
            } catch (e: Exception) {
                _taskDetailState.value = Result.failure(e)
            }
        }
    }

    /** ✅ Xóa Task */
    fun deleteTask(id: Int, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val resp = repo.deleteTask(id)
                if (resp.isSuccessful) {
                    val body = resp.body()
                    if (body != null && body.isSuccess) {
                        onComplete(true, null)
                        loadTasks() // reload lại danh sách
                    } else {
                        onComplete(false, body?.message ?: "Delete failed")
                    }
                } else {
                    onComplete(false, "Delete failed: ${resp.code()}")
                }
            } catch (e: Exception) {
                onComplete(false, e.localizedMessage)
            }
        }
    }
}
