package com.example.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class FavAdaptor extends RecyclerView.Adapter<FavAdaptor.FavHolder> {
    ArrayList<Fav> favArrayList;

    public FavAdaptor(ArrayList<Fav> favArrayList) {
        this.favArrayList = favArrayList;
    }


    @Override
    public FavHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FavHolder(binding);
    }

    @Override
    public void onBindViewHolder(FavHolder holder, int position) {
        holder.binding.recyclerViewTextViewName.setText(favArrayList.get(position).favName);
        holder.binding.recyclerViewTextViewPrice.setText(favArrayList.get(position).favPrice);
        holder.binding.recyclerViewTextViewQuantity.setText(favArrayList.get(position).favQuantity);

    }

    @Override
    public int getItemCount() {
        return favArrayList.size();
    }

    public class FavHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;
        public FavHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
