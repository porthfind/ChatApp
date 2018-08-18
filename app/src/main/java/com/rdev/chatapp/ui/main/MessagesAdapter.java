package com.rdev.chatapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdev.chatapp.R;
import com.rdev.chatapp.db.Message;
import com.rdev.chatapp.vo.CardViewItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import timber.log.Timber;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.AdHolder> {

    Context mContext;
    private ArrayList<CardViewItem> cardViewItems;
    final String ME = "Me";
    final String OTHERS = "Others";

    public MessagesAdapter(@Nonnull Context context) {
        mContext = context;
        cardViewItems =  new ArrayList<>();;
    }

    //returns the total number of the list size.
    @Override
    public int getItemCount() {

        return cardViewItems.size();
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

        final CardViewItem message= cardViewItems.get(position);
        final long idUser = message.getUserId();

        if(idUser == 1){


            if(message.getUrl() != null)//attach
            {
                holder.name.setText(ME);
                holder.name.setGravity(Gravity.RIGHT);
                holder.content.setText(message.getContent());
                holder.content.setGravity(Gravity.RIGHT);

                Glide.with(mContext)
                        .load(cardViewItems.get(position).getUrl())
                        .into(holder.attachement);

                holder.attach_name.setText(message.getTitulo());
                holder.attach_name.setVisibility(View.VISIBLE);
                holder.attach_name.setGravity(Gravity.RIGHT);
                holder.user_profile_photo.setVisibility(View.GONE);

            }
            else//message
            {
                holder.name.setText(ME);
                holder.name.setGravity(Gravity.RIGHT);
                holder.content.setText(message.getContent());
                holder.content.setGravity(Gravity.RIGHT);
                holder.user_profile_photo.setVisibility(View.GONE);
                holder.attachement.setVisibility(View.GONE);
                holder.attach_name.setVisibility(View.GONE);

            }

        }
        else {

            if(message.getUrl() != null)//attach
            {
                holder.name.setText(OTHERS);
                holder.name.setGravity(Gravity.LEFT);
                holder.content.setText(message.getContent());
                holder.content.setGravity(Gravity.LEFT);
                //holder.attach_name.setGravity(Gravity.LEFT);

                Glide.with(mContext)
                    .load(cardViewItems.get(position).getUrl())
                    .into(holder.attachement);

                holder.attachement.setVisibility(View.VISIBLE);
                holder.attach_name.setVisibility(View.VISIBLE);
                holder.attach_name.setText(message.getTitulo());
                holder.attach_name.setGravity(Gravity.LEFT);
                holder.user_profile_photo.setVisibility(View.VISIBLE);

            }
            else {
                holder.content.setText(message.getContent());
                holder.content.setGravity(Gravity.LEFT);
                holder.name.setText(OTHERS);
                holder.name.setGravity(Gravity.LEFT);
                holder.content.setGravity(Gravity.LEFT);
                holder.attachement.setVisibility(View.GONE);
                holder.attach_name.setVisibility(View.GONE);
                holder.user_profile_photo.setVisibility(View.VISIBLE);

            }


        }


    }

    public void setMessages(List<CardViewItem> cardViewItem){

        this.cardViewItems.clear();
        this.cardViewItems.addAll(cardViewItem);
        notifyDataSetChanged(); //Refresh the data in the recyclerview

    }


    //other users
    public static class AdHolder extends RecyclerView.ViewHolder {

        ImageView user_profile_photo;
        TextView name;
        TextView content;
        ImageView attachement;
        TextView attach_name;

        AdHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            content = (TextView) view.findViewById(R.id.content);
            user_profile_photo = (ImageView) view.findViewById(R.id.user_profile_photo);
            attachement = (ImageView) view.findViewById(R.id.attachment);
            attach_name = (TextView) view.findViewById(R.id.attach_name);;

        }
    }

}
