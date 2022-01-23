package com.mgs.mgsdashboard.model.petner

data class Petner(
    val adoption_pets_count: Int,
    val comments_in_last_five_months_chart: List<CommentsİnLastFiveMonthsChart>,
    val comments_in_last_month_count: Int,
    val conversations_in_last_five_weeks_chart: List<ConversationsİnLastFiveWeeksChart>,
    val conversations_in_last_week_count: Int,
    val logs: List<Log>,
    val pets_count: Int,
    val posts_in_last_five_months_chart: List<PostsİnLastFiveMonthsChart>,
    val posts_in_last_month_count: Int,
    val register_users: List<RegisterUser>,
    val users_count: Int,
    val users_in_last_five_months_chart: List<UsersİnLastFiveMonthsChart>,
    val users_in_last_month_count: Int,
    val users_logged_ln_last_week_chart: List<UsersLoggedLnLastWeekChart>,
    val users_logged_ln_today_count: Int
)