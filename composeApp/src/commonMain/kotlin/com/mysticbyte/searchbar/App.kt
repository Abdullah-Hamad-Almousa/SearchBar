package com.mysticbyte.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mysticbyte.searchbar.theme.AppColor
import com.mysticbyte.searchbar.theme.TextApp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val persons by viewModel.persons.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize().background(AppColor.Blue))
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search") },
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = AppColor.Yellow,
                    focusedTextColor = AppColor.Yellow,
                    unfocusedContainerColor = AppColor.Black,
                    focusedContainerColor = AppColor.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isSearching){

                Box(modifier = Modifier.fillMaxSize()){

                    CircularProgressIndicator(
                        modifier = Modifier.align ( Alignment.Center )
                    )

                }

            }

            else {

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    items(persons) { persons ->
                        Text(
                            text = "${persons.firstName} ${persons.lastName}",
                            modifier = Modifier.fillMaxWidth()
                                .padding(16.dp, 16.dp, 0.dp, 16.dp),
                            color = AppColor.White
                        )
                    }
                }

            }

        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = TextApp.textApp,
                fontSize = 12.sp,
                color = AppColor.Yellow,
                modifier = Modifier.padding(18.dp),
                textDecoration = TextDecoration.Underline
            )
        }

    }

}