package reynold.project.taipeizoo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import reynold.project.taipeizoo.databinding.ListItemPlantBinding
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.mvp.AreaDetailFragmentPresenter
import reynold.project.taipeizoo.util.executeAfter
import reynold.project.taipeizoo.util.setSafeOnClickListener

class PlantListAdapter(private val areaDetailFragmentPresenter: AreaDetailFragmentPresenter) :
    ListAdapter<PlantList.Result.Detail, PlantListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ListItemPlantBinding.inflate(inflater, parent, false).apply {
            root.setSafeOnClickListener {
                areaDetailFragmentPresenter.onPlantClick(plantDetail!!)
            }
        })
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: ListItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlantList.Result.Detail) {
            binding.executeAfter {
                plantDetail = item
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<PlantList.Result.Detail>() {
        override fun areContentsTheSame(
            oldItem: PlantList.Result.Detail, newItem: PlantList.Result.Detail
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: PlantList.Result.Detail, newItem: PlantList.Result.Detail): Boolean {
            return oldItem.id == newItem.id
        }
    }
}