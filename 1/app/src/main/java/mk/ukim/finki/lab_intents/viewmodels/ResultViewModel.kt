package mk.ukim.finki.lab_intents.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    private val _result: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getResultValue(): String {
        return _result.value ?: "N/A"
    }

    fun setResultValue(result: String) {
        this._result.value = result
    }

    fun getResult(): MutableLiveData<String> {
        return _result
    }
}