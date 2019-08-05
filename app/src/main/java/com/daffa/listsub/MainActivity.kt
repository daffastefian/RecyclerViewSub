package com.daffa.listsub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = RVAdapter(context, list())
        }
    }

    private fun list() : List<DataModel> = listOf(
        DataModel("a","b")
    )

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
