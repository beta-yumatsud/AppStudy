package example.android.menudialogsample;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.content.SharedPreferences;
import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MenuDialogSampleActivity extends Activity {
	// プリファレンスファイル名
	private static final String FILE_NAME = "MenuDialogFile";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudialogsample);
    }
    
    // オプションメニュー設定
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	// メニューアイテム1の追加
    	MenuItem item1 = menu.add(0, 0, 0, "設定");
    	item1.setIcon(android.R.drawable.ic_menu_add);
    	// メニューアイテム2の追加
    	MenuItem item2 = menu.add(0, 1, 0, "表示");
    	item2.setIcon(android.R.drawable.ic_menu_info_details);
    	return true;
    }
    
    // メニューアイテム選択処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch( item.getItemId() ){
    	case 0:
    		showDatePickerDialog();
    		return true;
    	case 1:
    		// プリファレンスオブジェクト取得
    		SharedPreferences preference = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
    		
    		// 保存データ取得
    		String date = preference.getString("DATE", "ありません");
    		
    		showDialog(date);
    		return true;
    	}
    	return true;
    }
    
    // 日付選択ダイアログの表示
    public void showDatePickerDialog(){
    	Calendar cal = Calendar.getInstance();
    	DatePickerDialog dialog = new DatePickerDialog(MenuDialogSampleActivity.this
    			, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker picker, int year, int month, int day) {
						// プリファレンスオブジェクト取得
			    		SharedPreferences preference = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
			    		// プリファレンス編集用オブジェクト取得
			    		SharedPreferences.Editor editor = preference.edit();
			    		// 取得した文字列をプリファレンスファイルに保存
			    		editor.putString("DATE", year + "年" + (month + 1) + "月" + day + "日");
			    		editor.commit();
					}
				}
    			,cal.get(Calendar.YEAR)
    			,cal.get(Calendar.MONTH)
    			,cal.get(Calendar.DAY_OF_MONTH)
    	);
    	dialog.show();
    }
    
    // ダイアログ表示
    private void showDialog(String text){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(MenuDialogSampleActivity.this);
    	dialog.setTitle("保存結果");
    	dialog.setMessage(text);
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				MenuDialogSampleActivity.this.setResult(Activity.RESULT_OK);
			}
		});
    	dialog.create();
    	dialog.show();
    }
}