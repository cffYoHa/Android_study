package com.websarva.wings.android.graphqlcitysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.AllCityQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val lvCities = findViewById<ListView>(R.id.lvCities)

        CoroutineScope(Dispatchers.Default).launch {
            val res = try {
                apolloClient.query(AllCityQuery()).toDeferred().await()
            }catch (ex: ApolloException){
                Log.e(packageName,ex.toString())
                return@launch
            }
            val cities = res?.data?.allCity?.map { it.name } ?: return@launch
            withContext(Dispatchers.Main){
                val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,cities)
                lvCities.adapter =adapter
                lvCities.onItemClickListener = LvCitiesClickListener()
            }
        }
    }

    private inner class LvCitiesClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            val intent = Intent(applicationContext, CityDetailActivity::class.java)
            intent.putExtra("id",p2)
            startActivity(intent)
        }
    }
}