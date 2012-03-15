package example.android.bindservicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BindServiceSampleActivity extends Activity {
	// サービスに連携するインターフェース
	private BindServiceAIDL bindserviceIf = null;
	// 接続オブジェクト
	private ServiceConnection conn = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindservicesample);
        
        // 開始ボタンオブジェクトにクリックリスナー設定
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new StartButtonClickListener());
        
        // 終了ボタンオブジェクトにクリックリスナー設定
        Button stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new StopButtonClickListener());
    }
    
    // 開始ボタンクリックリスナー定義
    class StartButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		// メッセージ表示
    		Toast.makeText(BindServiceSampleActivity.this, "サービスを開始します", Toast.LENGTH_SHORT).show();
    		// インテント生成
    		Intent intent = new Intent(BindServiceAIDL.class.getName());
    		// Service接続・切断用オブジェクト生成
    		conn = new SampleServiceConnection();
    		// サービスにバインド
    		bindService(intent, conn, BIND_AUTO_CREATE);
    	}
    }
    
    // 終了ボタンクリックリスナー定義
    class StopButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		try{
    			// アクティビティに連携するインターフェースを解除
    			bindserviceIf.unregisterCallback(bindactivityIf);
    		}catch(RemoteException e){
    			Log.e("ERROR", e.getMessage());
    		}
    		// メッセージ表示
    		Toast.makeText(BindServiceSampleActivity.this, "サービスを終了します", Toast.LENGTH_SHORT).show();
    		// サービスをアンバインド
    		unbindService(conn);
    		// インテント生成
    		Intent intent = new Intent(BindServiceAIDL.class.getName());
    		// サービスを停止
    		stopService(intent);
    	}
    }
    
    // サービス接続・切断クラス定義
    class SampleServiceConnection implements ServiceConnection{
    	// 接続イベントハンドラ
    	public void onServiceConnected(ComponentName compName, IBinder binder){
    		// サービスと連携するインターフェース取得
    		bindserviceIf = BindServiceAIDL.Stub.asInterface(binder);
    		try{
    			// アクティビティに連携するインターフェースを登録
    			bindserviceIf.registerCallback(bindactivityIf);
    		}catch(RemoteException e){
    			Log.e("ERROR", e.getMessage());
    		}
    	}
    	// 切断イベントハンドラ
    	public void onServiceDisconnected(ComponentName arg0){
    		bindserviceIf = null;
    	}
    }
    
    // アクティビティに連携するインターフェースオブジェクト
    private BindActivityAIDL bindactivityIf = new BindActivityAIDL.Stub() {
		@Override
		public void displayTime(String msg) throws RemoteException {
			// handlerにメッセージを送信
			handler.sendMessage(Message.obtain(handler, 0, msg));
		}
	};
	
	// ハンドラ生成
	private Handler handler = new Handler(){
		// メッセージ表示
		public void handleMessage(Message msg){
			Toast.makeText(BindServiceSampleActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
		}
	};
}