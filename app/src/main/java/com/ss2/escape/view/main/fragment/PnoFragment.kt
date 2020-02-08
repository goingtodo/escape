package com.ss2.escape.view.fragment

import android.content.Context
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
    private var level = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pno, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null) {
            level = this.arguments!!.getInt("Level", 0)
        }
        SLog.d("Level : " + level)

        recycle_Pno.layoutManager = gridLayoutManager
        var dataList = RealmDB.readLevelStroyData(level)
        recyclerItemList = ArrayList()

        for (i in dataList!!) {
            val recyclerItem1 = RecyclerItem(i.p_No!!, R.drawable.ic_launcher_foreground)
            recyclerItemList.add(recyclerItem1)
        }
        adapterMain = MainItemAdapter(recyclerItemList as ArrayList<RecyclerItem>, context, this)
        recycle_Pno.adapter = adapterMain
    }


    override fun itemClick(arg: String) {
        val nextIntent = Intent(context, DetailPNoActivity::class.java)
        nextIntent.putExtra("DetailPno", arg)
        startActivity(nextIntent)
    }

    companion object{
        fun newInstacne(level:Int): PnoFragment {
            val fragment = PnoFragment()
            val args = Bundle()
            args.putInt("Level", level)
            fragment.arguments = args
            return fragment
        }
    }

}


