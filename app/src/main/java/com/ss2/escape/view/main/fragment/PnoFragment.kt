package com.ss2.escape.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ss2.escape.R
import com.ss2.escape.adapter.ItemAdapter
import com.ss2.escape.model.RecyclerItem
import kotlinx.android.synthetic.main.fragment_pno.*

//Pno View
//Pno에서 표현해야하는 것들은 여기서 표현해야함
class PnoFragment : Fragment() {
    private lateinit var recyclerItemList: MutableList<RecyclerItem>
    private val gridLayoutManager by lazy { GridLayoutManager(context,5)}
    private lateinit var adapter: ItemAdapter

    private lateinit var selectedName: String

    companion object{
        fun newInstacne(): PnoFragment{
            return PnoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pno, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycle_Pno.layoutManager = gridLayoutManager

        recyclerItemList = ArrayList()

        //Test Data
        for (i in 0 until 20) {
            val recyclerItem1 = RecyclerItem("RecyclerFragment1 Test_$i", R.drawable.ic_launcher_background)
            recyclerItemList.add(recyclerItem1)
        }

        adapter = ItemAdapter(recyclerItemList as ArrayList<RecyclerItem>, context)
        recycle_Pno.adapter = adapter

    }
}

