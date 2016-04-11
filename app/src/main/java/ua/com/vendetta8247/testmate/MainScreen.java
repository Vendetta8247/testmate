package ua.com.vendetta8247.testmate;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rw = (RecyclerView) findViewById(R.id.contentList);

        rw.setAdapter(new MainScreenAdapter());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rw.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}


class MainScreenAdapter extends RecyclerView.Adapter
{

    public MainScreenAdapter()
    {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public MainScreenItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    class MainScreenItemViewHolder extends RecyclerView.ViewHolder
    {
        public MainScreenItemViewHolder(View v, Context context)
        {
            super(v);

        }
    }
}
