package com.ruhul.letschatdemo.fragments.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruhul.letschatdemo.databinding.RowContactBinding
import com.ruhul.letschatdemo.db.data.ChatUser
import com.ruhul.letschatdemo.utils.ItemClickListener
import java.util.*
import kotlin.collections.ArrayList

class AdContact(context: Context, allUsers: ArrayList<ChatUser>) :
           RecyclerView.Adapter<AdContact.UserViewModel>() {

    private var users: ArrayList<ChatUser> = allUsers

    private var allUsers: ArrayList<ChatUser> = ArrayList()

    init {
        this.allUsers.addAll(users)
    }

    companion object {
        var itemClickListener: ItemClickListener? = null
    }

    fun filter(query: String) {
        try {
            users.clear()
            if (query.isEmpty())
                users.addAll(allUsers)
            else {
                for (country in allUsers) {
                    if (country.localName.toLowerCase(Locale.getDefault())
                            .contains(query.toLowerCase(Locale.getDefault())))
                       users.add(country)
                }
            }
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowContactBinding.inflate(layoutInflater, parent, false)
        return UserViewModel(binding)
    }


    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.bind(users[position])
    }

    class UserViewModel(val binding: RowContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatUser) {
            binding.chatUser = item
            binding.viewRoot.setOnClickListener { v ->
                itemClickListener?.onItemClicked(v, bindingAdapterPosition)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = users.size

}
