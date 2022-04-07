package com.svalero.enjoypadel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.domain.Player;

import java.util.List;

public class PlayerAdapter  extends BaseAdapter {

    private Context context;
    private List<Player> playerList;
    private LayoutInflater inflater;

    public PlayerAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView photo;
        TextView name;
        TextView surname;
        TextView level;
        TextView availability;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fila, null);

            holder = new ViewHolder();
            holder.photo = (ImageView) convertView.findViewById(R.id.photo);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.surname = (TextView) convertView.findViewById(R.id.surname);
            holder.level = (TextView) convertView.findViewById(R.id.level);
            holder.availability = (TextView) convertView.findViewById(R.id.availability);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Player player = playerList.get(position);
        if (player.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(player.getImage(), 0, player.getImage().length);
            holder.photo.setImageBitmap(bitmap);
        }

        holder.name.setText(player.getName());
        holder.surname.setText(player.getSurname());
        holder.level.setText(player.getLevel());
        if (player.isAvailability() == true) {
            holder.availability.setText(R.string.available);
        } else {
            holder.availability.setText(R.string.not_available);
        }
        return convertView;
    }
}
