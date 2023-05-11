package com.example.recycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.Adapter.UserAdapter
import com.Models.MySharedPreferences
import com.Models.MovieList
import com.Models.User
import com.example.recycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),UserAdapter.RvClick {
private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        MySharedPreferences.init(this)
        MovieList.list = MySharedPreferences.obektString
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            userAdapter= UserAdapter(MovieList.list, this@MainActivity, object : UserAdapter.RvClick{
                override fun itemClick(user: User, position: Int) {
                    startActivity(Intent(this@MainActivity, InfoMovie::class.java).putExtra("position", position))
                }
                override fun itemEdit(user: User, position: Int) {
                    startActivity(Intent(this@MainActivity, Edit::class.java).putExtra("position", position))
                }


            })
            rv.adapter=userAdapter
        }
        userAdapter.notifyItemInserted(MovieList.list.size-1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_uz,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.icon_add->{
                startActivity(Intent(this,MainActivity2::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun itemClick(user: User, position: Int) {
        startActivity(Intent(this,InfoMovie::class.java))
    }

    override fun itemEdit(user: User, position: Int) {
        startActivity(Intent(this,Edit::class.java))
    }
} 