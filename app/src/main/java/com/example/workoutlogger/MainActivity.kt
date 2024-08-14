

package com.example.workoutlogger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var workoutNameEditText: EditText
    private lateinit var workoutDurationEditText: EditText
    private lateinit var logWorkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        workoutNameEditText = findViewById(R.id.workoutNameEditText)
        workoutDurationEditText = findViewById(R.id.workoutDurationEditText)
        logWorkoutButton = findViewById(R.id.logWorkoutButton)

        logWorkoutButton.setOnClickListener {
            val workoutName = workoutNameEditText.text.toString()
            val workoutDuration = workoutDurationEditText.text.toString()

            if (workoutName.isNotEmpty() && workoutDuration.isNotEmpty()) {
                val intent = Intent(this, ViewWorkoutActivity::class.java)
                intent.putExtra("workoutName", workoutName)
                intent.putExtra("workoutDuration", workoutDuration)
                startActivity(intent)

                Toast.makeText(this, "Workout logged", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter both workout name and duration", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val item = menu.findItem(R.id.viewLoggedWorkouts)
        item?.actionView?.setBackgroundResource(R.color.white) ?: Log.e("MainActivity", "Action view is null")

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewLoggedWorkouts -> {
                // Passing additional data when starting LoggedWorkoutsActivity
                val intent = Intent(this, LoggedWorkoutsActivity::class.java).apply {
                    putExtra("extraMessage", "Viewing Logged Workouts")
                }
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


