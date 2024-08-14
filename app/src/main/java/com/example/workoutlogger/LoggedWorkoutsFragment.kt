package com.example.workoutlogger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class LoggedWorkoutsFragment : Fragment() {

    private lateinit var loggedWorkoutsListView: ListView
    private lateinit var loggedWorkoutsList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logged_workouts, container, false)

        loggedWorkoutsListView = view.findViewById(R.id.loggedWorkoutsListView)

        // Dummy data
        loggedWorkoutsList = ArrayList()
        loggedWorkoutsList.add("Workout: Push-ups, Duration: 30 mins")
        loggedWorkoutsList.add("Workout: Running, Duration: 45 mins")

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, loggedWorkoutsList)
        loggedWorkoutsListView.adapter = adapter

        return view
    }
}
