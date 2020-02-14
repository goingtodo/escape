package com.ss2.escape.view.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ss2.escape.R
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import kotlinx.android.synthetic.main.activity_detailpno.*
import kotlinx.android.synthetic.main.activity_detailpno.tv_DetailPno_Directive
import kotlinx.android.synthetic.main.activity_detailpno.tv_DetailPno_Next

class DetailPNoActivity : AppCompatActivity() {
    private lateinit var pNo_position: String
    private var data: StoryData? = null
    private lateinit var nextPno : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpno)
        if(intent.hasExtra("DetailPno")){
            pNo_position = intent.getStringExtra("DetailPno")
        }else{
            Toast.makeText(this, "전달된 포지션이 없음", Toast.LENGTH_SHORT).show();
        }
        init()
    }

    fun init(){
        data = RealmDB.readStoryData(pNo_position);
        if(data != null){
            viewData()
        }
        tv_DetailPno_Next.setOnClickListener {
            next(nextPno)
        }
    }

    fun next(pno: String){
        if(pno.equals("Main")){
            onBackPressed()
        }else{
            pNo_position = pno
            viewData()
        }
    }

    fun viewData(){
        data = RealmDB.readStoryData(pNo_position);
        tv_DetailPno_Title.text = data!!.p_No
        tv_DetailPno_MainStory.text = data!!.mainStory
        tv_DetailPno_Directive.text = data!!.directive
        if(data!!.directive.indexOf('P') != -1){
            nextPno = data!!.directive.substring(data!!.directive.indexOf('P'),data!!.directive.indexOf('P')+4)
            nextPno = nextPno.replace("P","P-")
            tv_DetailPno_Next.text =  nextPno + " GO";
        }else{
            tv_DetailPno_Next.text =  "Main Go";
            nextPno = "Main"
        }
        //스크롤 초기화
        sv_DetailPno.scrollTo(0,0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

