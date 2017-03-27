package winning.errorsound;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_start;
    private Button btn_show;
    private MediaPlayer mp;//mediaPlayer对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(MainActivity.this, R.raw.error);//创建mediaplayer对象
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, getLocationRealCode("idm/aaaa"), Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.release();//释放资源
        super.onDestroy();
    }

    public String getLocationRealCode(String s) {
        String returnCode;
        try {
            String[] strings = s.split("/");
            returnCode = strings[1].trim();
        } catch (Exception ex) {
            returnCode = s;
        }
        return returnCode;
    }


    private void play() {
        try {
            mp.reset();
            mp = MediaPlayer.create(MainActivity.this, R.raw.error);//重新设置要播放的音频
            mp.start();//开始播放
        } catch (Exception e) {
            e.printStackTrace();//输出异常信息
        }
    }

}
