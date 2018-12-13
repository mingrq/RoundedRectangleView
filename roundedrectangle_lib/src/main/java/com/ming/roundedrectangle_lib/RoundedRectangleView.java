package com.ming.roundedrectangle_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2018/12/13 13:40
 */
public class RoundedRectangleView extends AppCompatImageView {

    float radius = 0;//所有圆角大小
    float verticalRadius = 0;//垂直圆角大小
    float horizontalRadius = 0;//水平圆角大小


    public RoundedRectangleView(Context context) {
        this(context, null);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundedRectangleView);
        radius = array.getDimension(R.styleable.RoundedRectangleView_radius, 0);
        verticalRadius = array.getDimension(R.styleable.RoundedRectangleView_verticalRadius, 0);
        horizontalRadius = array.getDimension(R.styleable.RoundedRectangleView_horizontalRadius, 0);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = getBitmap(getDrawable());
        BitmapShader shader = null;
        if (bitmap != null) {
            int viewWidth = getWidth();//控件宽度
            int viewHeight = getHeight();//控件高度
            int width = bitmap.getWidth();//图片宽度
            int height = bitmap.getHeight();//图片高度

            if (shader == null) {
                shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (shader != null) {
                Matrix matrix = new Matrix();
                matrix.setScale(viewWidth / width, viewHeight / height);
                shader.setLocalMatrix(matrix);
            }
            Paint paint = new Paint();
            paint.setShader(shader);
            Rect rect = new Rect(0, 0, viewWidth, viewHeight);
            RectF rectF = new RectF(rect);
            if (radius > 0) {
                verticalRadius = radius;
                horizontalRadius = radius;
            }
            canvas.drawRoundRect(rectF, horizontalRadius, verticalRadius, paint);
        } else {
            super.onDraw(canvas);
        }

    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color), Color.alpha(color), Color.alpha(color), Color.alpha(color));
            return bitmap;
        } else {
            return null;
        }
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getVerticalRadius() {
        return verticalRadius;
    }

    public void setVerticalRadius(float verticalRadius) {
        this.verticalRadius = verticalRadius;
    }

    public float getHorizontalRadius() {
        return horizontalRadius;
    }

    public void setHorizontalRadius(float horizontalRadius) {
        this.horizontalRadius = horizontalRadius;
    }


}
