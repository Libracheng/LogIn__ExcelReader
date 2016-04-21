package com.login__excelreader;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import sqlliteopenhelper.DBHelper;

public class MainActivity extends Activity {

	private SQLiteDatabase database;
	private DBHelper dBHelper;

	private Button button;
	private TextView register;
	private TextView find;

	private EditText editText01;
	private EditText editText02;

	private EditText logIn;
	private EditText registerPassword;
	
	private EditText findName;
	private EditText updatePassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dBHelper = new DBHelper(getApplicationContext());
		database = dBHelper.getWritableDatabase();

		button = (Button) this.findViewById(R.id.button);
		button.setOnClickListener(new LogIn_ButtonOnClickListener());

		register = (TextView) this.findViewById(R.id.register);
		register.setOnClickListener(new RegisterOnClickListener());
		
		find = (TextView)this.findViewById(R.id.find);
		find.setOnClickListener(new FindOnClickListener());

		editText01 = (EditText) this.findViewById(R.id.editText01);
		editText02 = (EditText) this.findViewById(R.id.editText02);
		
		
	}

	class LogIn_ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			
			try {
				String logIn_Name = editText01.getText().toString().trim();
				String logIn_Password = editText02.getText().toString().trim();
				Cursor cursor = database.query("mydata", new String[] { "logIn", "registerPassword" },
						"logIn = ?", new String[] { logIn_Name }, null, null, null);
					if(cursor.moveToFirst()){//�ж��α��Ƿ�Ϊ��
						String password = cursor.getString(cursor.getColumnIndex("registerPassword"));
						if ( logIn_Password.equals(password)) {//ע�⣺����equals()�������бȽϣ�����
							Intent intent = new Intent();
							intent.setClass(MainActivity.this, ExcelReader.class);
							startActivity(intent);
						}
						else {
							Toast.makeText(MainActivity.this, "������������ԣ�����", Toast.LENGTH_LONG).show();
						}
					}
				else {
					Toast.makeText(MainActivity.this, "�����˺Ŵ�������ע�ᣡ����", Toast.LENGTH_LONG).show();
				}
             

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (database!=null) {
					database.close();
				}
			}
			
		}
		
		/**������
			String logIn_Name = editText01.getText().toString().trim();
			String logIn_Password = editText02.getText().toString().trim();
			Cursor cursor = database.query("mydata", null,null,null, null, null, null);
		if (cursor.moveToFirst()){//���α��Ƶ���һ��
         do{//��do-while������������ִ�е�һ�����ݣ�Ȼ�������¼��������ʹ��while����ô��ֱ�Ӵӵڶ��п�ʼִ�У���һ�о�������
        	 String zhanghao = cursor.getString(cursor.getColumnIndex("logIn"));
        	 String mima = cursor.getString(cursor.getColumnIndex("registerPassword"));
        	 Log.d("zhanghao",zhanghao);
        	 Log.d("mima",mima);
        	 if(logIn_Name.equals(zhanghao)&&logIn_Password.equals(mima)){
        		    Intent intent = new Intent();
				    intent.setClass(MainActivity.this, ExcelReader.class);
					startActivity(intent);
					Toast.makeText(MainActivity.this, "�˺�������ȷ", Toast.LENGTH_SHORT).show();
                 }
        	 else if(logIn_Name.equals(zhanghao)&&!(logIn_Password.equals(mima))){
        		 Toast.makeText(MainActivity.this, "�������", Toast.LENGTH_SHORT).show();
        	 }
        	 else if(!(logIn_Name.equals(zhanghao))){
        		 Toast.makeText(MainActivity.this, "�˺Ų����ڣ�", Toast.LENGTH_SHORT).show();
        	 }
        	 }while(cursor.moveToNext());
         }
	}
		*/
		
		/**������                            
		 * �Ȱ����ݿ������ݷŵ�map�����У�Ȼ�����������������map���Ͻ��бȽ�
		 * ��map�Ļ�����ֱ���ж�������˺��Ƿ�������ݿ��У����ڵĻ�����֤����
		  Map<String,String> map = new HashMap<String,String>();
		  String logIn_Name = editText01.getText().toString().trim();
		  String logIn_Password = editText02.getText().toString().trim();
		  Cursor cursor = database.query("registerdata", null,null,null, null, null, null);
		  if (cursor.moveToFirst()){	
				
				do{

            	 String zhanghao = cursor.getString(cursor.getColumnIndex("logIn"));
            	 String mima = cursor.getString(cursor.getColumnIndex("registerPassword"));
            	 map.put(zhanghao, mima);
            	  }while(cursor.moveToNext());
             }

			if(map.containsKey(logIn_Name)){
				if(logIn_Password.equals(map.get(logIn_Name))){
					Intent intent = new Intent();
			        intent.setClass(MainActivity.this, ExcelReader.class);
					startActivity(intent);
					Toast.makeText(MainActivity.this, "�˺�������ȷ!", Toast.LENGTH_SHORT).show();
                }else{
                	Toast.makeText(MainActivity.this, "�������", Toast.LENGTH_SHORT).show();
                }
           }else{
        	   Toast.makeText(MainActivity.this, "�˺Ų����ڣ�", Toast.LENGTH_SHORT).show();
           }
}
          */
	}

	class RegisterOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

			final View custom_dialog_view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog,
					null);
			builder.setView(custom_dialog_view); // ���öԻ�����ʾ��View����

			builder.setTitle("��ע���˺�");
			builder.setIcon(R.drawable.gaoyuanyuanxiao);

			builder.setPositiveButton("ע��", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

						registerPassword = (EditText) custom_dialog_view.findViewById(R.id.registerPassword);// ע�⣺Ҫ��ע���ĸ������ļ��Ŀؼ�
						logIn = (EditText) custom_dialog_view.findViewById(R.id.logIn);

						String li = logIn.getText().toString().trim();
						String rp = registerPassword.getText().toString().trim();

						ContentValues values = new ContentValues();
						values.put("logIn", li);
						values.put("registerPassword", rp);

						database.insert("mydata", null, values);
						Toast.makeText(MainActivity.this, "ע��ɹ�������", Toast.LENGTH_LONG).show();
				
				}

			});
			// ����������ʾ�Ի���
			final AlertDialog dialog = builder.create();
			dialog.show();

			//���Ը���setPositiveButton�еĲ���
			// dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new
			// View.OnClickListener() {
			//
			// public void onClick(View v) {
			// Toast.makeText(MainActivity.this, "ע��ɹ�������",
			// Toast.LENGTH_LONG).show();
			// dialog.dismiss();
			// }
			// });

		}
	}
	
	class FindOnClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

			final View custom_dialog2_view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog2,
					null);
			builder.setView(custom_dialog2_view); // ���öԻ�����ʾ��View����

			builder.setTitle("�޸�����");
			builder.setIcon(R.drawable.gaoyuanyaun1xiao);

			builder.setPositiveButton("ȷ���޸�", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
						updatePassword = (EditText) custom_dialog2_view.findViewById(R.id.updatePassword);// ע�⣺Ҫ��ע���ĸ������ļ��Ŀؼ�
						findName = (EditText) custom_dialog2_view.findViewById(R.id.findName);
						
						String name = findName.getText().toString().trim();
						String passward = updatePassword.getText().toString().trim();
						
								ContentValues values = new ContentValues();
						        values.put("registerPassword", passward);
						        database.update("mydata", values, "logIn = ?", new String[]{name});
						        Toast.makeText(MainActivity.this, "�޸ĳɹ�������", Toast.LENGTH_LONG).show();
				}});
			// ����������ʾ�Ի���
			final AlertDialog dialog = builder.create();
			dialog.show();
		   }
	   }
		
	}


