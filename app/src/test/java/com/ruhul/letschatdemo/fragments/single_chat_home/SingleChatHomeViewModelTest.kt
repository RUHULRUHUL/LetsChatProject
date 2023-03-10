package com.ruhul.letschatdemo.fragments.single_chat_home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ruhul.letschatdemo.db.DbRepositoryTest
import com.ruhul.letschatdemo.db.data.ChatUser
import com.ruhul.letschatdemo.getOrAwaitValues
import com.ruhul.letschatdemo.models.UserProfile
import com.ruhul.letschatdemo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SingleChatHomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SingleChatHomeViewModel

    @Before
    fun setUp(){
       viewModel= SingleChatHomeViewModel(DbRepositoryTest())
    }

    @Test
    fun insert_ChatUser() = runBlockingTest{
        val chatUser=ChatUser("ruhulId","ruhul", UserProfile("testId",13232113L,123321321L),)
        viewModel.insertChatUser(chatUser)
        val chatUsers=viewModel.getAllChatUser().getOrAwaitValues()
        assertThat(chatUsers).contains(chatUser)
    }

    @Test
    fun insert_MultipleUser()=runBlockingTest {
        val chatUser1=ChatUser("testId1","Gowtham", UserProfile("testId1",13232113L,123321321L),)
        val chatUser2=ChatUser("testId2","Gowtham", UserProfile("testId2",13232113L,123321321L),)
        viewModel.insertMultipleChatUser(arrayListOf(chatUser1,chatUser2))
        val list=viewModel.getAllChatUser().getOrAwaitValues()
        assertThat(list).containsExactly(chatUser1,chatUser2)
    }

    @Test
    fun delete_User_ById() = runBlockingTest {
        val user=ChatUser("testDeleteUserId","Gowtham", UserProfile("testDeleteUserId",13232113L,123321321L),)
        viewModel.insertChatUser(user)
        viewModel.deleteUser("testDeleteUserId")
        val chatUsers=viewModel.getAllChatUser().getOrAwaitValues()
        assertThat(chatUsers).doesNotContain(user)
    }



}