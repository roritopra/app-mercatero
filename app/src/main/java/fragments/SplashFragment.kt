package fragments


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentLoginBinding
import com.mercatero.app.databinding.FragmentSplashBinding
import java.util.logging.Handler


class SplashFragment : BaseFragment() {

    lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        android.os.Handler(Looper.getMainLooper()).postDelayed(Runnable {
            navigateToLogin()
        },3000)

    }



    private fun navigateToLogin(){
        findNavController().navigate(SplashFragmentDirections.navToLogin())
    }

}

