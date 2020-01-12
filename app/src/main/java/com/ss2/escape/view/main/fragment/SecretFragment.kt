package com.ss2.escape.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ss2.escape.R
import com.ss2.escape.adapter.MainItemAdapter
import com.ss2.escape.model.RecyclerItem
import com.ss2.escape.util.SLog
import kotlinx.android.synthetic.main.fragment_secret.*
//Secret View
//수수께기에서 표현해야하는 것들은 여기서 표현해야함
class SecretFragment: MainItemAdapter.setOnRecyclerItemClickListener, Fragment() {
    private lateinit var recyclerItemList: MutableList<RecyclerItem>
    private val gridLayoutManager by lazy { GridLayoutManager(context,3) }
    private lateinit var adapterMain: MainItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_secret, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycle_Secret.layoutManager = gridLayoutManager

        recyclerItemList = ArrayList()

        //Test Data
        for (i in 0 until 20) {
            val recyclerItem1 = RecyclerItem("RecyclerFragment2 Test_$i", R.drawable.ic_launcher_background)
            recyclerItemList.add(recyclerItem1)
        }

        adapterMain = MainItemAdapter(recyclerItemList as ArrayList<RecyclerItem>, context, this)
        recycle_Secret.adapter = adapterMain
    }

    override fun itemClick(position: Int) {

    }
}