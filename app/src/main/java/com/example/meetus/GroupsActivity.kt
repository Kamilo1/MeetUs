package com.example.meetus

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class GroupsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var recyclerView: RecyclerView
    private lateinit var groupsAdapter: GroupsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        // Znajdź Toolbar i ustaw go jako ActionBar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicjalizacja DrawerLayout i ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Inicjalizacja NavigationView i menu_groups
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setCheckedItem(R.id.nav_groups)

        // Pogrub tekst dla opcji "Groups" dynamicznie
        val menuItem: MenuItem = navigationView.menu.findItem(R.id.nav_groups)
        val spannableString = android.text.SpannableString(menuItem.title)
        spannableString.setSpan(android.text.style.StyleSpan(Typeface.BOLD), 0, spannableString.length, 0)
        spannableString.setSpan(RelativeSizeSpan(1.2f), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        menuItem.title = spannableString

        // Obsługa kliknięć w NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_debt -> true
                R.id.nav_availability -> true
                R.id.nav_places -> true
                else -> false
            }
        }

        // Znajdź RecyclerView i ustaw adapter
        recyclerView = findViewById(R.id.groups_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mockowana lista grup
        val groups = listOf(
            "Grupa A",
            "Grupa B",
            "Grupa C",
            "Grupa D",
            "Grupa E"
        )

        // Ustaw adapter
        groupsAdapter = GroupsAdapter(groups)
        recyclerView.adapter = groupsAdapter
    }

    // Ładowanie menu dla Toolbara
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_groups_toolbar, menu)
        val menuItem = menu?.findItem(R.id.action_add)
        menuItem?.icon?.setTint(ContextCompat.getColor(this, R.color.white))
        return true
    }

    // Obsługa kliknięć w ikonę hamburgera i ikonę "Add" w Toolbarze
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {  // Obsługa hamburgera (otwieranie szuflady)
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    true
                } else {
                    super.onOptionsItemSelected(item)
                }
            }
            R.id.action_add -> {
                val intent = Intent(this, AddNewGroupActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
