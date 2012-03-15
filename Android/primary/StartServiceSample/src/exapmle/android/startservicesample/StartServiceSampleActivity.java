package exapmle.android.startservicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartServiceSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startservicesample);
        // �J�n�{�^���ɃN���b�N���X�i�[�ݒ�
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new StartButtonClickListener());
        // �I���{�^���ɃN���b�N���X�i�[�ݒ�
        Button stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new StopButtonClickListener());
    }
    // �J�n�{�^�����X�i�[�N���X
    class StartButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		// �C���e���g����
    		Intent intent = new Intent(StartServiceSampleActivity.this, StartServiceSampleService.class);
    		// �I�����Ԃ��C���e���g�ɐݒ�
    		EditText stopcount = (EditText)findViewById(R.id.stopcount);
    		intent.putExtra("STOPTIME", stopcount.getText().toString());
    		// �T�[�r�X�J�n
    		startService(intent);
    	}
    }
    // �I���{�^�����X�i�[�N���X
    class StopButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		// �C���e���g����
    		Intent intent = new Intent(StartServiceSampleActivity.this, StartServiceSampleService.class);
    		// �T�[�r�X�I��
    		stopService(intent);
    	}
    }
}