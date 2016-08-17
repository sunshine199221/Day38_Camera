package com.lhr.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 自定义的SurfaceView
 * Created by Administrator on 2016/8/10 0010.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    Paint paint;
    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.RED);
        holder=getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isFinish=true;
    }
    Boolean isFinish=false;
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();

            for (int i=0;i<50;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isFinish){
                    return;
                }
                /**
                 * 锁定画布
                 */
                Canvas canvas=holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(200,200+i*20,50,paint);
                /**
                 * 解锁画布
                 */
                holder.unlockCanvasAndPost(canvas);
            }

        }
    }

}
