package com.example.birdshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.logging.LogRecord;


public class GameView extends View {


    startGame st=new startGame();
    Paint paint=new Paint();
    Bitmap back;
    Rect r;
    int width,height,birdY,birdframe,birdwidth,birdY2;
    float velocity;
    float birdX,birdX2,birdX3,birdY3;
    Bitmap bird[]=new Bitmap[18];
    Bitmap cannon;
    Random random;
    Handler handler;
    Runnable runnable;
    int bulletw,bulleth,birdw,birdh;
    Bitmap bullet;
    Boolean touch=false;
    int canwidth,canheight, bulletX=340;float bulletY=900;
    int score=0,misscount=0;
    final long UPDATE=30;


    public  GameView(Context context) {
        super(context);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.bkg);
        Display d = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        d.getSize(size);
        width = size.x;
        height = size.y;
        r = new Rect(0, 0, width, height);
        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.e1);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.e2);
        bird[2] = BitmapFactory.decodeResource(getResources(), R.drawable.e3);
        bird[3] = BitmapFactory.decodeResource(getResources(), R.drawable.e4);
        bird[4] = BitmapFactory.decodeResource(getResources(), R.drawable.e5);
        bird[5] = BitmapFactory.decodeResource(getResources(), R.drawable.e6);
        bird[6] = BitmapFactory.decodeResource(getResources(), R.drawable.e7);
        bird[7] = BitmapFactory.decodeResource(getResources(), R.drawable.e8);
        cannon = BitmapFactory.decodeResource(getResources(), R.drawable.cannon);
        bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);


        birdX = -100;
        birdY = 250;
        birdX2=-200;
        birdY2=40;
        birdX3=800;
        birdY3=200;

        velocity = 10;
        birdframe = 0;
        canwidth = cannon.getWidth();
        canheight = cannon.getHeight();
        bulletw=bullet.getWidth();
        bulleth=bullet.getHeight();
        birdw=bird[0].getWidth();
        birdh=bird[0].getHeight();

        paint.setTextSize(60);
        paint.setColor(Color.BLACK);


        canwidth /= 2;
        canheight /= 2;

        cannon = Bitmap.createScaledBitmap(cannon, canwidth, canheight, false);
        bulleth/=3;
        bulletw/=3;
        bullet = Bitmap.createScaledBitmap(bullet, bulletw, bulleth, false);


        birdwidth = bird[0].getWidth();

        random = new Random();
        handler = new android.os.Handler();



        runnable = new Runnable() {
            @Override
            public void run() {



                invalidate();




            }



        };


    }








    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(back,null,r,null);
        canvas.drawBitmap(bird[birdframe],birdX,birdY,null);
        canvas.drawBitmap(bird[birdframe],birdX2,birdY2,null);


        canvas.drawBitmap(cannon,250,900,null);
        canvas.drawBitmap(bullet,bulletX,bulletY,null);
        canvas.drawText("SCORE:"+score ,20,1000,paint);

        Thread t=new Thread();

        birdframe++;
        birdX+=velocity+velocity*0.5;
        birdX2+=velocity+velocity*0.1;

        if(touch){

            bulletY-=velocity*4;
        }
        if(bulletY<0){
            bulletY=900;
            misscount++;

            touch=false;
        }
        if(misscount==3){
            canvas.drawText("GameOver " ,200,400,paint);
            canvas.drawText("Your SCORE: "+score ,200,500,paint);
            bulletY=900;


            touch=false;
            try{

                t.sleep(2000);

            }catch(Exception e){}
            return;





        }


        if(birdframe>7){
            birdframe=0;
        }


        if(birdX>width+birdwidth ){

            birdX=-200;

            birdY=random.nextInt(550);

            velocity=velocity;

        }

        if(birdX2>width+birdwidth ){

            birdX2=-300;

            birdY2=random.nextInt(400);

            velocity=velocity;


        }
        if(bulletX<=birdX+birdwidth/2 && bulletX>=birdX+60 && bulletY<=birdY+bulleth && bulletY>=birdY-bulleth){

            birdX=-300;
            bulletY=900;
            touch=false;
            birdY=random.nextInt(500);
            score+=10;
            velocity+=0.4;




        }
        if(bulletX<=birdX2+birdwidth/2 && bulletX>=birdX2+60 && bulletY<=birdY2+bulleth && bulletY>=birdY2-bulleth){

            birdX=-200;
            bulletY=900;
            touch=false;
            birdY2=random.nextInt(300);

            score+=10;
            velocity+=0.4;


        }







        handler.postDelayed(runnable,UPDATE);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN ){
            touch=true;



        }


        return true;
    }
}
