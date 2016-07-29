package com.example.raymour.tintswear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by raymour on 7/25/16.
 */
public class CPRecyclerViewAdapter extends RecyclerView.Adapter<CPViewHolder> {

    List<Inventory> mCustomProductList;


    public CPRecyclerViewAdapter (final List<Inventory> customProductList){

        mCustomProductList = customProductList;
    }

    @Override
    public CPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customproduct, parent, false);
        CPViewHolder viewHolder = new CPViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CPViewHolder holder, final int position) {

        final Context context = holder.mImageView.getContext();

        int sunglassImageLocation = context.getResources().getIdentifier(mCustomProductList.get(position).getSunglassImageLocation(),"drawable",context.getPackageName());

        holder.mTitleView.setText(mCustomProductList.get(position).getSunglassName());
        holder.mImageView.setImageResource(sunglassImageLocation);
        holder.mPriceView.setText(mCustomProductList.get(position).getSunglassPrice());
        holder.mDescView.setText(mCustomProductList.get(position).getSunglassDescription());

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.buttonAddToCart:
                        Toast.makeText(view.getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(view.getContext(), "Click Add to Cart Button to make Purchase", Toast.LENGTH_SHORT).show();

                }
                DatabaseHelper helper = DatabaseHelper.getsInstance(context);
                helper.addToCart(mCustomProductList.get(position));
            }


        };
        holder.mButton.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return mCustomProductList.size();
    }
}
