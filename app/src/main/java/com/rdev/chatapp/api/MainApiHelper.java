package com.rdev.chatapp.api;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

import android.arch.lifecycle.LiveData;

import com.rdev.chatapp.vo.Conversation;

import retrofit2.http.GET;


public interface MainApiHelper {

    @GET("conversation")
    LiveData<ApiResponse<Conversation>> getConversation();


}

