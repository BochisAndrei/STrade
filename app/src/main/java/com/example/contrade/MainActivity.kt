package com.example.contrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout : DrawerLayout
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    lateinit var navigationView : NavigationView
    lateinit var actionBarToggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instantiate drawer components
        setup_drawer()

        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(
                            R.id.container_fragment,
                            FragmentPieteTranzactionare()
                    )
                    .commit()
            navigationView.setCheckedItem(R.id.nav_piete_tranzactionare)
        }
    }

    private fun setup_drawer() {
        //set toolbar
        toolbar = findViewById(R.id.drawer_toolbar_toolbar)
        setSupportActionBar(toolbar)
        //set drawer_layout (activity_main layout)
        drawerLayout = findViewById(R.id.activity_main)

        //set drawer header
        var header = LayoutInflater.from(this).inflate(R.layout.drawer_header, null)

        //var loggedLayout = header.findViewById<View>(R.id.drawer_logged)

        //create navigationView's components
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this@MainActivity)
        actionBarToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar,
                R.string.open,
                R.string.close
        )
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.isDrawerSlideAnimationEnabled
        actionBarToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_piete_tranzactionare -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(
                                R.id.container_fragment,
                                FragmentPieteTranzactionare()
                        )
                        .commit()
            }
            R.id.nav_portofoliu -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(
                                R.id.container_fragment,
                                FragmentPortofoliu()
                        )
                        .commit()
            }
            R.id.nav_setari -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(
                                R.id.container_fragment,
                                FragmentSetari()
                        )
                        .commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun openLogin(view: View) {
        var loginLayoutIntent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(loginLayoutIntent)
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }
}