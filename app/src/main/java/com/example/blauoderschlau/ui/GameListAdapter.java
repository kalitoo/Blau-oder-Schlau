package com.example.blauoderschlau.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blauoderschlau.R;
import com.example.blauoderschlau.model.Game;


import java.util.List;


public class GameListAdapter extends ArrayAdapter<Game> {

    public GameListAdapter(Context context, List<Game> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (null == rowView) {
            LayoutInflater inflater = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            rowView = inflater.inflate(R.layout.rowview, parent, false);

            ImageView gameIconView = rowView.findViewById(R.id.game_icon);
            TextView gameDateTextView = rowView.findViewById(R.id.game_date);
            TextView gameScoreTextView = rowView.findViewById(R.id.game_score);

            GameViewHolder viewHolder = new GameViewHolder();
            viewHolder.gameIconView = gameIconView;
            viewHolder.gameDateView = gameDateTextView;
            viewHolder.gameScoreView = gameScoreTextView;
            rowView.setTag(viewHolder);
        }
        Game game = getItem(position);
        GameViewHolder viewHolder = (GameViewHolder) rowView.getTag();
        viewHolder.gameDateView.setText(game.getDate().toString());
        viewHolder.gameScoreView.setText(Integer.toString(game.getScore()));
        viewHolder.gameIconView.setImageResource(R.drawable.ic_atom);
        return rowView;
    }

    static class GameViewHolder {
        ImageView gameIconView;
        TextView gameDateView;
        TextView gameScoreView;
    }


}
