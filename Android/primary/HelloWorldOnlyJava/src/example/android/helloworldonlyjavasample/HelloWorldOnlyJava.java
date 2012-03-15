package example.android.helloworldonlyjavasample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloWorldOnlyJava extends Activity {
    /** Called when the activity is first created. */
	// match_parent定数
	private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
	// wrap_content定数
	private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	// onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        
        // レイアウトの生成
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MP, MP));
        // レイアウト設定
        setContentView(layout);
        
        // テキストビューの生成
        TextView textView = new TextView(this);
        textView.setText("Hello World!!");
        textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        
        // テキスュビューをレイアウトに追加
        layout.addView(textView);
    }
}