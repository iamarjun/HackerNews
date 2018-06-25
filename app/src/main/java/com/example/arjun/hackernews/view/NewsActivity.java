package com.example.arjun.hackernews.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.transition.Fade;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arjun.hackernews.R;
import com.example.arjun.hackernews.data.FakeNews;
import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.OnDownloadComplete;
import com.example.arjun.hackernews.logic.Controller;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

public class NewsActivity extends AppCompatActivity implements ViewInterface, OnDownloadComplete, View.OnClickListener {
    private static final String TAG = "NewsActivity";

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DRAWABLE = "EXTRA_DRAWABLE";

    private ArrayList<News> newsList;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private Toolbar toolbar;
    private Controller controller;

    @Override
    public void onDownloadComplete() {
        Log.d(TAG, "onDownloadComplete: Callback Success");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.tlb_list_activity);

        layoutInflater = getLayoutInflater();

        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setLogo(R.drawable.baseline_view_list_white_24);
        toolbar.setTitleMarginStart(72);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_create_new_item);

        floatingActionButton.setOnClickListener(this);

        controller = new Controller(this, new FakeNews());
    }

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource, View viewRoot) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_DRAWABLE, colorResource);

        getWindow().setEnterTransition(new Fade(Fade.IN));
        getWindow().setEnterTransition(new Fade(Fade.OUT));

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                new Pair<View, String>(viewRoot.findViewById(R.id.imv_list_item_circle),
                        getString(R.string.transition_drawable)),
                new Pair<View, String>(viewRoot.findViewById(R.id.lbl_message),
                        getString(R.string.transition_message)),
                new Pair<View, String>(viewRoot.findViewById(R.id.lbl_date_and_time),
                        getString(R.string.transition_time_and_date)));
        startActivity(i, options.toBundle());
    }

    @Override
    public void setAdapterAndView(ArrayList<News> newsList) {
        this.newsList = newsList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                Objects.requireNonNull(ContextCompat.getDrawable(
                        NewsActivity.this,
                        R.drawable.divider_white))
        );

        recyclerView.addItemDecoration(
                itemDecoration
        );

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void addNewsItemToView(News newsItem) {
        newsList.add(newsItem);

        int endOfList = newsList.size() - 1;

        adapter.notifyItemChanged(endOfList);

        recyclerView.smoothScrollToPosition(endOfList);
    }

    @Override
    public void deleteNewsItemAt(int position) {
        newsList.remove(position);

        adapter.notifyItemRemoved(position);
    }

    @Override
    public void showUndoSnackbar() {
        Snackbar.make(
                findViewById(R.id.root_list_activity),
                getString(R.string.action_delete_item),
                Snackbar.LENGTH_LONG
        ).setAction(R.string.action_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onUndoConfirmed();
            }
        }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);

                controller.onSnackbarTimeout();
            }
        }).show();

    }

    @Override
    public void insertNewsItemAt(News newsItem, int position) {
        newsList.add(position, newsItem);
        adapter.notifyItemInserted(position);
    }

    @Override
    public void onClick(View v) {

        int viewID = v.getId();

        if (viewID == R.id.fab_create_new_item){
            controller.createNewsItem();

        }

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private CircleImageView coloredCircle;
            private TextView time;
            private TextView title;
            private ProgressBar loading;
            private ViewGroup viewGroup;



            ViewHolder(View itemView) {
                super(itemView);

                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.time = itemView.findViewById(R.id.lbl_date_and_time);
                this.title = itemView.findViewById(R.id.lbl_message);
                this.viewGroup = itemView.findViewById(R.id.recyclerViewList);
                this.loading = itemView.findViewById(R.id.pro_item_data);

                this.viewGroup.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                News news = newsList.get(this.getAdapterPosition());

                controller.onNewsItemClicked(news, view);

            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(
                    R.layout.activity_recyclerviewlist,
                    parent,
                    false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            News currentNews = newsList.get(position);

            holder.coloredCircle.setImageResource(currentNews.getColorResource());
            holder.time.setText(currentNews.getDateAndTime());
            holder.title.setText(currentNews.getHeadline());
            holder.loading.setVisibility(View.INVISIBLE);

        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

    }

    private Callback createHelperCallback(){

        return new SimpleCallback(
                0,
                LEFT | RIGHT
        ) {
            @Override
            public boolean onMove(RecyclerView recyclerView1, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                controller.onNewsItemSwiped(
                        position,
                        newsList.get(position)
                );

            }
        };
    }

}