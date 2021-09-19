package com.itis.secondcourseitis.model

open class Animal (var name: String, val livingArea: String){
    open fun walking() : String = "$name goes"
    open fun whereLive() : String = "$name lives in $livingArea"
}
