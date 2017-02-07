package liusuwan.androiddemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liusuwan.androiddemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends BaseFragment {


    public ChildFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child, container, false);
    }

}
