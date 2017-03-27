package winning.focusedittext;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText et_focus;
    private EditText et_anim;
    private Button tv_test;
    private Button test_coun;
    private TextView tv_count;
    private TextView tv_left;
    private TextView tv_right;
    private LinearLayout ll_anim;

    //获取一个日历对象
    private Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);

    //获取日期格式器对象
    private DateFormat fmtDate = new SimpleDateFormat("yyyyMMdd");


    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            upDateDate();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_focus = (EditText) findViewById(R.id.et_focus);
        et_focus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (et_focus.isFocused()) {
                    et_focus.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(MainActivity.this, "焦点", Toast.LENGTH_SHORT).show();
                } else {
                    et_focus.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        tv_test = (Button) findViewById(R.id.test_count);
        final SmsTimer smsTimer = new SmsTimer(tv_test);
//        smsTimer.start();

        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsTimer.start();
            }
        });

        tv_count = (TextView) findViewById(R.id.tv_count);

        tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = tv_count.getText().toString().trim();
                DatePickerDialog dateDlg = new DatePickerDialog(MainActivity.this, dateSetListener,
                        Integer.parseInt(date.substring(0, 4)),
                        Integer.parseInt(date.substring(4, 5)),
                        Integer.parseInt(date.substring(5, 6)));
                dateDlg.show();
            }
        });


        test_coun = (Button) findViewById(R.id.test_coun);
        test_coun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = "★ 17-4-2";
                if ("★ 17-4-2".contains("★")) {
                    String test = string.substring(("★").length());
                    Toast.makeText(MainActivity.this, test.trim(), Toast.LENGTH_LONG).show();
                }
            }
        });

        ll_anim = (LinearLayout) findViewById(R.id.ll_anim);
        et_anim = (EditText) findViewById(R.id.et_anim);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);

        et_anim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ObjectAnimator oa0 = ObjectAnimator.ofFloat(ll_anim, "TranslationX", -50);
                    oa0.setDuration(200).start();
                    oa0.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            tv_right.setVisibility(View.VISIBLE);
                            tv_left.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                } else {
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(ll_anim, "TranslationX", 30);
                    oa1.setDuration(200).start();
                    oa1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            tv_left.setVisibility(View.VISIBLE);
                            tv_right.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });
    }

    /**
     * 更新日期
     */
    private void upDateDate() {
        tv_count.setText(fmtDate.format(dateAndTime.getTime()));
    }

}
