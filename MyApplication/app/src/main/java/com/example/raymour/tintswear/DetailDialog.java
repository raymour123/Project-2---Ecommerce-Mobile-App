package com.example.raymour.tintswear;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by raymour on 8/22/16.
 */
public class DetailDialog {

    public TextView sunglassNameDetail, sunglassPriceDetail, sunglassDescriptionDetail;
    public ImageView sunglassPicDetail;

    //sets up Detail Dialog box to be called in various activities
    public void launchDetailDialog(final Context context, final int position, final List<Inventory> sunglassList){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.detailed_dialog,null);
        builder.setView(dialogLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();
        ImageButton button = (ImageButton) dialogLayout.findViewById(R.id.button_cancel_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        FloatingActionButton addToCart = (FloatingActionButton) dialogLayout.findViewById(R.id.fab_detail_dialog);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = DatabaseHelper.getsInstance(context);
                Inventory inventory = sunglassList.get(position);

                helper.addToCart(inventory);
                Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }
        });

        sunglassNameDetail = (TextView) dialog.findViewById(R.id.textview_sunglass_name_detail);
        sunglassPriceDetail = (TextView) dialog.findViewById(R.id.textview_sunglass_price);
        sunglassDescriptionDetail = (TextView) dialog.findViewById(R.id.textview_description_detail);
        sunglassPicDetail = (ImageView) dialog.findViewById(R.id.imageview_dialog_detail);

        setDetailDialogInfo(sunglassList.get(position));


    }

    //links the dialog layout to the specific data being called
    public void setDetailDialogInfo(Inventory inventory){
        sunglassNameDetail.setText(inventory.getSunglassName());
        sunglassPriceDetail.setText(inventory.getSunglassPrice());
        sunglassDescriptionDetail.setText(inventory.getSunglassDescription());
        //sunglassPicDetail.setImageResource(sunglassImageLocation);
    }

}
