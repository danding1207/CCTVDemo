package com.msc.cctvdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.msc.cctvdemo.adapter.TVAdapter
import com.msc.cctvdemo.bean.TvData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TVAdapter
    private lateinit var data: ArrayList<TvData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TVAdapter(this)
        data = ArrayList()

        recyclerView.adapter = adapter
        adapter.setOnClickLisenter { view, position ->

           val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("tvUri", data[position].tvUri)
            startActivity(intent)

        }
        initData()

    }

    private fun initData() {


        data.add(TvData("CCTV1FHD", "", "http://223.110.241.204:6610/gitv/live1/G_CCTV-1-HQ/G_CCTV-1-HQ/"))
        data.add(TvData("CCTV3FHD", "", "http://223.110.241.204:6610/gitv/live1/G_CCTV-3-HQ/G_CCTV-3-HQ/"))
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())
//        data.add(TvData())

        adapter.setData(data)

    }
}
