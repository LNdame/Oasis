package inqb8.ansteph.oasis.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by loicstephan on 2017/04/02.
 */

public class BitmapUtils {


    public static Bitmap drawabletoBitmap(Drawable drawable)
    {
        Bitmap bitmap = null;

        if(drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
            if(bitmapDrawable.getBitmap()!=null){
                return  bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth()<=0 || drawable.getIntrinsicHeight()<=0){
            bitmap= Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888); //single color bitmap will be created of 1x1 pixel
        }else{
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}