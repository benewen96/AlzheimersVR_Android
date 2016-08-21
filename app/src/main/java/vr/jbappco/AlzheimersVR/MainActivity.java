package vr.jbappco.AlzheimersVR;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.vr.sdk.widgets.video.VrVideoView;

        import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    protected VrVideoView videoWidgetView;
    protected EditText url_field;
    protected Button play_btn;

    //declare our video file location
    Uri fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind our views
        videoWidgetView = (VrVideoView) findViewById(R.id.video_view);
        url_field = (EditText) findViewById(R.id.url_field);
        play_btn = (Button) findViewById(R.id.play_btn);


    }

    //play our VR video
    public void playVideo(View v) {
        //try and load & play our video into the view
        try {
            fname = Uri.parse(url_field.getText().toString());
            videoWidgetView.loadVideo(fname,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoWidgetView.playVideo();
    }
}
