package com.websarva.wings.android.animalbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        lionButton.setOnClickListener {//ライオンボタンが押された時の処理
            val fragment = LionFragment()//ライオンを表示するためのインスタンスを生成し、fragmentへ代入している
            val fragmentManager = this.getSupportFragmentManager()//FragmentManagerのインスタンスを取得している
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)//フラグメントの置き換えを行っている
                .addToBackStack(null)//上のフラグメントの処理をバックスタックに追加している
                    //バックスタックに保存しておくと、端末の戻るボタンで、一つ前の状態に戻ることができる
                .commit()//commitメソッドでフラグメントへの操作を確定する←これをしないとトランザクションが終了しない
        }

        hippoButton.setOnClickListener {//カバボタンが押された時の処理
            val fragment = HippoFragment()//カバを表示するためのインスタンスを生成し、fragmentへ代入している
            val fragmentManager = this.getSupportFragmentManager()//FragmentManagerのインスタンスを取得している
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)//フラグメントの置き換えを行っている
                .addToBackStack(null)//上のフラグメントの処理をバックスタックに追加している
                //バックスタックに保存しておくと、端末の戻るボタンで、一つ前の状態に戻ることができる
                .commit()//commitメソッドでフラグメントへの操作を確定する←これをしないとトランザクションが終了しない
        }

        giraffeButton.setOnClickListener {//キリンボタンが押された時の処理
            val fragment = GiraffeFragment()//キリンを表示するためのインスタンスを生成し、fragmentへ代入している
            val fragmentManager = this.getSupportFragmentManager()//FragmentManagerのインスタンスを取得している
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)//フラグメントの置き換えを行っている
                .addToBackStack(null)//上のフラグメントの処理をバックスタックに追加している
                //バックスタックに保存しておくと、端末の戻るボタンで、一つ前の状態に戻ることができる
                .commit()//commitメソッドでフラグメントへの操作を確定する←これをしないとトランザクションが終了しない
        }

        val fragment = titleFragment as? TitleFragment//　id名：titleFragmentをTitleFragment型にキャストしている.これによって, setTitleが使えるようになる
        fragment?.setTitle("図鑑画面")//"?."は安全呼び出し演算子fragmentオブジェクトがnullでなければ、図鑑画面が出力される
    }
}