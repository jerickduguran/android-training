package com.groupm.machineproblemflicker;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FLICKER_URL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json";
    private static final String TAG = "MV_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FlickerFetcher().execute();
    }


    private class FlickerFetcher  extends AsyncTask<Void, Void, Void> {

        protected ArrayList<Bitmap> user_lists;
        protected ProgressDialog pd;

        protected void onPreExecute() {
            user_lists = new ArrayList<>();
            Toast.makeText(MainActivity.this, "Starting to Fetch Images...", Toast.LENGTH_SHORT).show();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Fetching Images...");
            pd.show();
        }
        protected Void doInBackground(Void... urls) {
            try {
                HttpURLConnection connection = initConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    builder.append(line);
                }
                String  str = builder.toString().substring("jsonFlickrFeed(".length(), builder.toString().length() - 1);
                JSONObject jo = new JSONObject(str);
                JSONArray  items = jo.getJSONArray("items");

                for (int i = 0; i < items.length(); i++) {
                    Bitmap mIcon_val = null;
                    JSONObject  itemObject = items.getJSONObject(i);
                    String media      = itemObject.getString("media");
                    JSONObject _media = new JSONObject(media);
                    URL newurl        = new URL(_media.getString("m"));
                    Log.d("URL_",_media.getString("m"));

                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    user_lists.add(mIcon_val);
                }


            }catch(ProtocolException e){
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

            protected void onPostExecute(Void unused){
            if(user_lists.size() > 0){
                ListView lists = (ListView) findViewById(R.id.image_list);
                final ArrayList<Bitmap> list  = new ArrayList<>();
                JsonAdapter aAdapter        = new JsonAdapter(MainActivity.this, list); 
                for(int i=0;i<user_lists.size();i++){
                    list.add(user_lists.get(i));
                    pd.setMessage("Fetching image " + i + " of " + user_lists.size());
                }
                lists.setAdapter(aAdapter);
            }
            if (pd != null)
            {
                pd.dismiss();
            }
            Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
        }

    }

    private class JsonAdapter extends BaseAdapter {

        Context context;

        ArrayList<Bitmap> image_list = new ArrayList<>();

        public JsonAdapter(Context context, ArrayList<Bitmap> arrayList)
        {
            this.context = context;
            this.image_list = arrayList;
        }

        @Override
        public int getCount() {
            return image_list.size();
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderItem viewHolder;
            /*
             View view = convertView;
            if(view == null)
            {
                view = LayoutInflater.from(context).inflate(R.layout.image_list,parent,false);
                ImageView image = (ImageView) view.findViewById(R.id.flicker_image);
                image.setImageBitmap(image_list.get(position));
            }
            return view;
            */

            if(convertView==null){
                LayoutInflater inflater = ((MainActivity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.image_list, parent, false);
                viewHolder = new ViewHolderItem();
                viewHolder.imageViewItem = (ImageView) convertView.findViewById(R.id.flicker_image);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolderItem) convertView.getTag();
            }

            Bitmap image =  image_list.get(position);
            if(image != null) {
                viewHolder.imageViewItem.setImageBitmap(image);
            }

            return convertView;
        }

    }


    public static HttpURLConnection initConnection(){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(FLICKER_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return connection;
    }

    static class ViewHolderItem {
        ImageView imageViewItem;
    }
}
