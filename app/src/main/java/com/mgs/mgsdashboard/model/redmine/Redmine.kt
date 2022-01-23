package com.mgs.mgsdashboard.model.redmine

data class Redmine(
    val points_per_user: List<Any>,
    val task_completed: List<TaskCompleted>,
    val task_completed_team: List<TaskCompletedTeam>,
    val task_created: List<TaskCreated>
)