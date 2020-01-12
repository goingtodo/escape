package com.ss2.escape.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ss2.escape.R
import com.ss2.escape.model.RecyclerItem

/* RecyclerView에 Item 추가 Adapter
   Item.xml을 추가하면서 어떤 오브젝트를 변경하고 설정할 것인지 Control
* */
class ItemAdapter(val list:ArrayList<RecyclerItem>, val context: Context?): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_rec, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private var img = itemView.findViewById<ImageView>(R.id.item_Image)
        private var txt = itemView.findViewById<TextView>(R.id.item_Date)
        private var layout = itemView.findViewById<LinearLayout>(R.id.item_layout)

        override fun onClick(v: View?) {
            Log.d("RecyclerView", adapterPosition.toString())
        }

        fun bind(item:RecyclerItem){
            //View에 데이터 변화
            img.setBackgroundResource(item.image)
            txt.setText(item.title)

            //클릭 이벤트
            layout.setOnClickListener {
                Log.d("RecyclerView", "Click!! : " + adapterPosition)
            }
        }
    }


}