package com.xyron.datastoreexample.repository


import com.xyron.datastoreexample.Phonebook
import kotlinx.coroutines.flow.Flow

interface Abstract {

    suspend fun savePhoneBook(phonebook: Phonebook)

    suspend fun getPhoneBook():Flow<Phonebook>
}