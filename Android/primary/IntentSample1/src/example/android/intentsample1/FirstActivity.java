package example.android.intentsample1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FirstActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstlayout);
        
        // ListView�I�u�W�F�N�g�擾
        ListView listview = (ListView)findViewById(R.id.fruitlist);
        // ListView�I�u�W�F�N�g�ɃN���b�X�i���X�i�[���֘A�Â�
        listview.setOnItemClickListener(new ListItemClickListener());
    }
    
    // �A�C�e���N���b�N���X�i�[��`
    class ListItemClickListener implements OnItemClickListener{
    	// onItemClick���\�b�h(�l�I�����C�x���g�n���h��)
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
    		// ListView�I�u�W�F�N�g�擾
    		ListView listview = (ListView)parent;
    		// �I�����ꂽ�l�擾
    		String item = (String)listview.getItemAtPosition(position);
    		
    		// �C���e���g�̐���(�Ăяo���N���X�̎w��)
    		Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
    		// �I�����ꂽ�l���C���e���g�ɐݒ�
    		intent.putExtra("SELECTED_PICT", item);
    		// ���̃A�N�e�B�r�e�B�̋O��
    		startActivity(intent);
    	}
    }
}