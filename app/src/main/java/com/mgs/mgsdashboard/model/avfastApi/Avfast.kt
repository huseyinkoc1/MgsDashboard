package com.mgs.mgsdashboard.model.avfastApi

data class Avfast(
    val daily_logged_in_users_chart: List<DailyLoggedİnUsersChart>,
    val daily_logged_in_users_count: Int,
    val logs: List<Log>,
    val monthly_total_users_chart: List<MonthlyTotalUsersChart>,
    val monthly_total_users_count: Int,
    val online_users_count: Int,
    val register_users: List<RegisterUser>,
    val users_count: Int,
    val weekly_applied_tasks_chart: List<WeeklyAppliedTasksChart>,
    val weekly_applied_tasks_count: Int,
    val weekly_done_tasks_chart: List<WeeklyDoneTasksChart>,
    val weekly_done_tasks_count: Int,
    val weekly_evaluated_tasks_chart: List<WeeklyEvaluatedTasksChart>,
    val weekly_evaluated_tasks_count: Int,
    val weekly_tasks_chart: List<WeeklyTasksChart>,
    val weekly_tasks_count: Int
)