package icesi.edu.co.mercatero_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import icesi.edu.co.mercatero_app.databinding.ActivityMainBinding
import utils.AppPreferences.getUserType
import utils.Constants.KEY_CONSUMER

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var currentFragment:Int=R.id.splashFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNav()

    }

    private fun setupNav() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frag_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        val popupMenu = android.widget.PopupMenu(this,null)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.bottom_menu, popupMenu.menu)

        binding.bottomBar.setupWithNavController(popupMenu.menu,navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            currentFragment = destination.id

            when (destination.id) {
                R.id.dashboardFragment -> showBottomNav()
                R.id.storeDashboardFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                R.id.myOrdersFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

        binding.bottomBar.onItemSelected = {
            updateFragment(it)
        }
    }

    private fun showBottomNav() {
        binding.bottomBar.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomBar.visibility = View.GONE
    }

    private fun updateFragment(pos:Int){
        when(pos){
            0 ->{
                if(getUserType().equals(KEY_CONSUMER)) {
                    navigate(R.id.dashboardFragment)
                }else{
                    navigate(R.id.storeDashboardFragment)
                }
            }
            1 ->{ navigate(R.id.myOrdersFragment) }
            2 ->{ navigate(R.id.profileFragment) }
            //3 ->{ navigate(R.id.loginFragment)}
        }
    }

    private fun navigate(id: Int){

        val navOptions= NavOptions.Builder().setPopUpTo(currentFragment,true).build()
        navController.navigate(id,null,navOptions)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}