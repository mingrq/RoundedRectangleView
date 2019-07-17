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

    private float radius;//所有圆角大小
    private Context context;

    public RoundedRectangleView(Context context) {
        this(context, null);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundedRectangleView);
        radius = array.getDimension(R.styleable.RoundedRectangleView_radius, 0);
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
            canvas.drawRoundRect(rectF, radius, radius, paint);
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


    /**
     * 获取圆角角度
     *
     * @return
     */
    public float getRadius() {
        return radius;
    }

    /**
     * 设置圆角大小 单位 px
     *
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }


    /**
     * dp转px
     */
    public float dp2px(float dpValues) {
        dpValues = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValues, context.getResources().getDisplayMetrics());
        return dpValues;
    }
}
