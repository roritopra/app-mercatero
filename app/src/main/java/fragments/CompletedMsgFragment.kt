package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentCompletedMsgBinding
import utils.AppPreferences.getUserType
import utils.Constants
import utils.Constants.KEY_ORDER


class CompletedMsgFragment : BaseFragment() {

    val args:CompletedMsgFragmentArgs by navArgs()
    lateinit var binding: FragmentCompletedMsgBinding
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed_msg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCompletedMsgBinding.bind(view)
        navController=findNavController()

        binding.okBtn.setOnClickListener { navigate() }

        if(args.msgType == KEY_ORDER){
            binding.msg.text="Has realizado tu pedido"
            binding.okBtn.text="Continuar"
        }

    }



    private fun navigate(){
        if(args.msgType== KEY_ORDER){ navController.navigate(CompletedMsgFragmentDirections
            .navToTrackOrder()) }
        else {navController.navigate(CompletedMsgFragmentDirections
            .navToSetLocation()) }
    }

}
