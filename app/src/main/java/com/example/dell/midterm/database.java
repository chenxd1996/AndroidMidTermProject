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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class database extends SQLiteOpenHelper{
    private static final String DB_NAME = "mysql";
    private static final String TABLE_NAME = "mytable";
    private static final int DB_VERSION = 3;
    private Context mcontext;
    public  database(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int v) {
        super(context,name, cursorFactory, DB_VERSION);
        mcontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //创建表
        //id,姓名，性别，首字母，势力，现在籍贯，古代籍贯，寿命，文字描述
        String todo = "create table mytable(id integer primary key , name text , sex text , first_char text," +
                "shili text, now_place text, old_place text, life INT , description text, picture INT)";
        sqLiteDatabase.execSQL(todo);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

    }

    public void myinsert( String name ,String sex, String first_char, String shili,
                  String now_place , String old_place, int life , String description, int picture){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("sex", sex);
        cv.put("first_char", first_char);
        cv.put("shili", shili);
        cv.put("name", name);
        cv.put("now_place", now_place);
        cv.put("old_place", old_place);
        cv.put("life", life);
        cv.put("description", description);
        cv.put("picture", picture);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
    public void myupdate( String old_name,String name ,String sex, String first_char, String shili,
                          String now_place , String old_place, int life , String description, int picture){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
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
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = {name};

        db.delete(TABLE_NAME,whereClause, whereArgs);
        db.close();

    }
    public void myfind(String name) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from mytable name like ?", new String[]{name});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始
            String thename = cursor.getString(1);
            String sex = cursor.getString(2);
            String first_char = cursor.getString(3);
            String shili= cursor.getString(4);
            String now_place = cursor.getString(5);
            String old_place= cursor.getString(6);
            int life = cursor.getInt(7);
            String description = cursor.getString(8);
            int picture = cursor.getInt(9);
            //
        }
        cursor.close();
        db.close();

    }
}
