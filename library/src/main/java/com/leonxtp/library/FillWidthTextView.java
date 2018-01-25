package com.leonxtp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by leonxtp on 2018/1/25.
 */

public class FillWidthTextView extends AppCompatTextView {

    public FillWidthTextView(Context context) {
        super(context);
    }

    public FillWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillWidthTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {

        String text = getText().toString();
        if (TextUtils.isEmpty(text)) {
            return;
        }

        canvas.save();

        // 支持padding
        canvas.clipRect(new Rect(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight()));

        int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();

        int baseLineY = getPaddingTop()
                + (getHeight() - getPaddingTop() - getPaddingBottom()) / 2
                + getTextRect(getPaint(), "中").height() / 2;

        boolean isEndWithColon = text.endsWith("：") || text.endsWith(":");

        int textSpace = (contentWidth - getTxtTotalWidth(text)) / (text.length() - (isEndWithColon ? 2 : 1));
        textSpace = textSpace < 0 ? 0 : textSpace;

        int lastX = getPaddingLeft();
        for (int i = 0; i < text.length(); i++) {

            String char2Draw;
            // 最后如果包含冒号，那么几不需要间距了，直接连在一起绘制
            if (isEndWithColon && i == text.length() - 2) {
                char2Draw = text.substring(i, i + 2);
                canvas.drawText(char2Draw, lastX, baseLineY, getPaint());
                break;

            } else {
                char2Draw = text.substring(i, i + 1);
                canvas.drawText(char2Draw, lastX, baseLineY, getPaint());
            }

            int textWidth = getTextRect(getPaint(), char2Draw).width();
            lastX = lastX + textWidth + textSpace;
        }

        canvas.restore();
    }

    /**
     * 计算所有字符占用的总宽度，如果直接用getTextRect()，将会得到一个字符之间带有一定间隔的总长度
     * 所以还是要一个个计算
     */
    private int getTxtTotalWidth(String text) {
        int result = 0;
        boolean isEndWithColon = text.endsWith("：") || text.endsWith(":");
        for (int i = 0; i < text.length(); i++) {
            String text2Draw;
            if (isEndWithColon && i == text.length() - 2) {
                text2Draw = text.substring(i, i + 2);
                result += getTextRect(getPaint(), text2Draw).width();
                break;
            } else {
                text2Draw = text.substring(i, i + 1);
                result += getTextRect(getPaint(), text2Draw).width();
            }
        }
        return result;
    }

    /**
     * 获取字符的占用宽度
     */
    private Rect getTextRect(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

}
