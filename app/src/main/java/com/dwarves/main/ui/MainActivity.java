package com.dwarves.main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

public class MainActivity extends AppCompatActivity implements MainMvpView {

    @BindView(R.id.list_view)
    RecyclerView recyclerView;
    @BindView(R.id.input_text)
    EditText inputEdText;
    @BindView(R.id.split_btn)
    Button splitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //
        MainPresenter mainPresenter = new MainPresenter(this);
        splitButton.setOnClickListener(v -> {
            String inputMessage = getInputMessage();
            mainPresenter.getAndShowSubMessageList(inputMessage);
        });
    }

    @Override
    public String getInputMessage() {
        return inputEdText.getText().toString();
    }

    @Override
    public void showSubMessageView(ArrayList<String> subMessageList) {
        LinearLayoutManager mLinearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        MessageAdapter adapter = new MessageAdapter(this, subMessageList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(int errorCode) {
        String error = null;
        if (errorCode == 1) {
            error = getString(R.string.error1);
        } else if (errorCode == 2){
            error = getString(R.string.error2);
        }
        if (error != null && !error.isEmpty())
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
