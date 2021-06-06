package com.example.exmp1.db;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exmp1.R;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private final ArrayList<DbData> displayData;

    CustomAdapter(Activity activity, Context context, ArrayList<DbData> displayData)
    {
        this.activity = activity;
        this.context = context;
        this.displayData = displayData;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.product_txt.setText(displayData.get(position).getProductData());
        holder.group_txt.setText(displayData.get(position).getGroupData());
        holder.quantity_txt.setText(displayData.get(position).getQuantityData());
        holder.units_txt.setText(displayData.get(position).getUnitsData());
        holder.price_txt.setText(displayData.get(position).getPriceData());
        holder.charge_txt.setText(displayData.get(position).getExtraChargeData());
        holder.totalBasicPrice_txt.setText(displayData.get(position).getTotalBasicPriceData());
        holder.totalSellingPrice_txt.setText(displayData.get(position).getTotalSellingPriceData());
        holder.sellingPrice_txt.setText(displayData.get(position).getSellingPriceData());
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateItemActivity.class);
            intent.putExtra("id", displayData.get(position).getProductId());
            intent.putExtra("product", displayData.get(position).getProductData());
            intent.putExtra("group", displayData.get(position).getGroupData());
            intent.putExtra("quantity", displayData.get(position).getQuantityData());
            intent.putExtra("units", displayData.get(position).getUnitsData());
            intent.putExtra("price", displayData.get(position).getPriceData());
            intent.putExtra("charge", displayData.get(position).getExtraChargeData());
            activity.startActivityForResult(intent,1);

        });
    }

    @Override
    public int getItemCount() {
        return displayData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_txt, group_txt, quantity_txt, units_txt, price_txt, charge_txt, sellingPrice_txt, totalSellingPrice_txt,totalBasicPrice_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            product_txt = itemView.findViewById(R.id.tvProductName);
            group_txt = itemView.findViewById(R.id.tvGroupName);
            quantity_txt = itemView.findViewById(R.id.tvQuantity);
            units_txt = itemView.findViewById(R.id.tvUnits);
            price_txt = itemView.findViewById(R.id.tvPrice);
            charge_txt = itemView.findViewById(R.id.tvCharge);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);

            sellingPrice_txt = itemView.findViewById(R.id.tvSellingPrice);
            totalSellingPrice_txt = itemView.findViewById(R.id.tvTotalSellingPrice);
            totalBasicPrice_txt = itemView.findViewById(R.id.tvTotalBasicPrice);

            mainLayout.setAnimation(translate_anim);
        }
    }
}
