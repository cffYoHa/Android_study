package com.websarva.wings.android.myslidesshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*

val IMG_RES_ID = "IMG_RES_ID"//Bundleクラスで値を保存するために使用するkey

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    companion object {// Javaでいうstatic methodで、このクラスImageFragment内でのmethod
    //ImageFragment内のImageViewに関するリソースIDを取得して、生成したimageFragmentのインスタンスを返す
        fun newInstance(imageResourceID: Int) : ImageFragment {
            val bundle = Bundle()
            bundle.putInt(IMG_RES_ID, imageResourceID)//上で生成したBundleインスタンスにidを書き込んでいる
            val imageFragment = ImageFragment()
            imageFragment.arguments = bundle//フラグメントのデータをargumentsに保存する
            return imageFragment
        }
    }

    private var imgResID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {//Bundleから値を取り出す処理
        super.onCreate(savedInstanceState)
        arguments?.let{//安全よびだし演算子とスコープ関数（）letを使用して、nullではないことを確認している
            //letを使うことで、スコープ（特定の名前で参照される範囲を限定）でき、いちいちarguments?.getIntと書かずにit.getIntと書くことができる
            imgResID = it.getInt(IMG_RES_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imgResID?.let {
            imageView.setImageResource(it)//imgResIdに保存しておいた画像リソースIDを使用して、フラグメント内のImageViewに画像を設定
        }
    }
}