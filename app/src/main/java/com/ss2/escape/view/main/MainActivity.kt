package com.ss2.escape.view.main

import android.os.Bundle
import android.os.Debug
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ss2.escape.R
import com.ss2.escape.util.SLog
import com.ss2.escape.view.fragment.PnoFragment
import com.ss2.escape.view.fragment.SecretFragment

//Main View
//Bottom Tap과 Fragment를 보여주는 Activity
class MainActivity : AppCompatActivity() {
    var level : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(intent.hasExtra("Level")){
            level = intent.getIntExtra("Level", 0)
            SLog.d("LEVEL MAIN : " + level)
        }

        bottomNavigationInit()
    }

    fun bottomNavigationInit(){
        var bottomNavigation:BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        supportFragmentManager.replaceFragment(
            R.id.frameLayout,
            PnoFragment.newInstacne(level)
        )
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        SLog.d("BACK")
    }

    /*private fun setViewPager() {
            val mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
            mainActivityViewPager.adapter = mainViewPagerAdapter
            mainActivityTabLayout.setupWithViewPager(mainActivityViewPager)

            mainActivityButton.setOnClickListener {
                startActivity<SecondActivity>()
            }
        }
    */
    val navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var fm = supportFragmentManager
                SLog.d("Navi : " + level)
                when (item.itemId) {
                    R.id.pnoItem -> {
                        var fragment = PnoFragment.newInstacne(level)

                        fm.replaceFragment(
                            R.id.frameLayout,
                            fragment
                        )
                        return true
                    }
                    R.id.secretItem -> {
                        fm.replaceFragment(
                            R.id.frameLayout,
                            SecretFragment()
                        )
                        return true
                    }
                }
                return false
            }
        }
    fun openFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun FragmentManager.replaceFragment(containerId: Int, fragment: Fragment) {
        beginTransaction().replace(containerId, fragment).addToBackStack(null).commit()
    }
}
