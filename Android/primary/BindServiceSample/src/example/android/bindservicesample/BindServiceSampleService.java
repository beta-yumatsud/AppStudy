package example.android.bindservicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class BindServiceSampleService extends Service {
	// コールバック管理リスト
	private final RemoteCallbackList<BindActivityAIDL> callbacklist = new RemoteCallbackList<BindActivityAIDL>();
    // Timerオブジェクト
	private Timer timer = null;
	// 経過時間
	private int countTime;
	
	// バインドイベントハンドラ
	@Override
	public IBinder onBind(Intent intent){
		if(BindServiceAIDL.class.getName().equals(intent.getAction())){
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
		
		// タイマー設定(10秒毎にrunメソッド呼び出し)
		timer.schedule(task, 10000, 10000);
	}
	
	// サービスに連携するインターフェースオブジェクト
	private BindServiceAIDL.Stub serviceCallbackIf = new BindServiceAIDL.Stub() {
		@Override
		//public void unregisterCallback(BindActivityAIDL callback) throws RemoteException {
		public void unregisterCallback(BindActivityAIDL callback) {
			// Callbackリストに登録
			callbacklist.unregister(callback);
		}
		
		@Override
		//public void registerCallback(BindActivityAIDL callback) throws RemoteException {
		public void registerCallback(BindActivityAIDL callback) {
			// Callbackリストから削除
			callbacklist.register(callback);
		}
	};
	
	// TimerTaskオブジェクト生成
	private TimerTask task = new TimerTask(){
		@Override
		public void run(){
			// 10秒毎にカウントアップ
			countTime += 10;
			
			// Callbackのリスト処理を開始
			int n = callbacklist.beginBroadcast();
			// 全アイテム分ループ
			for( int i = 0; i < n ; i++ ){
				try{
					// アクティビティのdisplayTimeメソッド実行
					callbacklist.getBroadcastItem(i).displayTime(
							countTime / 60 +
							"分" +
							countTime % 60 + "秒経過しました！");
				}catch(RemoteException e){
					Log.e("ERROR", e.getMessage());
				}
			}
			// Callbackのリスト処理を終了
			callbacklist.finishBroadcast();
		}
	};
}