package com.example.lab05

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

val summons = arrayOf("Lich", "Ghoul", "Skeleton")

@Entity(tableName = "item_table")
class DatabaseItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var text_name : String = "Nameless"
    var text_spec : String = "None"
    var item_strength : Float = Random.nextInt(0, 6).toFloat()
    var item_type : String = summons[Random.nextInt(0, 3)]
    var dangerous : Boolean = Random.nextBoolean()
    var isChecked: Boolean = false

    constructor()

    constructor(num: Int) : this() {
        text_name = "Voidling "+num
        if (item_type=="Lich"){
            dangerous=true
        }
    }

    constructor(name: String, spec:String, strength:Float, type:String, danger:Boolean) : this() {
        text_name = name
        text_spec = spec
        item_strength = strength
        item_type = type
        dangerous = danger

    }
}