package com.abdo.patrick.abdo.Controllers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Views.Registraion.PainPlacement;

import static java.security.AccessController.getContext;

/**
 * Created by Khaled on 04-04-2017.
 */

public class ImageController {

    PainPlacement _painPlacement;

    public ImageController() {
    }

    public ImageController(PainPlacement painPlacement) {
        _painPlacement = painPlacement;
    }


    //Actionbar should be 0 if you don't got it implemented in your view Patrick
    public Touch GetTouchedPosition(ImageView imageView, MotionEvent event, int actionbarHeight){
        Touch touch = new Touch();
        int[] viewCoordinates = new int[2];
        imageView.getLocationOnScreen(viewCoordinates);

        Log.i("INFO", "viewCoordinates: "+viewCoordinates[0]+" - "+viewCoordinates[1]);

        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        Log.i("INFO", "Touch coordinates: "+touchX+" - "+touchY);

        touch.setX(touchX);
        touch.setY(touchY);

        return touch;
    }
    public int GetPixelColor(ImageView imageView, Touch touch) {

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        
        touch.setY((int) ((touch.getY()*1.41)));

        if (bitmap.getWidth() < touch.getX() || bitmap.getHeight() < touch.getY()
                || touch.getX() < 0 || touch.getY() < 0) return -1;

        int pixel = bitmap.getPixel(touch.getX(), touch.getY());

        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);

        Log.i("INFO", "Touched color: "+redValue+" "+greenValue+" "+blueValue);

        if (isYellow(redValue, greenValue, blueValue)) return Color.YELLOW;

        if (isRed(redValue, greenValue, blueValue)) return Color.RED;

        if (isGreen(redValue, greenValue, blueValue)) return Color.GREEN;

        if (isBlue(redValue, greenValue, blueValue)) return Color.BLUE;

        return -1;
    }

    private boolean isRed(int red, int green, int blue){
        return red == 237 && green == 28 && blue == 36;
    }

    private boolean isYellow(int red, int green, int blue){
        return red == 255 && green == 242 && blue == 0;
    }

    private boolean isBlue(int red, int green, int blue){
        return red == 63 && green == 72 && blue == 204;
    }

    private boolean isGreen(int red, int green, int blue){
        return red == 34 && green == 177 && blue == 76;
    }

public class Touch {

        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
