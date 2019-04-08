package com.example.rapstargo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class AbilityAdaptateur extends BaseAdapter {
    private final Context mContext;
    private List<Ability> mAbilities;

    // 1
    public AbilityAdaptateur(Context context, List<Ability> characters) {
        this.mContext = context;
        this.mAbilities = characters;
    }

    public void UpdateAbilityList(List<Ability> _newList)
    {
        this.mAbilities = _newList;
        notifyDataSetChanged();
    }

    // 2
    @Override
    public int getCount() {
        return mAbilities.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return mAbilities.get(position).getId();
    }

    // 4
    @Override
    public Object getItem(int position) {
        return mAbilities.get(position);
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button dummyTextView = new Button(mContext);

        String res = mAbilities.get(position).toString();
        dummyTextView.setText(res);
        dummyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DIM", getItem(position).toString());
                SocketManager.getInstance().UseAbility(Integer.toString(((Ability) getItem(position)).getId()));
            }
        });
        return dummyTextView;
    }
}
