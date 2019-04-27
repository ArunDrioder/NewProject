package com.ninositsolution.packandmove.truckrental;

import android.view.View;

public interface OnItemClickListener
{
    void onClick(View view, int position);
    void onRemoved(int position);

}
