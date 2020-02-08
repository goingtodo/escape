package com.ss2.escape.view.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ss2.escape.R
import com.ss2.escape.model.SecretData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import kotlinx.android.synthetic.main.activity_detail_s.*
import kotlinx.android.synthetic.main.activity_detailpno.*

class DetailSecretActivity : AppCompatActivity() {

    private var s_position: Int = 0
    private var data: SecretData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_s)
        if(intent.hasExtra("DetailSno")){
            s_position = intent.getIntExtra("DetailSno", 0)
        }else{
            Toast.makeText(this, "전달된 포지션이 없음", Toast.LENGTH_SHORT).show();
        }

        data = RealmDB.readSecretData(s_position);


        if(data != null){
            tv_Detail_s.text = data!!.s_no


        }
    }

}