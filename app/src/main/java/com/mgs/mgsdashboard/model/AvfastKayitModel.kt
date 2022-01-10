package com.mgs.mgsdashboard.model

class AvfastKayitModel {
    var imgResId : Int? = 0
    var codeName: String? = null
    var versionName: String? = null

    constructor(imgResId: Int, codeName: String, versionName: String) {
        this.imgResId = imgResId
        this.codeName = codeName
        this.versionName = versionName
    }
}