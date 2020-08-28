package com.websarva.wings.android.myscheduler

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm//あとで初期化するため、lateinit修飾子をつけている

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        realm = Realm.getDefaultInstance()//インスタンスを取得
        list.layoutManager = LinearLayoutManager(this)//インスタンスを生成し、recyclerViewのレイアウうとマネージャーとして登録
        val schedules = realm.where<Schedule>().findAll()
        val adapter = ScheduleAdapter(schedules)//ScheduleAdapterのインスタンスを生成し、RecyclerViewに設定
        //        list.adapter = adapter

        fab.setOnClickListener { view ->
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()//realmのインスタンスを破棄し、リソースを開放する
    }
}