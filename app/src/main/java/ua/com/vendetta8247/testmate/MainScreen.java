package ua.com.vendetta8247.testmate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainScreen extends AppCompatActivity {

    MainScreenAdapter adapter = new MainScreenAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView rw = (RecyclerView) findViewById(R.id.contentList);



        rw.setAdapter(adapter);

        adapter.clearList();
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/TestMate");
        if (!dir.exists())
            dir.mkdir();
        for (int i = 0; i < dir.listFiles().length; i++)
            adapter.addItem(new MainCard1(dir.listFiles()[i].getName(), dir.listFiles()[i].getAbsolutePath()));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rw.setLayoutManager(llm);
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
        newHolder.filePath = card.filePath;
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

    public void clearList()
    {
        cardList.clear();
        notifyDataSetChanged();
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
        public String filePath;
        private String content;
        public MainScreenItemViewHolder(View v, final Context context)
        {
            super(v, context);
            this.context = context;
            headerText = (TextView) v.findViewById(R.id.header_text_main_1);

            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v2, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            CardView view = (CardView) v2;
                            view.setCardBackgroundColor(0xFFF0F0F0);
                            v2.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            Intent intent = new
                                    Intent(v2.getContext(), MainActivity.class);
                            try {
                                content = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
                            }
                            catch(FileNotFoundException ex)
                            {
                                content = "File not found";
                                content += ex.getMessage();
                            }
                            intent.putExtra("content", content);
                            //System.out.println(articleUrl);
                            //intent.putExtra("title", TITLE);
                            v2.getContext().startActivity(intent);
                        }
                        case MotionEvent.ACTION_CANCEL: {
                            CardView view = (CardView) v2;
                            view.setCardBackgroundColor(0xFFFFFFFF);
                            view.invalidate();
                            break;
                        }
                    }
                    return true;
                }
            });

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
    public String filePath;

    public MainCard1(String headerText, String filePath)
    {
        this.headerText = headerText;
        this.filePath = filePath;

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