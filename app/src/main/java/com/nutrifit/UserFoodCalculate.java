package com.nutrifit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFoodCalculate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonStr = getIntent().getStringExtra("foodListJson");
        String mealType = getIntent().getStringExtra("mealType");
        FoodDBHelper dbHelper = new FoodDBHelper(this);
        SQLiteDatabase foodDb = dbHelper.getReadableDatabase();

        JSONArray foodList = new JSONArray();

        if (jsonStr != null) {
            try {
                JSONArray foodArray = new JSONArray(jsonStr);
                for (int i = 0; i < foodArray.length(); i++) {
                    JSONObject obj = foodArray.getJSONObject(i);
                    String foodName = obj.getString("foodName");
                    float servingSize = (float) obj.getDouble("servingSize");

                    Log.d("받은음식", foodName + ": " + servingSize + "g");

                    Cursor cursor = foodDb.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME + " WHERE foodName = ?", new String[]{foodName});
                    if (cursor.moveToFirst()) {
                        float ratio = servingSize / 100f;

                        double energy = cursor.getDouble(cursor.getColumnIndexOrThrow("energy")) * ratio;
                        double protein = cursor.getDouble(cursor.getColumnIndexOrThrow("protein")) * ratio;
                        double fat = cursor.getDouble(cursor.getColumnIndexOrThrow("fat")) * ratio;
                        double carb = cursor.getDouble(cursor.getColumnIndexOrThrow("carbohydrate")) * ratio;
                        double sugar = cursor.getDouble(cursor.getColumnIndexOrThrow("sugar")) * ratio;
                        double fiber = cursor.getDouble(cursor.getColumnIndexOrThrow("fiber")) * ratio;
                        double calcium = cursor.getDouble(cursor.getColumnIndexOrThrow("calcium")) * ratio;
                        double iron = cursor.getDouble(cursor.getColumnIndexOrThrow("iron")) * ratio;
                        double sodium = cursor.getDouble(cursor.getColumnIndexOrThrow("sodium")) * ratio;
                        double vitA = cursor.getDouble(cursor.getColumnIndexOrThrow("vitaminA")) * ratio;
                        double vitC = cursor.getDouble(cursor.getColumnIndexOrThrow("vitaminC")) * ratio;

                        Log.d("영양소계산", foodName + " " + servingSize + "g 기준");
                        Log.d("영양소계산",
                                "에너지: " + energy + "kcal\n" +
                                        "단백질: " + protein + "g\n" +
                                        "지방: " + fat + "g\n" +
                                        "탄수화물: " + carb + "g\n" +
                                        "당류: " + sugar + "g\n" +
                                        "식이섬유: " + fiber + "g\n" +
                                        "칼슘: " + calcium + "mg\n" +
                                        "철: " + iron + "mg\n" +
                                        "나트륨: " + sodium + "mg\n" +
                                        "비타민A: " + vitA + "μg RAE\n" +
                                        "비타민C: " + vitC + "mg"
                        );

                        // 영양소 OBJ 저장
                        JSONObject foodObj = new JSONObject();

                        foodObj.put("foodName", foodName);
                        foodObj.put("servingSize", servingSize);
                        foodObj.put("mealType", mealType);  // 아점저

                        JSONObject nutrition = new JSONObject();
                        nutrition.put("calories", energy);
                        nutrition.put("protein", protein);
                        nutrition.put("fat", fat);
                        nutrition.put("carbohydrate", carb);
                        nutrition.put("sugar", sugar);
                        nutrition.put("fiber", fiber);
                        nutrition.put("calcium", calcium);
                        nutrition.put("iron", iron);
                        nutrition.put("sodium", sodium);
                        nutrition.put("vitaminA", vitA);
                        nutrition.put("vitaminC", vitC);
                        foodObj.put("nutrition", nutrition);
                        foodObj.put("eatAmount", 1);

                        foodList.put(foodObj);
                    }
                    else {
                        Log.w("영양소계산", foodName + " 을(를) DB에서 찾을 수 없습니다.");
                    }
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        try {
            JSONObject root = new JSONObject();
            root.put("userSelectedFood", foodList);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = "food_data_" + timestamp + ".json";
            File file = new File(getFilesDir(), filename);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(foodList.toString());
                writer.flush();  // 선택적
                Log.d("JSON저장", "파일 저장 완료: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(UserFoodCalculate.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
