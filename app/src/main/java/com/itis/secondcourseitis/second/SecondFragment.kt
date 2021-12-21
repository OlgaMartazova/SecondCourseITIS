package com.itis.secondcourseitis.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itis.secondcourseitis.SpaceItemDecorator
import com.itis.secondcourseitis.databinding.FragmentSecondBinding
import com.itis.secondcourseitis.model.Window
import com.itis.secondcourseitis.model.WindowRepository.windows
import com.itis.secondcourseitis.second.recycler.WindowListAdapter

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var windowListAdapter: WindowListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        windowListAdapter = WindowListAdapter(Glide.with(this)) {
            windows.remove(it)
            windowListAdapter.submitList(windows)
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(windowListAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rvWindows)

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding) {
            rvWindows.run {
                adapter = windowListAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            fabRefresh.setOnClickListener {
                showDialog()
            }
        }
        windowListAdapter.submitList(windows)
    }

    private var title:String? = null
    private var desc:String? = null
    private var pos:String? = null

    private fun showDialog() {
        WindowDialog.show(
            childFragmentManager,
            positive = {
                title = it[0]
                desc = it[1]
                pos = it[2]
                updateData()
            }
        )
    }

    private fun updateData() {
        if (pos.equals("")) {
            addItem(windows.size)
        }
        else {
            pos?.also { pos->
                val posInt = Integer.parseInt(pos)
                if (posInt<0 || posInt>=windows.size)
                    addItem(windows.size)
                else
                    addItem(posInt)
            }
        }
        windowListAdapter.submitList(windows)
    }

    private fun addItem(position: Int) {
        val defaultWindow = "https://upload.wikimedia.org/wikipedia/ru/thumb/5/54/Microsoft_Windows_XP_Logo.svg/1200px-Microsoft_Windows_XP_Logo.svg.png"
        title?.let { title ->
            desc?.let { desc ->
                windows.add(position, Window(title, desc, defaultWindow))
            }
        }
    }
}
