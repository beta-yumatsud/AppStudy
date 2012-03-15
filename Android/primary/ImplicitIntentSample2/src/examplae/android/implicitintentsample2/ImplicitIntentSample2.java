package examplae.android.implicitintentsample2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ImplicitIntentSample2 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicitintentsample2);
        
        // �{�^���I�u�W�F�N�g�擾
        Button button1 = (Button)findViewById(R.id.button1);
        // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
        button1.setOnClickListener(new ButtonClickListener());
    }
    
    // �N���b�N���X�i�[��`
    class ButtonClickListener implements OnClickListener{
    	// onClick���\�b�h(�{�^���N���b�N���C�x���g)
    	public void onClick(View v){
    		// �e�L�X�g�{�b�N�X�̓��͏��擾
    		EditText content = (EditText)findViewById(R.id.content);
    		String forwardstr = content.getText().toString();
    		
    		// �I�����X�g�̑I�����擾
    		Spinner selectapi = (Spinner)findViewById(R.id.selectapli);
    		String selecteditem = (String)selectapi.getSelectedItem();
    		
    		// �A�N�V�����̐ݒ�
    		String action = null;
    		if( selecteditem.equals("�d�b") ){
    			action = "android.intent.action.DIAL";
    			forwardstr = "tel:" + content.getText().toString();
    		}else if( selecteditem.equals("WEB�u���E�U") ){
    			action = "android.intent.action.VIEW";
    		}else if( selecteditem.equals("�n�}") ){
    			action = "android.intent.action.VIEW";
    			forwardstr = "geo:" + content.getText().toString();
    		}else if( selecteditem.equals("���b�Z�[�W") ){
    			action = "android.intent.action.VIEW";
    		}
    		// �C���e���g�̐���
    		Intent intent = new Intent(action, Uri.parse(forwardstr));
    		if( selecteditem.equals("���b�Z�[�W") ){
    			intent = new Intent(action);
    			intent.putExtra("sms_body", content.getText().toString());
    			intent.setType("vnd.android-dir/mms-sms");
    		}
    		// ���̃A�N�e�B�r�e�B�̋N��
    		startActivity(intent);
    	}
    }
}