package com.websarva.wings.android.myslidesshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer//lateinit修飾子をつけると、null非許容型のプロパティを作成し、初期化を遅らせることができる


    class MyAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {//classのなかにclassを入れることを、ネストしたクラスと言う
        //わざわざネストする意義としては、MyAdapterはMyActivity専用のクラスである、と言うことが一見してわかるからである
        private val resources = listOf(//フラグメントに表示する画像を10枚保持するためのInt型リスト
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
        )

        override fun getItemCount(): Int {//総ページ数を返すメソッド
            return resources.size
        }

        override fun createFragment(position: Int): Fragment {//引数にページ番号を受け取り、対応するフラグメントを戻り値として返す
            return ImageFragment.newInstance(resources[position])//newInstanceはクラスImageFragmentのコンパニオンオブジェクトだから、この記述が可能
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MyAdapter(this)
        val handler = Handler()
        timer(period = 5000){
            handler.post{
                pager.currentItem = (pager.currentItem + 1) % 10
            }
        }
        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true

    }

    override fun onResume() {//アクティビティが画面表示されるときに呼ばれる
        super.onResume()
        player.start()
    }

    override fun onPause() {//
        super.onPause()
        player.pause()
    }
}