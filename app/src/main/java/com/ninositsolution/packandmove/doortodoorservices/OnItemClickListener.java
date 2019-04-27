package com.ninositsolution.packandmove.doortodoorservices;

import android.view.View;

public interface OnItemClickListener {

    void onClick(View view, int position);
    void onRemoved(int position);
}
