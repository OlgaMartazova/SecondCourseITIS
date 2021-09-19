package com.itis.secondcourseitis.model

class Rabbit(name: String, livingArea: String) : Animal(name, livingArea), RabbitLife {

    var color : String = ""

    constructor(
        name: String,
        livingArea: String,
        color : String
    ) : this(name, livingArea) {
        this.color = color
    }
    override fun walking() = "$name jumps"
    override fun breath(): String = "$name breathes with lungs"
    override fun born(): String = "$name is born by mother"
    override fun hasLegs(): Boolean = true
    override fun whatEats(): String = "$name eats carrot"
    override fun whereLive() :String = "$color $name lives in $livingArea"
}
