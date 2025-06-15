package com.nutrifit_n;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserFoodSelectActivity extends AppCompatActivity {

    private String mealType;
    private EditText foodNameEditText, servingSizeEditText;
    private ArrayList<UserFoodItem> foodList;
    private FoodListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food_select);

        mealType = getIntent().getStringExtra("mealType");
        foodNameEditText = findViewById(R.id.foodNameEditText);
        servingSizeEditText = findViewById(R.id.servingSizeEditText);
        foodList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.foodRecyclerView);
        adapter = new FoodListAdapter(foodList, position -> {
            foodList.remove(position);
            adapter.notifyItemRemoved(position);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.addButton).setOnClickListener(v -> addFood());
        findViewById(R.id.saveButton).setOnClickListener(v -> saveAllFoods());
    }

    private void addFood() {
        String name = foodNameEditText.getText().toString().trim();
        String servingSizeStr = servingSizeEditText.getText().toString().trim();
        if (name.isEmpty() || servingSizeStr.isEmpty()) {
            Toast.makeText(this, "모든 값을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        float size = Float.parseFloat(servingSizeStr);
        foodList.add(new UserFoodItem(name, size));
        adapter.notifyItemInserted(foodList.size() - 1);

        foodNameEditText.setText("");
        servingSizeEditText.setText("");
    }

    private void saveAllFoods() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (UserFoodItem item : foodList) {
                JSONObject obj = new JSONObject();
                obj.put("foodName", item.foodName);
                obj.put("servingSize", item.servingSize);
                jsonArray.put(obj);
            }

            Intent intent = new Intent(UserFoodSelectActivity.this, UserFoodCalculate.class);
            intent.putExtra("foodListJson", jsonArray.toString());
            intent.putExtra("mealType", mealType);
            startActivity(intent); // 다음 액티비티로 이동

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
