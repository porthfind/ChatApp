package com.rdev.chatapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdev.chatapp.BR;
import com.rdev.chatapp.R;
import com.rdev.chatapp.databinding.ActivityMainBinding;
import com.rdev.chatapp.db.User;
import com.rdev.chatapp.ui.base.BaseActivity;
import com.rdev.chatapp.vo.CardViewItem;
import com.rdev.chatapp.vo.Conversation;
import com.rdev.chatapp.db.Message;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity  extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityMainBinding mActivityStartBinding;
    private MainViewModel mainViewModel;

    ArrayList<Conversation> conversation;

    MessagesAdapter adapter;

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MessagesAdapter(this);

        mActivityStartBinding = getViewDataBinding();
        mainViewModel.firstTime().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                ;
                if(aBoolean !=null && aBoolean){
                    mainViewModel.setisFirstRun();
                }

            }
        });


        //Inicializar o Recycler view e adicionar o adapter
        mActivityStartBinding.recyclerview.setHasFixedSize(true);
        mActivityStartBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mActivityStartBinding.recyclerview.setAdapter(adapter);

        mainViewModel.getValuesFromDb().observe(this, new Observer<List<CardViewItem>>() {
            @Override
            public void onChanged(@Nullable List<CardViewItem> cardViewItems) {
                for(int i=0; i<cardViewItems.size();i++){
                    adapter.setMessages(cardViewItems);
                }
            }
        });

    }


    public MainViewModel getViewModel() {
        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



}
