package com.example.customdrawablenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.customdrawablenavigation.adapter.PagerAdapter
import com.example.customdrawablenavigation.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener{
    private lateinit var mDrawerLayout: DrawerLayout
    private val tabTitles = listOf<String>("Frag1","Frag2","Frag3")
    private lateinit var binding:ActivityMainBinding
    private lateinit var navView : NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TabLayout & ViewPager

        binding.vpMain.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabMain,binding.vpMain){ tab,index ->
            tab.text = tabTitles[index]
        }.attach()

        // ToolBar & Navigator
        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.nav_open,R.string.nav_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        binding.tabMain.addOnTabSelectedListener(this)
    }


    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }


    //Dong do tab va navigator
    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(binding.tabMain.selectedTabPosition){
            0-> navView.setCheckedItem(R.id.nav_frag1)
            1-> navView.setCheckedItem(R.id.nav_frag2)
            2-> navView.setCheckedItem(R.id.nav_frag3)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        when(binding.tabMain.selectedTabPosition){
            0-> navView.setCheckedItem(R.id.nav_frag1)
            1-> navView.setCheckedItem(R.id.nav_frag2)
            2-> navView.setCheckedItem(R.id.nav_frag3)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.nav_frag1 ->{
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                binding.tabMain.getTabAt(0)?.select()
            }
            R.id.nav_frag2 -> {
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
                binding.tabMain.getTabAt(1)?.select()
            }
            R.id.nav_frag3 -> {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                binding.tabMain.getTabAt(2)?.select()
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}