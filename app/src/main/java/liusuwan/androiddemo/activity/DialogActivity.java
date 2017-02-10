package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.fragment.LinkBluetoothDialog;

public class DialogActivity extends AppCompatActivity {

    public Button btnBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkBluetoothDialog linkBluetoothDialog = new LinkBluetoothDialog();
                linkBluetoothDialog.show(getFragmentManager(), null);
            }
        });
    }
}
