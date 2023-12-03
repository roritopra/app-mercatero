package fragments


import adapters.StoreOrdersAdapter
import adapters.TabsAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mercatero.app.R
import com.mercatero.app.databinding.FragmentStoreDashboardBinding
import models.OrderModel
import models.ProductModel
import models.UserModel
import utils.Constants


class StoreDashboardFragment : BaseFragment(){

    lateinit var binding: FragmentStoreDashboardBinding
    lateinit var adapter: TabsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreDashboardBinding.bind(view)

        adapter= TabsAdapter(this)
        binding.viewpager.adapter=adapter


        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text=when(position){
                0->"Por aceptar"
                1->"En preparación"
                2->"Por entregar"
                else->""
            }
        }.attach()


    }




}

