package winning.focusedittext;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * 定时器 控制短信验证码发送
 * */
public class SmsTimer extends CountDownTimer
{

    /**
     * 获取验证码按钮对象
     */
    private Button tv_code;

    private long countDownInterval = 1000;

    /**
     * @param millisInFuture 定时总时间
     * @param countDownInterval 执行时间间隔
     * @param button 获取验证码按钮
     * */
    public SmsTimer(Button tv_code)
    {
        super(60 * 1000 - 1,1000);
        this.tv_code = tv_code;
        this.countDownInterval = 1000;

    }

    @Override
    public void onFinish()
    {
        tv_code.setClickable(true);
        tv_code.setText("获取");
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
        tv_code.setClickable(false);
        tv_code.setText(millisUntilFinished / countDownInterval + "秒");
    }

    public void stop()
    {
        this.cancel();
        this.onFinish();
    }

}
