package com.chch.mycompose

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "알림"
        val body = remoteMessage.notification?.body ?: "내용 없음"

        showNotification(title, body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "FCM token refreshed: $token")
         sendTokenToServer(token)
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "default_channel"
        val notificationId = 1

        // 알림 채널 생성 (Android 8.0 이상 필수)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        showNotificationSafe(this, notificationId, builder)
    }

    private fun showNotificationSafe(
        context: Context,
        notificationId: Int,
        builder: NotificationCompat.Builder
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
        } else {
            Log.w("FCM", "No POST_NOTIFICATIONS permission, cannot show notification.")
        }
    }

    private fun sendTokenToServer(token: String) {
        // 로그인 유저 ID (익명이라면 기기 UUID 등 사용 가능)
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"

        val db = FirebaseFirestore.getInstance()
        val data = hashMapOf("token" to token)

        // push_tokens/userId/token: "abc123..."
        db.collection("push_tokens")
            .document(userId)
            .set(data)
            .addOnSuccessListener {
                // 저장 성공 시
                Log.d("FCM", "Token successfully written to Firestore!")
            }
            .addOnFailureListener { e ->
                // 저장 실패 시
                Log.w("FCM", "Error writing token", e)
            }
    }
}


