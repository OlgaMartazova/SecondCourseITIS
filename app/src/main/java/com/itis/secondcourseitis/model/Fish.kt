package com.itis.secondcourseitis.model

class Fish(name: String, livingArea: String) : Animal(name, livingArea), FishLife {
    override fun walking() = "$name swims"
    override fun breath(): String = "$name breaths by gills"
    override fun born(): String = "$name is born from caviar"
    override fun hasLegs(): Boolean = false
    //Log.e("Some message", name)
}
