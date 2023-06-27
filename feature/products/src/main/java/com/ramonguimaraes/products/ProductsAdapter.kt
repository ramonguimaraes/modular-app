package com.ramonguimaraes.products

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.ramonguimaraes.products.databinding.ItemLayoutBinding

class ProductsAdapter(private var list: ArrayList<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {

    val initList = ArrayList<Product>().apply {
        addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filteredList: ArrayList<Product> = ArrayList()
            if (constraint.isNullOrEmpty()) {
                initList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase()
                initList.forEach { product ->

                    if (product.title.trim().lowercase().contains(query)) {
                        filteredList.add(product)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                list.clear()

                val producs = results.values as ArrayList<*>
                list.addAll(producs as ArrayList<Product>)
                notifyDataSetChanged()
            }
        }
    }
}

class ProductViewHolder(private val binding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Product) {
        binding.txtTitle.text = item.title
        binding.txtPrice.text = item.price
    }
}

data class Product(
    val id: Long,
    val title: String,
    val price: String
)
