package com.example.notionclone.screen.auth_screen

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.example.notionclone.service.ClerkService
import com.example.notionclone.util.createHttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.launch

val client = createHttpClient(
    engine = Android.create()
)
val service = ClerkService(client)
@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.Center),
            onClick = {
                scope.launch {
                    val url = service.signIn()
                    if (url != null) {
                        println("URL đăng nhập: $url")

                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                        context.startActivity(intent)
//                        val data: Uri? = intent.data
//                        data?.let {
//                            val sessionId = it.getQueryParameter("created_session_id")!!
//                            val rotatingToken = it.getQueryParameter("rotating_token_nonce")!!
//                            service.getSession(sessionId = sessionId, sessionToken = rotatingToken)
//
//
//                            // TODO: Gọi Clerk API hoặc lưu session tùy mục đích
//                        }
                    } else {
                        // Show error (Toast, Snackbar,...)
                        println("Không lấy được URL đăng nhập")
                    }
                }
            }

        ) {
            Text(
                text = "Sign in With Google",
            )
        }
    }
}