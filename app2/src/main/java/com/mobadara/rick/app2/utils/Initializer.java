package com.mobadara.rick.app2.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Implementation.AndroidGraphics;
import com.example.emobadaragaminglib.Implementation.AndroidImage;
import com.mobadara.rick.app2.R;
import com.mobadara.rick.app2.assets.Hero;

public class Initializer {
    public static void initAssets(Context mContext){
        Resources res = mContext.getResources();
        Bitmap img = BitmapFactory.decodeResource(res,R.drawable.morty);

        Hero.avatar1 = new AndroidImage(img);
        Hero.avatar2 = new AndroidImage(img);
    }

    public static void disposer(){
        Hero.avatar1.dispose();
        Hero.avatar2.dispose();
    }
}
