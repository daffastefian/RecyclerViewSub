package com.daffa.listsub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Laptops"
        loading(true)
        list()
    }

    private fun loading(load: Boolean) {
        if (load) {
            rvList.visibility = View.GONE
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
            rvList.visibility = View.VISIBLE
        }
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
                    Handler().postDelayed({
                        loading(false)
                    }, 2500)
                }

                override fun onError(anError: ANError?) {
                    loading(false)
                    Toast.makeText(this@MainActivity, "Failed to connect!", Toast.LENGTH_SHORT).show()
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
        if (id == R.id.menu_about) startActivity(Intent(this@MainActivity, About::class.java))
        return super.onOptionsItemSelected(item)
    }

}
