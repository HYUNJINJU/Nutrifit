package com.nutrifit_n;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "food.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "food_table";

    public FoodDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "foodName TEXT NOT NULL, " +
                "standardAmount REAL DEFAULT 100, " + // 100g 기준
                "energy REAL, " +         // kcal
                "protein REAL, " +        // g
                "fat REAL, " +            // g
                "carbohydrate REAL, " +   // g
                "sugar REAL, " +          // g
                "fiber REAL, " +          // g
                "calcium REAL, " +        // mg
                "iron REAL, " +           // mg
                "sodium REAL, " +         // mg
                "vitaminA REAL, " +       // μg RAE
                "vitaminC REAL" +         // mg
                ");";
        db.execSQL(SQL_CREATE_TABLE);

        // 음식 데이터 100g 기준
        // 토마토 스파게티
        String INSERT_SPAGHETTI = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'토마토 스파게티', " +
                "118.0, 3.98, 1.61, 21.62, 4.49, 1.1, 26.6667, 0.6667, 227.0, 12.5, 5.0" +
                ");";
        db.execSQL(INSERT_SPAGHETTI);

        // 안심 스테이크
        String INSERT_STEAK = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'안심스테이크', " +
                "195.0, 20.49, 11.92, 0.0, 0.0, 0.0, 318.0, 2.7, 52.0, 8.0, 0.0" +
                ");";
        db.execSQL(INSERT_STEAK);

        // 치즈 피자
        String INSERT_PIZZA = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'치즈 피자', " +
                "276.0, 12.33, 11.74, 30.33, 3.56, 1.9, 161.0, 1.5, 537.0, 70.0, 0.9" +
                ");";
        db.execSQL(INSERT_PIZZA);

        // 돼지고기김치찌개
        String INSERT_PORK_KIMCHI_SOUP = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'돼지고기김치찌개', " +
                "64.0, 4.09, 4.05, 3.19, 0.89, 0.7, 168.0, 1.3, 238.0, 25.0, 3.0" +
                ");";
        db.execSQL(INSERT_PORK_KIMCHI_SOUP);

        //된장찌개
        String INSERT_SOYBEAN_SOUP = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'된장찌개', " +
                "76.0, 4.3, 4.8, 3.5, 1.1, 0.9, 52.0, 1.1, 654.0, 20.0, 2.0" +
                ");";
        db.execSQL(INSERT_SOYBEAN_SOUP);

        // 현미밥
        String INSERT_BROWN_RICE = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'현미밥', " +
                "153.0, 3.0, 0.73, 32.83, 0.0, 0.8, 78.0, 0.4, 2.0, 0.0, 0.0" +
                ");";
        db.execSQL(INSERT_BROWN_RICE);

        // 오징어젓갈
        String INSERT_SALTEDSQUID = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'오징어젓갈', " +
                "83.0, 13.16, 1.17, 4.03, 0.13, 0.1, 222.0, 0.9, 848.0, 5.0, 2.0" +
                ");";
        db.execSQL(INSERT_SALTEDSQUID);

        // 콩나물무침
        String INSERT_BEANSPROUT = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'콩나물무침', " +
                "47.0, 2.89, 1.41, 7.49, 2.46, 2.4, 138.0, 0.6, 501.0, 15.0, 3.5" +
                ");";
        db.execSQL(INSERT_BEANSPROUT);

        //시금치나물
        String INSERT_SPINACH = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'시금치나물', " +
                "50.0, 3.0, 1.5, 7.0, 0.8, 3.6, 98.0, 2.2, 250.0, 470.0, 11.0" +
                ");";
        db.execSQL(INSERT_SPINACH);

        //무생채
        String INSERT_RADISH = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'무생채', " +
                "40.0, 1.2, 1.0, 8.5, 3.0, 1.6, 62.0, 0.5, 310.0, 10.0, 16.0" +
                ");";
        db.execSQL(INSERT_RADISH);

        //깍두기
        String INSERT_KKAKDUGI = "INSERT INTO " + TABLE_NAME + " (" +
                "foodName, energy, protein, fat, carbohydrate, sugar, fiber, calcium, iron, sodium, vitaminA, vitaminC" +
                ") VALUES (" +
                "'깍두기', " +
                "33.0, 1.3, 0.4, 6.9, 2.1, 2.0, 55.0, 0.8, 450.0, 20.0, 18.0" +
                ");";
        db.execSQL(INSERT_KKAKDUGI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}