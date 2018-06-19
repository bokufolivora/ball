package jp.example.hoge.ball

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_block_breaking.*
import java.util.*

class blockBreaking : AppCompatActivity() {
    // タイマー用
    private val time = 50
    private val handler = Handler() 

    // 難易度用
    private val ballSpeed : Float = -10F

    // オブジェクト生成
    internal lateinit var ball: Ball
    internal lateinit var kabe :  Kabe
    internal lateinit var gameArea : gameDisp

    // ゲーム状態
    enum class GmSt {
        INIT ,
        PLAY ,
        STOP ,
        END
    }
    var isGame : GmSt = GmSt.INIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block_breaking)

        ban.setBackgroundColor(Color.BLACK)

        ball = Ball()
        kabe = Kabe(ball)
        gameArea = gameDisp(this, ball )
        ban.addView(gameArea)

        // 中央のボタン　状態で処理を変える
        button.setOnClickListener(){
            when(isGame) {
                GmSt.INIT ,GmSt.STOP -> {
                    button.text = getString(R.string.stop)
                    isGame = GmSt.PLAY
                    handler.post(runnable)
                }
                GmSt.PLAY -> {
                    button.text = getString(R.string.restart)
                    isGame = GmSt.STOP
                    handler.removeCallbacks(runnable)
                }
                GmSt.END -> {
                    button.text = getString(R.string.start)
                    isGame = GmSt.INIT
                }
            }
        }
    }
    // 表示後に呼ばれる onCreate()と違いサイズを取得できる (画面再表示時毎回呼ばれるけど)
    override fun onWindowFocusChanged( hasFocus : Boolean ) {
        super.onWindowFocusChanged(hasFocus)
        // カベ準備
        kabe.arrangement(ban.width.toFloat(),ban.height.toFloat())
        // ボール配置
        ball.reset(ban.width.toFloat()/2, ban.height.toFloat()/2, Random().nextInt(4).toFloat() +8F, ballSpeed)
        textView.text = getString(R.string.ready)
        gameArea.invalidate()
    }

    public override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
    // 10mS
    val runnable = object : Runnable {
        override fun run() {
            // ボール移動(仮)
            ball.move()
            if (false == kabe.chkCollision()) {
                // 仮の移動でぶつからなかったので位置確定
                ball.fix()
            }
            gameArea.invalidate()
            handler.postDelayed(this, time.toLong())
        }
    }

}
