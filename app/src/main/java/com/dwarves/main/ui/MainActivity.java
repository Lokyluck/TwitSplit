package com.dwarves.main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dwarves.main.R;
import com.dwarves.main.adapter.MessageAdapter;

import java.util.ArrayList;

/**
 * @author Ngo Tan Tien
 * Email: ngotantien@outlook.com
 * Date: Feb 21th 2019
 * */

public class MainActivity extends AppCompatActivity implements  MainMvpView {

    @BindView(R.id.list_view)
    RecyclerView recyclerView;
    String sampleMsg = "I can't believe Tweeter now supports chunking my ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //
        MainPresenter mainPresenter = new MainPresenter(this, this);
        mainPresenter.getSubMessageList(sampleMsg);
    }

    @Override
    public void showSubMessageView(ArrayList<String> subMessageList) {
        LinearLayoutManager mLinearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        MessageAdapter adapter = new MessageAdapter(this, subMessageList);
        recyclerView.setAdapter(adapter);
    }
}
