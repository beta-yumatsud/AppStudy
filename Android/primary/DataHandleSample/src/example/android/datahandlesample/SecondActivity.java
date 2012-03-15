package example.android.datahandlesample;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

public class SecondActivity extends Activity {
	// �v���t�@�����X�t�@�C����
	private static final String PFILE_NAME = "PreferenceDataHandle";
	// �t�@�C����
	private static final String FILE_NAME = "FileDataHandle";
	
	// ���̓f�[�^
	String name = "";
	String address = "";
	String month = "";
	String day = "";
	String gender = "";
	String apple = "";
	String orange = "";
	String peach = "";
	
	// �ۑ��f�[�^�`��(p:�v���t�@�����X, f:�t�@�C��, s:SQLite)
	// ���ʏ킱�̂悤�Ȃ��Ƃ͂��Ȃ����A���K�����P�ɂ܂Ƃ߂邽��
	String data_type = "p";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        
        // �C���e���g�̕t�����擾
        Bundle extra = getIntent().getExtras();
        
        // �t����񂩂���̓f�[�^�擾
        name = extra.getString("NAME");
        address = extra.getString("ADDRESS");
        month = extra.getString("MONTH");
        day = extra.getString("DAY");
        gender = extra.getString("GENDER");
        apple = extra.getString("APPLE");
        orange = extra.getString("ORANGE");
        peach = extra.getString("PEACH");
        
        // �o�͗p�e�L�X�g�I�u�W�F�N�g�擾
        TextView inputName = (TextView)findViewById(R.id.name);
        TextView inputAddress = (TextView)findViewById(R.id.address);
        TextView inputMonth = (TextView)findViewById(R.id.month);
        TextView inputDay = (TextView)findViewById(R.id.day);
        TextView inputGender = (TextView)findViewById(R.id.gender);
        TextView inputApple = (TextView)findViewById(R.id.apple);
        TextView inputOrange = (TextView)findViewById(R.id.orange);
        TextView inputPeach = (TextView)findViewById(R.id.peach);
        
        // �o�͗p�e�L�X�g�I�u�W�F�N�g�ɓ��̓f�[�^�ݒ�
        inputName.setText(name);
        inputAddress.setText(address);
        inputMonth.setText(month);
        inputDay.setText(day);
        inputGender.setText(gender);
        if( apple != null ) inputApple.setText(apple);
        if( orange != null ) inputOrange.setText(orange);
        if( peach != null ) inputPeach.setText(peach);
        
        // �߂�{�^���̃N���b�N���X�i�[�ݒ�
        Button backbutton = (Button)findViewById(R.id.backbutton);
        backbutton.setTag("back");
        backbutton.setOnClickListener(new ButtonBackClickLisener());
        // �m�F�{�^���̃N���b�N���X�i�[�ݒ�
        Button confirmbutton = (Button)findViewById(R.id.confirmbutton);
        confirmbutton.setTag("confirm");
        confirmbutton.setOnClickListener(new ButtonBackClickLisener());
    }
    // �N���b�N���X�i�[��`
    class ButtonBackClickLisener implements OnClickListener{
    	public void onClick(View v){
    		// �^�O�̎擾
    		String tag = (String)v.getTag();
    		
    		// �m�F�{�^���������ꂽ�ꍇ
    		if( tag.equals("confirm") ){
    			// �ۑ��`�����v���t�@�����X�̏ꍇ
    			if( data_type.equals("p") ){
    				// �v���t�@�����X�I�u�W�F�N�g�擾
    	    		SharedPreferences preference = getSharedPreferences(PFILE_NAME, MODE_PRIVATE);
    	    		// �v���t�@�����X�̕ҏW�p�I�u�W�F�N�g�擾
        			SharedPreferences.Editor editor = preference.edit();
        			// �v���t�@�����X�t�@�C���ɕۑ�
        			editor.putString("NAME", name);
        			editor.putString("ADDRESS", address);
        			editor.putString("MONTH", month);
        			editor.putString("DAY", day);
        			editor.putString("GENDER", gender);
        			editor.putString("APPLE", apple);
        			editor.putString("ORANGE", orange);
        			editor.putString("PEACH", peach);
        			editor.commit();
        			
        		// �ۑ��`�����t�@�C���̏ꍇ
    			}else if( data_type.equals("f") ){
    				// �t�@�C���Ƀf�[�^�ۑ�
        			try{
        				FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
        				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
        				
        				out.write(name + "," +
        						address + "," +
        						month + "/" + day + "," +
        						gender + "," +
        						apple + "/" +
        						orange + "," +
        						peach);
        				out.newLine();
        				out.close();
        			}catch(Exception e){
        				Log.e("FILE_ERROR", "�t�@�C���������݂Ɏ��s���܂���");
        			}
        			
        		// �ۑ��`����SQLite�̏ꍇ
    			}else{
    				// DB�A�N�Z�X
    				CreateProductHelper helper = new CreateProductHelper(SecondActivity.this);
    				// DB�I�u�W�F�N�g�擾
    				SQLiteDatabase db = helper.getWritableDatabase();
    				// �e�[�u���쐬
        			try{
        				String sql = "create table datahandle(" +
        							 "_id integer primary key autoincrement," +
        							 "name text not null," +
        							 "address text not null," +
        							 "gender text not null," +
        							 "apple integer default 0," +
        							 "orange integer default 0," +
        							 "peach integer default 0)";
        				// SQL���s
        				db.execSQL(sql);
        			}catch(Exception e){
        				Log.e("CREATE_TABLE_ERROR", e.toString());
        			}
        			
        			// �f�[�^�o�^
        			try{
        				// �g�����U�N�V��������J�n
        				db.beginTransaction();
        				
        				// �o�^�f�[�^�ݒ�
        				ContentValues val = new ContentValues();
        				val.put("name", name);
        				val.put("address", address);
        				val.put("gender", gender);
        				val.put("apple", apple);
        				val.put("orange", orange);
        				val.put("peach", peach);
        				db.insert("datahandle", null, val);
        				// �R�~�b�g
        				db.setTransactionSuccessful();
        				// �g�����U�N�V��������I��
        				db.endTransaction();
        				// DB�I�u�W�F�N�g�N���[�Y
        	    		db.close();
        			}catch(Exception e){
        				Log.e("DB_ERROR", "�f�[�^�x�[�X�o�^�Ɏ��s���܂����B");
        			}
    			}
    			
    			// �C���e���g�̐���
    			Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
    			// ���̃A�N�e�B�r�e�B�̋N��
    			startActivity(intent);
    			
    		// �߂�{�^���������ꂽ�ꍇ
    		}else if( tag.endsWith("badk") ){
    			// ��ʂ��N���[�Y
    			finish();
    		}
    	}
    }
}