package com.itenf.jsonapplicationcopy.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itenf.jsonapplication.R;

import java.util.ArrayList;

public class DogListAdapter extends RecyclerView.Adapter<RecyclerViewHolderDog> {

    private ArrayList<StringBuffer> hondenLijst;
    private final LayoutInflater mInflater;

    //Constructor
    public DogListAdapter(Context context){
      mInflater = LayoutInflater.from(context);
    }

    //to get the layout made for the recycler item:
    @Override
    public int getItemViewType(final int position){
        return  R.layout.recyclerview_item_dog;
    }

    @NonNull
    @Override
    public RecyclerViewHolderDog onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_dog, parent, false);
        return new RecyclerViewHolderDog(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderDog holder, int position){
        StringBuffer hond = hondenLijst.get(position);
        holder.getView().setText(hond);
    }


    @Override
    public int getItemCount(){
        if( hondenLijst != null){
            return hondenLijst.size();
        }else return 0;
    }

    public void setHondenLijst(ArrayList<StringBuffer> lijstMetHonden){
        hondenLijst = lijstMetHonden;
        notifyDataSetChanged();
    }

}
