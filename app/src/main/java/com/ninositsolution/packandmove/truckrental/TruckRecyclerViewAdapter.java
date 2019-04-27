package com.ninositsolution.packandmove.truckrental;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class TruckRecyclerViewAdapter extends RecyclerView.Adapter<TruckRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<String> truckRentalPicturesList;
    private OnItemClickListener clickListener;

    private static final int REQUEST_CAMERA = 100;
    private static final String TAG = "TruckRecyclerAdapter";
    private static final int RESULT_LOAD_IMAGE = 101;




    public TruckRecyclerViewAdapter(ArrayList<String> truckRentalPicturesList, Context context, OnItemClickListener onItemClickListener)
    {
        this.truckRentalPicturesList = truckRentalPicturesList;
        this.context = context;
        clickListener = onItemClickListener;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public TruckRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.truckrental_recycleradapter,parent,false);

        return new TruckRecyclerViewAdapter.MyViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckRecyclerViewAdapter.MyViewHolder myViewHolder, int position)
    {
        if (position ==truckRentalPicturesList.size())
        {
            if (truckRentalPicturesList.size() == 5)
            {
                if (myViewHolder.truckRentalAddPhotoLayout.getVisibility() == View.VISIBLE)
                {
                    myViewHolder.truckRentalAddPhotoLayout.setVisibility(View.GONE);
                }
            }
            else
            {
                myViewHolder.truckRentalAddPhotoLayout.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            myViewHolder.truckRentalAddPhotoLayout.setVisibility(View.GONE);
            myViewHolder.truckImageLayout.setVisibility(View.VISIBLE);

            File file = new File(truckRentalPicturesList.get(position));
            Glide.with(context).load(file).into(myViewHolder.truckAddedImage);
        }




    }

    @Override
    public int getItemCount()

    {
        return truckRentalPicturesList.size() +1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout truckRentalAddPhotoLayout, truckImageLayout;
        ImageView truckAddedImage;
        Button removeImage;
        OnItemClickListener onItemClickListener;






        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener)
        {
            super(itemView);

            this.onItemClickListener = onItemClickListener;
            truckImageLayout =  itemView.findViewById(R.id.truckImageLayout);
            truckRentalAddPhotoLayout = itemView.findViewById(R.id.truckRentalAddPhotoLayout);
            truckAddedImage = itemView.findViewById(R.id.truckAddedImage);
            removeImage = itemView.findViewById(R.id.truckRemoveImage);



            truckRentalAddPhotoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyViewHolder.this.onItemClickListener.onClick(view, getAdapterPosition());
                }
            });

            removeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyViewHolder.this.onItemClickListener.onRemoved(getAdapterPosition());
                }
            });
        }
    }
}
