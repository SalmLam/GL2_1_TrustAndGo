package com.example.movie_recommender;

import android.widget.ArrayAdapter;
import java.util.*;
import android.widget.*;
import android.content.*;
import android.view.View;
import android.view.*;

public class ItemArrayAdapter extends ArrayAdapter {
    private List scoreList = new ArrayList();

    static class ItemViewHolder {
        TextView name;
        TextView score;
    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

    }

    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public Object getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            viewHolder.score = (TextView) row.findViewById(R.id.score);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = (String[]) getItem(position);
        viewHolder.name.setText(stat[0]);
        viewHolder.score.setText(stat[1]);
        return row;
    }
}
