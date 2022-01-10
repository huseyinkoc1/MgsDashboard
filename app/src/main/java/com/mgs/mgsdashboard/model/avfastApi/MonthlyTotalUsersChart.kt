package com.mgs.mgsdashboard.model.avfastApi

data class MonthlyTotalUsersChart(
    val created_at: String,
    val month: String,
    val users_count: Int
)