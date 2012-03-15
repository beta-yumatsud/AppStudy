package example.android.dialogsample;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class DialogSampleActivity extends Activity {
	TextView label = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsample);
        
        // �\�����x���擾
        label = (TextView)findViewById(R.id.label_dialogtext);
        
        // �ʏ�_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button dialogBtn = (Button)findViewById(R.id.dialogButton);
        dialogBtn.setTag("dialog");
        dialogBtn.setOnClickListener(new ButtonClickListener());
        
        // �e�L�X�g�_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button txtDialogBtn = (Button)findViewById(R.id.textDialogButton);
        txtDialogBtn.setTag("textDialog");
        txtDialogBtn.setOnClickListener(new ButtonClickListener());
        
        // �P��I���_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button singleSelectDialogBtn = (Button)findViewById(R.id.singleSelectDialogButton);
        singleSelectDialogBtn.setTag("singleSelectDialog");
        singleSelectDialogBtn.setOnClickListener(new ButtonClickListener());
        
        // ���t�I���_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button DatePickerDialogBtn = (Button)findViewById(R.id.datePickerDialogButton);
        DatePickerDialogBtn.setTag("datePickerDialogBtn");
        DatePickerDialogBtn.setOnClickListener(new ButtonClickListener());
        
        // ���ԑI���_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button TimePickerDialogBtn = (Button)findViewById(R.id.timePickerDialogButton);
        TimePickerDialogBtn.setTag("timePickerDialogBtn");
        TimePickerDialogBtn.setOnClickListener(new ButtonClickListener());
        
        // �v���O���X�o�[�_�C�A���O�\���{�^���̃N���b�N���X�i�[�ݒ�
        Button ProgressDialogBtn = (Button)findViewById(R.id.progressDialogButton);
        ProgressDialogBtn.setTag("progressDialogBtn");
        ProgressDialogBtn.setOnClickListener(new ButtonClickListener());
    }
    
    // �N���b�N���X�i�[��`
    class ButtonClickListener implements OnClickListener{
    	public void onClick(View v){
    		String tag = (String)v.getTag();
    		if( tag.equals("dialog")){
    			showDialog();
    		}else if( tag.equals("textDialog") ){
    			showTextDialog();
    		}else if( tag.equals("singleSelectDialog")){
    			showSingleSelectDialog();
    		}else if( tag.equals("datePickerDialogBtn")){
    			showDatePickerDialog();
    		}else if( tag.equals("timePickerDialogBtn")){
    			showTimePickerDialog();
    		}else if( tag.equals("progressDialogBtn")){
    			showProgressDialog();
    		}
    	}
    }
    
    // �ʏ�_�C�A���O�̕\��
    private void showDialog(){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
    	dialog.setTitle("�ʏ�_�C�A���O");
    	dialog.setMessage("�I�����Ă��������B");
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("�ʏ�_�C�A���O�FOK���I������܂����B");
			}
		});
    	dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("�ʏ�_�C�A���O�FNG���I������܂����B");
			}
		});
    	dialog.show();
    }
    
    // �e�L�X�g�_�C�A���O�̕\��
    public void showTextDialog(){
    	final EditText editText = new EditText(DialogSampleActivity.this);
    	AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
    	dialog.setTitle("�e�L�X�g�_�C�A���O");
    	dialog.setMessage("�e�L�X�g����͂��Ă��������B");
    	dialog.setView(editText);
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				label.setText("�e�L�X�g�_�C�A���O�F" + editText.getText().toString() + "�����͂���܂����B");
			}
		});
    	dialog.show();
    }
    
    // �P��I���_�C�A���O�̕\��
    final String[] items = new String[]{"����", "����", "������"};
    int which = 0;
    public void showSingleSelectDialog(){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(DialogSampleActivity.this);
    	dialog.setTitle("�P��I���_�C�A���O");
    	dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				which = whichButton;
			}
		});
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String selected = items[which];
				label.setText("�P��I���_�C�A���O�F" + selected + "�����͂���܂����B");
			}
		});
    	dialog.show();
    }
    
    // ���t�I���_�C�A���O�̕\��
    public void showDatePickerDialog(){
    	Calendar cal = Calendar.getInstance();
    	
    	DatePickerDialog dialog = new DatePickerDialog(DialogSampleActivity.this
    			, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int day) {
						label.setText("���t�I���_�C�A���O�F" + year + "�N" + (month+1) + "��" + day + "��");
					}
				}
    			,cal.get(Calendar.YEAR)
    			,cal.get(Calendar.MONTH)
    			,cal.get(Calendar.DAY_OF_MONTH)
    	);
    	dialog.show();
    }
    
    // ���ԑI���_�C�A���O�̕\��
    public void showTimePickerDialog(){
    	TimePickerDialog dialog = new TimePickerDialog(DialogSampleActivity.this
    			,new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hour, int minute) {
						label.setText("���ԑI���_�C�A���O�F" + hour + "��" + minute + "��");
					}
				}
    			,0
    			,0
    			,true
    	);
    	dialog.show();
    }
    
    // �v���O���X�o�[�_�C�A���O�̕\��
    ProgressDialog dialog;
    public void showProgressDialog(){
    	dialog = new ProgressDialog(DialogSampleActivity.this);
    	dialog.setTitle("�v���O���X�o�[�_�C�A���O");
    	dialog.setMessage("���΂炭���҂����������E�E�E");
    	dialog.setIndeterminate(false);
    	dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	//dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setMax(100);
    	dialog.setCancelable(false);
    	dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
			}
		});
    	dialog.show();
    	new Thread(new Runnable(){
    		@Override
    		public void run(){
    			try{
    				for(int i = 0; i < dialog.getMax(); i++){
    					dialog.setProgress(i);
    					Thread.sleep(100);
    				}
    			}catch(Exception e){
    				
    			}
    			dialog.dismiss();
    		}
    	}).start();
    }
}