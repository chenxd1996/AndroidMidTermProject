package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/24.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class database extends SQLiteOpenHelper{
    private static final String DB_NAME = "testsql1";
    public static final String TABLE_NAME = "Mytable2";
    private static final int DB_VERSION = 3;
    private SQLiteDatabase db;
    private Context mcontext;
    public database(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
        mcontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //创建表
        //id,姓名，性别，首字母，势力，现在籍贯，古代籍贯，寿命，文字描述
        String todo = "create table if not exists " + TABLE_NAME + " (name text primary key, sex text , first_char text," +
                "shili text, now_place text, old_place text, life INT , description text, picture text)";
        // String todo = "drop table mytable";
        sqLiteDatabase.execSQL(todo);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        db = sqLiteDatabase;
    }

    public void myinsert( String name ,String sex, String first_char, String shili,
                  String now_place , String old_place, int life , String description, String picture){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (name != null)
            cv.put("name", name);
        if (sex != null)
            cv.put("sex", sex);
        if (first_char != null)
            cv.put("first_char", first_char);
        if (shili != null)
            cv.put("shili", shili);
        if (now_place != null)
            cv.put("now_place", now_place);
        if (old_place != null)
            cv.put("old_place", old_place);
        cv.put("life", life);
        if (description != null)
            cv.put("description", description);
        if (picture != null);
            cv.put("picture", picture);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
    public void myupdate( String old_name,String name ,String sex, String first_char, String shili,
                          String now_place , String old_place, int life , String description, String picture){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        if (sex != null)
            cv.put("sex", sex);
        cv.put("first_char", first_char);
        cv.put("shili", shili);
        cv.put("name", name);
        cv.put("now_place", now_place);
        cv.put("old_place", old_place);
        cv.put("life", life);
        cv.put("description", description);
        cv.put("picture", picture);
        String whereClause = "name=?";
        String[] whereArgs = {old_name};
        db.update(TABLE_NAME,cv,whereClause,whereArgs);
        db.close();
    }

    public void deleteDB(String name) {
        db = getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = {name};

        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();

    }
    public ArrayList<Person> myfind(HashMap<filtersData.filterType, String> filters) {
        db = getWritableDatabase();
        String query = "select * from " + TABLE_NAME+ " where ";
        ArrayList<Person> result = new ArrayList<Person>();
        int index = 0;
        for (HashMap.Entry<filtersData.filterType, String> entry : filters.entrySet()) {
            switch (entry.getKey()) {
                case first_char_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "first_char == '" + entry.getValue() + "' ";
                        } else {
                            query += "and first_char == '" + entry.getValue() + "' ";
                        }
                    break;
                case native_place_modern_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "now_place == '" + entry.getValue() + "' ";
                        } else {
                            query += "and now_place == '" + entry.getValue() + "' ";
                        }
                    break;
                case native_place_ancient_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "old_place == '" + entry.getValue() + "' ";
                        } else {
                            query += "and old_place == '" + entry.getValue() + "' ";
                        }
                    break;
                case sex_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "sex == '" + entry.getValue() + "' ";
                        }
                        else {
                            query += "and sex == '" + entry.getValue() + "' ";
                        }
                    break;
                case camp_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "shili == '" + entry.getValue() + "' ";
                        } else {
                            query += "and shili == '" + entry.getValue() + "' ";
                        }
                    break;
                case name_filter:
                    if (entry.getValue().length() != 0)
                        if (index == 0) {
                            index++;
                            query += "name == '" + entry.getValue() + "' ";
                        } else {
                            query += "and name == '" + entry.getValue() + "' ";
                        }
                    break;
            }
        }
        if (query.equals("select * from " + TABLE_NAME + " where ")){
            query = "select * from " + TABLE_NAME;

        }
        Cursor cursor = db.rawQuery(query,
                null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String sex = cursor.getString(1);
            String first_char = cursor.getString(2);
            String shili= cursor.getString(3);
            String now_place = cursor.getString(4);
            String old_place= cursor.getString(5);
            int life = cursor.getInt(6);
            String description = cursor.getString(7);
            String picture = cursor.getString(8);
            Person person = new Person(first_char, name, shili, sex, old_place,
                    now_place, life, picture, description);
            result.add(person);
        }
        cursor.close();
        db.close();
        return result;
    }
}
