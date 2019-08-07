package com.daffa.listsub

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {
    private var data: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data = intent.getSerializableExtra("data") as DataModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Specification"
        initLayout()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        data?.let {
            Glide.with(applicationContext)
                .load(it.image)
                .error(R.drawable.err)
                .centerCrop()
                .into(ivPost)
            tvSeri.text = it.seri
            tvCpu.text = it.cpu
            tvOs.text = it.os
            tvDisplay.text = it.display
            tvChipset.text = it.chipset
            tvGraphics.text = it.graphics
            tvMemory.text = it.memory
            tvDrive.text = it.drive
            tvWebCam.text = it.webcam
            tvKeyboard.text = it.keyboard
            tvConnect.text = it.connect
            tvJack.text = it.jack
            tvAdapter.text = it.adapter
            tvDimension.text = it.dimension
            tvPort.text = it.port
        }
        official.setOnClickListener {
            startActivity(Intent(ACTION_VIEW, Uri.parse(data?.support)))
        }
    }

}
