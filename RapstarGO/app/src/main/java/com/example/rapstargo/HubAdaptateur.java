package com.example.rapstargo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class HubAdaptateur extends BaseAdapter {
    private final Context mContext;
    private List<Hub> mHubs;

    // 1
    public HubAdaptateur(Context context, List<Hub> hubs) {
        this.mContext = context;
        this.mHubs = hubs;
    }

    public void UpdateHubList(List<Hub> _newList)
    {
        this.mHubs = _newList;
        notifyDataSetChanged();
    }

    // 2
    @Override
    public int getCount() {
        return mHubs.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return mHubs.get(position).getId();
    }

    // 4
    @Override
    public Object getItem(int position) {
        return mHubs.get(position);
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button dummyTextView = new Button(mContext);
        String res = mHubs.get(position).toString();
        dummyTextView.setText(res);
        dummyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DIM", getItem(position).toString());
                SocketManager.getInstance().ConnectToHub(((Hub) getItem(position)).getId());
            }
        });
        return dummyTextView;
    }
}