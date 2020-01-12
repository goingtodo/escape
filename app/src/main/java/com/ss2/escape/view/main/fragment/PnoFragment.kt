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
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import com.ss2.escape.view.detail.DetailPNoActivity
import kotlinx.android.synthetic.main.fragment_pno.*


//Pno View
//Pno에서 표현해야하는 것들은 여기서 표현해야함
class PnoFragment : MainItemAdapter.setOnRecyclerItemClickListener, Fragment() {
    private lateinit var recyclerItemList: MutableList<RecyclerItem>
    private val gridLayoutManager by lazy { GridLayoutManager(context,5)}
    private lateinit var adapterMain: MainItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pno, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycle_Pno.layoutManager = gridLayoutManager

        recyclerItemList = ArrayList()
        var s: String
        SLog.d(RealmDB.readStoryCount().toString());
        //Test Data
        for (i in 1 until RealmDB.readStoryCount() +1) {
            s = String.format("%03d", i);
            val recyclerItem1 = RecyclerItem("P-"+s, R.drawable.ic_launcher_foreground)
            recyclerItemList.add(recyclerItem1)
        }
        adapterMain = MainItemAdapter(recyclerItemList as ArrayList<RecyclerItem>, context, this)
        recycle_Pno.adapter = adapterMain
    }

    override fun itemClick(position: Int) {
        SLog.d("ViewPosition = " + position)
        val nextIntent = Intent(context, DetailPNoActivity::class.java)
        nextIntent.putExtra("DetailPno", position)
        startActivity(nextIntent)
    }

}


