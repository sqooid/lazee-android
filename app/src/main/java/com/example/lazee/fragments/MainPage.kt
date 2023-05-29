package com.example.lazee.fragments

import android.content.Context
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.lazee.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainPage(navController: NavHostController, database: AppDatabase) {

    val scope= rememberCoroutineScope()

    Button(onClick = {
        scope.launch(Dispatchers.IO) {
            val events=database.sleepEventDao().getAll()
            Log.d("test","Sleep events: $events")
        }
    }) { Text(text = "Hello")}
}