package com.websarva.wings.android.myscheduler

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_edit)
        realm = Realm.getDefaultInstance()//Realmのインスタンスを生成

        val scheduleId = intent?.getLongExtra("schedule_id", -1L)
        if (scheduleId != -1L) {
            val schedule = realm.where<Schedule>()
                .equalTo("id", scheduleId).findFirst()
            dateEdit.setText(DateFormat.format("yyyy/MM/dd", schedule?.date))
            titleEdit.setText(schedule?.title)
            detailEdit.setText(schedule?.detail)
            delete.visibility = View.VISIBLE//
        } else {
            delete.visibility = View.INVISIBLE
        }

        save.setOnClickListener { view: View ->//以下、「保存」ボタンが押された時の処理
            when (scheduleId) {
                -1L -> {
                    realm.executeTransaction { db: Realm ->//トランザクション（データベースへの書き込みの一貫性、スレッドセーフを保証 するために必要）の開始・終了・キャンセルを自動で行ってくれる
                        val maxId = db.where<Schedule>().max("id")
                        val nextId = (maxId?.toLong()
                            ?: 0L) + 1//エルビス演算子maxIdがnull意外ならその値をLong型に変換 、nullならLong型の0を取得し、＋1して返す
                        val schedule = db.createObject<Schedule>(nextId)//データを1行追加
                        val date = dateEdit.text.toString().toDate("yyyy/MM/dd")
                        if (date != null) schedule.date = date//上のscheduleオブジェクトに値を設定
                        schedule.title = titleEdit.text.toString()
                        schedule.detail = detailEdit.text.toString()
                    }
                    Snackbar.make(
                        view,
                        "追加しました",
                        Snackbar.LENGTH_SHORT
                    )//Toast同様に一定時間メッセージを表示できるが、actionを設定できる
                        .setAction("戻る") { finish() }
                        .setActionTextColor(Color.YELLOW)
                        .show()
                }
                else -> {
                    realm.executeTransaction { db: Realm ->//トランザクション（データベースへの書き込みの一貫性、スレッドセーフを保証 するために必要）の開始・終了・キャンセルを自動で行ってくれる
                        val schedule = db.where<Schedule>()//データを1行追加
                            .equalTo("id", scheduleId).findFirst()
                        val date = dateEdit.text.toString()
                            .toDate("yyyy/MM/dd")
                        if (date != null) schedule?.date = date//上のscheduleオブジェクトに値を設定
                        schedule?.title = titleEdit.text.toString()
                        schedule?.detail = detailEdit.text.toString()
                    }
                    Snackbar.make(
                        view,
                        "修正しました",
                        Snackbar.LENGTH_SHORT
                    )//Toast同様に一定時間メッセージを表示できるが、actionを設定できる
                        .setAction("戻る") { finish() }
                        .setActionTextColor(Color.YELLOW)
                        .show()
                }
            }
        }

        delete.setOnClickListener { view: View ->
            realm.executeTransaction { db: Realm ->
                db.where<Schedule>().equalTo("id", scheduleId)
                    ?.findFirst()
                    ?.deleteFromRealm()//RealmObjectクラスのdeleteFromRealmメソッドを使用
            }
            Snackbar.make(view, "削除しました", Snackbar.LENGTH_SHORT)
                .setAction("戻る") { finish() }
                .setActionTextColor(Color.YELLOW)
                .show()
        }
    }

    override fun onDestroy() {//データベースを閉じる処理
        super.onDestroy()
        realm.close()
    }

    private fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm"): Date? {
        return try {
            SimpleDateFormat(pattern).parse(this)
        } catch (e: IllegalArgumentException) {
            return null
        } catch (e: ParseException) {
            return null
        }
    }
}
