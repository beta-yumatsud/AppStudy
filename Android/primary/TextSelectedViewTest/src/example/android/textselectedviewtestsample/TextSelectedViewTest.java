package example.android.textselectedviewtestsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TextSelectedViewTest extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textselectedviewtest);
        
        // Spinner�I�u�W�F�N�g�擾
        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        // Spinner�I�u�W�F�N�g�ɃC�x���g���X�i�[���֘A�Â�
        spinner.setOnItemSelectedListener(new SelectItemSelectedListener());
    }
    // �C�x���g���X�i�[��`
    class SelectItemSelectedListener implements OnItemSelectedListener{
    	// onItemSelected���\�b�h
    	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
    		// �N���b�N����Spinner�I�u�W�F�N�g�擾
    		Spinner spinner = (Spinner)parent;
    		// �I�����ꂽ���X�g�̒l�擾
    		String item = (String)spinner.getItemAtPosition(position);
    		// �e�L�X�g�{�b�N�X�̓��͏��擾
    		EditText input = (EditText)findViewById(R.id.text_name);
    		
    		// ���͏����g�[�X�g�@�\�ŉ�ʕ\��
    		Toast.makeText(TextSelectedViewTest.this, item+"\n"+input.getText()+"����", Toast.LENGTH_SHORT).show();
    	}
    	// onNothingSelected���\�b�h
    	public void onNothingSelected(AdapterView<?> parent){}
    }
}