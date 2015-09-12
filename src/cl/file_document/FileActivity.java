package cl.file_document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cl.intentService.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FileActivity extends Activity implements OnClickListener {
	private EditText et;
	private Button btn;
	
	private final String fileName = "chenlong.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_file);
		et = (EditText) this.findViewById(R.id.et_file);
		btn = (Button) this.findViewById(R.id.btn_file);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_file:
			writeToFile();
			break;
		default:
			break;
		}
	}

	private void writeToFile() {
		String str = et.getText().toString();
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
//			byte[] 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try{
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
