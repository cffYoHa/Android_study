package com.websarva.wings.android.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override
                fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty()) height.text = it
                    }
                }

                override
                fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        val pref = PreferenceManager.getDefaultSharedPreferences(this)//共有プリファレンスを作成するメソッド
        val heightVal = pref.getInt("HEIGHT", 160)//共有プリファレンスの設定項目をInt型で取得する
        // HEIGHTという名前でkeyを設定し、設定項目が未設定なら160を返す
        height.text = heightVal.toString()//heightValはIntなので、Stiringに変換している
        seekBar.progress = heightVal//seekBarに先ほどアクセスした共有プリファレンスの値を設定している

        seekBar.setOnSeekBarChangeListener( //シークバーを操作したときに実行するリスナーを登録している
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,//シークバーを変更した時の処理を記述、
                    progress: Int,//設定された値
                    fromUser: Boolean
                ) {//ユーザによる変更かどうか
                    height.text = progress.toString()//設定された値（Int）をStringに変換し、TextViewに表示
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        radioGroup.setOnCheckedChangeListener {
            group, checkedID -> //ラムダ式だよ、プロパティが２つだから、この書き方になってる　これによって、動的に処理を変更できるそう？？
            height.text = findViewById<RadioButton>(checkedID).text//< >はジェネリックメソッドで、メソッドの引数をメソッドの呼び出しに際に決められるメソッドのこと
        // List<String>とかと同じ〜
        }
    }

    override fun onPause() {//こいつはオーバーライド必須のメソッドじゃないよ
        //アクテビティが非表示になるときに呼ばれる
        super.onPause()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            //textプロパティは、CharSequence型のため、toIntを直接使えないので、一旦toStringを挟んでいる
            putInt ("HEIGHT", height.text.toString().toInt())
        }
    }
}