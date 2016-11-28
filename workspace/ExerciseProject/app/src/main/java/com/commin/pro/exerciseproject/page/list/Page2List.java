package com.commin.pro.exerciseproject.page.list;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.commin.pro.exerciseproject.R;

import java.util.ArrayList;
import java.util.LinkedList;

public class Page2List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_list_layout);

        createGUI();
    }

    private void createGUI() {
        createList();
    }

    private void createList() {
        ListView listView = (ListView) findViewById(R.id.lst_menu);
        listView.setDivider(null);
        final Page2ListAdapter adapter = new Page2ListAdapter(Page2List.this);
        listView.setAdapter(adapter);

        final String[] menus = getResources().getStringArray(R.array.menu);
        LinkedList<Drawable> icons = new LinkedList<Drawable>();
        icons.add(ContextCompat.getDrawable(Page2List.this, R.drawable.url));
        icons.add(ContextCompat.getDrawable(Page2List.this, R.drawable.camera));
        icons.add(ContextCompat.getDrawable(Page2List.this, R.drawable.documents));
        icons.add(ContextCompat.getDrawable(Page2List.this, R.drawable.earings));


        for (String menu : menus) {
            adapter.addItem(icons.pop(), menu);
        }



    }
}
