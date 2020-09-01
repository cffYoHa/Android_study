package com.websarva.wings.android.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_menu_thanks.*

/**
 * A simple [Fragment] subclass.
 * Use the [MenuThanksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuThanksFragment : Fragment() {

    /**
     * 大画面かどうかの判定フラグ
     * trueが大画面,falseが通常画面
     * 判定ロジックは同一画面にリストフラグメントが存在するかどうかで行う
     */
    private var _isLayoutXlarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //自身が所属するアクティビティからmenuListFragmentを取得
        val menuListFragment = activity?.findViewById<View>(R.id.fragmentMenuList)
        //menuListFragmentがnull、つまり存在しないなら・・・
        if (menuListFragment == null) {
            //判定フラグを通常画面にする
            _isLayoutXlarge = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)
        //Bundleオブジェクトを宣言
        val extras: Bundle?
        //大画面の場合
        if (_isLayoutXlarge) {
            //このフラグメントに埋め込まれた引き継ぎデータを取得
            extras = arguments
        }
        //通常画面の場合
        else {
            //所属アクティビティからインテントを生成
            val intent = activity?.intent
            //インテントから引き継ぎデータをまとめたもの(Bundleオブジェクトを取得)
            extras = intent?.extras
        }


        //定食名と金額を取得
        val menuName = extras?.getString("menuName")
        val menuPrice = extras?.getString("menuPrice")
        //定食名と金額を表示させるTextViewを取得
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)

        //TextViewに定食名と金額を表示
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice
        // 戻るボタンを取得
        val btBackButton =view.findViewById<Button>(R.id.btBackButton)
        //戻るボタンにリスナを登録
        btBackButton.setOnClickListener (ButtonClickListener())

        //インフレーとされた画面を戻り値として取得
        return view
    }
    //buttonが押されたときの処理が記述されたメンバクラス
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            //大画面の場合
            if (_isLayoutXlarge) {
                //フラグメントトランザクションの開始
                val transaction = fragmentManager?.beginTransaction()
                //自分自身を削除
                transaction?.remove(this@MenuThanksFragment)
                //フラグメメントトランザクションのコミット
                transaction?.commit()
            }//通常画面の場合
            else {
                //自信が所属するアクティビティを終了
                activity?.finish()
            }
        }
    }
}