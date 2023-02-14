package com.ruhul.letschatdemo.core

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.ruhul.letschatdemo.FirebasePush
import com.ruhul.letschatdemo.db.DbRepository
import com.ruhul.letschatdemo.db.data.ChatUser
import com.ruhul.letschatdemo.models.UserProfile
import com.ruhul.letschatdemo.utils.OnSuccessListener
import com.ruhul.letschatdemo.utils.UserUtils
import com.ruhul.letschatdemo.utils.Utils

class ChatUserUtil(private val dbRepository: DbRepository,
                   private val usersCollection: CollectionReference,
                   private val listener: OnSuccessListener?) {

    fun queryNewUserProfile(context: Context,chatUserId: String,docId: String?, unReadCount: Int=1,
                            showNotification: Boolean=false) {
        try {
            usersCollection.document(chatUserId)
                .get().addOnSuccessListener { profile ->
                    if (profile.exists()) {
                        val userProfile = profile.toObject(UserProfile::class.java)
                        val mobile = userProfile?.mobile?.country + " " + userProfile?.mobile?.number
                        val chatUser = ChatUser(userProfile?.uId!!, mobile, userProfile)
                        chatUser.unRead=unReadCount
                        if(docId!=null)
                            chatUser.documentId=docId
                        if (Utils.isContactPermissionOk(context)) {
                            val contacts = UserUtils.fetchContacts(context)
                            val savedContact=contacts.firstOrNull { it.mobile.contains(userProfile.mobile!!.number) }
                            savedContact?.let {
                                chatUser.localName=it.name
                                chatUser.locallySaved=true
                            }
                        }
                        listener?.onResult(true,chatUser)
                        dbRepository.insertUser(chatUser)
                        if(showNotification)
                        FirebasePush.showNotification(context,dbRepository)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}