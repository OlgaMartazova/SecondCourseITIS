package com.itis.secondcourseitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itis.secondcourseitis.model.Animal
import com.itis.secondcourseitis.model.Fish
import com.itis.secondcourseitis.model.Rabbit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val someAnimal = Animal("Rex", "land")
        val fish = Fish("Dorri", "sea")
        val rabbit1 = Rabbit("Peter","forest")
        val rabbit2 = Rabbit("James","snow", "white")

        Log.i("Message", someAnimal.whereLive())
        Log.i("Message", someAnimal.walking())

        Log.i("Message", fish.whereLive())
        Log.i("Message", fish.walking())
        Log.i("Message", fish.born())
        Log.i("Message", fish.breath())
        Log.i("Message", fish.hasLegs().toString())

        Log.i("Message", rabbit1.whereLive())
        Log.i("Message", rabbit2.whereLive())
        Log.i("Message", rabbit1.born())
        Log.i("Message", rabbit1.breath())
        Log.i("Message", rabbit1.hasLegs().toString())
    }
}
