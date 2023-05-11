package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Models.MySharedPreferences
import com.Models.MovieList
import com.Models.User
import com.example.recycleview.databinding.ActivityMain2Binding

class  MainActivity2 : AppCompatActivity() {
    private val binding by lazy {ActivityMain2Binding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)



    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            MySharedPreferences.init(this@MainActivity2)
            MovieList.list = MySharedPreferences.obektString
            val name = movieName.text
            val author = movieAuthors.text
            val about  = movieAbout.text
            val data  = movieData.text
            movieSave.setOnClickListener {
                MovieList.list.add(User("$name","$author","$about","$data"))
                MySharedPreferences.obektString = MovieList.list
                finish()
            }
        }
    }
}