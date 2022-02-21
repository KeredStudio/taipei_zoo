package reynold.project.taipeizoo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import reynold.project.taipeizoo.databinding.ListItemAreaBinding
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.mvp.AreaListFragmentPresenter
import reynold.project.taipeizoo.util.executeAfter
import reynold.project.taipeizoo.util.setSafeOnClickListener

class AreaListAdapter(private val areaListFragmentPresenter: AreaListFragmentPresenter) :
    ListAdapter<AreaList.Result.Detail, AreaListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ListItemAreaBinding.inflate(inflater, parent, false).apply {
            root.setSafeOnClickListener {
                areaListFragmentPresenter.onAreaClick(areaDetail!!)
            }
        })
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: ListItemAreaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AreaList.Result.Detail) {
            binding.executeAfter {
                areaDetail = item
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<AreaList.Result.Detail>() {
        override fun areContentsTheSame(
            oldItem: AreaList.Result.Detail, newItem: AreaList.Result.Detail
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: AreaList.Result.Detail, newItem: AreaList.Result.Detail): Boolean {
            return oldItem.id == newItem.id
        }
    }
}