package com.cesara.chattinggroup;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity2 extends AppCompatActivity{

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int image_picture = R.mipmap.ic_launcher;
    EditText name_list, mess_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acttivity2);

        getSupportActionBar().setTitle("Add Message");

        name_list = (EditText) findViewById(R.id.isi_nama);
        mess_list = (EditText) findViewById(R.id.isi_pesan);
        pref = getSharedPreferences(MainActivity.fileMain,0);
        editor = pref.edit();
    }

    public void send(View view) {
        int image = image_picture;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Sender",name_list.getText().toString());
            jsonObject.put("Content",mess_list.getText().toString());
            jsonObject.put("Time",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("Image",image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(pref.contains("message")) {
            String dataMessage = pref.getString("message","");

            try {
                JSONArray jsonArray = new JSONArray(dataMessage);
                jsonArray.put(jsonObject);
                editor.putString("message", jsonArray.toString());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("message", jsonArray.toString());
            editor.apply();
        }

        finish();
    }
}
