package example.android.intentsample1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        
        // インテント取得
        Intent data = getIntent();
        // インテントの付加情報取得
        Bundle extras = data.getExtras();
        // 付加情報から選択された値取得
        String disp_pict = extras != null ? extras.getString("SELECTED_PICT") : "";
        
        // ImageViewオブジェクト取得
        ImageView image = (ImageView)findViewById(R.id.fruitimage);
        // 表示する画像の設定
        if( disp_pict.equals("Apple") ){
        	image.setImageResource(R.drawable.apple);
        }else if( disp_pict.equals("Banana") ){
        	image.setImageResource(R.drawable.banana);
        }else if( disp_pict.equals("Grape")){
        	image.setImageResource(R.drawable.grape);
        }
        
        // Buttonオブジェクト取得
        Button button = (Button)findViewById(R.id.backbutton);
        // ボタンオブジェクトにクリックリスナー設定
        button.setOnClickListener(new ButtonClickListener());
    }
    
    // アイテムクリックリスナー定義
    class ButtonClickListener implements OnClickListener{
    	// onClickメソッド(ボタンクリック時イベントハンドラ)
    	@Override
    	public void onClick(View view){
    		// アクティビティ終了(画面クローズ)
    		finish();
    	}
    }
}