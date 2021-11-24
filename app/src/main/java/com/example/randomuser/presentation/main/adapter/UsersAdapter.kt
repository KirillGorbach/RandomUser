package com.example.randomuser.presentation.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.randomuser.R
import com.example.randomuser.databinding.ItemExploreBinding
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.presentation.util.extentions.inflate

class UsersAdapter(
    private val saveUser: (UserEntity) -> Unit,
    private val removeUser: (UserEntity) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    private val diffUtil = AsyncListDiffer(this, UsersCallback())

    override fun getItemCount() = diffUtil.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(parent.inflate(R.layout.item_explore))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.onBind(diffUtil.currentList[position])
    }

    fun setData(newData: List<UserEntity>) {
        diffUtil.submitList(newData)
    }

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewModel by viewBinding(ItemExploreBinding::bind)

        fun onBind(userEntity: UserEntity) {
            with(viewModel) {
                Glide.with(root.context).load(userEntity.pictureUrl).into(userPicture)
                userName.text = userEntity.firstname.plus(" ").plus(userEntity.lastname)
                userEmail.text = userEntity.email
                with(userAddRemoveChbox){
                    isChecked = userEntity.saved
                    setOnCheckedChangeListener { _, checked ->
                        if(checked) {
                            userEntity.saved = true
                            saveUser(userEntity)
                        } else {
                            userEntity.saved = false
                            removeUser(userEntity)
                        }
                    }
                }
            }
        }
    }

    inner class UsersCallback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}