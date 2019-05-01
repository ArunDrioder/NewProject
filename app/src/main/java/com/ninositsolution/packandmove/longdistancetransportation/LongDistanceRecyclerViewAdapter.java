package com.ninositsolution.packandmove.longdistancetransportation;

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

public class LongDistanceRecyclerViewAdapter extends RecyclerView.Adapter<LongDistanceRecyclerViewAdapter.MyViewHolder>
{
    private Context context;
    private List<String> longDistancePicturesList;
    private OnItemClickListener clickListener;

    private static final int REQUEST_CAMERA = 100;
    private static final String TAG = "LongRecyclerAdapter";
    private static final int RESULT_LOAD_IMAGE = 101;



    public LongDistanceRecyclerViewAdapter(ArrayList<String> longDistancePicturesList, Context context, OnItemClickListener onItemClickListener)
    {
        this.longDistancePicturesList = longDistancePicturesList;
        this.context = context;
        clickListener = onItemClickListener;
        notifyDataSetChanged();

    }




    @NonNull
    @Override
    public LongDistanceRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.longdistancerecycler_adapter,parent,false);

        return new LongDistanceRecyclerViewAdapter.MyViewHolder(view,clickListener);



    }

    @Override
    public void onBindViewHolder(@NonNull LongDistanceRecyclerViewAdapter.MyViewHolder myViewHolder, int position)
    {
        if (position ==longDistancePicturesList.size())
        {
            if (longDistancePicturesList.size() == 5)
            {
                if (myViewHolder.longDistanceAddPhotoLayout.getVisibility() == View.VISIBLE)
                {
                    myViewHolder.longDistanceAddPhotoLayout.setVisibility(View.GONE);
                }
            }
            else
            {
                myViewHolder.longDistanceAddPhotoLayout.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            myViewHolder.longDistanceAddPhotoLayout.setVisibility(View.GONE);
            myViewHolder.longDistanceImageLayout.setVisibility(View.VISIBLE);

            File file = new File(longDistancePicturesList.get(position));
            Glide.with(context).load(file).into(myViewHolder.longDistanceAddedImage);

        }
    }

    @Override
    public int getItemCount()
    {
        return longDistancePicturesList.size() +1;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout longDistanceAddPhotoLayout, longDistanceImageLayout;
        ImageView longDistanceAddedImage;
        Button longDistanceRemoveImage;
        OnItemClickListener onItemClickListener;







        public MyViewHolder(@NonNull View itemView , OnItemClickListener onItemClickListener)
        {
            super(itemView);

            this.onItemClickListener = onItemClickListener;
            longDistanceImageLayout = itemView.findViewById(R.id.longDistanceImageLayout);
            longDistanceAddPhotoLayout = itemView.findViewById(R.id.longDistanceAddPhotoLayout);
            longDistanceAddedImage = itemView.findViewById(R.id.longDistanceAddedImage);
            longDistanceRemoveImage = itemView.findViewById(R.id.longDistanceRemoveImage);




            longDistanceAddPhotoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyViewHolder.this.onItemClickListener.onClick(view, getAdapterPosition());
                }
            });


            longDistanceRemoveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyViewHolder.this.onItemClickListener.onRemoved(getAdapterPosition());
                }
            });
        }
    }

}
