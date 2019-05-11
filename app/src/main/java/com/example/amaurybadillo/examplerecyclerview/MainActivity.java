package com.example.amaurybadillo.examplerecyclerview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;
import com.example.amaurybadillo.examplerecyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getName();
    private ActivityMainBinding mBinding;
    private LinkedList<String> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initList();
        mBinding.fab.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {
        Log.d(TAG, "initList");
        for (int i = 0; i < 20; i++) {
            mWordList.addLast(getString(R.string.word) + " " + i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Log.d(TAG, "onClick: add new element to RecyclerView");
                int size = mWordList.size();
                mWordList.addLast(getString(R.string.word) + " " + size);
                mRecyclerView.getAdapter().notifyItemInserted(size);
                mRecyclerView.smoothScrollToPosition(size);
                break;
        }
    }
}
