package com.example.rapstargo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CharacterAdaptateur extends BaseAdapter {
    private final Context mContext;
    private List<Character> mCharacters;

    // 1
    public CharacterAdaptateur(Context context, List<Character> characters) {
        this.mContext = context;
        this.mCharacters = characters;
    }

    public void UpdateCharacterList(List<Character> _newList)
    {
        this.mCharacters = _newList;
        notifyDataSetChanged();
    }

    // 2
    @Override
    public int getCount() {
        return mCharacters.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return mCharacters.get(position).getId();
    }

    // 4
    @Override
    public Object getItem(int position) {
        return mCharacters.get(position);
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button dummyTextView = new Button(mContext);

        String res = mCharacters.get(position).toString();
        dummyTextView.setText(res);
        dummyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DIM", getItem(position).toString());
                SocketManager.getInstance().SelectCharacter(((Character) getItem(position)).getId());
            }
        });
        return dummyTextView;
    }
}
