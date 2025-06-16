package com.nutrifit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    public interface OnItemDeleteListener {
        void onDelete(int position);
    }

    private ArrayList<UserFoodItem> foodList;
    private OnItemDeleteListener deleteListener;

    public FoodListAdapter(ArrayList<UserFoodItem> foodList, OnItemDeleteListener listener) {
        this.foodList = foodList;
        this.deleteListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameText;
        TextView servingSizeText;
        ImageButton deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodNameText = itemView.findViewById(R.id.item_food_name);
            servingSizeText = itemView.findViewById(R.id.item_serving_size);
            deleteButton = itemView.findViewById(R.id.item_delete);
        }
    }

    @NonNull
    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.ViewHolder holder, int position) {
        UserFoodItem item = foodList.get(position);
        holder.foodNameText.setText(item.foodName);
        holder.servingSizeText.setText(item.servingSize + "g");

        holder.deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
