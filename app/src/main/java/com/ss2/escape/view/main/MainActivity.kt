package com.ss2.escape.view.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ss2.escape.R
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
        }

        bottomNavigationInit()
    }

    fun bottomNavigationInit(){
        var bottomNavigation:BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        supportFragmentManager.replaceFragment(
            R.id.frameLayout,
            PnoFragment()
        )
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

                when (item.itemId) {
                    R.id.pnoItem -> {
                        var bundle = Bundle()
                        bundle.putInt("Level",level)
                        var fragment = PnoFragment()
                        fragment.arguments = bundle

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
