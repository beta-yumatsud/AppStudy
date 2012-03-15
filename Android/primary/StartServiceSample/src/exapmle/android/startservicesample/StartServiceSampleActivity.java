package exapmle.android.startservicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartServiceSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startservicesample);
        // 開始ボタンにクリックリスナー設定
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new StartButtonClickListener());
        // 終了ボタンにクリックリスナー設定
        Button stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new StopButtonClickListener());
    }
    // 開始ボタンリスナークラス
    class StartButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		// インテント生成
    		Intent intent = new Intent(StartServiceSampleActivity.this, StartServiceSampleService.class);
    		// 終了時間をインテントに設定
    		EditText stopcount = (EditText)findViewById(R.id.stopcount);
    		intent.putExtra("STOPTIME", stopcount.getText().toString());
    		// サービス開始
    		startService(intent);
    	}
    }
    // 終了ボタンリスナークラス
    class StopButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		// インテント生成
    		Intent intent = new Intent(StartServiceSampleActivity.this, StartServiceSampleService.class);
    		// サービス終了
    		stopService(intent);
    	}
    }
}