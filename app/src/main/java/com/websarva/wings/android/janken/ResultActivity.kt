package com.websarva.wings.android.janken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    val gu = 0
    val choki = 1
    val pa = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val id = intent.getIntExtra("My_HAND", 0) /*遷移先ResultActivityでインテントからデータを取り出す*/

        val myHand: Int
        myHand = when (id) {
            R.id.gu -> {
                myHandImage.setImageResource(R.drawable.gu)
                gu
            }
            R.id.choki -> {
                myHandImage.setImageResource(R.drawable.choki)
                choki
            }
            R.id.pa -> {
                myHandImage.setImageResource(R.drawable.pa)
                pa
            }
            else -> gu
        }

        // コンピュータの手を決める
        val comHand = (Math.random() * 3 ).toInt()//0~2のランダムな整数を返す
        when (comHand) {
            gu -> comHandImage.setImageResource(R.drawable.com_gu)
            choki -> comHandImage.setImageResource(R.drawable.com_choki)
            pa -> comHandImage.setImageResource(R.drawable.com_pa)
        }

        //勝敗を判定する
        val gameResult = (comHand - myHand + 3 ) % 3//ジャンケン判定ロジック
        when (gameResult) {
            0 -> resultLabel.setText(R.string.result_draw)   //引き分け
            1 -> resultLabel.setText(R.string.result_win)    //勝ち
            2 -> resultLabel.setText(R.string.result_lose)   //負け
        }
        backButton.setOnClickListener { finish() } /* ラムダ式とSAM変換でクリック時のリスナーを設定　*/
    }
}