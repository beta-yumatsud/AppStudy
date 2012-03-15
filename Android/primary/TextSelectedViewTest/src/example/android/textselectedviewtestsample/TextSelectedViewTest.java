package example.android.textselectedviewtestsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TextSelectedViewTest extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textselectedviewtest);
        
        // Spinnerオブジェクト取得
        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        // Spinnerオブジェクトにイベントリスナーを関連づけ
        spinner.setOnItemSelectedListener(new SelectItemSelectedListener());
    }
    // イベントリスナー定義
    class SelectItemSelectedListener implements OnItemSelectedListener{
    	// onItemSelectedメソッド
    	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
    		// クリック時のSpinnerオブジェクト取得
    		Spinner spinner = (Spinner)parent;
    		// 選択されたリストの値取得
    		String item = (String)spinner.getItemAtPosition(position);
    		// テキストボックスの入力情報取得
    		EditText input = (EditText)findViewById(R.id.text_name);
    		
    		// 入力情報をトースト機能で画面表示
    		Toast.makeText(TextSelectedViewTest.this, item+"\n"+input.getText()+"さん", Toast.LENGTH_SHORT).show();
    	}
    	// onNothingSelectedメソッド
    	public void onNothingSelected(AdapterView<?> parent){}
    }
}