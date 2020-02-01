package com.ss2.escape.view.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ss2.escape.R
import com.ss2.escape.util.SLog
import com.ss2.escape.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_selete.*

class SelectActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selete)

        tv_Selete_Tutorial.setOnClickListener(levelClickListener)
    }

    val levelClickListener = object : View.OnClickListener{
        override fun onClick(v: View?) {
            var intent = Intent(applicationContext, MainActivity::class.java)
            when (v!!.id) {
                R.id.tv_Selete_Tutorial -> intent.putExtra("Level", 0)
                R.id.tv_Selete_File1 -> intent.putExtra("Level", 1)
                R.id.tv_Selete_File2 -> intent.putExtra("Level", 2)
                R.id.tv_Selete_File3 -> intent.putExtra("Level", 3)
                else -> null
            }
            startActivity(intent)
        }

    }


}

