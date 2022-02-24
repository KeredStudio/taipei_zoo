package reynold.project.taipeizoo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import reynold.project.taipeizoo.databinding.ListItemPlantDetailBinding
import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.util.executeAfter

class PlantDetailListAdapter : ListAdapter<PlantDetailItem, PlantDetailListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ListItemPlantDetailBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: ListItemPlantDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlantDetailItem) {
            binding.executeAfter {
                plantDetailItem = item
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<PlantDetailItem>() {
        override fun areContentsTheSame(
            oldItem: PlantDetailItem, newItem: PlantDetailItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: PlantDetailItem, newItem: PlantDetailItem): Boolean {
            return oldItem == newItem
        }
    }
}