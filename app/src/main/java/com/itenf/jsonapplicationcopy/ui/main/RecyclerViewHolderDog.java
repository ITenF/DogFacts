package com.itenf.jsonapplicationcopy.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.itenf.jsonapplication.R;

public class RecyclerViewHolderDog extends RecyclerView.ViewHolder {

    private TextView dogView;

    public RecyclerViewHolderDog(View itemview){
        super(itemview);
        dogView = itemview.findViewById(R.id.dog_item_textView);
    }


    public TextView getView(){
        return dogView;
    }
}
