package hzkj.cc.mypiechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.util.List;

public class MyPieChart extends View {
    private List<PieEntry> pieEntries;
    //    private PieEntry pieEntry;
    private Paint paint; //画笔
    private float centerX;   //中心点 x坐标
    private float centerY;  //中心点 y坐标
    private float radius;    //未选中状态的半径
    private float choosedRadius; //选中状态的半径
    private float start = 0;
    private ClickPieEntryListener clickPieEntryListener;

    public MyPieChart(Context context) {
        super(context, null);
    }

    public MyPieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<PieEntry> entries) {
        this.pieEntries = entries;
    }

    public void setClickPieEntryListener(ClickPieEntryListener clickPieEntryListener) {
        this.clickPieEntryListener = clickPieEntryListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStrokeWidth(2);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float sum = 0;
        for (PieEntry pieEntry : pieEntries) {
            sum += pieEntry.getNumber();
        }
        centerX = getPivotX();
        centerY = getPivotY();
        float realRadius = 0;
        radius = (getWidth() > getHeight() ? getHeight() / 2 - Util.dipTopx(getContext(), 40) : getWidth() / 2);
        choosedRadius = radius + Util.dipTopx(getContext(), 5);
        for (int i = 0; i < pieEntries.size(); i++) {
            if (pieEntries.get(i)
                    .isSelected()) {
                realRadius = choosedRadius;
            } else {
                realRadius = radius;
            }
            PieEntry pieEntry = pieEntries.get(i);
            paint.setColor(pieEntry.getColorRes());
            pieEntry.setStartC(start % 360);
            float sweep = pieEntry.getNumber() / sum * 360;
            pieEntry.setEndC(sweep + start % 360);
            paint.setAntiAlias(true);
            RectF rectF = new RectF(centerX - realRadius, centerY - realRadius, centerX + realRadius, centerY + realRadius);
            canvas.drawArc(rectF, start, sweep, true, paint);
            paint.setColor(getResources().getColor(R.color.black));
            float arcCenterC = (start + sweep / 2) % 360;
            start += sweep;
            float arcCenterX = 0;
            float arcCenterY = 0;
            float arcCenterX2 = 0;
            float arcCenterY2 = 0;
            arcCenterX = (float) (centerX + realRadius * Math.cos(Math.toRadians(arcCenterC)));
            arcCenterY = (float) (centerY + realRadius * Math.sin(Math.toRadians(arcCenterC)));
            arcCenterX2 = (float) (arcCenterX + Util.dipTopx(getContext(), 8) * Math.cos(Math.toRadians(arcCenterC)));
            arcCenterY2 = (float) (arcCenterY + Util.dipTopx(getContext(), 8) * Math.sin(Math.toRadians(arcCenterC)));
            canvas.drawLine(arcCenterX, arcCenterY, arcCenterX2, arcCenterY2, paint);
            paint.setTextSize(Util.spTopx(getContext(), 14));
            String text = decimalFormat.format(pieEntry.getNumber() / sum * 100) + "%";
            if (arcCenterC >= 0 && arcCenterC <= 45) {
                canvas.drawText(text, (float) (arcCenterX2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.getTextHeight(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC > 45 && arcCenterC <= 90) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.getTextHeight(text, paint) + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC > 90 && arcCenterC < 135) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.getTextHeight(text, paint) + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC >= 135 && arcCenterC <= 180) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.getTextHeight(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC > 180 & arcCenterC <= 225) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC > 225 & arcCenterC <= 270) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 - Util.getTextHeight(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC > 270 & arcCenterC < 315) {
                canvas.drawText(text, (float) (arcCenterX2 - Util.getTextWidth(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 - Util.getTextHeight(text, paint) / 2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            } else if (arcCenterC >= 315 & arcCenterC < 360) {
                canvas.drawText(text, (float) (arcCenterX2 + Util.dipTopx(getContext(), 2) * Math.cos(Math.toRadians(arcCenterC))), (float) (arcCenterY2 + Util.dipTopx(getContext(), 2) * Math.sin(Math.toRadians(arcCenterC))), paint);
            }
//            Log.d("ccnb", "complete");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double angle = 0;
//        switch (event.getAction()) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            if (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) < Math.pow(radius, 2)) {
                angle = Math.toDegrees(Math.atan2(y - centerY, x - centerX));
                if (angle < 0) {
                    angle += 360;
                }
//                Log.d("ccnb", angle + "");
            }
            for (int i = 0; i < pieEntries.size(); i++) {
                PieEntry pieEntry = pieEntries.get(i);
//                Log.d("ccnb", pieEntry.getStartC() + " and " + pieEntry.getEndC());
                if (angle > pieEntry.getStartC() && angle < pieEntry.getEndC()) {
                    pieEntry.setSelected(true);
                    clickPieEntryListener.click(pieEntry);
                } else {
//                    Log.d("ccnb", "in");
                    pieEntry
                            .setSelected(false);
                }
//                Log.d("ccnb", pieEntry.isSelected() + "");
            }
            invalidate();
//                break;
//            }
        }
        return true;
    }
}
