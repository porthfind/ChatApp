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

    private boolean loading = true;

    private int totalItemCount;

    private int previousTotal = 0;
    private int offset=20;

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MessagesAdapter(this, new ArrayList<Message>());

        mActivityStartBinding = getViewDataBinding();
        mainViewModel.firstTime().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean !=null && aBoolean){
                    mainViewModel.setisFirstRun();
                }

            }
        });

        //Inicializar o Recycler view e adicionar o adapter
        mActivityStartBinding.recyclerview.setHasFixedSize(true);
        mActivityStartBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mActivityStartBinding.recyclerview.setAdapter(adapter);

        mainViewModel.getValuesFromDb();

        mainViewModel.getmMessage().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                Timber.d("------------------INICIO-----------------------------------");
                for(int i=0; i<messages.size(); i++)
                    Timber.d("===messages==== " + messages.get(i).getContent());

                adapter.setMessages(messages);
            }
        });

        mainViewModel.getUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for(int i=0; i<users.size(); i++)
                    Timber.d("===USER==== " + users.get(i).getName());
            }
        });

    }


    /////
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MessagesAdapter(this, new ArrayList<Message>());

        mActivityStartBinding = getViewDataBinding();


        //Inicializar o Recycler view e adicionar o adapter
        mActivityStartBinding.recyclerview.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        mActivityStartBinding.recyclerview.setLayoutManager(linearLayoutManager);
        mActivityStartBinding.recyclerview.setAdapter(adapter);



        mainViewModel.get20Message(offset).observe(MainActivity.this, new Observer<List<com.rdev.chatapp.db.Message>>() {
            @Override
            public void onChanged(@Nullable List<com.rdev.chatapp.db.Message> messages) {

                ArrayList<Message> temp = new ArrayList<Message>();

                for(int i = 0; i<= messages.size()-1; i++){
                    Timber.d("-------->"+messages.get(i).getContent());
                    temp.add(messages.get(i));
                }

                adapter.setMessages(temp);
                previousTotal = previousTotal+20;
            }
        });



       //Pagination -- onScroll
        mActivityStartBinding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener(){


            protected void loadMoreItems(){

                if(loading)
                {
                    if (totalItemCount >= previousTotal) {//all items showed
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                    else
                    {
                        offset=offset+20;//read next 20 items

                        //get next 20 items
                        mainViewModel.get20Message(offset).observe(MainActivity.this, new Observer<List<com.rdev.chatapp.db.Message>>() {
                            @Override
                            public void onChanged(@Nullable List<com.rdev.chatapp.db.Message> messages) {

                                ArrayList<Message> temp = new ArrayList<Message>();

                                for(int i = 0; i<= messages.size()-1; i++){
                                    temp.add(messages.get(i));
                                }

                                adapter.setMessages(temp);
                            }
                        });

                        previousTotal = previousTotal+20;//number of items showed

                    }
                }

            }
        });

    }
*/
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
