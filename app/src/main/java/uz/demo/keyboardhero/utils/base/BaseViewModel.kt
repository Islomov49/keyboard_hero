package uz.demo.keyboardhero.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        errorProcess(exception)
    }

    val vmScope = viewModelScope + handler + Dispatchers.IO

    private fun errorProcess(throwable: Throwable) {
        // catch any common exceptions
        throwable.printStackTrace()
    }
}
