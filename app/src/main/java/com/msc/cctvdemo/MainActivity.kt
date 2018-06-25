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


        data.add(TvData("CCTV1FHD", R.drawable.cctv1, "http://223.110.241.204:6610/gitv/live1/G_CCTV-1-HQ/G_CCTV-1-HQ/"))
        data.add(TvData("CCTV2FHD", R.drawable.cctv2, "http://223.110.241.204:6610/gitv/live1/G_CCTV-2-CQ/G_CCTV-2-CQ/"))
        data.add(TvData("CCTV3FHD", R.drawable.cctv3, "http://223.110.241.204:6610/gitv/live1/G_CCTV-3-HQ/G_CCTV-3-HQ/"))
        data.add(TvData("CCTV4FHD", R.drawable.cctv4, "http://223.110.241.204:6610/gitv/live1/G_CCTV-4-HQ/G_CCTV-4-HQ/"))
        data.add(TvData("CCTV5FHD", R.drawable.cctv5, "http://223.110.241.204:6610/gitv/live1/G_CCTV-5-HQ/G_CCTV-5-HQ/"))
        data.add(TvData("CCTV6FHD", R.drawable.cctv6, "http://223.110.241.204:6610/gitv/live1/G_CCTV-6-HQ/G_CCTV-6-HQ/"))
        data.add(TvData("CCTV7FHD", R.drawable.cctv7, "http://223.110.241.204:6610/gitv/live1/G_CCTV-7-HQ/G_CCTV-7-HQ/"))
        data.add(TvData("CCTV8FHD", R.drawable.cctv8, "http://223.110.241.204:6610/gitv/live1/G_CCTV-8-HQ/G_CCTV-8-HQ/"))
        data.add(TvData("CCTV9FHD", R.drawable.cctv9, "http://223.110.241.204:6610/gitv/live1/G_CCTV-9/G_CCTV-9/"))
        data.add(TvData("CCTV10FHD", R.drawable.cctv10, "http://223.110.241.204:6610/gitv/live1/G_CCTV-10-HQ/G_CCTV-10-HQ/"))
        data.add(TvData("CCTV11FHD", R.drawable.cctv11, "http://223.110.241.204:6610/gitv/live1/G_CCTV-11-HQ/G_CCTV-11-HQ/"))
        data.add(TvData("CCTV12FHD", R.drawable.cctv12, "http://223.110.241.204:6610/gitv/live1/G_CCTV-12-HQ/G_CCTV-12-HQ/"))
        data.add(TvData("CCTV13FHD", R.drawable.cctv13, "http://223.110.241.204:6610/gitv/live1/G_CCTV-13-HQ/G_CCTV-13-HQ/"))
        data.add(TvData("CCTV14FHD", R.drawable.cctv14, "http://223.110.241.204:6610/gitv/live1/G_CCTV-14/G_CCTV-14/"))
        data.add(TvData("CCTV15FHD", R.drawable.cctv15, "http://223.110.241.204:6610/gitv/live1/G_CCTV-15/G_CCTV-15/"))

        data.add(TvData("北京卫视", R.drawable.beijing, "http://223.110.241.204:6610/gitv/live1/G_BEIJING-HQ/G_BEIJING-HQ/"))
        data.add(TvData("安徽卫视", R.drawable.anhui, "http://223.110.241.204:6610/gitv/live1/G_ANHUI-CQ/G_ANHUI-CQ/"))
//        data.add(TvData("东方卫视", R.drawable.dongfang, "http://223.110.241.204:6610/gitv/live1/G_CCTV-15/G_CCTV-15/"))
//        data.add(TvData("河北卫视", R.drawable.hebei, "http://223.110.241.204:6610/gitv/live1/G_CCTV-15/G_CCTV-15/"))
        data.add(TvData("湖南卫视", R.drawable.hunan, "http://223.110.241.204:6610/gitv/live1/G_HUNAN-HQ/G_HUNAN-HQ/"))
        data.add(TvData("江苏卫视", R.drawable.jiangsu, "http://223.110.241.204:6610/gitv/live1/G_JIANGSU-HQ/G_JIANGSU-HQ/"))
//        data.add(TvData("吉林卫视", R.drawable.jilin, "http://223.110.241.204:6610/gitv/live1/G_CCTV-15/G_CCTV-15/"))
        data.add(TvData("辽宁卫视", R.drawable.liaoning, "http://223.110.241.204:6610/gitv/live1/G_LIAONING-CQ/G_LIAONING-CQ/"))
//        data.add(TvData("陕西卫视", R.drawable.shanxi3, "http://223.110.241.204:6610/gitv/live1/G_CCTV-15/G_CCTV-15/"))
        data.add(TvData("天津卫视", R.drawable.tianjin, "http://223.110.241.204:6610/gitv/live1/G_TIANJIN-HQ/G_TIANJIN-HQ/"))
        data.add(TvData("浙江卫视", R.drawable.zhejiang, "http://223.110.241.204:6610/gitv/live1/G_ZHEJIANG-HQ/G_ZHEJIANG-HQ/"))


        adapter.setData(data)

    }
}
