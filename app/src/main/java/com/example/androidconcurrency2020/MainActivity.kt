package com.example.androidconcurrency2020

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidconcurrency2020.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageViews: Array<ImageView>
    private lateinit var drawables: Array<Int>
    val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            val data = msg.data

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding for view object references
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollButton.setOnClickListener { rollTheDice() }

    }

    /**
     * Run some code
     */
    private fun rollTheDice() {
        imageViews = arrayOf(binding.die1, binding.die2, binding.die3, binding.die4, binding.die5)
        drawables = arrayOf(
            R.drawable.die_1,
            R.drawable.die_2,
            R.drawable.die_3,
            R.drawable.die_4,
            R.drawable.die_5,
            R.drawable.die_6
        )
        val bundle = Bundle()
        for (dieIndex in imageViews.indices) {
            val dieNumber = getDieValue()
            /*imageViews[dieIndex].setImageResource(drawables[dieNumber - 1])*/
            bundle.putInt("", dieNumber)
            Message().also {
                it.data = bundle
                handler.sendMessage(it)
            }
        }

    }

    /**
     * Get a random number from 1 to 6
     */
    private fun getDieValue(): Int {
        return Random.nextInt(1, 7)
    }

}
