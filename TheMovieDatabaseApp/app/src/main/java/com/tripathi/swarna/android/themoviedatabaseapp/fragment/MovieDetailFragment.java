package com.tripathi.swarna.android.themoviedatabaseapp.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tripathi.swarna.android.themoviedatabaseapp.R;
import com.tripathi.swarna.android.themoviedatabaseapp.model.MovieResult;

import java.util.ArrayList;

public class MovieDetailFragment extends DialogFragment
{
    private TextView tv_title;
    private TextView tv_year;
    private TextView tv_overview;
    private ImageView coverImage;

    private ArrayList<MovieResult> movieResults;
    private int selectedPosition = 0;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w500";

    public static MovieDetailFragment getInstance()
    {
        return  new MovieDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);


        tv_title = (TextView) view.findViewById(R.id.title);
        tv_year = (TextView) view.findViewById(R.id.year);
        coverImage = (ImageView) view.findViewById(R.id.cover);
        tv_overview = (TextView) view.findViewById(R.id.overview);
        movieResults = (ArrayList<MovieResult>) getArguments().getSerializable("MovieResult");
        selectedPosition = getArguments().getInt("position");

        displayMetaInfo(selectedPosition);

        return view;
    }

    /**
     * This method is to display the details of the moview item selected from the list.
     * @param position position of the item selected
     */
    private void displayMetaInfo(int position)
    {
        MovieResult movieResult = movieResults.get(position);
        tv_title.setText(movieResult.getTitle());
        tv_year.setText(movieResult.getReleaseDate());
        String overview = (movieResult.getOverview() != null && !movieResult.getOverview().isEmpty() ) ? movieResult.getOverview() : "There is no OVERVIEW available";
        tv_overview.setText(overview);
        Glide.with(getActivity()).load(BASE_URL_IMG+movieResult.getPosterPath())
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(coverImage);
    }
}
