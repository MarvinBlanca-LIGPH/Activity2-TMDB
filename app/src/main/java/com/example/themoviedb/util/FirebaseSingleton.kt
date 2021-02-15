package com.example.themoviedb.util

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

object FirebaseSingleton {
    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                println("Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }
            val token = task.result.toString()
            println("Token: $token")
        })
    }
}