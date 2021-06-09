package com.example.exmp1;

import android.content.Context;
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
import com.example.exmp1.db.DbOrderData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapterForArchive extends RecyclerView.Adapter<CustomAdapterForArchive.ArchiveViewHolder>  {

    private final Context context;
    private final ArrayList<DbOrderData> displayData;

    CustomAdapterForArchive(Context context, ArrayList<DbOrderData> displayData) {
        this.context = context;
        this.displayData = displayData;
    }

    @NonNull
    @NotNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_for_archive,parent,false);
        return new ArchiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArchiveViewHolder holder, int position) {
        holder.product_txt.setText(displayData.get(position).getProductData());
        holder.group_txt.setText(displayData.get(position).getGroupData());
        holder.quantity_txt.setText(displayData.get(position).getQuantityData());
        holder.units_txt.setText(displayData.get(position).getUnitsData());
        holder.totalSellingPrice_txt.setText(displayData.get(position).getSellingPriceData());
        holder.date_txt.setText(displayData.get(position).getSellingDate());
    }

    @Override
    public int getItemCount() {
        return displayData.size();
    }

    public class ArchiveViewHolder extends RecyclerView.ViewHolder {

        TextView product_txt, quantity_txt, units_txt, totalSellingPrice_txt, group_txt, date_txt;
        LinearLayout mainLayout;

        public ArchiveViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            product_txt = itemView.findViewById(R.id.tvProductNameArchive);
            group_txt = itemView.findViewById(R.id.tvGroupNameArchive);
            quantity_txt = itemView.findViewById(R.id.tvQuantityArchive);
            units_txt = itemView.findViewById(R.id.tvUnitsArchive);
            date_txt = itemView.findViewById(R.id.tvSellingDateDataArchive);
            mainLayout = itemView.findViewById(R.id.layoutArchive);
            totalSellingPrice_txt = itemView.findViewById(R.id.tvTotalSellingPriceArchive);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
