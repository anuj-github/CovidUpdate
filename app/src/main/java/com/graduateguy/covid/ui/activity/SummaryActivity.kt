package com.graduateguy.covid.ui.activity

import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.graduateguy.covid.R
import com.graduateguy.covid.databinding.SummaryGraphBinding
import com.graduateguy.covid.ui.fragment.CountryInfoFragment
import com.graduateguy.covid.ui.fragment.SummaryFragment
import com.graduateguy.covid.viewModel.SummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding : SummaryGraphBinding
    // Keep track of the fragments needed for Bottom Navigation View
    private val fragments = SparseArray<Fragment>()
    private val summaryViewModel : SummaryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigationMenu()
    }

    private  fun initNavigationMenu(){
        binding.navigationMenu.apply {
            setOnNavigationItemSelectedListener(navigationMenuListener)
            selectedItemId = R.id.summary_menu
        }
    }
    private val navigationMenuListener = BottomNavigationView.OnNavigationItemSelectedListener{
        item->
        when(val id = item.itemId){
            R.id.summary_menu, R.id.country_menu, R.id.update_menu ->{
                return@OnNavigationItemSelectedListener openFragment(id)
            }

            else -> return@OnNavigationItemSelectedListener false
        }
    }

    private fun openFragment(id: Int): Boolean {
        val fragment = getFragment(id)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                // .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }

    private fun getFragment(menuItemId: Int): Fragment? {
        var fragment = fragments[menuItemId]
        if (fragment == null) {
            fragment = when (menuItemId) {
                R.id.summary_menu -> SummaryFragment()
                R.id.country_menu -> CountryInfoFragment()
                R.id.update_menu -> SummaryFragment() // TODO Anuj
                else -> return null
            }
            fragments.put(menuItemId, fragment)
        }
        return fragment
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.summary_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh -> refreshData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshData(){
        summaryViewModel.refreshData()
    }



    companion object{
        private const val TAG = "LauncherActivity"
    }
}
