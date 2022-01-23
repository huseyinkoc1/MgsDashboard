package com.mgs.mgsdashboard.model

class RedmineRcyclerModelSecond {
    var rank : String? = null
    var nameSurname: String? = null
    var scoreBoard: String? = null

    constructor(rank: String, nameSurname: String, scoreBoard: String) {
        this.rank = rank
        this.nameSurname = nameSurname
        this.scoreBoard = scoreBoard
    }
}