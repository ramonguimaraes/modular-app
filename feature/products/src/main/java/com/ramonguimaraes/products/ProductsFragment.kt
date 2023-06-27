package com.ramonguimaraes.products

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
 import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ramonguimaraes.products.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private val binding by lazy {
        FragmentProductsBinding.inflate(layoutInflater)
    }

    private val adapter = ProductsAdapter(mockList())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        configureRv()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Type here to search"
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapter.getFilter().filter(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.getFilter().filter(p0)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun configureRv() {
        binding.rvProducts.adapter = adapter
    }

    private fun mockList() = arrayListOf(
        Product(0L, "Teclado Logitech", "R$ 200,99"),
        Product(1L, "Mouse Logitech", "R$ 200,99"),
        Product(2L, "Controle Logitech", "R$ 200,99"),
        Product(3L, "Headset Logitech", "R$ 200,99"),
        Product(4L, "Microfone Logitech", "R$ 200,99"),
        Product(5L, "Pendrive Logitech", "R$ 200,99")
    )
}
