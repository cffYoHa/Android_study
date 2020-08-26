package com.websarva.wings.android.listviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ListViewオブジェクトを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //ListViewにリスナを設定
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, posision: Int, id: Long) {
            //タップされた定食名を表示
            val item = parent?.getItemAtPosition(posision) as String
            //トーストで表示する文字列を生成
            val show = "あなたが選んだ定食:" + item
            //トーストの表示
            Toast.makeText(applicationContext, show, Toast.LENGTH_LONG).show()
        }
    }
}