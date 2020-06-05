package com.example.movie_recommender;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<Movie> {
    private static final String TAG = "MovieListAdapter";


    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    static class ViewHolder {
        TextView title;
        TextView kind;
        EditText rating;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public MovieListAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //get the persons information
        String title = getItem(position).getTitle();
        String kind = getItem(position).getKind();




        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.textView1);
            holder.kind = (TextView) convertView.findViewById(R.id.textView2);
            holder.rating=(EditText) convertView.findViewById(R.id.editid);


            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }




        holder.title.setText(title);
        holder.kind.setText(kind);

        return convertView;}


        /**
         * Required for setting up the Universal Image loader Library
         */

}