package com.example.raymour.tintswear;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raymour on 7/25/16.
 */
public class CPViewHolder extends RecyclerView.ViewHolder{

    public TextView mTitleView;
    public ImageView mImageView;
    public TextView mPriceView;
    public TextView mDescView;
    public Button mButton;


    public CPViewHolder(View itemView) {
        super(itemView);

        mTitleView = (TextView) itemView.findViewById(R.id.productName);
        mImageView = (ImageView) itemView.findViewById(R.id.productImage);
        mPriceView = (TextView) itemView.findViewById(R.id.productPrice);
        mDescView = (TextView) itemView.findViewById(R.id.productDesc);
        mButton = (Button) itemView.findViewById(R.id.buttonAddToCart);
    }
}
