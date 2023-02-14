package com.ruhul.letschatdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ruhul.letschatdemo.db.daos.ChatUserDao
import com.ruhul.letschatdemo.db.daos.GroupDao
import com.ruhul.letschatdemo.db.daos.GroupMessageDao
import com.ruhul.letschatdemo.db.daos.MessageDao
import com.ruhul.letschatdemo.db.data.ChatUser
import com.ruhul.letschatdemo.db.data.Group
import com.ruhul.letschatdemo.db.data.GroupMessage
import com.ruhul.letschatdemo.db.data.Message

@Database(entities = [ChatUser::class, Message::class,Group::class,GroupMessage::class],
    version = 2, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class ChatUserDatabase : RoomDatabase()  {
    abstract fun getChatUserDao(): ChatUserDao
    abstract fun getMessageDao(): MessageDao
    abstract fun getGroupDao(): GroupDao
    abstract fun getGroupMessageDao(): GroupMessageDao
}