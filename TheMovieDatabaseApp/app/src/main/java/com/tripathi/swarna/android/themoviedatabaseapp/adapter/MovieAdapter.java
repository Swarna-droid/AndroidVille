package com.tripathi.swarna.android.themoviedatabaseapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tripathi.swarna.android.themoviedatabaseapp.R;
import com.tripathi.swarna.android.themoviedatabaseapp.model.MovieResult;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w500";

    private List<MovieResult> movieResults;
    private Context context;

    private boolean isLoadingAdded = false;

    public MovieAdapter(Context context) {
        this.context = context;
        movieResults = new ArrayList<>();
    }

    public List<MovieResult> getMovies() {
        return movieResults;
    }

    public void setMovies(List<MovieResult> movieResults) {
        this.movieResults = movieResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MovieResult result = movieResults.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;

                movieVH.tv_name.setText(result.getTitle());

                if (result.getPosterPath()!= null)
                    Glide.with(context).load(BASE_URL_IMG + result.getPosterPath())
                            .crossFade()
                            .thumbnail(0.1f)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(movieVH.thumbnail);
                else
                    movieVH.thumbnail.setImageResource(R.drawable.ic_no_preview);
                break;

            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movieResults == null ? 0 : movieResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(MovieResult r) {
        movieResults.add(r);
        notifyItemInserted(movieResults.size() - 1);
    }

    public void addAll(List<MovieResult> moveResults) {
        for (MovieResult result : moveResults)
        {
            add(result);
        }
    }

    public void remove(MovieResult r) {
        int position = movieResults.indexOf(r);
        if (position > -1) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new MovieResult());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieResults.size() - 1;
        MovieResult result = getItem(position);

        if (result != null) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public MovieResult getItem(int position) {
        return movieResults.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class MovieVH extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView tv_name;

        public MovieVH(View view)
        {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    /**
     * This is the listener class for the touch events on the items of the Recycler view.
     */
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener
    {
        private GestureDetector gestureDetector;
        private MovieAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MovieAdapter.ClickListener clickListener)
        {
            this.clickListener = clickListener;

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()
            {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child != null  && clickListener != null)
                    {
                        clickListener.onLongClick(child,recyclerView.getChildPosition(child));
                    }
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
        {
            View view = rv.findChildViewUnder(e.getX(),e.getY());

            if(view != null && clickListener != null && gestureDetector.onTouchEvent(e))
                clickListener.onClick(view, rv.getChildPosition(view));
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e){}

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){}
    }

    public interface ClickListener
    {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }


}
