package winning.focusedittext;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Toast工具类
 *
 * @version 2012-5-21 下午9:21:01
 */
public class ToastUtil {
    private static Toast toast = null;
    private static Timer timer = new Timer();
    private static int i = 0;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);

            LinearLayout layout = (LinearLayout) toast.getView();
            TextView textView = (TextView) layout.getChildAt(0);
            textView.setTextSize(20);
            toast.show();

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.show();
                }
            }, 0, 1000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                    timer.cancel();
                    toast = null;
                }
            }, 4000);

        } else {
            i++;
            toast.setText(i+"");
        }


    }
}