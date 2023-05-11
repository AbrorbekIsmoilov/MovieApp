package com.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.Models.MySharedPreferences
import com.Models.User
import com.example.recycleview.R
import com.example.recycleview.databinding.ItemRvBinding


class UserAdapter(val list: ArrayList<User>, val context: Context, val itemClick: RvClick) :
    RecyclerView.Adapter<UserAdapter.Vh>() {
    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(user: User, position: Int) {
            val animation= AnimationUtils.loadAnimation(context, R.anim.anim)
            itemRvBinding.root.startAnimation(animation)
            itemRvBinding.movieName.text = user.name
            itemRvBinding.movieAbout.text = user.about
            itemRvBinding.movieData.text = user.date
            itemRvBinding.root.setOnClickListener {
                itemClick.itemClick(user, position)
            }
            itemRvBinding.movieEdit.setOnClickListener {
                itemClick.itemEdit(user, position)
            }
            itemRvBinding.movieDelete.setOnClickListener {
                list.remove(user)
                this@UserAdapter.notifyItemRemoved(position)
                MySharedPreferences.init(context)
                MySharedPreferences.obektString = list
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
    interface RvClick {
        fun itemClick(user: User, position: Int)
        fun itemEdit(user: User, position: Int)
    }
}