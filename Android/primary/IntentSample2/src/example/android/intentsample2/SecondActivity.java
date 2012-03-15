package example.android.intentsample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class SecondActivity extends Activity {	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        
        // ボタンオブジェクト取得
        Button button = (Button)findViewById(R.id.backbutton);
        // ボタンオブジェクトにクリックリスナー設定
        button.setOnClickListener(new ButtonClickListener());
    }
    
    // クリックリスナー定義
    class ButtonClickListener implements OnClickListener{
    	// onClickメソッド(ボタンクリック時イベントハンドラ)
    	public void onClick(View v){
    		// インテント取得
    		Intent intent = getIntent();
    		
    		// Spinnerオブジェクト取得
    		Spinner spinner = (Spinner)findViewById(R.id.greeting);
    		// 選択されたあいさつ取得
    		String greeting = (String)spinner.getSelectedItem();
    		
    		// 選択された値をインテントに設定
    		intent.putExtra("SELECTED_GREETING", greeting);
    		// 結果情報の設定
    		setResult(RESULT_OK, intent);
    		
    		// アクティビティ終了
    		finish();
    	}
    }
}