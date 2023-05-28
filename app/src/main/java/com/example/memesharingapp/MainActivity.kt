package com.example.memesharingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val url = "https://meme-api.com/gimme"
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById(R.id.my_image_view)

        loadImageFromApi()



    }

    private fun loadImageFromApi() {
        //get data using api


        //volley

        //create request queue
        val queue = Volley.newRequestQueue(this)
        //create request queue

        val request = JsonObjectRequest(Request.Method.GET, this.url, null,
            { response ->
                Log.d("Result",response.toString())
//                progressBar1.visibility = View.VISIBLE
                Picasso.get().load(response.get("url").toString()).placeholder(R.drawable.loading).into(imageView)
            },
            {
               Log.e("error",it.toString())
                Toast.makeText(applicationContext,"Error in loading the meme from server",Toast.LENGTH_LONG).show()
            }
        )
        //add request to queue5y9

        queue.add(request)

        //picasso
//        2.71828
    }

    fun shareMeme(view: android.view.View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type ="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey checkout this cool meme $imageView")
        val chooser = Intent.createChooser(intent,"Share this meme using...")
        startActivity(chooser)
    }

    fun changeImage(view: android.view.View) {

        this.loadImageFromApi()
    }
}