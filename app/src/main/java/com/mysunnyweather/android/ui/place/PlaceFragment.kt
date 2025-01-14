package com.mysunnyweather.android.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mysunnyweather.android.MySunnyWeatherApplication
import com.mysunnyweather.android.databinding.PlaceFragmentBinding

class PlaceFragment: Fragment() {
   private lateinit var binding:PlaceFragmentBinding
   private lateinit var adapter: PlaceAdapter
   private val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlaceFragmentBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        val adapter = PlaceAdapter(this,viewModel.placeList)
        binding.placesRecyclerview.layoutManager = layoutManager
        binding.placesRecyclerview.adapter = adapter
        binding.searchPlaceEdit.addTextChangedListener{
            editable->
            val content = editable.toString()
            if(content.isNotEmpty()){
                viewModel.searchPlace(content)
            }else{
                binding.bgImageview.visibility = View.VISIBLE
                binding.placesRecyclerview.visibility = View.GONE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }


        }
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer{ result ->
            val places = result.getOrNull()
            if(places != null){
                binding.bgImageview.visibility = View.GONE
                binding.placesRecyclerview.visibility = View.VISIBLE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"搜索不到城市信息",Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }


}


