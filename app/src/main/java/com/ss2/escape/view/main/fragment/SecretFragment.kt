package com.ss2.escape.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss2.escape.R
import com.ss2.escape.adapter.MainItemAdapter
import com.ss2.escape.model.RecyclerItem
import com.ss2.escape.model.SecretData
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import com.ss2.escape.view.detail.DetailPNoActivity
import com.ss2.escape.view.detail.DetailSecretActivity
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
        recycle_Secret.layoutManager = gridLayoutManager as RecyclerView.LayoutManager?

        recyclerItemList = ArrayList()

        for (i in 0 until RealmDB.readSecretCount()) {
            val data: SecretData? = RealmDB.readSecretData(i)
            val recyclerItem1 = RecyclerItem(data!!.s_no+"", R.drawable.ic_launcher_foreground)
            recyclerItemList.add(recyclerItem1)
        }

        adapterMain = MainItemAdapter(recyclerItemList as ArrayList<RecyclerItem>, context, this)
        recycle_Secret.adapter = adapterMain
    }

    override fun itemClick(arg: String) {

    }

    override fun itemClick(position: Int) {
        SLog.d("ViewPosition = " + position)
        val nextIntent = Intent(context, DetailSecretActivity::class.java)
        nextIntent.putExtra("DetailSno", position)
        startActivity(nextIntent)
    }
}