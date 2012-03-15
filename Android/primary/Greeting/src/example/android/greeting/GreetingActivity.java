package example.android.greeting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetinglayout);
        
        // �{�^���I�u�W�F�N�g�擾
        Button button = (Button)findViewById(R.id.button1);
        // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
        button.setOnClickListener(new ButtonClickLisener());
    }
    // �N���b�N���X�i�[��`
    class ButtonClickLisener implements OnClickListener{
    	public void onClick(View arg0){
    		// ���͒l�̎擾
    		EditText input = (EditText)findViewById(R.id.hourtext);
    		
    		// �C���e���g�̐���
    		Intent intent = new Intent(Intent.ACTION_VIEW);
    		
    		// ���͒l�̐U�蕪��
    		int time = 0;
    		try{
    			time = Integer.parseInt(input.getText().toString());
    		}catch( NumberFormatException e){
    			// ���͏����g�[�X�g�@�\�ŉ�ʕ\��
    			Toast.makeText(GreetingActivity.this, "�s���Ȓl�����͂���܂����I", Toast.LENGTH_SHORT).show();
    			// �v���O�����̏I��
    			return;
    		}
    		
    		String text = "";
    		String prefix = "";
    		if( time >= 4 && time <= 12 ){
    			text = "���͂悤�I";
    			prefix = "goodmorning";
    		}else if( time >= 13 && time <= 18 ){
    			text = "����ɂ��́I";
    			prefix = "goodafternoon";
    		}else if( ( time >= 19 && time <= 24 ) || ( time >= 1 && time <= 3 ) ){
    			text = "����΂�́I";
    			prefix = "goodevening";
    		}else{
    			// ���͏����g�[�X�g�@�\�ŉ�ʕ\��
    			Toast.makeText(GreetingActivity.this, "�s���Ȓl�����͂���܂����I", Toast.LENGTH_SHORT).show();
    			// �v���O�����̏I��
    			return;
    		}
    		// URI�ݒ�
    		Uri uri = Uri.parse("intentmondai:///"+prefix+"?greeting="+text);
    		// URI���C���e���g�ɐݒ�
    		intent.setData(uri);
    		// ���̃A�N�e�B�r�e�B�N��
    		startActivity(intent);
    	}
    }
}