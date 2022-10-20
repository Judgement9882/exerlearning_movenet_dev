package org.tensorflow.lite.examples.poseestimation

import android.content.Intent
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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class LoginActivity : AppCompatActivity() {

    lateinit var id: EditText
    lateinit var password: EditText
    lateinit var login_button : Button
    lateinit var sign_up_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        id = findViewById(R.id.textId)
        password = findViewById(R.id.textPassword)
        login_button = findViewById(R.id.loginButton)
        sign_up_button = findViewById(R.id.signUpButton)

        var gson = GsonBuilder().setLenient().create()

        // 2. 레트로핏 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-13-57-234-0.us-west-1.compute.amazonaws.com:3000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(SignService::class.java)


        // 로그인 리스너
        login_button.setOnClickListener{
            val idStr = id.text.toString()
            val pwStr = password.text.toString()
            service.login(idStr, pwStr).enqueue(object : Callback<LoginJson> {
                override fun onResponse(call: retrofit2.Call<LoginJson>, response: Response<LoginJson>) {
                    val result = response.body()
                    Log.e("로그인1", "${result}")
                    if(result?.code == "200"){
                        val nextIntent = Intent(this@LoginActivity,HomeActivity::class.java)
                        nextIntent.putExtra("id", idStr)
                        startActivity(nextIntent)
                    }
                }

                override fun onFailure(call: retrofit2.Call<LoginJson>, t: Throwable) {
                    Log.e("로그인2", "${t.localizedMessage}")
                }
            })
        }

        // 회원가입 리스너
        sign_up_button.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }
    }
}

interface SignService{
    @FormUrlEncoded
    @POST("/process/login")
    fun login(@Field("id") id:String, @Field("password") password:String) : retrofit2.Call<LoginJson>

}