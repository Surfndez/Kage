package com.absinthe.kage.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.absinthe.kage.R
import com.absinthe.kage.manager.ActivityStackManager
import com.absinthe.kage.service.TCPService
import com.absinthe.kage.ui.main.MainActivity
import com.drakeet.multitype.ItemViewBinder
import timber.log.Timber


class ServiceRunningItemViewBinder : ItemViewBinder<ServiceRunningItem, ServiceRunningItemViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        Timber.d("onCreateViewHolder")
        return ViewHolder(inflater.inflate(R.layout.item_service_running_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ServiceRunningItem) {
        if (item.isServiceRunning) {
            holder.info.text = holder.itemView.context.getString(R.string.service_is_running)
            holder.tip.text = holder.itemView.context.getString(R.string.tap_to_stop_service)
            holder.icon.setImageResource(R.drawable.ic_done)

            holder.itemView.setOnClickListener {
                TCPService.stop(holder.itemView.context)

                val activity = ActivityStackManager.topActivity
                if (activity is MainActivity) {
                    activity.viewModel.isServiceRunning.value = false
                }
            }
        } else {
            holder.info.text = holder.itemView.context.getString(R.string.service_is_not_running)
            holder.tip.text = holder.itemView.context.getString(R.string.tap_to_start_service)
            holder.icon.setImageResource(R.drawable.ic_no)

            holder.itemView.setOnClickListener {
                TCPService.start(holder.itemView.context)

                val activity = ActivityStackManager.topActivity
                if (activity is MainActivity) {
                    activity.viewModel.isServiceRunning.value = true
                }
            }
        }

        holder.itemView.setOnTouchListener(HolderConstant.onTouchListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val info: TextView = itemView.findViewById(R.id.tv_info)
        val tip: TextView = itemView.findViewById(R.id.tv_tip)
        val icon: ImageView = itemView.findViewById(R.id.iv_icon)
    }
}