package com.websarva.wings.android.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ボタンオブジェクトを取得
        val btClick = findViewById<Button>(R.id.btClick)
        //listenerクラスのインスタンス生成
        val listener = HelloListener()
        //表示ボタンにリスナーを設定
        btClick.setOnClickListener(listener)
        val btClear = findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listener)
    }

    /**
     * ボタンをクリックしたときのリスナクラス。
     */
    private inner class HelloListener : View.OnClickListener {
        //interfaceを実装
        override fun onClick(view: View) {
            //名前入力欄であるEditTextオブジェクトを取得。
            val input = findViewById<EditText>(R.id.etName)
            //メッセージを表示するTextViewオブジェクトを取得。
            val output = findViewById<TextView>(R.id.tvOutput)

            //idの値に応じて処理を分岐

            when (view.id) {
                R.id.btClick -> {
                    var inputStr = input.text.toString()
                    //メッセージを表示
                    output.text = inputStr + "さん、こんにちは！"
                }
                R.id.btClear -> {
                    //名前入力欄をから文字に設定
                    input.setText("")
                    //メッセージ表示欄を空文字に設定
                    output.text = ""
                }
            }
        }
    }
}