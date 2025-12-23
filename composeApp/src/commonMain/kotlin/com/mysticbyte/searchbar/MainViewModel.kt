package com.mysticbyte.searchbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(FlowPreview::class)
class MainViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _person = MutableStateFlow(allPerson)
    val persons = searchText
        .debounce (500L)
        .onEach { _isSearching.update { true } }
        .combine(_person) { text, persons ->

            if (text.isBlank()){
                persons
            } else {
                delay(1000L)
                persons.filter {
                    it.doseMatchSearchQuery(text)
                }
            }

        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _person.value
        )


    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}

data class Person(
    val firstName: String,
    val lastName: String
){
    fun doseMatchSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
            "${firstName.first()}${lastName.first()}"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allPerson = listOf(

    Person(
        "Abdullah", "AL-Mousa"
    ),

    Person(
        "David", "Goggins"
    ),

    Person(
        "Adam", "Grant"
    ),

    Person(
        "Dr.Matthew", "Walker"
    ),

    Person(
        "Robert", "Kiyosaki"
    )

)