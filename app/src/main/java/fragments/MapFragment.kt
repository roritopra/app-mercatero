package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentMapBinding
import utils.AppPreferences.getUserType
import utils.Constants


class MapFragment : BaseFragment() {

    lateinit var binding: FragmentMapBinding
    lateinit var navController: NavController





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        navController=findNavController()
        binding.okBtn.setOnClickListener { navigate() }

    }



    private fun navigate(){
        if(context?.getUserType()== Constants.KEY_CONSUMER) {
            navController.navigate(MapFragmentDirections.navToDashboard())
        }
        else if(context?.getUserType()== Constants.KEY_STORE) {
            navController.navigate(MapFragmentDirections.navToStoreDashboard())
        }
    }

}

