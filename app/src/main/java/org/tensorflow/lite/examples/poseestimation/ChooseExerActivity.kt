package org.tensorflow.lite.examples.poseestimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseExerActivity : AppCompatActivity() {
    lateinit var exer_one_button : Button
    lateinit var exer_two_button : Button
    lateinit var exer_three_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_exer)

        val intent = intent
        val user_id = intent.getStringExtra("id")
        // 버튼에 대한 구현 해야함

        exer_one_button = findViewById(R.id.exer_one)
        exer_two_button = findViewById(R.id.exer_two)
        exer_three_button = findViewById(R.id.exer_three)

        exer_one_button.setOnClickListener {
            val nextIntent = Intent(this@ChooseExerActivity,InsertSetRep::class.java)
            nextIntent.putExtra("id", user_id)
            nextIntent.putExtra("exer_name", "exer_one")
            startActivity(nextIntent)
        }

        exer_two_button.setOnClickListener {
            val nextIntent = Intent(this@ChooseExerActivity,InsertSetRep::class.java)
            nextIntent.putExtra("id", user_id)
            nextIntent.putExtra("exer_name", "exer_two")
            startActivity(nextIntent)
        }

        exer_three_button.setOnClickListener {
            val nextIntent = Intent(this@ChooseExerActivity,InsertSetRep::class.java)
            nextIntent.putExtra("id", user_id)
            nextIntent.putExtra("exer_name", "exer_three")
            startActivity(nextIntent)
        }
    }
}