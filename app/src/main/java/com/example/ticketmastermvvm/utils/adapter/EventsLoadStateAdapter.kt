package com.example.ticketmastermvvm.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R

class EventsLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<EventsLoadStateAdapter.ViewHolder>(){

    class ViewHolder( view: View, retry: () -> Unit) : RecyclerView.ViewHolder(view){

        init{
            view.findViewById<Button>(R.id.retry_button).setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) = with(itemView) {
            if (loadState is LoadState.Error) {
                itemView.findViewById<TextView>(R.id.error_msg).text =loadState.error.localizedMessage

            }
            val progressBar = itemView.findViewById<ProgressBar>(R.id.nuprogress_bar)
            val retryButton = itemView.findViewById<Button>(R.id.retry_button)
            val errorMsg = itemView.findViewById<TextView>(R.id.error_msg)

            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_state, parent, false), retry
        )
    }
}