package com.websarva.wings.android.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画面部品　ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        // SimpleAdapterで使用するMutableオブジェクトを用意
        val menuList : MutableList<MutableMap<String, String>> = mutableListOf()
        //「唐揚げ定食の」データを格納するMapオブジェクトの用意とmenulistへのデータ登録
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "800円")
        menuList.add(menu)
        //ハンバーグ定食のデータを格納するMapオブジェクトの用意とmenulistへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        //以下データ登録の繰り返し。
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to "950円")
        menuList.add(menu)
        //simpleAdapter第四引数from用データの用意
        val from = arrayOf("name", "price")
        //simpleAdapter第四引数from用データの用意
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        //simpleAdapterの生成
        val adapter = SimpleAdapter(applicationContext, menuList, android.R.layout.simple_list_item_2,
        from, to)
        //アダプタの登録
        lvMenu.adapter = adapter
        //リストタップのリスナクラス登録
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    //リストがタップされた時の処理が記述されたメンバクラス
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMutableMap型！
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額を取得
            val menuName = item ["name"]
            val menuPrice = item["price"]
            //インテントオブジェクトを生成
            val intent = Intent(applicationContext, MenuThanksActivity::class.java)
            //第二画面に送るデータを格納
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", menuPrice)
            //第二が画面の起動
            startActivity(intent)
        }
    }
}
