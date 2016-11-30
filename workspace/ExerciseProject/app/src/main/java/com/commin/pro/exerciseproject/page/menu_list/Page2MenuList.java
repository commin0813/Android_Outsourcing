package com.commin.pro.exerciseproject.page.menu_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.model.Model2Menu;
import com.commin.pro.exerciseproject.page.calendar.Page2Calendar;
import com.commin.pro.exerciseproject.page.do_excercise.Page2DoExcercise;
import com.commin.pro.exerciseproject.page.game.Page2Game;
import com.commin.pro.exerciseproject.page.photo_edit.Page2PhotoEdit;

import java.util.ArrayList;
import java.util.LinkedList;

public class Page2MenuList extends AppCompatActivity {

    private final int ITEM_SELECT_EXCERCIZE = 0;
    private final int ITEM_SELECT_CALENDAR = 1;
    private final int ITEM_SELECT_GAME = 2;
    private final int ITEM_SELECT_PHOTO = 3;

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
        ArrayList<Model2Menu> items = new ArrayList<Model2Menu>();
        final Adapter2MenuList adapter = new Adapter2MenuList(Page2MenuList.this, R.layout.page_list_item, items);
        listView.setAdapter(adapter);

        final String[] menus = getResources().getStringArray(R.array.menu);
        LinkedList<Drawable> icons = new LinkedList<Drawable>();
        icons.add(ContextCompat.getDrawable(this, R.drawable.camera));
        icons.add(ContextCompat.getDrawable(this, R.drawable.url));
        icons.add(ContextCompat.getDrawable(this, R.drawable.documents));
        icons.add(ContextCompat.getDrawable(this, R.drawable.earings));

        for (String menu : menus) {
            Model2Menu model = new Model2Menu();
            model.setIcon(icons.pop());
            model.setMenu_text(menu);
            items.add(model);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case ITEM_SELECT_EXCERCIZE: {
                        startActivity(new Intent(Page2MenuList.this,Page2DoExcercise.class));
                        break;
                    }
                    case ITEM_SELECT_CALENDAR: {
                        startActivity(new Intent(Page2MenuList.this,Page2Calendar.class));
                        break;
                    }
                    case ITEM_SELECT_GAME: {
                        startActivity(new Intent(Page2MenuList.this,Page2Game.class));
                        break;
                    }
                    case ITEM_SELECT_PHOTO: {
                        startActivity(new Intent(Page2MenuList.this,Page2PhotoEdit.class));
                        break;
                    }

                }
            }
        });


    }
}
