package org.tensorflow.lite.examples.poseestimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class SignUpActivity : AppCompatActivity() {

    lateinit var sign_up_id: EditText
    lateinit var sign_up_gender: EditText
    lateinit var sign_up_age: EditText
    lateinit var sign_up_password: EditText
    lateinit var sign_up_button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_id = findViewById(R.id.signup_id)
        sign_up_gender = findViewById((R.id.signup_gender))
        sign_up_age = findViewById((R.id.signup_age))
        sign_up_password = findViewById(R.id.signup_password)
        sign_up_button = findViewById(R.id.signUpButton2)

        var gson = GsonBuilder().setLenient().create()

        // 2. 레트로핏 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-13-57-234-0.us-west-1.compute.amazonaws.com:3000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            // https://stackoverflow.com/questions/42386250/android-retrofit-2-0-json-document-was-not-fully-consumed
            // JSON Document was not fully consumed 오류 해결방법
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val service = retrofit.create(SignService2::class.java)


        sign_up_button.setOnClickListener{

            val idStr = sign_up_id.text.toString()
            val genderStr = sign_up_gender.text.toString()
            val ageStr = sign_up_age.text.toString()
            val pwStr = sign_up_password.text.toString()

            service.signup(idStr, genderStr, ageStr, pwStr).enqueue(object : Callback<LoginJson> {
                override fun onResponse(call: retrofit2.Call<LoginJson>, response: Response<LoginJson>) {
                    val result = response.body()
                    Log.e("회원가입1", "${result}")
                    // ok라면
                    if(result?.code == "200"){
                        finish()
                    }

                }

                override fun onFailure(call: retrofit2.Call<LoginJson>, t: Throwable) {
                    Log.e("회원가입2", "${t.localizedMessage}")
                }
            })

        }

    }
}

interface SignService2{
    @FormUrlEncoded
    @POST("/process/adduser")
    fun signup(@Field("id") id:String,
               @Field("gender") gender:String,
               @Field("age") age:String,
               @Field("password") password:String) : retrofit2.Call<LoginJson>

}



