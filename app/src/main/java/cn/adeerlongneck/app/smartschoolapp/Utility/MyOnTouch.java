package cn.adeerlongneck.app.smartschoolapp.Utility;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by 长脖鹿 on 2017/12/13.
 */

public class MyOnTouch  implements View.OnClickListener, View.OnTouchListener {


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP){
            int chang=view.getWidth();
            ViewGroup.LayoutParams  lp = view.getLayoutParams();
            lp.width +=12;
            lp.height +=12;
            view.setLayoutParams(lp);
            Log.d("test", "cansal button ---> cancel"+chang);
           // view.setBackgroundResource(R.drawable.green);
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ViewGroup.LayoutParams  lp = view.getLayoutParams();
            lp.width -=12;
            lp.height -=12;
            view.setLayoutParams(lp);
            Log.d("test", "cansal button ---> down");
          //  view.setBackgroundResource(R.drawable.yellow);
        }



        return false;
    }
}
