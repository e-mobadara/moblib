package com.example.emobadaragaminglib.Implementation;

import java.util.List;
import android.view.View.OnTouchListener;
import com.example.emobadaragaminglib.Base.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);

    int getTouchY(int pointer);

    List<TouchEvent> getTouchEvents();
}
