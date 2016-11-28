package com.commin.pro.exerciseproject.page.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.model.Model2Menu;

import java.util.ArrayList;

/**
 * Created by user on 2016-11-28.
 */
public class Page2ListAdapter extends BaseAdapter {
    private ArrayList<Model2Menu> items = new ArrayList<Model2Menu>();
    private Context context;

    Page2ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final int item_position = position;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.page_list_item, parent, false);
        }
        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        final TextView textView = (TextView) view.findViewById(R.id.tv_list_menu_name);

        final Model2Menu model = items.get(position);

        imageView.setImageDrawable(model.getIcon());
        textView.setText(model.getMenu_text());


        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    public void addItem(Drawable icon, String menu_text) {
        Model2Menu item = new Model2Menu();

        item.setIcon(icon);
        item.setMenu_text(menu_text);

        items.add(item);


    }


}
