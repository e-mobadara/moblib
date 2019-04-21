package com.example.emobadaragaminglib.Implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.emobadaragaminglib.Base.Input;
import java.util.List;

/**
 * This class is responsible for updating the screen
 * It is a thread that runs after each DeltaTime
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    Paint paint;
    volatile boolean running = false;

    public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
        this.paint = new Paint();
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();

    }

    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while(running) {
            if(!holder.getSurface().isValid())
                continue;

            float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
            startTime = System.nanoTime();

            if (deltaTime > 3.15){
                deltaTime = (float) 3.15;
            }

            //handle touch event
            List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
            // 1. All touch input is handled here:
            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                Input.TouchEvent event = touchEvents.get(i);
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    game.getCurrentScreen().handleTouchDown(event.x,event.y,event.pointer);
                }
                if (event.type == Input.TouchEvent.TOUCH_UP) {
                    game.getCurrentScreen().handleTouchUp(event.x,event.y,event.pointer);
                }
                if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {
                    game.getCurrentScreen().handleDragging(event.x,event.y,event.pointer);
                }
            }
        ///////////////////////////////:

            game.getCurrentScreen().drawSprites();
            game.getCurrentScreen().render(deltaTime);


            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            //redrawing all the bitmaps
            canvas.drawBitmap(framebuffer, null, dstRect, paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        running = false;
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }

        }
    }


}