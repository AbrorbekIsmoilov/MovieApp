package com.example.recycleview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Models.MySharedPreferences
import com.Models.MovieList
import com.example.recycleview.databinding.ActivityInfoMovieBinding

class InfoMovie : AppCompatActivity() {
    private val binding by lazy {ActivityInfoMovieBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.apply {
            MySharedPreferences.init(this@InfoMovie)
            MovieList.list =MySharedPreferences.obektString
            val position=intent.getIntExtra("position", 0)
            movieName.text ="${movieName.text} ${MovieList.list[position].name}"
            movieAuthors.text ="${movieAuthors.text} ${MovieList.list[position].authors}"
            movieAbout.text ="${movieAbout.text} ${MovieList.list[position].about}"
            movieData.text ="${movieData.text} ${MovieList.list[position].date}"
            close.setOnClickListener {
                finish()
            }
        }

    }
}