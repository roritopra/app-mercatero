package fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentLoginBinding
import utils.AppPreferences.getUserType
import utils.AppPreferences.setUserId
import utils.AppPreferences.setUserType
import utils.Constants
import utils.Constants.COLLECTION_USERS


class LoginFragment : BaseFragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var navController: NavController
    lateinit var db:FirebaseFirestore
    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController=findNavController()
        auth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            navigate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        db= FirebaseFirestore.getInstance()


        initViews()

    }


    private fun initViews() {
        binding.signupBtn.setOnClickListener {
            navController.navigate(LoginFragmentDirections.navToUserType())
        }
        binding.loginBtn.setOnClickListener {
            val email=binding.emailBox.text.toString().trim()
            val password=binding.passwordBox.text.toString().trim()

            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(requireContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            login(email,password)
        }
    }


    private fun login(email:String,password:String) {

        auth.signInWithEmailAndPassword(email , password).addOnSuccessListener {

            auth.currentUser?.uid?.let {
                context?.setUserId(it)
                getUser(it)
            }

            Toast.makeText(requireContext(),"¡Registrado!",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
        }
    }

    private fun getUser(id:String){
        db.collection(COLLECTION_USERS).whereEqualTo("id",id).get().addOnSuccessListener {
            context?.setUserType(it.documents[0]?.data?.get("userType").toString())
            Log.v("Login",context?.getUserType().toString())
            navigate()

        }.addOnFailureListener {
            Log.v("Login",it.message.toString())
        }
    }


    private fun navigate(){
        if(context?.getUserType()== Constants.KEY_CONSUMER) {
            navController.navigate(LoginFragmentDirections.navToDashboard())
        }
        else if(context?.getUserType()== Constants.KEY_STORE) {
            navController.navigate(LoginFragmentDirections.navToStoreDashboard())
        }
    }



}

