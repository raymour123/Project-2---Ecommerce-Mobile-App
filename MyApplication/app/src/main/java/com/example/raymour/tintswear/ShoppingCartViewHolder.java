package com.example.raymour.tintswear;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raymour on 7/28/16.
 */
public class ShoppingCartViewHolder extends RecyclerView.ViewHolder{

    public TextView mNameViewSC;
    public TextView mPriceViewSC;
    public Button mButtonRemoveSC;
//    public ImageView mImageViewSC;

    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        mNameViewSC = (TextView) itemView.findViewById(R.id.shoppingCartItemName);
        mPriceViewSC = (TextView) itemView.findViewById(R.id.shoppingCartItemPrice);
        mButtonRemoveSC = (Button) itemView.findViewById(R.id.itemRemoveButton);
//        mImageViewSC = itemView.findViewById(R.id.shoppingCartItemImage);
    }
}
