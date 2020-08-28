package com.websarva.wings.android.myscheduler

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Schedule : RealmObject() {//Realmのモデルクラスは継承できるようにopen修飾子をつける
    @PrimaryKey//データを一意に管理できるようになる
     var id: Long = 0
    var date: Date = Date()
    var title : String = ""
    var detail: String = ""
}