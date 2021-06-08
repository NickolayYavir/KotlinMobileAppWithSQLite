package com.example.exmp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exmp1.db.CustomAdapter;
import com.example.exmp1.db.DbData;
import com.example.exmp1.db.UpdateItemActivity;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class CustomAdapterForOrder extends RecyclerView.Adapter<CustomAdapterForOrder.OrderViewHolder> {

    private final Context context;
    private final Activity activity;
    private final ArrayList<DbData> displayData;

    CustomAdapterForOrder(Activity activity, Context context, ArrayList<DbData> displayData) {
        this.activity = activity;
        this.context = context;
        this.displayData = displayData;
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_order_row,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderViewHolder holder, int position) {
        holder.product_txt.setText(displayData.get(position).getProductData());
        holder.group_txt.setText(displayData.get(position).getGroupData());
        holder.quantity_txt.setText(displayData.get(position).getQuantityData());
        holder.units_txt.setText(displayData.get(position).getUnitsData());
        holder.sellingPrice_txt.setText(displayData.get(position).getSellingPriceData());
        holder.mainLayout.setOnClickListener(v -> {

            Intent intent = new Intent(context, OrderItemSelected.class);
            intent.putExtra("productData", displayData.get(position));
            activity.startActivityForResult(intent,1);
        });
    }

    @Override
    public int getItemCount() {
        return displayData.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView product_txt, group_txt, quantity_txt, units_txt,sellingPrice_txt;
        LinearLayout mainLayout;

        public OrderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            product_txt = itemView.findViewById(R.id.tvProductNameOrder);
            group_txt = itemView.findViewById(R.id.tvGroupNameOrder);
            quantity_txt = itemView.findViewById(R.id.tvQuantityOrder);
            units_txt = itemView.findViewById(R.id.tvUnitsOrder);
            mainLayout = itemView.findViewById(R.id.mainOrderLayout);
            sellingPrice_txt = itemView.findViewById(R.id.tvSellingPriceOrder);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
