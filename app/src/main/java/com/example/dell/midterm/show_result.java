package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

;


public class show_result extends AppCompatActivity {
    private database db;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.result);
       db = new database(this);

       HashMap<filtersData.filterType, String> filters_selected = filtersData.getInstance().getFiltersSelected();
       final ArrayList<Person> results = db.myfind(filters_selected);


       final ContactAdapter adapter = new ContactAdapter(show_result.this, R.layout.result_form, results);
       final ListView listView = (ListView) findViewById(R.id.result_list);

       listView.setAdapter(adapter);
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int p, long l) {
               final Person temp = results.get(p);
               Dialog delete = new AlertDialog.Builder(show_result.this).
                       setTitle("移除人物").
                       setMessage("从人物列表移除" + temp.name + "?").
                       setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           }
                       }).
                       setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               results.remove(p);
                               db.deleteDB(temp.name);
                               adapter.notifyDataSetChanged();
                               Toast.makeText(show_result.this, "删除成功！", Toast.LENGTH_SHORT).show();
                           }
                       }).
                       show();
               return true;
           }
       });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(show_result.this, person_detail.class);
               intent.putExtra("name", results.get(position).name);
               startActivity(intent);
           }
       });
   }
}
