package com.login__excelreader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader extends Activity {
	private Button button;
	private ListView listView;
	private SimpleAdapter simpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excelreader);

		listView = (ListView) findViewById(R.id.listView);
		simpleAdapter = new SimpleAdapter(ExcelReader.this, getData(), R.layout.excelreader,
				new String[] { "id", "time", "air_temperature", "rainfall" },
				new int[] { R.id.id, R.id.time, R.id.air_temperature, R.id.rainfall });
		listView.setAdapter(simpleAdapter);

	}

	private List<Map<String, String>> getData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		AssetManager am = this.getAssets();// �õ���Դ������AssetManager
		InputStream is = null;
		try {
			is = am.open("data.xls");
			// �õ�Excel����������
			Workbook wb = Workbook.getWorkbook(is);
			// �õ�Excel���������Sheet���±��Ǵ�0��ʼ ����ȡ��һ��Sheet�� ��
			Sheet sheet = wb.getSheet(0);
			// �õ�Excel���������
			int row = sheet.getRows();
			for (int i = 1; i < row; i++) {//i���ܴ�0��ʼ����Ϊ��һ������������0��ʼ�Ļ��������������ȥ
				// �õ�Excel������ָ���еĵ�Ԫ�� ��getCell(x,y)��ʾ��y�еĵ�x��
				Cell id = sheet.getCell(0, i);
				Cell time = sheet.getCell(1, i);
				Cell air_temperature = sheet.getCell(2, i);
				Cell rainfall = sheet.getCell(3, i);

				//��������д���ŵ�ѭ���⣬��ô��������ݶ���һ���ģ����һ�е����ݣ�
				//�ŵ�ѭ�����棬�ͱ�ʾֻ������һ��Map���ϣ���ֻ��洢���һ�ε����ݣ�֮ǰ�����ݱ�����
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("id", id.getContents());
				map.put("time", time.getContents());
				map.put("air_temperature", air_temperature.getContents());
				map.put("rainfall", rainfall.getContents());
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

}
