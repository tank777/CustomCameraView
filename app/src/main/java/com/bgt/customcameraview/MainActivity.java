package com.bgt.customcameraview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.camera)
    CameraView camera;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;
    @BindView(R.id.ll_camera)
    LinearLayout llCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        camera.setCameraListener(new CameraListener() {
            @Override
            public void onVideoTaken(File video) {
                super.onVideoTaken(video);
                // The File parameter is an MP4 file.
                Log.e("TAG", "onVideoTaken: " );
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.start();
    }

    @Override
    protected void onPause() {
        camera.stop();
        super.onPause();
    }

    @OnClick({R.id.ll_video, R.id.ll_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_video:

                camera.startRecordingVideo();
                camera.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        camera.stopRecordingVideo();
                    }
                }, 2500);
                break;
            case R.id.ll_camera:
                break;
        }
    }
}
