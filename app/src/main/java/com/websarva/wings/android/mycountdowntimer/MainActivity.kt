package com.websarva.wings.android.mycountdowntimer

import android.content.IntentSender
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool//インスタンス宣言と、あとで初期化するよ
    private var soundResId = 0//サウンドプロパティのリソースID


    //innerがつくことで、外部の変数（レイアウトに配置されたビューのID名を参照できるようになる
    inner class  MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        var isRunnig = false

        override fun onTick(millisUntilFinished : Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            timerText.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            timerText.text = "0:00"
            soundPool.play(soundResId, 1.0f, 100f, 0,0,1.0f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText.text = "3:00"
        val timer = MyCountDownTimer(3 * 60 * 1000, 100)
        playStop.setOnClickListener {
            timer.isRunnig = when (timer.isRunnig) {
                true -> {
                    timer.cancel()
                    playStop.setImageResource(
                        R.drawable.ic_baseline_play_arrow_24
                    )
                    false
                }
                false -> {
                    timer.start()
                    playStop.setImageResource(
                        R.drawable.ic_baseline_stop_24
                    )
                    true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        soundPool =
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                @Suppress("DEPRECATION")
                SoundPool(2, AudioManager.STREAM_ALARM, 0)//soudPoolはAPI21移行// (LolliPop)でdeprecated
            } else {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build()
            }
        soundResId = soundPool.load(this, R.raw.bellsound, 1)
    }

    override  fun onPause (){
        super.onPause()
        soundPool.release()
    }
}