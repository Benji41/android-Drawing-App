package noe.basic.drawingapp


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawingView(context: Context,attrs: AttributeSet) : View( context,attrs ) {
    private var mDrawPath : CustomPath? = null
    private var mCanvasBitmap : Bitmap? = null
    private var mDrawPaint : Paint? = null
    private var mCanvasPaint : Paint? = null
    private var mBrushSize : Float = 0.0F
    private var mColor = Color.GREEN
    private var mCanvas : Canvas? = null
    init {
        setUpDraw()
    }

    private fun setUpDraw() {
        mDrawPaint = Paint()
        mDrawPath = CustomPath(mColor,mBrushSize)
        mDrawPaint!!.color = mColor
        mDrawPaint!!.style = Paint.Style.STROKE
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
        mBrushSize = 20F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mCanvasBitmap!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mCanvasBitmap!!,0F,0F,mCanvasPaint)
        if(!mDrawPath!!.isEmpty){
            //mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
            //mDrawPaint!!.color = mDrawPath!!.color
            canvas?.drawPath(mDrawPath!!,mCanvasPaint!!)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event!!.x
        val touchy = event.y
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                //mDrawPath!!.color = mColor
                //mDrawPath!!.brushThickness = mBrushSize
                mDrawPath!!.reset()
                if(touchX != null && touchy != null){
                    mDrawPath!!.moveTo(touchX,touchy)
                }

            }
            MotionEvent.ACTION_UP ->{

            }
        }

        return super.onTouchEvent(event)
    }
    internal inner class CustomPath(color : Int, brushThickness : Float) : Path(){

    }
}


