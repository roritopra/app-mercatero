package fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentProfileBinding
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDetailBinding
import models.UserModel
import utils.AppPreferences.getUserId
import utils.AppPreferences.getUserType
import utils.AppPreferences.setUserType
import utils.Constants


class ProfileFragment : BaseFragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var auth: FirebaseAuth
    lateinit var db:FirebaseFirestore
    var user:UserModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        binding.signoutBtn.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.navToLogin())
        }

        binding.about.setOnClickListener {
            user?.let {
                findNavController().navigate(ProfileFragmentDirections.navToEdit(it))
            }

        }

        context?.getUserId()?.let { getUser(it) }


    }

    private fun getUser(id:String){
        db.collection(Constants.COLLECTION_USERS).whereEqualTo("id",id).get().addOnSuccessListener {
            user = it.documents[0]?.toObject(UserModel::class.java)
            user?.let {
                binding.email.text=user?.email
                binding.name.text=user?.name
                binding.des.text=user?.des
                context?.let { it1 ->
                    if (user != null&&user!!.img.isNotEmpty()) {
                        Glide.with(it1).load(user!!.img).into(binding.profileImg)
                    }
                }
            }


        }.addOnFailureListener {
            Log.v("Profile",it.message.toString())
        }
    }

}
