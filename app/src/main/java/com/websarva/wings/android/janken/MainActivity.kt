package com.websarva.wings.android.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.content.edit as edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gu.setOnClickListener { onJankenButtonTapped(it) } /*ラムダ式とSAM変換でクリック時のリスナーを設定　　　*/
        choki.setOnClickListener { onJankenButtonTapped(it) }
        pa.setOnClickListener { onJankenButtonTapped(it) }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            clear()   // アプリケーション起動時にクリア
        }
    }

    fun onJankenButtonTapped(view: View?) { /*null許容型のView*/
        val intent = Intent(this, ResultActivity::class.java) /*開きたいactivityを指定して、Intentクラスのインスタンス生成*/
        intent.putExtra("My_HAND", view?.id) /*インテントにデータを格納する*/
        startActivity(intent) //生成したインスタンスをstartActivityメソッドの引数として渡す
    }
}