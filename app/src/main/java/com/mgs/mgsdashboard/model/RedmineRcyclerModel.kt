package com.mgs.mgsdashboard.model

class RedmineRcyclerModel {
    var rank : String? = null
    var imgResId : Int? = 0
    var nameSurname: String? = null
    var scoreBoard: String? = null

    constructor(rank: String, imgResId: Int, nameSurname: String, scoreBoard: String) {
        this.rank = rank
        this.imgResId = imgResId
        this.nameSurname = nameSurname
        this.scoreBoard = scoreBoard
    }
}