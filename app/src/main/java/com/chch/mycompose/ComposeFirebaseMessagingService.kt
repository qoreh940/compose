package com.chch.mycompose

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.chch.mycompose.util.ComposeLogger.logd
import com.chch.mycompose.util.loge
import com.chch.mycompose.util.logw
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ComposeFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "알림"
        val body = remoteMessage.notification?.body ?: "내용 없음"

        showNotification(title, body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        logd("FCM token refreshed: $token")
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
            logw("No POST_NOTIFICATIONS permission, cannot show notification.")
        }
    }

    private fun sendTokenToServer(token: String) {
        // TODO: 로그인 기능 구현 필요
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"

        val db = FirebaseFirestore.getInstance()
        val data = hashMapOf("token" to token)

        // push_tokens/userId/token: "abc123..."
        db.collection("push_tokens")
            .document(userId)
            .set(data)
            .addOnSuccessListener {
                logd("Token successfully written to Firestore!")
            }
            .addOnFailureListener { e ->
                loge("Error writing token", e)
            }
    }
}


