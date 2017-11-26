package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;;
import java.util.List;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


public class show_result extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.result);
       final List<Contactlist> contactlist = new ArrayList<Contactlist>();
       final ContactAdapter adapter = new ContactAdapter(show_result.this, R.layout.result_form, contactlist);
       final ListView listView = (ListView) findViewById(R.id.result_list);

       listView.setAdapter(adapter);
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int p, long l) {
               Contactlist temp = contactlist.get(p);
               Dialog delete = new AlertDialog.Builder(show_result.this).
                       setTitle("移除人物").
                       setMessage("从人物列表移除" + temp.getName() + "?").
                       setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           }
                       }).
                       setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               contactlist.remove(p);
                               adapter.notifyDataSetChanged();
                               Toast.makeText(show_result.this, "删除成功！", Toast.LENGTH_SHORT).show();
                           }
                       }).
                       show();
               return true;
           }
       });
   }
}
