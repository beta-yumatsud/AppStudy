package example.android.bindservicetestsample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BindServiceTestService extends Service {
    // Timer�I�u�W�F�N�g
	private Timer timer = null;
	// �o�ߎ���
	private int countTime;
	// �R�[���o�b�N�I�u�W�F�N�g
	ActivityAIDL callback = null;
	// �ꎞ��~�t���O
	boolean flg = false;
	
	// �o�C���h�C�x���g�n���h��
	@Override
	public IBinder onBind(Intent intent){
		if(ServiceAIDL.class.getName().equals(intent.getAction())){
			return serviceCallbackIf;
		}
		return null;
	}
	
	// �A���o�C���h�C�x���g�n���h��
	@Override
	public boolean onUnbind(Intent intent){
		// �^�C�}�[�ݒ����
		timer.cancel();
		timer.purge();
		
		return super.onUnbind(intent);
	}
	
	// onCreate���\�b�h(�T�[�r�X�����N���C�x���g�n���h��)
	@Override
	public void onCreate(){
		super.onCreate();
		// �^�C�}�[�ƌo�ߎ��ԏ�����
		timer = new Timer();
		countTime = 0;
		
		// �^�C�}�[�ݒ�(1�~���b����run���\�b�h�Ăяo��)
		timer.schedule(task, 0, 1);
	}
	
	// �T�[�r�X�ɘA�g����C���^�[�t�F�[�X�I�u�W�F�N�g
	private ServiceAIDL.Stub serviceCallbackIf = new ServiceAIDL.Stub() {
		@Override
		public void stopService() throws RemoteException {
			// ���b�Z�[�W�𑗐M
			BindServiceTestService.this.sendDisplayTime(false);
			// �ꎞ��~�t���O��ύX
			flg = true;
		}
		
		@Override
		public void startService(ActivityAIDL callback) throws RemoteException {
			// �R�[���o�b�N�I�u�W�F�N�g�擾
			BindServiceTestService.this.callback = callback;
			// �ꎞ��~�t���O��ύX
			flg = false;
		}
		
		@Override
		public void resetService(ActivityAIDL callback) throws RemoteException {
			// ���b�Z�[�W�𑗐M
			BindServiceTestService.this.sendDisplayTime(true);
			// �R�[���o�b�N�I�u�W�F�N�g�폜
			BindServiceTestService.this.callback = null;
			// �ꎞ��~�t���O��ύX
			flg = false;
			// �o�ߎ��Ԃ�������
			countTime = 0;
		}
	};
	
	// TimerTask�I�u�W�F�N�g����
	private TimerTask task = new TimerTask(){
		@Override
		public void run(){
			if( !flg ){
				// ���b�Z�[�W�𑗐M
				BindServiceTestService.this.sendDisplayTime(false);
			}
			// �R���}1�b���ɃJ�E���g�A�b�v
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