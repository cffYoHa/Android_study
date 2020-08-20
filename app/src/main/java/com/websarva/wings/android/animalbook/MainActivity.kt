package com.websarva.wings.android.animalbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub.*
import kotlinx.android.synthetic.main.activity_sub.titleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment =
            titleFragment as? TitleFragment//titleFragmentをTitleFragment型にキャストしている.これによって, setTitleが使えるようになる
        fragment?.setTitle("フラグメント動物図鑑")//TitleFragmentクラスに作ったsetTitleメソッド


        nextButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)//Intentクラスのインスタンスを生成する、第二引数はjava.lang.Classを指定するが、Kotlinでは"クラス名::class.java"と書く
            startActivity(intent)//startActivityメソッドで画面遷移をする
        }
    }
}