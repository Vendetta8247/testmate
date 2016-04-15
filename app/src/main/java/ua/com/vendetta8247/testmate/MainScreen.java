package ua.com.vendetta8247.testmate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    MainScreenAdapter adapter = new MainScreenAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rw = (RecyclerView) findViewById(R.id.contentList);

        rw.setAdapter(adapter);
        adapter.addItem(new MainCard1("Test"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rw.setLayoutManager(llm);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}


class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.SuperViewHolder>
{
    private List<MainCard1> cardList;

    public MainScreenAdapter()
    {
        cardList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        MainCard1 card = cardList.get(position);
        MainScreenItemViewHolder newHolder = (MainScreenItemViewHolder) holder;
        newHolder.headerText.setText(card.headerText);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void addItem(MainCard1 card)
    {
        cardList.add(card);
        notifyItemInserted(cardList.size()-1);

    }

    @Override
    public MainScreenItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.main_screen_card_1, parent, false);

        return new MainScreenItemViewHolder(itemView, parent.getContext());
    }


    class MainScreenItemViewHolder extends SuperViewHolder
    {
        protected TextView headerText;
        public Context context;
        public MainScreenItemViewHolder(View v, Context context)
        {
            super(v, context);
            this.context = context;
            headerText = (TextView) v.findViewById(R.id.header_text_main_1);

        }
    }

    class SuperViewHolder extends RecyclerView.ViewHolder
    {
        public SuperViewHolder(View v, Context context) {
            super(v);
        }
    }
}


class MainCard1{
    public String headerText;

    public MainCard1(String headerText)
    {
        this.headerText = headerText;

    }
}

class MainCard2
{
    public String insideText;

    public MainCard2(String insideText)
    {
        this.insideText = insideText;
    }
}