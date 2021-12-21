package com.itis.secondcourseitis.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itis.secondcourseitis.SpaceItemDecorator
import com.itis.secondcourseitis.databinding.FragmentThirdBinding
import com.itis.secondcourseitis.model.HouseRepository.houses
import com.itis.secondcourseitis.third.recycler.HouseAdapter

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding
    private lateinit var houseAdapter: HouseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        houseAdapter = HouseAdapter(houses, Glide.with(this))

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding) {
            rvHouses.run {
                adapter = houseAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }
}
