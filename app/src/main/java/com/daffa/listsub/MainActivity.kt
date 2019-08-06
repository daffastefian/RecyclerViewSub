package com.daffa.listsub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val catalogue = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list()
    }

    private fun list() {
        catalogue.clear()
        AndroidNetworking.get("https://hostlearning18.000webhostapp.com/API/catalogue/show.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val jsonArray = response.getJSONArray("result")
                    Log.d("Data Array", jsonArray.toString())
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)

                        catalogue.add(
                            DataModel(
                                jsonObject.getString("seri"),
                                jsonObject.getString("cpu"),
                                jsonObject.getString("os"),
                                jsonObject.getString("display")
                                ,
                                jsonObject.getString("chipset"),
                                jsonObject.getString("graphics"),
                                jsonObject.getString("memory"),
                                jsonObject.getString("drive")
                                ,
                                jsonObject.getString("webcam"),
                                jsonObject.getString("keyboard"),
                                jsonObject.getString("connect"),
                                jsonObject.getString("jack")
                                ,
                                jsonObject.getString("adapter"),
                                jsonObject.getString("dimension"),
                                jsonObject.getString("port"),
                                jsonObject.getString("image"),
                                jsonObject.getString("support")
                            )
                        )
                    }
                    rvList.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = RVAdapter(context, catalogue)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("err", anError.toString())
                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.menu_about) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
