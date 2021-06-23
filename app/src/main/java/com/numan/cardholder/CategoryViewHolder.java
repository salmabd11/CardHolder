package com.numan.cardholder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txName,txEmail,txPhone,txmainbuisness,txurl;
    public ImageView ProimageView,ProimageView1;


    @SuppressLint("SetJavaScriptEnabled")
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        txName = (TextView)itemView.findViewById(R.id.txName);
        txPhone = (TextView)itemView.findViewById(R.id.txPhone);
        txEmail = (TextView)itemView.findViewById(R.id.txEmail);
        txmainbuisness = (TextView)itemView.findViewById(R.id.txmainbuisness);
        txurl = (TextView)itemView.findViewById(R.id.txurl);
        ProimageView = (ImageView)itemView.findViewById(R.id.CImageView);
        ProimageView1 = (ImageView)itemView.findViewById(R.id.CImageView1);

    }

    @Override
    public void onClick(View v) {

    }
}
