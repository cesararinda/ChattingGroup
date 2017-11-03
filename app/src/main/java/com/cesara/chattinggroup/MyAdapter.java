package com.cesara.chattinggroup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MAdapter> {

    JSONArray jsonArray;

    public MyAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.picture1.setImageResource(jsonObject.getInt("Image"));
            holder.nama1.setText(jsonObject.getString("Sender"));
            holder.message1.setText(jsonObject.getString("Content"));
            holder.date1.setText(jsonObject.getString("Time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class MAdapter extends RecyclerView.ViewHolder {
        ImageView picture1;
        TextView nama1, message1, date1;
        public MAdapter(View itemView) {
            super(itemView);
            picture1 = (ImageView) itemView.findViewById(R.id.gambar);
            nama1 = (TextView) itemView.findViewById(R.id.nama);
            message1 = (TextView) itemView.findViewById(R.id.pesan);
            date1 = (TextView) itemView.findViewById(R.id.tanggal);
        }
    }
}
