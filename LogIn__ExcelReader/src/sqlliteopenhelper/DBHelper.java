package sqlliteopenhelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static String name = "Data.db";//��ʾ���ݿ������
	 private static final int version = 1;//��ʾ���ݿ�İ汾����

	public DBHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql = "create table mydata (logIn varchar(64), registerPassword varchar(64))";
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
