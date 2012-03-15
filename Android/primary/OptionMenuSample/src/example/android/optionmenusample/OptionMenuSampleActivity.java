package example.android.optionmenusample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class OptionMenuSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionmenusample);
    }
    
    // �I�v�V�������j���[����
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	// ���j���[�A�C�e��1�̒ǉ�
    	MenuItem item1 = menu.add(0, 0, 0, "item1");
    	item1.setIcon(android.R.drawable.ic_menu_crop);
    	
    	// ���j���[�A�C�e��2�̒ǉ�
    	MenuItem item2 = menu.add(0, 1, 0, "item2");
    	item2.setIcon(android.R.drawable.ic_menu_search);
    	
    	// ���j���[�A�C�e��3�̒ǉ�
    	MenuItem item3 = menu.add(0, 2, 0, "item3");
    	item3.setIcon(android.R.drawable.ic_menu_save);
    	
    	// ���j���[�A�C�e��4�̒ǉ�
    	MenuItem item4 = menu.add(0, 3, 0, "item4");
    	item4.setIcon(android.R.drawable.ic_menu_call);
    	
    	// ���j���[�A�C�e��5�̒ǉ�
    	MenuItem item5 = menu.add(0, 4, 0, "item5");
    	item5.setIcon(android.R.drawable.ic_menu_camera);
    	
    	// ���j���[�A�C�e��6�̒ǉ�
    	SubMenu item6 = menu.addSubMenu(0, 5, 0, "���̑�");
    	item6.setIcon(android.R.drawable.ic_menu_more);
    	item6.add(0, 10, 0, "subitem1");
    	item6.add(0, 20, 0, "subitem2");
    	
    	return true;
    }
    
    // ���j���[�A�C�e���I������
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch( item.getItemId() ){
    		case 0:
    			showDialog("���j���[�A�C�e��1��I�����܂����B");
    			return true;
    		case 1:
    			showDialog("���j���[�A�C�e��2��I�����܂����B");
    			return true;
    		case 2:
    			showDialog("���j���[�A�C�e��3��I�����܂����B");
    			return true;
    		case 3:
    			showDialog("���j���[�A�C�e��4��I�����܂����B");
    			return true;
    		case 4:
    			showDialog("���j���[�A�C�e��5��I�����܂����B");
    			return true;
    		case 10:
    			showDialog("�T�u���j���[�A�C�e��1��I�����܂����B");
    			return true;
    		case 20:
    			showDialog("�T�u���j���[�A�C�e��2��I�����܂����B");
    			return true;
    	}
    	return true;
    }
    
    // �_�C�A���O�\��
    private void showDialog(String text){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(OptionMenuSampleActivity.this);
    	dialog.setTitle("���j���[�A�C�e���I������");
    	dialog.setMessage(text);
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				OptionMenuSampleActivity.this.setResult(Activity.RESULT_OK);
			}
		});
    	dialog.create();
    	dialog.show();
    }
}