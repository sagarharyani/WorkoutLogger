package com.example.workoutlogger

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import android.view.Menu
import android.view.MenuItem



class ViewWorkoutActivity : AppCompatActivity() {

    private lateinit var workoutDetailsTextView: TextView

    companion object {
        const val CHANNEL_ID = "workout_notification_channel"
        val loggedWorkoutsList: ArrayList<String> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_workout)

        workoutDetailsTextView = findViewById(R.id.workoutDetailsTextView)

        val workoutName = intent.getStringExtra("workoutName")
        val workoutDuration = intent.getStringExtra("workoutDuration")

        if (workoutName != null && workoutDuration != null) {
            val workoutDetails = "Workout: $workoutName\nDuration: $workoutDuration"
            workoutDetailsTextView.text = workoutDetails

            // Add to the static list
            loggedWorkoutsList.add(workoutDetails)

            sendNotification(workoutName, workoutDuration)
        }
    }

    private fun sendNotification(workoutName: String, workoutDuration: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Workout Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for workout notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_workout)
            .setContentTitle("Workout Logged")
            .setContentText("Workout: $workoutName, Duration: $workoutDuration")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewLoggedWorkouts -> {
                val intent = Intent(this, LoggedWorkoutsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
