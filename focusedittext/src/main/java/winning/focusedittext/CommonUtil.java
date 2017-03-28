package winning.focusedittext;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CommonUtil {
    public static Toast toast = null;

    public static void showToast(Context context, String text) {
        final Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
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
        }, 0, 4000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, 4000);
    }
}