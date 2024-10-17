package com.example.meetus

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Znajdź Toolbar i ustaw go jako ActionBar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Znajdź DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Skonfiguruj ActionBarDrawerToggle (ikonę hamburgera)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,  // String do otwierania
            R.string.close_drawer  // String do zamykania
        )

        // Dodaj ActionBarDrawerToggle jako listener dla DrawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        // Synchronizuj stan ActionBarDrawerToggle
        actionBarDrawerToggle.syncState()

        // Obsługa kliknięć w NavigationView
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {

                    true
                }
                R.id.nav_debt -> {

                    true
                }
                R.id.nav_groups -> {
                    val intent = Intent(this, GroupsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_availability -> {

                    true
                }
                R.id.nav_places -> {

                    true
                }
                R.id.nav_sign_in -> {
                    // Przejście do ekranu logowania
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Upewnij się, że kliknięcie w ikonę hamburgera jest obsługiwane
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}



