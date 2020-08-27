package com.websarva.wings.android.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class OrderConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //ダイアログビルダを生成
        val builder = AlertDialog.Builder(activity)
        //ダイアログタイトルを設定
        builder.setTitle(R.string.dialog_title)
        //ダイアログメッセージを設定
        builder.setMessage(R.string.dialog_msg)
        //Positiveボタンを設定
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())
        //negativeボタンを設定
        builder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())
        //neutralボタンを設定
        builder.setNeutralButton(R.string.dialog_btn_nu, DialogButtonClickListener())
        //ダイアログオブジェクトを生成し、リターン
        val dialog = builder.create()
        return dialog
    }

    //ダイアログのアクションボタンがタップされた時の処理が記述されたメンバクラス
    private inner class DialogButtonClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            //トーストメッセージを用の文字列変数を用意1
            var msg = ""
            //タップされたアクションボタンで分岐
            when (which) {
                //Positiveボタンなら
                DialogInterface.BUTTON_POSITIVE ->
                    //注文用のメッセージを格納
                    msg = getString(R.string.dialog_ok_toast)
                //Negativeボタンなら
                DialogInterface.BUTTON_NEGATIVE ->
                    //キャンセルメッセージを表示を格納
                    msg = getString(R.string.dialog_ng_toast)
                //neutralボタンなら
                DialogInterface.BUTTON_NEUTRAL ->
                    //問合せメッセージを表示を格納
                    msg = getString(R.string.dialog_nu_toast)
            }
            //トーストの表示
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }
    }
}