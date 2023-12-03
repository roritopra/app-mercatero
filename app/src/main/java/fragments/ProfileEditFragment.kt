package fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentProfileBinding
import icesi.edu.co.mercatero_app.databinding.FragmentProfileEditBinding
import models.UserModel
import utils.AppPreferences.getUserId
import utils.AppPreferences.getUserType
import utils.Constants
import utils.Constants.KEY_CONSUMER
import utils.Constants.KEY_IMAGES
import utils.Constants.KEY_STORE
import utils.Helper.hasPermissionsStorage
import utils.Helper.requestStoragePermission
import java.io.IOException
import java.io.InputStream


class ProfileEditFragment : BaseFragment() {

    val args:ProfileEditFragmentArgs by navArgs()
    lateinit var binding: FragmentProfileEditBinding
    lateinit var auth: FirebaseAuth
    lateinit var db:FirebaseFirestore

    private var imageStream: InputStream?=null
    private lateinit var storageRef: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileEditBinding.bind(view)

        auth = FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        val storageDB= FirebaseStorage.getInstance()
        storageRef=storageDB.getReference(KEY_IMAGES).child(auth.currentUser?.uid!!)

        binding.backBtn.setOnClickListener { navUp() }
        binding.cancelBtn.setOnClickListener { navUp() }
        binding.okBtn.setOnClickListener {

        if(imageStream!=null) {uploadFile()}else { updateUser() }
        }

        initViews()

    }

    private fun initViews(){

        binding.nameBox.setText(args.user.name)
        if(context?.getUserType()== KEY_CONSUMER) {
            binding.surnameBox.visibility=View.VISIBLE
            binding.surNameTitle.visibility=View.VISIBLE
            binding.surnameBox.setText(args.user.surName)
        }
        binding.desBox.setText(args.user.des)

        binding.photoBtn.setOnClickListener {
            if(requireActivity().hasPermissionsStorage()){
                openGallery()
            }else{
                requireActivity().requestStoragePermission(storagePermissionsRequest)
            }
        }

    }

    private val storagePermissionsRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            openGallery()
        }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT //
        galleryLauncher.launch(Intent.createChooser(intent, "Select Image"))
    }

    private var galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onSelectFromGalleryResult(result.data)
        }
    }

    private fun onSelectFromGalleryResult(intent: Intent?) {
        if (intent != null) {
            try {
                if (intent.data != null) {
                    imageStream =  requireContext().contentResolver.openInputStream(intent.data!!)
                    binding.selectedFile.text=intent.data.toString()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadFile(){
        imageStream?.let {
            storageRef.putStream(it).addOnSuccessListener { task ->
                task.storage.downloadUrl.addOnSuccessListener {imageUri->
                    updateUser(imageUri.toString())
                }
            }
        }
    }

    private fun updateUser(img:String=""){

        val name=binding.nameBox.text.toString()
        val sureName=binding.surnameBox.text.toString()
        val des=binding.desBox.text.toString()


        if(name.isEmpty()
            ||sureName.isEmpty()||
            context?.getUserType()== KEY_CONSUMER
            &&des.isEmpty() ){

            Toast.makeText(requireContext(),"Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
            return
        }

        val updates: MutableMap<String, Any> = HashMap()
        updates["name"] = name
        updates["surName"] = sureName


        if(img.isNotEmpty()){updates["img"] = img}
        if(context?.getUserType()== KEY_CONSUMER){ updates["des"] = des }


        db.collection(Constants.COLLECTION_USERS).document(context?.getUserId().toString())
            .update(updates).addOnSuccessListener {
                navUp()
        }.addOnFailureListener {
            Log.v("Profile",it.message.toString())
        }
    }

    private fun navUp(){
        findNavController().navigateUp()
    }

}
