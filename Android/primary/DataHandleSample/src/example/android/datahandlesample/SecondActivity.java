package example.android.datahandlesample;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

public class SecondActivity extends Activity {
	// プリファレンスファイル名
	private static final String PFILE_NAME = "PreferenceDataHandle";
	// ファイル名
	private static final String FILE_NAME = "FileDataHandle";
	
	// 入力データ
	String name = "";
	String address = "";
	String month = "";
	String day = "";
	String gender = "";
	String apple = "";
	String orange = "";
	String peach = "";
	
	// 保存データ形式(p:プリファレンス, f:ファイル, s:SQLite)
	// ※通常このようなことはしないが、演習問題を１つにまとめるため
	String data_type = "p";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        
        // インテントの付加情報取得
        Bundle extra = getIntent().getExtras();
        
        // 付加情報から入力データ取得
        name = extra.getString("NAME");
        address = extra.getString("ADDRESS");
        month = extra.getString("MONTH");
        day = extra.getString("DAY");
        gender = extra.getString("GENDER");
        apple = extra.getString("APPLE");
        orange = extra.getString("ORANGE");
        peach = extra.getString("PEACH");
        
        // 出力用テキストオブジェクト取得
        TextView inputName = (TextView)findViewById(R.id.name);
        TextView inputAddress = (TextView)findViewById(R.id.address);
        TextView inputMonth = (TextView)findViewById(R.id.month);
        TextView inputDay = (TextView)findViewById(R.id.day);
        TextView inputGender = (TextView)findViewById(R.id.gender);
        TextView inputApple = (TextView)findViewById(R.id.apple);
        TextView inputOrange = (TextView)findViewById(R.id.orange);
        TextView inputPeach = (TextView)findViewById(R.id.peach);
        
        // 出力用テキストオブジェクトに入力データ設定
        inputName.setText(name);
        inputAddress.setText(address);
        inputMonth.setText(month);
        inputDay.setText(day);
        inputGender.setText(gender);
        if( apple != null ) inputApple.setText(apple);
        if( orange != null ) inputOrange.setText(orange);
        if( peach != null ) inputPeach.setText(peach);
        
        // 戻るボタンのクリックリスナー設定
        Button backbutton = (Button)findViewById(R.id.backbutton);
        backbutton.setTag("back");
        backbutton.setOnClickListener(new ButtonBackClickLisener());
        // 確認ボタンのクリックリスナー設定
        Button confirmbutton = (Button)findViewById(R.id.confirmbutton);
        confirmbutton.setTag("confirm");
        confirmbutton.setOnClickListener(new ButtonBackClickLisener());
    }
    // クリックリスナー定義
    class ButtonBackClickLisener implements OnClickListener{
    	public void onClick(View v){
    		// タグの取得
    		String tag = (String)v.getTag();
    		
    		// 確認ボタンが押された場合
    		if( tag.equals("confirm") ){
    			// 保存形式がプリファレンスの場合
    			if( data_type.equals("p") ){
    				// プリファレンスオブジェクト取得
    	    		SharedPreferences preference = getSharedPreferences(PFILE_NAME, MODE_PRIVATE);
    	    		// プリファレンスの編集用オブジェクト取得
        			SharedPreferences.Editor editor = preference.edit();
        			// プリファレンスファイルに保存
        			editor.putString("NAME", name);
        			editor.putString("ADDRESS", address);
        			editor.putString("MONTH", month);
        			editor.putString("DAY", day);
        			editor.putString("GENDER", gender);
        			editor.putString("APPLE", apple);
        			editor.putString("ORANGE", orange);
        			editor.putString("PEACH", peach);
        			editor.commit();
        			
        		// 保存形式がファイルの場合
    			}else if( data_type.equals("f") ){
    				// ファイルにデータ保存
        			try{
        				FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
        				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
        				
        				out.write(name + "," +
        						address + "," +
        						month + "/" + day + "," +
        						gender + "," +
        						apple + "/" +
        						orange + "," +
        						peach);
        				out.newLine();
        				out.close();
        			}catch(Exception e){
        				Log.e("FILE_ERROR", "ファイル書き込みに失敗しました");
        			}
        			
        		// 保存形式がSQLiteの場合
    			}else{
    				// DBアクセス
    				CreateProductHelper helper = new CreateProductHelper(SecondActivity.this);
    				// DBオブジェクト取得
    				SQLiteDatabase db = helper.getWritableDatabase();
    				// テーブル作成
        			try{
        				String sql = "create table datahandle(" +
        							 "_id integer primary key autoincrement," +
        							 "name text not null," +
        							 "address text not null," +
        							 "gender text not null," +
        							 "apple integer default 0," +
        							 "orange integer default 0," +
        							 "peach integer default 0)";
        				// SQL実行
        				db.execSQL(sql);
        			}catch(Exception e){
        				Log.e("CREATE_TABLE_ERROR", e.toString());
        			}
        			
        			// データ登録
        			try{
        				// トランザクション制御開始
        				db.beginTransaction();
        				
        				// 登録データ設定
        				ContentValues val = new ContentValues();
        				val.put("name", name);
        				val.put("address", address);
        				val.put("gender", gender);
        				val.put("apple", apple);
        				val.put("orange", orange);
        				val.put("peach", peach);
        				db.insert("datahandle", null, val);
        				// コミット
        				db.setTransactionSuccessful();
        				// トランザクション制御終了
        				db.endTransaction();
        				// DBオブジェクトクローズ
        	    		db.close();
        			}catch(Exception e){
        				Log.e("DB_ERROR", "データベース登録に失敗しました。");
        			}
    			}
    			
    			// インテントの生成
    			Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
    			// 次のアクティビティの起動
    			startActivity(intent);
    			
    		// 戻るボタンが押された場合
    		}else if( tag.endsWith("badk") ){
    			// 画面をクローズ
    			finish();
    		}
    	}
    }
}