package exapmle.android.startservicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class StartServiceSampleService extends Service {
    // Timerオブジェクト
	private Timer timer = null;
	// 経過時間
	private int countTime;
	// 終了時間
	private int stopTime;
	
	@Override
	public IBinder onBind(Intent arg0){
		return null;
	}
	
	// onCreateメソッド(サービス初期起動イベントハンドラ)
	public void onCreate(){
		super.onCreate();
		
		// トースト表示
		Toast.makeText(this, "サービスを起動します。", Toast.LENGTH_SHORT).show();
		// タイマーと経過時間初期化
		timer = new Timer();
		countTime = 0;
	}
	
	// onStartメソッド(サービス開始イベントハンドラ)
	@Override
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		
		// トースト表示
		Toast.makeText(this, "サービスを開始します。", Toast.LENGTH_SHORT).show();
		// タイマー設定(10秒毎にrunメソッド呼び出し)
		timer.schedule(task, 10000, 10000);
		
        // 終了時間取得
		Bundle bundle = intent.getExtras();
		stopTime = Integer.parseInt(bundle.getString("STOPTIME"));
	}
	
	// onDestroyメソッド(サービス終了イベントハンドラ)
	@Override
	public void onDestroy(){
		super.onDestroy();
		// トースト表示(例題用)
		Toast.makeText(this, "サービスを終了します。", Toast.LENGTH_SHORT).show();
		// タイマー設定解除
		timer.cancel();
		timer.purge();
		
		// 演習10用
		/*
		handler.removeMessages(0);
		// SMS起動
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.putExtra("address", "09012345678");
		intent.putExtra("sms_body",
				countTime / 60 + "分" +
				countTime % 60 + "秒経過して、サービスが終了しました！");
		intent.setType("vnd.android-dir/mms-sms");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		*/
	}
	
	// ハンドラー生成
	private Handler handler = new Handler(){
		// メッセージ表示
		public void handleMessage(Message msg){
			Toast.makeText(StartServiceSampleService.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
		}
	};
	
	// TimerTaskオブジェクト生成
	private TimerTask task = new TimerTask(){
		@Override
		public void run(){
			// 10秒毎にカウントアップ
			countTime += 10;
			
			if( countTime / 60 == stopTime ){
				// サービス終了
				stopSelf();
			}else{
				// handlerにメッセージ送信
				handler.sendMessage(Message.obtain(handler, 0, countTime / 60 + "秒" + countTime % 60 + "秒経過しました！"));
			}
		}
	};
}