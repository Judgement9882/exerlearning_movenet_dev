package org.tensorflow.lite.examples.poseestimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var user_info: TextView
    lateinit var exer_info_button : Button
    lateinit var start_exercise_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 전달한 id값 저장
        val intent = intent
        val user_id = intent.getStringExtra("id")

        user_info = findViewById(R.id.choose_text)
        exer_info_button = findViewById(R.id.exer_one)
        start_exercise_button = findViewById(R.id.exer_two)

        user_info.text = "  "+ user_id + " 님 \n 안녕하세요."

        exer_info_button.setOnClickListener {
            val nextIntent = Intent(this@HomeActivity,ExerInfo::class.java)
            nextIntent.putExtra("id", user_id)
            startActivity(nextIntent)
        }

        start_exercise_button.setOnClickListener {
            val nextIntent = Intent(this@HomeActivity,ChooseExerActivity::class.java)
            nextIntent.putExtra("id", user_id)
            startActivity(nextIntent)
        }
    }
}