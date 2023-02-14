package com.ruhul.letschatdemo.fragments.single_chat_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruhul.letschatdemo.db.DefaultDbRepo
import com.ruhul.letschatdemo.db.data.ChatUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleChatHomeViewModel  @Inject
constructor(private val dbRepo: DefaultDbRepo): ViewModel() {

    val message= MutableLiveData<String>()

    fun getChatUsers() = dbRepo.getChatUserWithMessages()

    fun getChatUsersAsList() = dbRepo.getChatUserWithMessagesList()

    fun insertChatUser(chatUser: ChatUser) = dbRepo.insertUser(chatUser)

    fun insertMultipleChatUser(users : List<ChatUser>) = dbRepo.insertMultipleUser(users)

    fun getAllChatUser() = dbRepo.getAllChatUser()

    fun deleteUser(userId: String) = dbRepo.deleteUserById(userId)

}