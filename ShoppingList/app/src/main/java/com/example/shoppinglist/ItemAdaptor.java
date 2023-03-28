package com.example.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.ItemHolder> {
    ArrayList<Item> itemArrayList;

    public ItemAdaptor(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.binding.recyclerViewTextViewName.setText(itemArrayList.get(position).name);
        holder.binding.recyclerViewTextViewPrice.setText(itemArrayList.get(position).price);
        holder.binding.recyclerViewTextViewQuantity.setText(itemArrayList.get(position).quantity);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public ItemHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
