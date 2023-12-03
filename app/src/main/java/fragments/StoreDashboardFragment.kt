package fragments


import adapters.StoreOrdersAdapter
import adapters.TabsAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import icesi.edu.co.mercatero_app.R
import icesi.edu.co.mercatero_app.databinding.FragmentStoreDashboardBinding



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
        binding.viewpager.offscreenPageLimit=1


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




}

