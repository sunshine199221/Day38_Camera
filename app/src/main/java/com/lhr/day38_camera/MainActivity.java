package com.lhr.day38_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1= (Button) findViewById(R.id.bt1);
        iv1= (ImageView) findViewById(R.id.iv1);
        /**
         * 按钮的点击事件，打开照相机，并把拍摄的照片保存到sd卡中
         */
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");
                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(in,0);
//                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");
//                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                startActivityForResult(in,0);
//                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");
//                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                startActivityForResult(in,0);
            }
//            public void onClick(View v) {
//                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");
//                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                startActivityForResult(in,0);
//            }
//            public void onClick(View v) {
//                Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");
//                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                startActivityForResult(in,0);
//            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file=new File( Environment.getExternalStorageDirectory(),"aaa.jpg");

        BitmapFactory.Options options=new BitmapFactory.Options();
        /**
         * 图片的二次采样
         */
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(file.getPath(),options);
        int insam=options.outWidth/600;
        options.inSampleSize=insam;
        options.inJustDecodeBounds=false;

        Bitmap bm=BitmapFactory.decodeFile(file.getPath(),options);
        iv1.setImageBitmap(bm);
    }
}
