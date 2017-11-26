package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
public class create_person extends AppCompatActivity  {
    public String sex1;
    public String shili;
    public String nowplace;
    public String oldplace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        Button b1 = (Button) findViewById(R.id.commit);
        final EditText temp = (EditText) findViewById(R.id.name);
        final   EditText temp1 = (EditText) findViewById(R.id.first);
        final  EditText temp2 = (EditText) findViewById(R.id.des);
        final  Spinner s1 = (Spinner)  findViewById(R.id.input_sex);
        final Spinner s2 = (Spinner)  findViewById(R.id.input_shili);
        final Spinner s3 = (Spinner)  findViewById(R.id.input_nowplace);
        final Spinner s4 = (Spinner)  findViewById(R.id.input_oldplace);

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
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oldplace = s4.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = temp.getText().toString();
                String first  = temp1.getText().toString();
                String des  = temp2.getText().toString();
            }
        });
    }
}
