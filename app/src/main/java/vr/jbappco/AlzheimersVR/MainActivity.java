package vr.jbappco.AlzheimersVR;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.video.VrVideoView;

        import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    protected VrVideoView videoWidgetView;
    protected String url_field;
    protected Button play_btn;
    protected int selectedVid;
    //declare our video file location
    Uri fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind our views
        videoWidgetView = (VrVideoView) findViewById(R.id.video_view);
        url_field = "";
        selectedVid = 0;

        //Configure our theme
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.lilly_logo_red);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D3D3D3")));

        //Declare our VR vids
        String[] vids = {"Las Vegas Drone", "Ultra Light Flight", "Park Bench"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, vids);

        //Bind our array adapter to our list view
        final ListView listView = (ListView) findViewById(R.id.videoList);
        listView.setAdapter(adapter);

        listView.setClickable(true);
        //Override onclick for an item in the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                listView.clearFocus();
                //Get video name
                Object o = listView.getItemAtPosition(position);
                listView.getChildAt(position).isSelected();
                //play video
                setVideo(o.toString());
            }
        });

    }

    //play our VR video
    public void playVideo(View v) {
        videoWidgetView.pauseVideo();
        //try and load & play our video into the view
        try {
            fname = Uri.parse(url_field);
            videoWidgetView.loadVideo(fname,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoWidgetView.playVideo();
    }

    //Sets the correct video url
    public void setVideo(String videoId) {
        switch(videoId) {
            case "Las Vegas Drone" :
                url_field = "http://mobile.360heros.com/producers/4630608605686575/5579686187673361/video/video_7776b10db31f349ede5d253b7744e110.mp4";
                break;
            case "Ultra Light Flight" :
                url_field = "http://mobile.360heros.com/producers/4630608605686575/9813601418398322/video/video_31b451b7ca49710719b19d22e19d9e60.mp4";
                break;
            case "Park Bench" :
                url_field = "";
                break;
            default :
                url_field = "";
                break;
        }

        playVideo(videoWidgetView);
    }

}
