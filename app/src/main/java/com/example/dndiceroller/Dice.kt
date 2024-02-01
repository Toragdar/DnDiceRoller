package com.example.dndiceroller

import kotlin.random.Random

class Dice(
    private val type: DiceType,
    var value: Int,
    var currentImageIndex: Int,
    val imagesIDs: Array<Int>) {

    enum class DiceType{
        D4,
        D6,
        D8,
        D10,
        D12,
        D20
    }

    fun rollDice() {
        this.value = when(this.type){
            DiceType.D4 -> Random.nextInt(4) + 1
            DiceType.D6 -> Random.nextInt(6) + 1
            DiceType.D8 -> Random.nextInt(8) + 1
            DiceType.D10 -> Random.nextInt(10) + 1
            DiceType.D12 -> Random.nextInt(12) + 1
            DiceType.D20 -> Random.nextInt(20) + 1
            else -> 0
        }
        this.currentImageIndex = this.value - 1
    }

}

