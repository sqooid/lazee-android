package com.example.lazee

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.lazee.database.AppDatabase
import com.example.lazee.receivers.BootReceiver
import com.example.lazee.receivers.ShutdownReceiver
import com.example.lazee.ui.theme.LazeeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"app-database").build()

        val br = ShutdownReceiver()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
//        ContextCompat.registerReceiver(this, br, filter, ContextCompat.RECEIVER_EXPORTED)

        setContent {
            val navController = rememberNavController()
            LazeeTheme {
                Scaffold(
                    bottomBar = {
                        NavBar(navController = navController)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        database = db
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazeeTheme {
        Greeting("Android")
    }
}