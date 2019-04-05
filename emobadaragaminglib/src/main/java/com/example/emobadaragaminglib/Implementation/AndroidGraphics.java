package com.example.emobadaragaminglib.Implementation;

import java.io.InputStream;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;

import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;

/**
 * This class is responsible for drawing the sprites in your screen
 */
public class AndroidGraphics implements Graphics {
    public static final String TAG ="AndroidGraphics";
    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    /**
     * Create a new Image Object
     * @param id The resource ID. example: R.drawable.my_pic
     * @param format ARGB888 | ARGB444 | RGB565
     * @param res The resources from where to search, if you have no idea just put getGraphics()
     * @return an Image Object.
     */
    @Override
    public Image newImage(int id, ImageFormat format,Resources res) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;


        InputStream in = null;
        Bitmap bitmap = null;
        try {
            bitmap =BitmapFactory.decodeResource(res,id,options);

            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '");
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + "'"+ " ERROR  : "+e);
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }

    /**
     * Make an Image with  an ARGB888 Format.
     * @param id
     * @param res
     * @return
     */
    public Image newImage(int id,Resources res){
        return newImage(id,ImageFormat.ARGB8888,res);
    }

    /**
     * Hides everything from the Screen with a color
     * It doesn't dispose of your Sprites.
     * @param color
     */
    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    /**
     * Draw a Line that passes from two points
     * @param x x of first Point
     * @param y Y of first Point
     * @param x2 x of Second Point
     * @param y2 y of Second Point
     * @param color Color of the Line
     */
    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    /**
     *  Draw a Rectangle starting from its UpperLeft corner
     * @param x x of UpperLeft Corner
     * @param y y of UpperLeft Corner
     * @param width Width of rectangle
     * @param height Height of rectangle
     * @param color Color To fill the Rectangle
     */
    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    /**
     * Fill the current space with a color using its ARGB code
     * @param a : Alpha (0-255)
     * @param r : red (0-255)
     * @param g : green (0-255)
     * @param b : blue (0-255)
     */
    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    /**
     * Write A text on the Screen
     * @param text The String to Write
     * @param x Left
     * @param y Top
     * @param paint The Painting Object to write your String Where you can customize how your String looks
     */
    @Override
    public void drawString(String text, int x, int y, Paint paint){
        canvas.drawText(text, x, y, paint);
    }

    /**
     * Draw Image Object
     * @param image The image to Draw
     * @param x x of UpperLeft Corner
     * @param y y of UpperLeft Corner
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) image).getBitmap(), srcRect, dstRect,
                null);
    }

    /**
     * Draw Image from left Top Corner using the original width and height of the Image
     * @param image Image to Draw
     * @param x Left
     * @param y Top
     */
    @Override
    public void drawImage(Image image, int x, int y) {
        canvas.drawBitmap(((AndroidImage)image).getBitmap(), x, y, null);
    }

    /**
     * Draw Image with Custom Width and height
     * @param image Image
     * @param x Left
     * @param y Top
     * @param width width
     * @param height height
     */
    @Override
    public void drawImage(Image image, int x, int y,int width,int height) {
        drawScaledImage(image , x, y, width,height,0,0,image.getWidth(),image.getHeight());
    }

    /**
     * Make Drawing an Image more customizable,
     * @param image image to draw
     * @param x x-coordinate of LeftTop
     * @param y y-coordinate of LetTop
     * @param width Width
     * @param height Height
     * @param srcX from where to start cropping the Image
     * @param srcY from where to start cropping the Image
     * @param srcWidth width to crop
     * @param srcHeight Height to crop
     */
    public void drawScaledImage(Image image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
        if(image.getBitmap() != null) {
            srcRect.left = srcX;
            srcRect.top = srcY;
            srcRect.right = srcX + srcWidth;
            srcRect.bottom = srcY + srcHeight;

            dstRect.left = x;
            dstRect.top = y;
            dstRect.right = x + width;
            dstRect.bottom = y + height;

            canvas.drawBitmap(((AndroidImage) image).getBitmap(), srcRect, dstRect, null);
        }
    }

    /**
     * Get Width of Device Screen
     * @return Device's Width
     */
    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    /**
     * Get Height of Device Screen
     * @return Device's Height
     */
    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
