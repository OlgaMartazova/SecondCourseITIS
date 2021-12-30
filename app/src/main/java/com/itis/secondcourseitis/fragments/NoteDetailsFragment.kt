package com.itis.secondcourseitis.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.itis.secondcourseitis.ExtConstants.NOTE_ID
import com.itis.secondcourseitis.R
import com.itis.secondcourseitis.database.NoteDao
import com.itis.secondcourseitis.database.NoteDatabase
import com.itis.secondcourseitis.databinding.FragmentNoteDetailsBinding
import com.itis.secondcourseitis.model.Note
import com.itis.secondcourseitis.recycler.DateConverter.convertDate
import java.util.*
import com.google.android.material.snackbar.Snackbar

class NoteDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailsBinding
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var noteId: Int? = null
    private var note: Note? = null

    private var calendarDate: Date = Date()

    private var REQUEST_LOCATION: Int = 123
    private var permittedLatitude: Double = 0.0
    private var permittedLongitude: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireContext())

        getLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDatabase = NoteDatabase.getInstance(this.requireContext())
        noteDao = noteDatabase.noteDao()

        if (arguments?.containsKey(NOTE_ID) == true) {
            arguments?.getInt(NOTE_ID)?.let {
                note = noteDao.findNoteById(it)
                noteId = it
                calendarDate = noteDao.findNoteById(it).date
            }

            note?.let {
                with(binding) {
                    etTitle.setText(it.title)
                    etDesc.setText(it.desc)
                    tvDateCreated.text = "Date created: ${convertDate(it.dateCreated)}"
                    tvLocation.text = "Location: ${it.longitude}, ${it.latitude}"
                }
            }
            binding.tvDate.text = convertDate(note?.date ?: Date())
        }

        binding.btnSetDate.setOnClickListener {
            showTimePicker()
        }

        binding.btnSave.setOnClickListener {
            getAcceptedNote().let {
                noteDao.saveNote(it)
            }
            findNavController().navigate(
                R.id.action_noteDetailsFragment_to_noteListFragment
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getLocation() {
        activity?.apply {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient
                    .lastLocation
                    .addOnCompleteListener {
                        permittedLatitude = it.result.latitude
                        permittedLongitude = it.result.longitude
                    }
                Log.e("PERMISSION", permittedLatitude.toString())
                binding.tvLocation.text =
                    "Location: ${permittedLatitude}, ${permittedLongitude}"
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    REQUEST_LOCATION
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                // permission was granted
                getLocation()
            } else {
                // permission denied
                Snackbar.make(
                    binding.root,
                    "permission denied",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this.requireContext(),
            { _, chosenYear, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.YEAR, chosenYear)
                calendarDate = calendar.time
                binding.tvDate.text = convertDate(calendarDate)
            }, day, month, year
        )
        dpd.show()
    }

    private fun getAcceptedNote(): Note {
        with(binding) {
            val id = noteId ?: 0
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val dateCreated = Date()
            val date = calendarDate
            val longitude = permittedLongitude
            val latitude = permittedLatitude
            return Note(id, title, desc, date, dateCreated, longitude, latitude)
        }
    }
}
