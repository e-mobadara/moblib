package com.example.emobadaragaminglib.Components;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.Log;


import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Implementation.AndroidImage;

import java.util.ArrayList;
import java.util.List;

public class DrawableImage  {

    private Bitmap bitmapToDarw;
    private Canvas canvasToDraw;

    private Bitmap bitmap;
    private Paint mPaint;
    private boolean canDraw = false;
    private boolean drawAgain = true;
    private float drawingArea;
    private float drawnArea;

    private List<Point> blackPoints;
    private List<Point> dotPoints;

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    DrawFinishListener drawFinishListener;
    private Paint circlePaint;
    private Path circlePath;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    int xImage,yImage,heightImage,widthImage;

    int colorDots=240;
    int colorDrawingArea=201;

    public DrawableImage(Bitmap bitmap,DrawFinishListener d,int xImage,int yImage,int widthImage,int heightImage) {
        //resize image
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, widthImage, heightImage, true);
        bitmap=resizedBitmap;
        //
        this.bitmap = resizedBitmap;
        drawFinishListener=d;
        dotedPart(bitmap);

        this.xImage=xImage;
        this.yImage=yImage;
        //this.widthImage=bitmap.getWidth();
        //this.heightImage=bitmap.getHeight();
        this.widthImage=widthImage;
        this.heightImage=heightImage;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(25);

        drawingArea = percentInnerPart(bitmap);
        Log.e("% ===", "" + drawingArea);

        mPath = new Path();

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        circlePath = new Path();

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(8f);

        // onSizeChanged
        mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        // my code
        bitmapToDarw = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvasToDraw =  new Canvas(bitmapToDarw);
    }


    public void draw(Game game){
        canvasToDraw.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvasToDraw.drawPath(mPath, mPaint);
        canvasToDraw.drawPath(circlePath, circlePaint);
        game.getGraphics().drawImage(new AndroidImage(bitmap, Graphics.ImageFormat.ARGB8888),xImage,yImage,widthImage,heightImage);
        game.getGraphics().drawImage(new AndroidImage(bitmapToDarw,Graphics.ImageFormat.ARGB8888),xImage,yImage,widthImage,heightImage);
    }
    private void touchStart(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if ((dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
        circlePath.reset();
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }
    private void checkResult() {
        int count = 0;
        for (Point blackPoint : blackPoints) {
            if (dotPoints.contains(blackPoint)) {
                count++;
            }
        }
        Log.e("Result ===>>", count + "");
        if (dotPoints.size() == count) {

            //compromise 3% area
            if (drawnArea - 3 < drawingArea) {
                canDraw = false;
                drawAgain = false;
                drawFinishListener.onDrawFinish();
            } else
                drawFinishListener.onDrawStop();
        } else
            drawFinishListener.onDrawStop();
    }

    // check if we can draw from x,y
    private void check(int x, int y) {
        if (bitmap != null && x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
            int pixel = bitmap.getPixel(x, y);
            if (Color.red(pixel) == colorDrawingArea && Color.red(pixel) == Color.blue(pixel) && Color.red(pixel) == Color.green(pixel)) {
                Log.e("Touch ===", "You're in");
                canDraw = true;
            } else {
                Log.e("Touch ===", "You're out");
                canDraw = false;
            }
        }
    }

    private void areaBlack(Bitmap bm) {
        final int width = bm.getWidth();
        final int height = bm.getHeight();

        int myColor = 0;
        blackPoints = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bm.getPixel(x, y) == Color.BLACK) {
                    blackPoints.add(new Point(x, y));
                    myColor++;
                }
            }
        }
        drawnArea = ((float) myColor * 100) / (width * height);
    }

    private float percentInnerPart(Bitmap bm) {
        final int width = bm.getWidth();
        final int height = bm.getHeight();

        int myColor = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = bitmap.getPixel(x, y);
                if (Color.red(pixel) == colorDrawingArea &&
                        Color.red(pixel) == Color.blue(pixel) &&
                        Color.red(pixel) == Color.green(pixel)) {
                    myColor++;
                }
            }
        }
        return ((float) myColor * 100) / (width * height);
    }

    private void dotedPart(Bitmap bm) {
        final int width = bm.getWidth();
        final int height = bm.getHeight();

        dotPoints = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = bm.getPixel(x, y);

                if (Color.red(pixel) == colorDots &&
                        Color.red(pixel) == Color.blue(pixel) &&
                        Color.red(pixel) == Color.green(pixel)) {
                    dotPoints.add(new Point(x, y));
                }
            }
        }
    }

    public void onTouchUp(int x,int y){
        if(contain(x,y)){
            touchUp();
            canvasToDraw.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            if (drawAgain) {
                areaBlack(mBitmap);
                checkResult();
            }
        }
    }
    public void onTouchDown(int x,int y){

        if(contain(x,y)){
            touchStart(x-xImage, y-yImage);
            canvasToDraw.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            drawFinishListener.onDrawStart();
        }
    }
    public void onDragging(int x,int y){
        if(contain(x,y)){
            x=x-xImage;
            y=y-yImage;
            check(x, y);
            if (drawAgain)
                if (canDraw) {
                    touchMove(x, y);
                } else {
                    touchUp();
                    touchStart(x, y);
                }
            canvasToDraw.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
    }
    public boolean contain(int a,int b){
        if(xImage<=a && a<=(xImage+widthImage) && yImage<=b && b<=(yImage+heightImage) ) return true;
        return false;
    }
}