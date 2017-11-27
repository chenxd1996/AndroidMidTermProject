package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
public class create_person extends AppCompatActivity  {
    public String sex1;
    public String shili;
    public String nowplace;
    public String oldplace;
    public String name;
    public String first;
    public String des;
    public String picture;
    public String life;

    public final static int SELECT_IMAGE_REQUEST_CODE = 100;
    private ImageView pic;
    private database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        db = new database(this);
        init();

        Button b1 = (Button) findViewById(R.id.commit);
        final EditText temp = (EditText) findViewById(R.id.name);
        final   EditText temp1 = (EditText) findViewById(R.id.first);
        final  EditText temp2 = (EditText) findViewById(R.id.des);
        final  Spinner s1 = (Spinner)  findViewById(R.id.input_sex);
        final Spinner s2 = (Spinner)  findViewById(R.id.input_shili);
        final Spinner s3 = (Spinner)  findViewById(R.id.input_nowplace);
        final Spinner s4 = (Spinner)  findViewById(R.id.input_oldplace);
        final Spinner s5 = (Spinner) findViewById(R.id.input_life);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex1 = s1.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shili = s2.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nowplace = s3.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oldplace = s4.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        s5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                life = s5.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name  = temp.getText().toString();
                first  = temp1.getText().toString();
                des  = temp2.getText().toString();
                db.myinsert(name, sex1, first, shili, nowplace, oldplace,
                        50, des, picture);
            }
        });
    }

    private void init() {
        pic = (ImageView) findViewById(R.id.pic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
    }

    private void showPopWindow() {
        View popView = View.inflate(this, R.layout.pop_window, null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        //popupWindow.setAnimationStyle(R.style.a);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELECT_IMAGE_REQUEST_CODE:
                Uri uri = data.getData();
                this.picture = uri.toString();
                pic.setImageURI(uri);
                break;
        }
    }
}
