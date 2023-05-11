package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Models.MySharedPreferences
import com.Models.MovieList
import com.example.recycleview.databinding.ActivityEditBinding

class Edit : AppCompatActivity() {
    private val binding by lazy {ActivityEditBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
    override fun onStart() {
        super.onStart()

        binding.apply {
            var position = intent.getIntExtra("position", 0)
            MySharedPreferences.init(this@Edit)
            MovieList.list = MySharedPreferences.obektString
            movieName.setText(MovieList.list[position].name)
            movieAuthors.setText(MovieList.list[position].authors)
            movieAbout.setText(MovieList.list[position].about)
            movieData.setText(MovieList.list[position].date)

            movieSave.setOnClickListener {
                MovieList.list[position].name = movieName.text.toString()
                MovieList.list[position].authors = movieAuthors.text.toString()
                MovieList.list[position].about = movieAbout.text.toString()
                MovieList.list[position].date = movieData.text.toString()
                MySharedPreferences.obektString = MovieList.list
                finish()
            }
        }
    }
}