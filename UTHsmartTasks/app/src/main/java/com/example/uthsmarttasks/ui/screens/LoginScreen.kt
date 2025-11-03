package com.example.uthsmarttasks.ui // Giữ nguyên package của bạn

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke // Import thêm
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Import thêm
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign // Import thêm
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavController) {
    // --- PHẦN LOGIC CỦA BẠN (GIỮ NGUYÊN) ---
    val context = LocalContext.current
    val activity = context as Activity
    val auth = FirebaseAuth.getInstance()

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener(activity) { task2 ->
                if (task2.isSuccessful) {
                    // Điều hướng đến ProfileScreen như trong logic của bạn
                    navController.navigate("profile") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        } catch (_: ApiException) {}
    }

    // --- PHẦN UI (CẬP NHẬT THEO ĐỀ BÀI) ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 16.dp), // Dùng padding thay vì 32.dp hết
        horizontalAlignment = Alignment.CenterHorizontally
        // Đã bỏ verticalArrangement = Arrangement.Center
    ) {

        // --- Phần Logo và Tiêu đề ---
        Spacer(modifier = Modifier.height(64.dp)) // Đẩy logo xuống 1 chút
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.size(120.dp) // Giảm kích thước logo một chút
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tên App
        Text(
            text = "SmartTasks", // Tên app theo yêu cầu
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Tagline
        Text(
            text = "A simple and intuitive to-do app", // Tagline theo yêu cầu
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        // --- Phần Welcome ---
        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "Welcome",
            style = MaterialTheme.typography.headlineSmall // Chữ nhỏ hơn
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ready to explore? Log in to get started.", // Hướng dẫn theo yêu cầu
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        // --- Nút bấm và Footer (Đẩy xuống đáy) ---
        Spacer(modifier = Modifier.weight(1f)) // Đây là mấu chốt để đẩy nút xuống

        // Nút Google đã được style lại
        Button(
            onClick = { launcher.launch(googleSignInClient.signInIntent) }, // Giữ nguyên logic của bạn
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color(0xFFDADCE0)), // Viền xám nhạt
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                // Bạn cần thêm file icon 'ic_google_g.xml' (xem bên dưới)
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_g),
                    contentDescription = "Google Logo",
                    tint = Color.Unspecified, // Giữ màu gốc của logo
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "SIGN IN WITH GOOGLE",
                    color = Color(0xFF3C4043), // Màu text xám đậm
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Phần Footer
        Text(
            text = "© UTHSmartTasks",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}