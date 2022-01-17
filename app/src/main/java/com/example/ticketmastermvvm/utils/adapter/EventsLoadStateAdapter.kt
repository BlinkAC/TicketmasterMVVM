package com.example.ticketmastermvvm.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.databinding.NetworkStateLoadingBinding

class EventsLoadStateAdapter(private val retry: () -> Unit):
LoadStateAdapter<EventsLoadStateAdapter.StateViewHolder>(){


    class StateViewHolder( private val binding: NetworkStateLoadingBinding, retry: () -> Unit)
        : RecyclerView.ViewHolder(binding.root){

        init{
            binding.retryButton.setOnClickListener { retry()  }
        }

        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage


            }


            nuprogressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = StateViewHolder(
        NetworkStateLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}