package liusuwan.androiddemo.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liusuwan.androiddemo.R;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private TextView mTextView;
    ListView bluetoothListView;
    List<Map<String,String>> blueToothItems=new ArrayList<>();
    List<String> blueToothAddress=new ArrayList<>();
    SimpleAdapter adapter;
    MenuItem mProgressMenu, mSearchMenu;
    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        initView();
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setSupportActionBar(toolbar);
        bluetoothListView=(ListView)findViewById(R.id.list_bluetooth);

        bluetoothListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBluetoothAdapter.isDiscovering()){
                    mBluetoothAdapter.cancelDiscovery();
                }
                Intent intent=new Intent();
                Map<String,String> map= blueToothItems.get(position);
                intent.putExtra("address",map.get("address"));
                intent.putExtra("name",map.get("name"));

                setResult(200,intent);
                finish();
            }
        });

        adapter=new SimpleAdapter(this,blueToothItems,android.R.layout.simple_list_item_2,
                new String[]{"name","address"},new int[]{android.R.id.text1,android.R.id.text2});
        bluetoothListView.setAdapter(adapter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()==false){
            mBluetoothAdapter.enable();
        }
        // 获取所有已经绑定的蓝牙设备
//        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
//        if (devices.size() > 0) {
//            for (BluetoothDevice bluetoothDevice : devices) {
//                mTextView.append(bluetoothDevice.getName() + ":"
//                        + bluetoothDevice.getAddress() + "\n\n");
//            }
//        }
        // 注册用以接收到已搜索到的蓝牙设备的receiver
        IntentFilter mFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, mFilter);
        // 注册搜索完时的receiver
        IntentFilter action_bond = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mReceiver, action_bond);
    }
    private void searchBlueDevice() {
        setLoadingState(true);
        setProgressBarIndeterminateVisibility(true);
        setTitle("正在搜索读写器设备...");
        blueToothItems.clear();
        adapter.notifyDataSetChanged();
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }


    @Override
    protected void onResume() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchBlueDevice();
            }
        },200);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
        }
        unregisterReceiver(mReceiver);
    }

    public void onClick_Search(View v) {
        setProgressBarIndeterminateVisibility(true);
        setTitle("正在扫描....");
        // 如果正在搜索，就先取消搜索
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
        mBluetoothAdapter.startDiscovery();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.refresh_find){
            searchBlueDevice();
            setLoadingState(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_progress,menu);
        mProgressMenu=menu.findItem(R.id.refresh_loading);
        mSearchMenu =menu.findItem(R.id.refresh_find);
        return true;
    }

    public void setLoadingState(boolean refreshing) {
        if (mProgressMenu != null) {
            if (refreshing) {
                mSearchMenu.setVisible(false);
                mProgressMenu
                        .setActionView(R.layout.actionbar_indeterminate_progress);
                mProgressMenu.setVisible(true);
            } else {
                mSearchMenu.setVisible(true);
                mProgressMenu.setVisible(false);
                mProgressMenu.setActionView(null);
            }
        }
    }
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String address=device.getAddress();
                Map<String,String> map=new HashMap<>();
                map.put("address",device.getAddress());
                map.put("name",device.getName());
                if (!blueToothAddress.contains(address)){
                    blueToothAddress.add(address);
                    blueToothItems.add(map);
                }
                adapter.notifyDataSetChanged();
                // 搜索完成
            } else if (action
                    .equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
//                setTitle("搜索完成");
//                setLoadingState(false);
            }
        }
    };
}
