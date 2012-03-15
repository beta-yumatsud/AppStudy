package example.android.banana;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class BananaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bananalayout);
        
        // URI�擾
        Uri uri = getIntent().getData();
        if( uri != null ){
        	// URI��QueryString���擾
        	String fruitname = uri.getQueryParameter("selecteditem");
        	// TextView�I�u�W�F�N�g�擾
        	TextView fruittext = (TextView)findViewById(R.id.fruitname);
        	// �ʕ��̖��O�\��
        	fruittext.setText(fruitname);
        }
        
        // �{�^���I�u�W�F�N�g�擾
        Button button = (Button)findViewById(R.id.backbutton);
        // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
        button.setOnClickListener(new ButtonClickListener());
    }
    
    // �N���b�N���X�i�[��`
    class ButtonClickListener implements OnClickListener{
    	// onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
    	@Override
    	public void onClick(View v){
    		// �A�N�e�B�r�e�B�I��(��ʃN���[�Y)
    		finish();
    	}
    }
}