package com.example.movie_recommender;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
public class RatingListAdapter extends ArrayAdapter<Rating> {
    private static final String TAG = "RatingListAdapter";


    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {

        EditText rating;

    }

    /**
     * Default constructor for the PersonListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public RatingListAdapter(Context context, int resource, ArrayList<Rating> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //get the persons information

        float rating=getItem(position).getRating();




        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();

            holder.rating=(EditText) convertView.findViewById(R.id.editid);


            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }





        holder.rating.setText((int) rating);

        return convertView;}


    /**
     * Required for setting up the Universal Image loader Library
     */

}
