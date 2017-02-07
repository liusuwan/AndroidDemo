package liusuwan.androiddemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentFragment extends BaseFragment {

    @BindView(R.id.img)
    ImageView img;

    public ParentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addSharedElement(view,"img").add(R.id.frame_root, new ChildFragment(), "child");
                ft.commit();
            }
        });
    }
}
