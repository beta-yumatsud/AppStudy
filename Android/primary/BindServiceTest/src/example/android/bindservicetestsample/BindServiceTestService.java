package example.android.bindservicetestsample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BindServiceTestService extends Service {
    // Timerオブジェクト
	private Timer timer = null;
	// 経過時間
	private int countTime;
	// コールバックオブジェクト
	ActivityAIDL callback = null;
	// 一時停止フラグ
	boolean flg = false;
	
	// バインドイベントハンドラ
	@Override
	public IBinder onBind(Intent intent){
		if(ServiceAIDL.class.getName().equals(intent.getAction())){
			return serviceCallbackIf;
		}
		return null;
	}
	
	// アンバインドイベントハンドラ
	@Override
	public boolean onUnbind(Intent intent){
		// タイマー設定解除
		timer.cancel();
		timer.purge();
		
		return super.onUnbind(intent);
	}
	
	// onCreateメソッド(サービス初期起動イベントハンドラ)
	@Override
	public void onCreate(){
		super.onCreate();
		// タイマーと経過時間初期化
		timer = new Timer();
		countTime = 0;
		
		// タイマー設定(1ミリ秒毎にrunメソッド呼び出し)
		timer.schedule(task, 0, 1);
	}
	
	// サービスに連携するインターフェースオブジェクト
	private ServiceAIDL.Stub serviceCallbackIf = new ServiceAIDL.Stub() {
		@Override
		public void stopService() throws RemoteException {
			// メッセージを送信
			BindServiceTestService.this.sendDisplayTime(false);
			// 一時停止フラグを変更
			flg = true;
		}
		
		@Override
		public void startService(ActivityAIDL callback) throws RemoteException {
			// コールバックオブジェクト取得
			BindServiceTestService.this.callback = callback;
			// 一時停止フラグを変更
			flg = false;
		}
		
		@Override
		public void resetService(ActivityAIDL callback) throws RemoteException {
			// メッセージを送信
			BindServiceTestService.this.sendDisplayTime(true);
			// コールバックオブジェクト削除
			BindServiceTestService.this.callback = null;
			// 一時停止フラグを変更
			flg = false;
			// 経過時間を初期化
			countTime = 0;
		}
	};
	
	// TimerTaskオブジェクト生成
	private TimerTask task = new TimerTask(){
		@Override
		public void run(){
			if( !flg ){
				// メッセージを送信
				BindServiceTestService.this.sendDisplayTime(false);
			}
			// コンマ1秒毎にカウントアップ
			countTime += 1;
		}
	};
	
	private void sendDisplayTime(boolean stopFlg){
		try{
			String time = "00:00:00";
			if( !stopFlg ){
				String countSec = String.valueOf(countTime % (1000 * 60) / 1000);
				if( countTime % (1000 * 60) / 1000 < 10 ){
					countSec = "0" + countTime % (1000 * 60) / 1000;
				}
				String countMin = String.valueOf(countTime / (1000 * 60));
				if( countTime / (1000 * 60) < 10 ){
					countMin = "0" + countTime / (1000 * 60);
				}
				String countMSec = String.valueOf(countTime % 1000 / 10);
				if( countTime % 1000 / 10 < 10 ){
					countMSec = "0" + countTime % 1000 / 10;
				}
				time = countMin + ":" + countSec + "." + countMSec;
			}
			
			if( this.callback != null ){
				this.callback.displayTime(time);
			}
		}catch(RemoteException e){
			Log.e("ERROR", e.getMessage());
		}
	}
}