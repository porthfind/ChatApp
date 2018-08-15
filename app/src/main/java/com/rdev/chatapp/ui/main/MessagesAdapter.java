package com.rdev.chatapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdev.chatapp.R;
import com.rdev.chatapp.db.Message;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import timber.log.Timber;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.AdHolder> {

    Context mContext;
    private ArrayList<Message> mMessage;

    public MessagesAdapter(@Nonnull Context context, ArrayList<Message> message) {
        mContext = context;
        mMessage = message;
    }

    //returns the total number of the list size.
    @Override
    public int getItemCount() {

        return mMessage.size();
    }

    //creates a new ViewHolder object whenever the RecyclerView needs a new one
    @Override
    public MessagesAdapter.AdHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
            return new AdHolder(inflatedView);

    }

    //takes the ViewHolder object and sets the proper list data for the particular row on the views inside.
    @Override
    public void onBindViewHolder(MessagesAdapter.AdHolder holder, int position) {

        final Message message= mMessage.get(position);
        holder.content.setText(message.getContent());

    }

    public void setMessages(List<Message> messages){

        this.mMessage.clear();
        this.mMessage.addAll(messages);
        notifyDataSetChanged(); //Refresh the data in the recyclerview

    }


    //other users
    public static class AdHolder extends RecyclerView.ViewHolder {

        ImageView user_profile_photo;
        TextView name;
        TextView content;



        AdHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            content = (TextView) view.findViewById(R.id.content);
            user_profile_photo = (ImageView) view.findViewById(R.id.user_profile_photo);

        }
    }

}
