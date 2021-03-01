package com.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.R
import com.app.data.model.BookedOrder
import com.app.databinding.OrderFragmentBinding
import com.app.ui.viewModule.OrderViewModel
import com.app.utils.ORDEAR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    companion object {
        fun newInstance() = OrderFragment()
    }

    private val  viewModel: OrderViewModel by viewModels()
    private lateinit var binding: OrderFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = OrderFragmentBinding.inflate(inflater,container,false).apply {
            vm = viewModel
            lifecycleOwner = this@OrderFragment
            fragment = this@OrderFragment
        }
        viewModel._item.value = arguments?.getParcelable(ORDEAR) as? BookedOrder
        return binding.root
    }
   fun orderBook(){ viewModel._item.value?.let { it1 -> viewModel.addItem(it1) }
       Toast.makeText(requireContext(), resources.getString(R.string.order_place), Toast.LENGTH_SHORT).show()
       activity?.onBackPressed()
   }


}