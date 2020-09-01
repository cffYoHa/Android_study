package com.websarva.wings.android.fragmentsample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {

    /**
     * 大画面かどうかの判定フラグ
     * trueが大画面,falseが通常画面
     * 判定ロジックは同一画面に注文表示フレームレイアウトが存在するかどうかで行う
     */
    private var _isLayoutXlarge = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //自身が所属するアクティビティからmenuThanksFrameを取得
        val menuThanksFrame = activity?.findViewById<View>(R.id.manuThanksFrame)
        //menuThanksFrameがnull、つまり存在しないなら・・・
        if (menuThanksFrame == null) {
            //判定フラグを通常画面にする
            _isLayoutXlarge = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
        //フラグメントで表示する画面をXMLファイルからインフレートする（inflate=膨らませる→.xmlファイルに記述された画面部品を、実際のjavaオブジェクトに膨らませる）
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        //画面部品ListViewを取得
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)

        //SimpleAdapterで使用するMutableListオブジェクトを用意
        var menuList: MutableList<MutableMap<String, String>> = mutableListOf()

        //「唐揚げ定食の」データを格納するMapオブジェクトの用意とmenulistへのデータ登録
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "800円")
        menuList.add(menu)
        //ハンバーグ定食のデータを格納するMapオブジェクトの用意とmenulistへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        //以下データ登録の繰り返し。
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to "950円")
        menuList.add(menu)
        //simpleAdapter第四引数from用データの用意
        val from = arrayOf("name", "price")
        //simpleAdapter第四引数from用データの用意
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        //simpleAdapterの生成
        val adapter = SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2, from, to)
        //アダプタの登録
        lvMenu.adapter = adapter

        //リスなの登録
        lvMenu.onItemClickListener = ListItemClickListener()

        //インフレーとされた画面を戻り値として返す
        return view
    }

    //リストがタップされた時の処理が記述されたメンバクラス
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMutableMap型！
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額を取得
            val menuName = item ["name"]
            val menuPrice = item["price"]

            //引き継ぎデータをまとめて格納できるBundleオブジェクトを生成
            val bundle = Bundle()
            //Bundleオブジェクトに引き継ぎデータを格納
            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", menuPrice)

            //大画面の場合
            if (_isLayoutXlarge) {
                //フラグメントトランザクションの開始
                val transaction = fragmentManager?.beginTransaction()
                //注文完了フラグメントを生成
                val menuThanksFragment = MenuThanksFragment()
                //引き継ぎデータを注文完了フラグメントに格納
                menuThanksFragment.arguments = bundle
                //生成した注文完了フラグメントをmenuThanksFrameレイアウト部品に追加（置き換え）
                transaction?.replace(R.id.manuThanksFrame, menuThanksFragment)
                //フラグメントトランザクションのコミット
                transaction?.commit()
            }
            //通常画面の場合
            else {
                //インテントオブジェクトを生成
                val intent = Intent(activity, MenuThanksActivity::class.java)
                //第二画面に送るデータを格納
                intent.putExtra("menuName", menuName)
                intent.putExtra("menuPrice", menuPrice)
                //第二が画面の起動
                startActivity(intent)
            }
        }
    }
}