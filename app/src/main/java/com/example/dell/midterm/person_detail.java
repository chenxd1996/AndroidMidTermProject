package com.example.dell.midterm;

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

import java.util.ArrayList;
import java.util.HashMap;

public class person_detail extends AppCompatActivity {
    public database db;
    public Person p;
    public final static int SELECT_IMAGE_REQUEST_CODE = 200;
    public ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        db = new database(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        init();
        HashMap<filtersData.filterType, String> filters_selected = new HashMap<>();
        filters_selected.put(filtersData.filterType.name_filter, name);
        ArrayList<Person> results = db.myfind(filters_selected);
        p = results.get(0);

        if (p.picture != null)
            pic.setImageURI(Uri.parse(p.picture));

        Button b1 = (Button) findViewById(R.id.commit);
        final EditText temp = (EditText) findViewById(R.id.name);
        final   EditText temp1 = (EditText) findViewById(R.id.first);
        final  EditText temp2 = (EditText) findViewById(R.id.des);
        final Spinner s1 = (Spinner)  findViewById(R.id.input_sex);
        final Spinner s2 = (Spinner)  findViewById(R.id.input_shili);
        final Spinner s3 = (Spinner)  findViewById(R.id.input_nowplace);
        final Spinner s4 = (Spinner)  findViewById(R.id.input_oldplace);
        final Spinner s5 = (Spinner) findViewById(R.id.input_life);

        temp.setText(p.name);
        temp1.setText(p.first_char);
        temp2.setText(p.description);

        ArrayList<String> sex_filter = filtersData.getInstance().getFilter(filtersData.filterType.sex_filter);
        int index = sex_filter.indexOf(p.sex);
        if (index > 0) {
            s1.setSelection(index - 1, true);
        }

        ArrayList<String> camp_filter = filtersData.getInstance().getFilter(filtersData.filterType.camp_filter);
        index = camp_filter.indexOf(p.camp);
        if (index > 0) {
            s2.setSelection(index - 1, true);
        }

        ArrayList<String> modern_filter = filtersData.getInstance().getFilter(filtersData.filterType.native_place_modern_filter);
        index = modern_filter.indexOf(p.native_place_modern);
        if (index > 0) {
            s3.setSelection(index - 1, true);
        }

        ArrayList<String> ancient_filter = filtersData.getInstance().getFilter(filtersData.filterType.native_place_ancient_filter);
        index = ancient_filter.indexOf(p.native_place_ancient);
        if (index > 0) {
            s4.setSelection(index - 1, true);
        }

        s5.setSelection(5);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p.sex = s1.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p.camp = s2.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p.native_place_modern = s3.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p.native_place_ancient = s4.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        s5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p.age = 50;
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.name  = temp.getText().toString();
                p.first_char  = temp1.getText().toString();
                p.description  = temp2.getText().toString();
                db.myupdate(p.name, p.name, p.sex, p.first_char, p.camp, p.native_place_modern,
                        p.native_place_ancient, 50, p.description, p.picture);
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
                p.picture = uri.toString();
                pic.setImageURI(uri);
                break;
        }
    }
}