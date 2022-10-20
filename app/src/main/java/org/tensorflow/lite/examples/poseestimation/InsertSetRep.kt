package org.tensorflow.lite.examples.poseestimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class InsertSetRep : AppCompatActivity() {
    lateinit var exer_set: EditText
    lateinit var exer_rep: EditText
    lateinit var exer_start_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_set_rep)

        val intent = intent
        val user_id = intent.getStringExtra("id")
        val exer_name = intent.getStringExtra("exer_name")

        exer_set = findViewById(R.id.exer_set_num)
        exer_rep = findViewById(R.id.exer_rep_num)
        exer_start_button = findViewById(R.id.exer_start)

        // 운동 시작 버튼
        exer_start_button.setOnClickListener {
            val nextIntent = Intent(this@InsertSetRep,MainActivity::class.java)
            nextIntent.putExtra("set_exer", exer_set.toString())
            nextIntent.putExtra("rep_exer", exer_rep.toString())
            nextIntent.putExtra("exer_name", exer_name)
            startActivity(nextIntent)

            Log.e("e", "1")
        }
    }
}