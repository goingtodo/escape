package com.example.escape

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bottomNavigation:BottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }
    val navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var fm = supportFragmentManager

                when (item.getItemId()) {
                    R.id.pnoItem-> {
                        fm.replaceFragment(R.id.frameLayout, PnoFragment())
                        Log.d("main","PNO Touch");
                        return true
                    }
                    R.id.secretItem -> {
                        fm.replaceFragment(R.id.frameLayout, SecretFragment())
                        Log.d("main","Secret Touch");
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
