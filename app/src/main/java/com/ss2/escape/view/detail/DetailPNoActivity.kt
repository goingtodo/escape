package com.ss2.escape.view.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ss2.escape.R
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_detailpno.*

class DetailPNoActivity : AppCompatActivity() {
    private var pNo_position: Int = 0
    private var data: StoryData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpno)
        if(intent.hasExtra("DetailPno")){
            pNo_position = intent.getIntExtra("DetailPno", 0)
        }else{
            Toast.makeText(this, "전달된 포지션이 없음", Toast.LENGTH_SHORT).show();
        }

        data = RealmDB.readStoryData(pNo_position);
        if(data != null){
            tv_DetailPno_Title.text = data!!.p_No
            tv_DetailPno_MainStory.text = data!!.mainStory
            tv_DetailPno_Directive.text = data!!.directive
            SLog.d(data!!.directive.indexOf('P').toString())
        }
    }
}

