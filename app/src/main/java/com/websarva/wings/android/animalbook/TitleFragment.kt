package com.websarva.wings.android.animalbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_title.*//TextViewのIDを直接参照しているため


class TitleFragment : Fragment() {//フラグメントはFragmentクラスを継承して作成する


    override fun onCreateView(//フラグメントに使用する画面レイアウトからビューを作成し、戻り値とする必要がある
         //フラグメントが初めてUIを描画するタイミングで呼び出される
        inflater: LayoutInflater,//inflator:フラグメント内のビューを作成するために使用するLayoutinflatorオブジェクトが渡される
        container: ViewGroup?,//container: フラグメントの親となるビューグループが渡される
        savedInstanceState: Bundle?//フラグメントの状態を保持するBundleオブジェクトが渡される
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    fun setTitle(title: String) {//タイトル表示領域の文字列を設定するメソッド
        titleText.text = title
    }
}