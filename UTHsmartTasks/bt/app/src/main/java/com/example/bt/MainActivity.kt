package com.example.bt

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NumberActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var btnCreate: Button
    private lateinit var tvError: TextView
    private lateinit var layoutButtons: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View đầy đủ
        edtNumber = findViewById(R.id.edtNumber)
        btnCreate = findViewById(R.id.btnCreate)
        tvError = findViewById(R.id.tvError)
        layoutButtons = findViewById(R.id.layoutButtons)

        btnCreate.setOnClickListener {
            val input = edtNumber.text.toString()
            generateButtons(input)
        }
    }

    private fun generateButtons(input: String) {
        layoutButtons.removeAllViews()
        tvError.visibility = View.GONE

        // Xử lý logic nhập số
        val number = input.toIntOrNull()

        if (number == null || number <= 0) {
            tvError.visibility = View.VISIBLE
            return
        }

        // Tạo nút bấm động
        for (i in 1..number) {
            val button = Button(this)
            button.text = i.toString()

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 8.dpToPx(), 0, 8.dpToPx())

            button.layoutParams = params
            button.setBackgroundResource(R.drawable.red_button_background)
            button.setTextColor(Color.WHITE)
            button.minHeight = 48.dpToPx()

            layoutButtons.addView(button)
        }
        edtNumber.setText(number.toString())
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}