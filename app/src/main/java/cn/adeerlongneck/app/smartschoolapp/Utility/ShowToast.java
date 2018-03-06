package cn.adeerlongneck.app.smartschoolapp.Utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 长脖鹿 on 2017/12/8.
 */

public class ShowToast {
    private  Toast toast;

    public void showToast(Context baseContext, String str) {
        toast.makeText(baseContext,str,Toast.LENGTH_SHORT).show();
    }


}
