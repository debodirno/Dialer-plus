package xenophobe.applications.com.dialerplus;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    private void initializeView() {
        screen = (EditText) findViewById(R.id.screen);
        int idList[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonstar, R.id.buttonhash,
                R.id.dial, R.id.imageButton};
        for (int d : idList) {
            View v = (View) findViewById(d);
            v.setOnClickListener(this);
        }
    }

    public void display(String val) {
        screen.append(val);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                display("0");
                break;
            case R.id.button1:
                display("1");
                break;
            case R.id.button2:
                display("2");
                break;
            case R.id.button3:
                display("3");
                break;
            case R.id.button4:
                display("4");
                break;
            case R.id.button5:
                display("5");
                break;
            case R.id.button6:
                display("6");
                break;
            case R.id.button7:
                display("7");
                break;
            case R.id.button8:
                display("8");
                break;
            case R.id.button9:
                display("9");
                break;
            case R.id.buttonstar:
                display("*");
                break;
            case R.id.buttonhash:
                display("#");
                break;
            case R.id.dial:
                if (screen.getText().toString().isEmpty())
                    ;
                else if (checkCallPermission()) {
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + screen.getText())));
                    Log.d("CALLS", screen.getText().toString());
                }
                break;
            case R.id.imageButton:
                if (screen.getText().toString().length() > 0) {
                    String newScreen = screen.getText().toString().substring(0, screen.getText().toString().length() - 1);
                    screen.setText(newScreen);
                    screen.setSelection(screen.getText().length());
                }
                break;
        }
    }

    public boolean checkCallPermission(){
        String permission = "android.permission.CALL_PHONE";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}
