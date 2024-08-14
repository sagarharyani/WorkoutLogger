package com.example.workoutlogger

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoggedWorkoutsActivity : AppCompatActivity() {

    private lateinit var loggedWorkoutsListView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_workouts)

        loggedWorkoutsListView = findViewById(R.id.loggedWorkoutsListView)

        // Retrieve the extra data sent from MainActivity
        val extraMessage = intent.getStringExtra("extraMessage")
        Toast.makeText(this, extraMessage, Toast.LENGTH_SHORT).show()

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ViewWorkoutActivity.loggedWorkoutsList
        )
        loggedWorkoutsListView.adapter = adapter
    }
}
