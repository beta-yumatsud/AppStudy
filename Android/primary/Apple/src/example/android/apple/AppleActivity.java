package example.android.apple;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AppleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applelayout);
        
        // URI取得
        Uri uri = getIntent().getData();
        if( uri != null ){
        	// URIのQueryString情報取得
        	String fruitname = uri.getQueryParameter("selecteditem");
        	// TextViewオブジェクト取得
        	TextView fruittext = (TextView)findViewById(R.id.fruitname);
        	// 果物の名前表示
        	fruittext.setText(fruitname);
        }
        
        // ボタンオブジェクト取得
        Button button = (Button)findViewById(R.id.backbutton);
        // ボタンオブジェクトにクリックリスナー設定
        button.setOnClickListener(new ButtonClickListener());
    }
    
    // クリックリスナー定義
    class ButtonClickListener implements OnClickListener{
    	// onClickメソッド(ボタンクリック時イベントハンドラ)
    	@Override
    	public void onClick(View v){
    		// アクティビティ終了(画面クローズ)
    		finish();
    	}
    }
}