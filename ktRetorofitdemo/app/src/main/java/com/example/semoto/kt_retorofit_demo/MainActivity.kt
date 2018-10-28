package com.example.semoto.kt_retorofit_demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val client = retrofit.create(IApiService::class.java)


        button.setOnClickListener {

            val call: Call<List<Repo>> = client.getUser(TARGET)
            call.enqueue(object : Callback<List<Repo>> {
                override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                    val result = response?.body()
//                    text.text = gson.toJson(result)
//                    Logger.debugEntire(gson.toJson(result))
                    print("Success!")
                }

                override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                    print("Failure!")
                }
            })
        }


    }

    companion object {
        val END_POINT = "https://api.github.com"
        val TARGET = "JakeWharton"
    }
}
