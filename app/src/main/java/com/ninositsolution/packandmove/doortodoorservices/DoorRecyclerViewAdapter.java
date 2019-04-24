package com.ninositsolution.packandmove.doortodoorservices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ninositsolution.packandmove.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DoorRecyclerViewAdapter extends RecyclerView.Adapter<DoorRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> picturesList;

    private static final int REQUEST_CAMERA = 100;
    private static final int RESULT_LOAD_IMAGE = 101;



    public DoorRecyclerViewAdapter(ArrayList<String> picturesList, Context context  )
    {
        this.picturesList = picturesList;
        this.mContext = context;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DoorRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.door_recycler_adapter,parent,false);
        return new DoorRecyclerViewAdapter.MyViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull DoorRecyclerViewAdapter.MyViewHolder myViewHolder, final int position)
    {
        if (position == picturesList.size())
        {
            if (picturesList.size() == 5)
            {
                myViewHolder.doorServiceAddPhotoLayout.setVisibility(View.GONE);
            }
            else
            {
                myViewHolder.doorServiceAddPhotoLayout.setVisibility(View.VISIBLE);
            }


        }

        else
        {
            myViewHolder.doorImageLayout.setVisibility(View.VISIBLE);

            File file =new File(picturesList.get(position));
            Glide.with(mContext).load(file).into(myViewHolder.addedDoorImage);

        }


        myViewHolder.removeDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                picturesList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return picturesList.size() +1;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout doorImageLayout,doorServiceAddPhotoLayout;
        ImageView addedDoorImage;
        Button removeDoorImage;




        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            doorImageLayout = itemView.findViewById(R.id.doorImageLayout);
            doorServiceAddPhotoLayout = itemView.findViewById(R.id.doorServiceAddPhotoLayout);
            addedDoorImage = itemView.findViewById(R.id.doorToDoorAddedImage);
            removeDoorImage = itemView.findViewById(R.id.removeImage);


        }
    }


    public interface onItemClickListener
    {
        public void onItemClickListener(int position);
    }


}
