package com.bibaswan.wipro_telstra.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bibaswan.wipro_telstra.R
import com.bibaswan.wipro_telstra.databinding.ActivityMainBinding
import com.bibaswan.wipro_telstra.ui.canadaInfo.CanadaInfoViewModel
import com.bibaswan.wipro_telstra.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KProperty


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var viewModel: CanadaInfoViewModel by viewModels()
    lateinit var navController: NavController;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController= navHostFragment.navController

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.infos.observe(this, Observer {
            if(it.status == Resource.Status.SUCCESS){
                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    if(!it.data?.title.isNullOrEmpty())
                    toolbar.title = it.data?.title ?: this.getString(R.string.loading)

                }
            }
        })


    }


}

private operator fun Any.setValue(mainActivity: MainActivity, property: KProperty<*>, canadaInfoViewModel: CanadaInfoViewModel) {

}
