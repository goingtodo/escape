package com.ss2.escape.view.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ss2.escape.R
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import kotlinx.android.synthetic.main.activity_detailpno.*

class DetailSecretActivity : AppCompatActivity() {

    private var s_position: Int = 0
    private var data: StoryData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_s)
        if(intent.hasExtra("DetailPno")){
            s_position = intent.getIntExtra("DetailPno", 0)
        }else{
            Toast.makeText(this, "전달된 포지션이 없음", Toast.LENGTH_SHORT).show();
        }

        data = RealmDB.readStoryData(s_position);

        if(data != null){
            tv_DetailPno_Title.text = data!!.p_No
            tv_DetailPno_MainStory.text = data!!.mainStory
            tv_DetailPno_Directive.text = data!!.directive
            if(data!!.directive.indexOf('P') != -1){
                var nextPno = data!!.directive.substring(data!!.directive.indexOf('P'),data!!.directive.indexOf('P')+4)
                tv_DetailPno_Next.text =  nextPno + " GO";
                tv_DetailPno_Next.setOnClickListener {
                    var nextIntent = Intent(this, DetailPNoActivity::class.java);
                    SLog.d("Next : " + nextPno.substring(1,nextPno.length));
                    nextIntent.putExtra("DetailPno", nextPno.substring(1,nextPno.length).toInt() - 1)
                    startActivity(nextIntent)
                }
            }

        }
    }

}