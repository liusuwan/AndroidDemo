package liusuwan.androiddemo.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

/**
 * Created by Jack on 2017-02-10.
 */

public class LinkBluetoothDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bluetooth, container);
        ButterKnife.bind(this, view);
        return view;
    }
}
