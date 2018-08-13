package com.rdev.chatapp.repository;

import android.arch.lifecycle.LiveData;

import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.vo.Conversation;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

public interface DataManager {
    boolean isFirstRun();
    void setFirstRun(boolean first);
    LiveData<ApiResponse<Conversation>> getConversation();

}
