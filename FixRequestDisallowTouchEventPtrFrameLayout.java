public class FixRequestDisallowTouchEventPtrFrameLayout extends PtrFrameLayout {    
    
    private boolean disallowInterceptTouchEvent = false;    
    
    public FixRequestDisallowTouchEventPtrFrameLayout(Context context) {    
        super(context);    
    }    
    
    public FixRequestDisallowTouchEventPtrFrameLayout(Context context, AttributeSet attrs) {    
        super(context, attrs);    
    }    
    
    public FixRequestDisallowTouchEventPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {    
        super(context, attrs, defStyle);    
    }    
    
    @Override    
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {    
        disallowInterceptTouchEvent = disallowIntercept;    
        super.requestDisallowInterceptTouchEvent(disallowIntercept);    
    }    
    
    @Override    
    public boolean dispatchTouchEvent(MotionEvent e) {    
        if (disallowInterceptTouchEvent) {    
            return dispatchTouchEventSupper(e);    
        }    
        return super.dispatchTouchEvent(e);    
    }    
}   