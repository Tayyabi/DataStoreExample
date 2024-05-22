package com.xyron.datastoreexample.viewmodel

import com.xyron.datastoreexample.Phonebook
import com.xyron.datastoreexample.repository.ImplRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private  val implRepository: ImplRepository): ViewModel() {

    var phone : MutableLiveData<String> = MutableLiveData("")
    var address : MutableLiveData<String> = MutableLiveData("")
    var name : MutableLiveData<String> = MutableLiveData("")

    var phonebook : MutableLiveData<Phonebook> = MutableLiveData()

    fun saveData(){
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.savePhoneBook(
                Phonebook(
                    phone = phone.value!!,
                    address = address.value!!,
                    name = name.value!!
                )
            )
        }
    }

    fun retrieveDate(){
        viewModelScope.launch(Dispatchers.IO) {
            implRepository.getPhoneBook().collect {
                phonebook.postValue(it)
            }
        }
    }
}