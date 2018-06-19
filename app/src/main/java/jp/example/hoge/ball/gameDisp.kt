package jp.example.hoge.ball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class gameDisp(context: Context, val ball:Ball ) : View(context) {
    internal var paint: Paint
    init {
        paint = Paint()
    }

    // 表示
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (0F != ball.x && 0F != ball.y) {
            // ボール座標有効なら、ボール表示
            paint.setColor(Color.WHITE)
            canvas.drawCircle(ball.x, ball.y, ball.size, paint)
        }
    }
}