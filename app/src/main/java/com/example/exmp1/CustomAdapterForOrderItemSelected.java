package com.example.exmp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exmp1.db.DbData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapterForOrderItemSelected extends RecyclerView.Adapter<CustomAdapterForOrderItemSelected.OrderItemViewHolder>  {

    private final Context context;
    private final ArrayList<DbData> displayData;

    CustomAdapterForOrderItemSelected(Context context, ArrayList<DbData> displayData) {
        this.context = context;
        this.displayData = displayData;
    }

    @NonNull
    @NotNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_for_item_selected,parent,false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderItemViewHolder holder, int position) {
        holder.product_txt.setText(displayData.get(position).getProductData());
        holder.quantity_txt.setText(displayData.get(position).getQuantityData());
        holder.units_txt.setText(displayData.get(position).getUnitsData());
        holder.sellingPrice_txt.setText(displayData.get(position).getSellingPriceData());
    }

    @Override
    public int getItemCount() {
        return displayData.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {

        TextView product_txt, quantity_txt, units_txt,sellingPrice_txt;
        LinearLayout mainLayout;

        public OrderItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            product_txt = itemView.findViewById(R.id.tvProductNameOrderItemSelected);
            quantity_txt = itemView.findViewById(R.id.tvQuantityOrderItemSelected);
            units_txt = itemView.findViewById(R.id.tvUnitsOrderItemSelected);
            mainLayout = itemView.findViewById(R.id.OrderItemSelectedLayout);
            sellingPrice_txt = itemView.findViewById(R.id.tvSellingPriceOrderItemSelected);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
