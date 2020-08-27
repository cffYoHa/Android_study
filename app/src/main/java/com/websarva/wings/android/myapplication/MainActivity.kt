package com.websarva.wings.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ListViewオブジェクトを生成
        var lvMenu = findViewById<ListView>(R.id.lvMenu)
        //リストビューに表示するデータを作成
        var menuList = mutableListOf("から揚げ定食", "ハンバーグ定食", "生姜焼き定食",
            "ステーキ定食", "野菜炒め定食", "とんかつ定食", "ミンチかつ定食", "チキンカツ定食",
            "コロッケ定食", "焼き魚定食", "焼肉定食")
        //アダプタオブジェクトを生成
        var adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, menuList)
        //リストビューにアダプタオブジェクトを設定
        lvMenu.adapter= adapter
        //リストビューにリスナを設定
        lvMenu.onItemClickListener = ListItemClickListener()

    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //注文確認ダイアログフラグメントオブジェクトを生成
            val dialogFragment = OrderConfirmDialogFragment()
            //ダイアログを表示
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}