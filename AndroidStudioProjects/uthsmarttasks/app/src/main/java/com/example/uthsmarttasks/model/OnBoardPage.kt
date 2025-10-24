package com.example.uthsmarttasks.model

import androidx.annotation.DrawableRes
import com.example.uthsmarttasks.R

data class OnBoardPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    @DrawableRes val logo: Int? = null // thêm logo tùy chọn
)

val onBoardPages = listOf(
    OnBoardPage(
        image = R.drawable.ic_time_management,
        title = "SmartTasks",
        description = "Manage your tasks smarter and more efficiently every day!",
    ),
    OnBoardPage(
        image = R.drawable.ic_work_effectiveness,
        title = "Increase Work Effectiveness",
        description = "Time management and analysis of more important tasks will give your job statistics better and always improve."
    ),
    OnBoardPage(
        image = R.drawable.ic_reminder,
        title = "Reminder Notification",
        description = "This app provides reminders for you so you don’t forget to finish your assignments on time."
    )
)
