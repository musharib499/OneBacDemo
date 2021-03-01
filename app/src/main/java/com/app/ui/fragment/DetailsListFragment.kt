package com.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.R
import com.app.data.model.BookedOrder
import com.app.data.model.FoodMenu
import com.app.databinding.CuisionDetailsListFragmentBinding
import com.app.databinding.FavItemBinding
import com.app.databinding.ItemCuisionListBinding
import com.app.ui.AppNavigatorInterface
import com.app.ui.Command
import com.app.ui.base.BaseAdapterBinding
import com.app.ui.viewModule.DetailsListViewModel
import com.app.utils.DETAILS
import com.app.utils.ORDEAR
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsListFragment : Fragment(), BaseAdapterBinding.BindAdapterListener {

    companion object {
        fun newInstance() = DetailsListFragment()
    }

    private val viewModel: DetailsListViewModel by viewModels()
    private lateinit var binding: CuisionDetailsListFragmentBinding
    private val orderBook: HashMap<Int, FoodMenu.Category.Details.FoodItem?> by lazy { HashMap() }

    @Inject
    lateinit var navigatorInterface: AppNavigatorInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CuisionDetailsListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailsListFragment
            vm = viewModel
            fragment = this@DetailsListFragment
            adapter = BaseAdapterBinding<FoodMenu.Category.Details.FoodItem>(
                this@DetailsListFragment.requireContext(),
                arrayListOf(),
                this@DetailsListFragment,
                R.layout.item_cuision_list
            )

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val category = arguments?.getParcelable(DETAILS) as? FoodMenu.Category
        viewModel._list.value = category
        viewModel._itemlist.value = category?.details
        var ad = BaseAdapterBinding<FoodMenu.Category.Details.Famou>(
            requireContext(),
            arrayListOf(),
            object : BaseAdapterBinding.BindAdapterListener {
                override fun onBind(
                    holder: BaseAdapterBinding.DataBindingViewHolder,
                    position: Int
                ) {
                    if (holder.binding is FavItemBinding) {
                        holder.binding.vm = viewModel
                        holder.binding.pos = position
                    }

                }
            },
            R.layout.fav_item
        )
        binding?.adapterHeader = ad
        viewModel.itemlist.observe(viewLifecycleOwner, {
            it?.famous?.let { v -> ad.setData(v) }
        })


    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
        if (holder.binding is ItemCuisionListBinding) {
            with(holder.binding) {
                pos = position
                vm = viewModel
                var v = if (tvCount.text.toString().isNullOrEmpty()) 0 else tvCount.text.toString()
                    .toInt()
                btnAdd.setOnClickListener { tvCount.text = "${if (v < 20) ++v else v}" }
                btnMin.setOnClickListener { tvCount.text = "${if (v > 1) --v else 1}" }
                btnOrder.setOnClickListener {
                    val f = viewModel.itemlist.value?.foodItem?.get(position)
                    f?.count = v
                    orderBook.put(position, f)
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green))
                }
               btnRemove.setOnClickListener {
                   orderBook.remove(position)
                   btnOrder.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.purple_200))
               }
            }
        }
    }


    fun moveNext() {
        val i = orderBook.values.iterator()
        var bookedOrder = BookedOrder()
        while (i.hasNext()) {
            with(i.next()) {
                bookedOrder.amount += (this?.price ?: 0) * (this?.count ?: 0)
                bookedOrder.dishName += this?.dishName ?: "" +","
                bookedOrder.count += this?.count ?: 0
                bookedOrder.image = this?.image
            }

        }
        if (orderBook.size > 0) {
            with(bookedOrder){
                gstAmount = ((amount*2.5)/100).toFloat()
                payAmount = gstAmount + amount
                time = System.currentTimeMillis()
            }

            navigatorInterface.navigator(Command.ORDER, b = bundleOf(ORDEAR to bookedOrder))
        }else Toast.makeText(requireContext(), "Please Add Order", Toast.LENGTH_SHORT).show()
    }
}









