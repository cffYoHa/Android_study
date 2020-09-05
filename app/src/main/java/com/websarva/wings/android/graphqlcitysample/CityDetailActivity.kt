package com.websarva.wings.android.graphqlcitysample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.CityQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        val tvLongitude = findViewById<TextView>(R.id.tvLongitude)
        val tvLatitude = findViewById<TextView>(R.id.tvLatitude)
        val tvName = findViewById<TextView>(R.id.tvCityName)

        val btnMap = findViewById<Button>(R.id.mapButton)

        val id = intent.getIntExtra("id",-1)
        if (id==-1){
            finish()
        }
        CoroutineScope(Dispatchers.Default).launch{
            val res = try{
                apolloClient.query(CityQuery(id+1)).toDeferred().await()
            }catch (e: ApolloException){
                Log.e(packageName,e.toString())
                return@launch
            }
            val longitude = res?.data?.city?.longitude ?:return@launch
            val latitude = res?.data?.city?.latitude ?: return@launch
            val name = res?.data?.city?.name ?: return@launch

            withContext(Dispatchers.Main){
                tvLongitude.text = longitude.toString()
                tvLatitude.text = latitude.toString()
                tvName.text = name
                btnMap.setOnClickListener {
                    val uriStr = "geo:${latitude},${longitude}"
                    Log.d(packageName,uriStr)
                    val uri = Uri.parse(uriStr)
                    val intent = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(intent)
                }
            }
        }
    }
}