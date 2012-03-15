package example.android.contentprovidersample2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.net.Uri;

public class ContentProviderSample2Activity extends Activity {
	TableLayout tablelayout = null;
	
	// �擾�f�[�^�I��(0:�d�b���̖��O, 1:�ʘb�����̔ԍ�)
	int type = 1;
	Uri uri;
	String index;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentprovidersample2);
        
        // �e�[�u�����C�A�E�g�擾
        tablelayout = (TableLayout)findViewById(R.id.tablelayout);
        
        try{
        	// �f�[�^�擾
        	if( type == 0 ){
        		uri = Contacts.CONTENT_URI;
        		index = Contacts.DISPLAY_NAME;
        	}else{
        		uri = CallLog.Calls.CONTENT_URI;
        		index = CallLog.Calls.NUMBER;
        	}
        	Cursor cur = managedQuery(uri, null, null, null, null);

        	// �f�[�^���擾�ł����ꍇ
        	if( cur.getCount() != 0 ){
        		// �擾�����f�[�^�\��
        		while( cur.moveToNext() ){
        			String name = cur.getString(cur.getColumnIndex(index));
        			setItems(name);
        		}
        	}else{
        		TextView message = new TextView(this);
        		message.setText("�f�[�^���擾�ł��܂���ł����B");
        		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayout);
        		linearlayout.addView(message);
        	}
        }catch(Exception e){
        	Log.e("ERROR", e.getMessage());
        }
    }
    
    // �e�[�u���\������
    private void setItems( String name ){
    	// ���O��ݒ�
    	TableRow row = new TableRow(this);
    	TextView displayName = new TextView(this);
    	displayName.setText(name);
    	row.addView(displayName);
    	// �e�[�u�����C�A�E�g�ɐݒ�
    	tablelayout.addView(row);
    }
}