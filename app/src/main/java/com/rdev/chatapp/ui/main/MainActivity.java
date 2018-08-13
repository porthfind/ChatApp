package com.rdev.chatapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.rdev.chatapp.BR;
import com.rdev.chatapp.R;
import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.databinding.ActivityMainBinding;
import com.rdev.chatapp.ui.base.BaseActivity;
import com.rdev.chatapp.vo.Conversation;
import com.rdev.chatapp.vo.Message;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity  extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityMainBinding mActivityStartBinding;
    private MainViewModel mainViewModel;

    ArrayList<Conversation> conversation;

    MessagesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MessagesAdapter(this, new ArrayList<Message>());

        mActivityStartBinding = getViewDataBinding();


//Inicializar o Recycler view e adicionar o adapter
        mActivityStartBinding.recyclerview.setHasFixedSize(true);
        mActivityStartBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mActivityStartBinding.recyclerview.setAdapter(adapter);

        mainViewModel.getConversations().observe(MainActivity.this, new Observer<ApiResponse<Conversation>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Conversation> conversationApiResponse) {

                if(conversationApiResponse.isSuccessful()) {
                    if( conversationApiResponse.body.getMessages().size()!=0) {
                        Timber.d("------------------INICIO-----------------------------------");


                        ArrayList<Message> temp = new ArrayList<Message>(conversationApiResponse.body.getMessages());
                        adapter.setMessages(temp);



                        /*for (int i = 0; i <= conversationApiResponse.body.getUsers().size() - 1; i++) {

                            //get User
                            if(conversationApiResponse.body.getUsers().get(i).getName().compareToIgnoreCase("userId1")==0){
                                Timber.d("User----->" +conversationApiResponse.body.getUsers().get(i).getName());
                                Timber.d("Mensagem--->"+conversationApiResponse.body.getMessages().get(i).getContent());
                               // TextView tv = (TextView) findViewById(R.id.name);


                            }
                            else
                            {

                                Timber.d("User----->" +conversationApiResponse.body.getUsers().get(i).getName());
                                Timber.d("Mensagem--->"+conversationApiResponse.body.getMessages().get(i).getContent());

                            }*/
                            //}
                            //get Message from the specified user
                            Timber.d("----------------------FIM-------------------------------");

                    }
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
