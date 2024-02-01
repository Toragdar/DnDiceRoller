package com.example.dndiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.example.dndiceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dicesImageViews: Array<ImageView>
    private var dices: MutableList<Dice> = mutableListOf()
    private var isD100onBoard = false
    private val maxDicesAmount = 9
    private var totalResult = 0

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preSet()
    }

    private fun preSet() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dicesImageViews = setImageViewsForDicesOnBoard()

        setEmptyImagesForBoardImageViews()

        binding.d4Image.setOnClickListener {
            addOnBoardD4()
        }

        binding.d6Image.setOnClickListener {
            addOnBoardD6()
        }

        binding.d8Image.setOnClickListener {
            addOnBoardD8()
        }

        binding.d10Image.setOnClickListener {
            addOnBoardD10()
        }

        binding.d12Image.setOnClickListener {
            addOnBoardD12()
        }

        binding.d20Image.setOnClickListener {
            addOnBoardD20()
        }

        binding.d100Image.setOnClickListener {
            addOnBoardD100()
        }

        binding.rollButton.setOnClickListener {
            rollAllDices()
        }
        binding.clearButton.setOnClickListener {
            clearBoard()
        }
    }

    private fun resetBoard() {

        if(isD100onBoard){
            dicesImageViews[0].visibility = View.GONE
            dicesImageViews[5].setImageResource(dices[0].imagesIDs[dices[0].currentImageIndex])
            dicesImageViews[6].setImageResource(dices[1].imagesIDs[dices[1].currentImageIndex])
        }
        else{
            if(dices.size==2||dices.size==4){

                setEmptyImagesForBoardImageViews()

                for(i in 0 until dices.size){
                    dicesImageViews[i+1].setImageResource(dices[i].imagesIDs[dices[i].currentImageIndex])
                }
            }
            else{
                for(i in 0 until dices.size){
                    dicesImageViews[i].setImageResource(dices[i].imagesIDs[dices[i].currentImageIndex])
                }
            }
        }

        totalResult = 0
        binding.resultValueText.text = totalResult.toString()
    }

    private fun clearBoard() {
        setEmptyImagesForBoardImageViews()
        dices.clear()
        binding.resultValueText.text = "0"

        if(isD100onBoard){
            isD100onBoard = false
            dicesImageViews[0].visibility = View.VISIBLE
        }
    }

    private fun addOnBoardD4() {

        backgroundRingAnimation(binding.d4Background)
        scalingImageAnimation(binding.d4Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d4 = Dice(Dice.DiceType.D4, 4, 3, getImagesIdsD4())
            dices.add(d4)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD4()
        }
    }

    private fun addOnBoardD6() {

        backgroundRingAnimation(binding.d6Background)
        scalingImageAnimation(binding.d6Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d6 = Dice(Dice.DiceType.D6, 6, 5, getImagesIdsD6())
            dices.add(d6)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD6()
        }
    }

    private fun addOnBoardD8() {

        backgroundRingAnimation(binding.d8Background)
        scalingImageAnimation(binding.d8Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d8 = Dice(Dice.DiceType.D8, 8, 7, getImagesIdsD8())
            dices.add(d8)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD8()
        }
    }

    private fun addOnBoardD10() {

        backgroundRingAnimation(binding.d10Background)
        scalingImageAnimation(binding.d10Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d10 = Dice(Dice.DiceType.D10, 10, 9, getImagesIdsD10())
            dices.add(d10)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD10()
        }
    }

    private fun addOnBoardD12() {

        backgroundRingAnimation(binding.d12Background)
        scalingImageAnimation(binding.d12Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d12 = Dice(Dice.DiceType.D12, 12, 11, getImagesIdsD12())
            dices.add(d12)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD12()
        }
    }

    private fun addOnBoardD20() {

        backgroundRingAnimation(binding.d20Background)
        scalingImageAnimation(binding.d20Image)

        if(dices.size < maxDicesAmount && !isD100onBoard){
            val d20 = Dice(Dice.DiceType.D20, 20, 19, getImagesIdsD20())
            dices.add(d20)

            resetBoard()
        }
        else if(isD100onBoard){
            clearBoard()
            addOnBoardD20()
        }
    }

    private fun addOnBoardD100() {

        backgroundRingAnimation(binding.d100Background)
        scalingImageAnimation(binding.d100Image)

        if(dices.isNotEmpty()){
            clearBoard()
        }

        if(!isD100onBoard){
            isD100onBoard = true

            val firstD10 = Dice(Dice.DiceType.D10, 9, 8, getImagesIdsD10())
            val secondD10 = Dice(Dice.DiceType.D10, 10, 9, getImagesIdsD10())
            dices.add(firstD10)
            dices.add(secondD10)

            resetBoard()
        }
    }

    private fun rollAllDices() {
        for(dice in dices){
            dice.rollDice()
        }
        resetBoard()
        setNewRollResult()
    }

    private fun setNewRollResult() {

        if(isD100onBoard){
            setNewRollResultD100()
        }
        else{
            totalResult = 0

            for(dice in dices){
                totalResult += dice.value
            }
            binding.resultValueText.text = totalResult.toString()
        }

    }

    private fun setNewRollResultD100() {
        var result = ""

        if(dices[0].value == 10 && dices[1].value == 10){
            binding.resultValueText.text = "100"
        }
        else{
            if(dices[0].value == 10){
                result += ""
            }
            else{
                result += dices[0].value.toString()
            }

            if(dices[1].value == 10){
                result += "0"
            }
            else{
                result += dices[1].value.toString()
            }
        }

        binding.resultValueText.text = result
    }

    private fun setImageViewsForDicesOnBoard(): Array<ImageView> {
        val dicesImageViews = Array<ImageView>(9) { index -> ImageView(this) }

        dicesImageViews[0] = binding.result1Image
        dicesImageViews[1] = binding.result2Image
        dicesImageViews[2] = binding.result3Image
        dicesImageViews[3] = binding.result4Image
        dicesImageViews[4] = binding.result5Image
        dicesImageViews[5] = binding.result6Image
        dicesImageViews[6] = binding.result7Image
        dicesImageViews[7] = binding.result8Image
        dicesImageViews[8] = binding.result9Image

        return dicesImageViews
    }

    private fun setEmptyImagesForBoardImageViews(){
        for(imageView in dicesImageViews){
            imageView.setImageResource(R.drawable.empty_dice)
        }
    }

    private fun getImagesIdsD4(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d4_1,
            R.drawable.d4_2,
            R.drawable.d4_3,
            R.drawable.d4_4
        )

        return imageResourceIds
    }

    private fun getImagesIdsD6(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d6_1,
            R.drawable.d6_2,
            R.drawable.d6_3,
            R.drawable.d6_4,
            R.drawable.d6_5,
            R.drawable.d6_6
        )

        return imageResourceIds
    }

    private fun getImagesIdsD8(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d8_1,
            R.drawable.d8_2,
            R.drawable.d8_3,
            R.drawable.d8_4,
            R.drawable.d8_5,
            R.drawable.d8_6,
            R.drawable.d8_7,
            R.drawable.d8_8,
        )

        return imageResourceIds
    }

    private fun getImagesIdsD10(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d10_1,
            R.drawable.d10_2,
            R.drawable.d10_3,
            R.drawable.d10_4,
            R.drawable.d10_5,
            R.drawable.d10_6,
            R.drawable.d10_7,
            R.drawable.d10_8,
            R.drawable.d10_9,
            R.drawable.d10_0
        )

        return imageResourceIds
    }

    private fun getImagesIdsD12(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d12_1,
            R.drawable.d12_2,
            R.drawable.d12_3,
            R.drawable.d12_4,
            R.drawable.d12_5,
            R.drawable.d12_6,
            R.drawable.d12_7,
            R.drawable.d12_8,
            R.drawable.d12_9,
            R.drawable.d12_10,
            R.drawable.d12_11,
            R.drawable.d12_12
        )

        return imageResourceIds
    }

    private fun getImagesIdsD20(): Array<Int> {

        val imageResourceIds = arrayOf(
            R.drawable.d20_1,
            R.drawable.d20_2,
            R.drawable.d20_3,
            R.drawable.d20_4,
            R.drawable.d20_5,
            R.drawable.d20_6,
            R.drawable.d20_7,
            R.drawable.d20_8,
            R.drawable.d20_9,
            R.drawable.d20_10,
            R.drawable.d20_11,
            R.drawable.d20_12,
            R.drawable.d20_13,
            R.drawable.d20_14,
            R.drawable.d20_15,
            R.drawable.d20_16,
            R.drawable.d20_17,
            R.drawable.d20_18,
            R.drawable.d20_19,
            R.drawable.d20_20
        )

        return imageResourceIds
    }

    private fun backgroundRingAnimation(imageView: ImageView){

        val width = imageView.width
        val height = imageView.height

        val pivotX = width / 2f
        val pivotY = height / 2f

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 100

        val scaleUp = ScaleAnimation(0.8f, 1.1f, 0.8f, 1.1f, pivotX, pivotY)
        scaleUp.duration = 250

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.startOffset = 100
        fadeOut.duration = 300

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(fadeIn)
        animationSet.addAnimation(scaleUp)
        animationSet.addAnimation(fadeOut)

        imageView.startAnimation(animationSet)
    }

    private fun scalingImageAnimation(imageView: ImageView){

        val width = imageView.width
        val height = imageView.height

        val pivotX = width / 2f
        val pivotY = height / 2f

        val scaleDown = ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f, pivotX, pivotY)
        scaleDown.duration = 50

        val scaleUp = ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, pivotX, pivotY)
        scaleUp.startOffset = 50
        scaleUp.duration = 50

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleDown)
        animationSet.addAnimation(scaleUp)

        imageView.startAnimation(animationSet)
    }


}