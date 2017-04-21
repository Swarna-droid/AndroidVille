package com.tripathi.swarna.android.themoviedatabaseapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tripathi.swarna.android.themoviedatabaseapp.Constants;
import com.tripathi.swarna.android.themoviedatabaseapp.R;
import com.tripathi.swarna.android.themoviedatabaseapp.adapter.MovieAdapter;
import com.tripathi.swarna.android.themoviedatabaseapp.api.MovieApi;
import com.tripathi.swarna.android.themoviedatabaseapp.api.MovieService;
import com.tripathi.swarna.android.themoviedatabaseapp.fragment.MovieDetailFragment;
import com.tripathi.swarna.android.themoviedatabaseapp.model.MovieResult;
import com.tripathi.swarna.android.themoviedatabaseapp.model.SearchResult;
import com.tripathi.swarna.android.themoviedatabaseapp.utils.RecyclerViewScrollListener;
import com.tripathi.swarna.android.themoviedatabaseapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button bt_search;
    private Button bt_submit;
    private EditText et_movie_name;
    private ArrayList<MovieResult> movieResults;
    private LinearLayout ll_search_box;
    private ProgressDialog progressDialog;

    private MovieAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewScrollListener recyclerViewScrollListener;
    private MovieAdapter.RecyclerTouchListener recyclerTouchListener;
    private MovieAdapter.ClickListener clickListener;

    RecyclerView rv_movieList;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private MovieService movieService;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_search = (Button) findViewById(R.id.bt_search);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        et_movie_name = (EditText) findViewById(R.id.edit_message);
        ll_search_box = (LinearLayout) findViewById(R.id.ll_search_box);
        progressDialog = new ProgressDialog(this);

        bt_search.setOnClickListener(this);
        bt_submit.setOnClickListener(this);

        rv_movieList = (RecyclerView) findViewById(R.id.rv_movie_list);

        adapter = new MovieAdapter(this);

        movieResults = (ArrayList<MovieResult>) adapter.getMovies();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_movieList.setLayoutManager(linearLayoutManager);

        rv_movieList.setItemAnimator(new DefaultItemAnimator());
        rv_movieList.setAdapter(adapter);
        rv_movieList.setHasFixedSize(false);

        //Setting up the scroll listener for the Recycler view to load more data when end is reached
        setScrollListener(linearLayoutManager);
        //adding the scroll listener to the recycler view
        rv_movieList.addOnScrollListener(recyclerViewScrollListener);

        //setting up onclick listener to display the details of movie
        setOnClickListener();

        //initializing the touch listener
        recyclerTouchListener = new MovieAdapter.RecyclerTouchListener(this, rv_movieList, clickListener);

        //adding the touch listener to the recycler view
        rv_movieList.addOnItemTouchListener(recyclerTouchListener);

        //init service and load data
        movieService = MovieApi.getClient().create(MovieService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_imdbsearch_result,menu);
        return true;
    }

    @Override
    public void onClick(View view)
    {
        if(view == bt_search) showSearchTools();
        if(view == bt_submit) searchMovies();
    }

    /**
     * It shows the the search box and submit button for query the Movie database.
     */
    public void showSearchTools()
    {
        bt_search.setVisibility(View.GONE);
        ll_search_box.setVisibility(View.VISIBLE);
    }

    /**
     * Its called when the submit button is clicked, its displays the progress dialog and loads the first page of results.
     */
    public void searchMovies()
    {
        progressDialog.setMessage("Searching...");
        progressDialog.show();

        Constants.query = et_movie_name.getText().toString().trim(); //<-- to store the name of the movie entered for search.
        resetScrollListener();

        loadFirstPage(Constants.query);

        et_movie_name.getText().clear();
    }

    /**
     * @param linearLayoutManager takes the type of layout manager as an input (Currently working for linear layout manager)
     *                            Is is used to initialize the scroll listener to handle the scrolling of recycler view.
     *                            Loads more data when the scrolled to the end.
     */
    public void setScrollListener(LinearLayoutManager linearLayoutManager)
    {
        recyclerViewScrollListener = new RecyclerViewScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        loadNextPage(Constants.query);
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
    }

    /**
     * This is used to initialize the click listener for the recycler view.
     */
    public void setOnClickListener()
    {
        clickListener = new MovieAdapter.ClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("MovieResult", movieResults);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                MovieDetailFragment newFragment = MovieDetailFragment.getInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft,TAG);
            }

            @Override
            public void onLongClick(View view, int position)
            {}
        };
    }

    /**
     * This method id used to reset the scroller everytime the search is performed.
     * It sets the loading as false ,  clears the list of Movie results stored and notifies the data changes.
     */
    public void resetScrollListener()
    {
        movieResults.clear();
        adapter.notifyDataSetChanged();
        isLoading = false;
    }

    /**
     * @param query The movie name, to be searched.
     *              This method loads the first page of the results fetched when the movie name is searched.
     */
    private void loadFirstPage(String query)
    {
        Log.d(TAG, "loadFirstPage: ");

        callToSearchMovies(query).enqueue(new Callback<SearchResult>()
        {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response)
            {
                progressDialog.dismiss();
                Utils.hideKeyboard(getApplicationContext(),bt_submit);

                List<MovieResult> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * @param query the name of the movie
     *              This method loads the next page of results if there are more pages.
     */
    private void loadNextPage(String query)
    {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callToSearchMovies(query).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<MovieResult> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
    /**
     * @param response response returned from the api call.
     * @return
     */
    private List<MovieResult> fetchResults(Response<SearchResult> response) {
        SearchResult searchResult = response.body();
        return searchResult.getResults();
    }

    /**
     * @param query is the input search string (which is the name of the movie to be searched )
     * @return
     */
    private Call<SearchResult> callToSearchMovies(String query) {
        return movieService.getListOfMovies(
                getString(R.string.my_api_key),
                query,
                currentPage
        );
    }
}
