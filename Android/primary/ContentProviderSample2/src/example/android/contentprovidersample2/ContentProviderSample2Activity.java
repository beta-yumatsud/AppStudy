package example.android.contentprovidersample2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.net.Uri;

public class ContentProviderSample2Activity extends Activity {
	TableLayout tablelayout = null;
	
	// 取得データ選択(0:電話帳の名前, 1:通話履歴の番号)
	int type = 1;
	Uri uri;
	String index;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentprovidersample2);
        
        // テーブルレイアウト取得
        tablelayout = (TableLayout)findViewById(R.id.tablelayout);
        
        try{
        	// データ取得
        	if( type == 0 ){
        		uri = Contacts.CONTENT_URI;
        		index = Contacts.DISPLAY_NAME;
        	}else{
        		uri = CallLog.Calls.CONTENT_URI;
        		index = CallLog.Calls.NUMBER;
        	}
        	Cursor cur = managedQuery(uri, null, null, null, null);

        	// データが取得できた場合
        	if( cur.getCount() != 0 ){
        		// 取得したデータ表示
        		while( cur.moveToNext() ){
        			String name = cur.getString(cur.getColumnIndex(index));
        			setItems(name);
        		}
        	}else{
        		TextView message = new TextView(this);
        		message.setText("データが取得できませんでした。");
        		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayout);
        		linearlayout.addView(message);
        	}
        }catch(Exception e){
        	Log.e("ERROR", e.getMessage());
        }
    }
    
    // テーブル表示処理
    private void setItems( String name ){
    	// 名前を設定
    	TableRow row = new TableRow(this);
    	TextView displayName = new TextView(this);
    	displayName.setText(name);
    	row.addView(displayName);
    	// テーブルレイアウトに設定
    	tablelayout.addView(row);
    }
}