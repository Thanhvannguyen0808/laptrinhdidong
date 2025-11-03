package com.example.homework_week1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liên kết Activity với file XML layout
        setContentView(R.layout.activity_main)

        // Bạn có thể thêm logic xử lý sự kiện cho các nút tại đây nếu cần
        /*
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            // Logic khi bấm nút Back (ví dụ: quay lại màn hình trước)
            finish()
        }
        */
    }
}