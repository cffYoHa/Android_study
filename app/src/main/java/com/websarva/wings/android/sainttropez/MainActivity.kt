package com.websarva.wings.android.sainttropez

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.websarva.wings.android.sainttropez.R.menu
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/html/index.html")
        
        registerForContextMenu(webView)//webViewを登録
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {//MenuクラスのインスタンスはonCreateOptionsMenuの引数として渡されている
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.top -> {
                webView.loadUrl(
                    "file:///android_asset/html/index.html"
                )
                return true
            }
            R.id.lunch01 -> {
                webView.loadUrl(
                    "file:///android_asset/html/lunch01.html"
                )
                return true
            }
            R.id.lunch02 -> {
                webView.loadUrl(
                    "file:///android_asset/html/lunch02.html"
                )
                return true
            }
            R.id.dinner01 -> {
                webView.loadUrl(
                    "file:///android_asset/html/dinner01.html"
                )
                return true
            }
            R.id.dinner02 -> {
                webView.loadUrl(
                    "file:///android_asset/html/dinner02.html"
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(//コンテキストメニューとして登録されたビューを長押しすると、onCreateMenuメソッドが呼ばれるため、これをオーバーライド
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)//inflateメソッドによりメニューXMLファイルをコンテキストメニューに設定する
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {//メニュー項目がタップされた時の処理
        when(item?.itemId) {
            R.id.sms -> {
                val number = "999-9999-999"
                val uri = Uri.parse("sms:$number")//電話番号からUriオブジェクトを生成
                var intent = Intent(Intent.ACTION_VIEW)//アクションとしてintent.ACTION_VIEWを想定し、インテントを作成
                intent.data = uri//Uriオブジェクトをデータとして設定
                startActivity(intent)//startActivityにインテントを渡してアプリ起動
                return true
            }
            R.id.mail -> {
                val email = "nobody@example.com"
                val subject = "予約問合せ"
                val text = "以下の通り予約希望します。"
                val uri = Uri.parse("mailto:")//parseメソッドでURI形式からUriオブジェクトを生成する
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = uri
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))//Intent.EXTRA_EMAIL型は、String型の配列である
                    .putExtra(Intent.EXTRA_SUBJECT, subject)
                    .putExtra(Intent.EXTRA_TEXT, text)
                if(intent.resolveActivity(packageManager) != null) {//intentを処理できるアプリが一つでもあれば
                    //仮に一つもないと、何もないのに起動しようとして強制終了してしまう
                    startActivity(intent)
                }
                return true
            }
            R.id.share -> {
                val text = "美味しいレストランを紹介します"
                val init = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, text)
                val chooser = Intent.createChooser(intent, null)
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(chooser)
                }
                return true
            }
            R.id.browse ->  {
                val url : String= "http://www.yahoo.co.jp/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}