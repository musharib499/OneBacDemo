package com.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.R
import com.app.data.model.BookedOrder
import com.app.databinding.HistoryFragmentBinding
import com.app.databinding.ItemOrderBinding
import com.app.ui.base.BaseAdapterBinding
import com.app.ui.viewModule.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() , BaseAdapterBinding.BindAdapterListener {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private  val viewModel: HistoryViewModel by viewModels()
    private lateinit var binding:HistoryFragmentBinding
    private val adap by lazy { BaseAdapterBinding<BookedOrder>(this@HistoryFragment.requireContext(), arrayListOf(),this@HistoryFragment, R.layout.item_order) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoryFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HistoryFragment
            adapter = adap
        }
        init()
        return binding.root
    }
    fun init(){
        viewModel.loadData(viewLifecycleOwner)
        viewModel.item.observe(viewLifecycleOwner,{
            adap.setData(it)
            if (it.size>0) binding.noData.visibility = View.GONE
        })
    }



    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
        with(holder.binding){
            if (this is ItemOrderBinding){
                vm = viewModel
                pos = position
            }
        }

    }

}