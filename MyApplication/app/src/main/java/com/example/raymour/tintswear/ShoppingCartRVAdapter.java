package com.example.raymour.tintswear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raymour on 7/28/16.
 */
public class ShoppingCartRVAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {

    Context mContext;
    List<SunglassSale> sunglassSalesList = new ArrayList<>();

    public ShoppingCartRVAdapter(Context context, List<SunglassSale> sunglassSalesList) {
        mContext = context;
        this.sunglassSalesList = sunglassSalesList;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customshoppingcart, parent, false);
        ShoppingCartViewHolder viewHolder = new ShoppingCartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, int position) {
        SunglassSale item = sunglassSalesList.get(position);
        holder.mNameViewSC.setText(item.getSunglassSaleName());
        holder.mPriceViewSC.setText(item.getSunglassSalePrice());

        holder.mButtonRemoveSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeByPosition(holder.getAdapterPosition());
                Toast.makeText(mContext, "Removed Item from Cart", Toast.LENGTH_SHORT).show();


            }
        });


    }





    @Override
    public int getItemCount() {
        return sunglassSalesList.size();
    }

    public void removeByPosition(int position) {
//        DatabaseHelper.getsInstance(mContext).removeItemFromCart(sunglassSalesList.get(position));
        sunglassSalesList.remove(position);
        notifyItemRemoved(position);
    }
}
