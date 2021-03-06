package example.android.greeting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetinglayout);
        
        // ボタンオブジェクト取得
        Button button = (Button)findViewById(R.id.button1);
        // ボタンオブジェクトにクリックリスナー設定
        button.setOnClickListener(new ButtonClickLisener());
    }
    // クリックリスナー定義
    class ButtonClickLisener implements OnClickListener{
    	public void onClick(View arg0){
    		// 入力値の取得
    		EditText input = (EditText)findViewById(R.id.hourtext);
    		
    		// インテントの生成
    		Intent intent = new Intent(Intent.ACTION_VIEW);
    		
    		// 入力値の振り分け
    		int time = 0;
    		try{
    			time = Integer.parseInt(input.getText().toString());
    		}catch( NumberFormatException e){
    			// 入力情報をトースト機能で画面表示
    			Toast.makeText(GreetingActivity.this, "不正な値が入力されました！", Toast.LENGTH_SHORT).show();
    			// プログラムの終了
    			return;
    		}
    		
    		String text = "";
    		String prefix = "";
    		if( time >= 4 && time <= 12 ){
    			text = "おはよう！";
    			prefix = "goodmorning";
    		}else if( time >= 13 && time <= 18 ){
    			text = "こんにちは！";
    			prefix = "goodafternoon";
    		}else if( ( time >= 19 && time <= 24 ) || ( time >= 1 && time <= 3 ) ){
    			text = "こんばんは！";
    			prefix = "goodevening";
    		}else{
    			// 入力情報をトースト機能で画面表示
    			Toast.makeText(GreetingActivity.this, "不正な値が入力されました！", Toast.LENGTH_SHORT).show();
    			// プログラムの終了
    			return;
    		}
    		// URI設定
    		Uri uri = Uri.parse("intentmondai:///"+prefix+"?greeting="+text);
    		// URIをインテントに設定
    		intent.setData(uri);
    		// 次のアクティビティ起動
    		startActivity(intent);
    	}
    }
}