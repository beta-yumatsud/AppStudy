package example.android.helloworldonlyjavasample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloWorldOnlyJava extends Activity {
    /** Called when the activity is first created. */
	// match_parent�萔
	private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
	// wrap_content�萔
	private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	// onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        
        // ���C�A�E�g�̐���
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MP, MP));
        // ���C�A�E�g�ݒ�
        setContentView(layout);
        
        // �e�L�X�g�r���[�̐���
        TextView textView = new TextView(this);
        textView.setText("Hello World!!");
        textView.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        
        // �e�L�X���r���[�����C�A�E�g�ɒǉ�
        layout.addView(textView);
    }
}