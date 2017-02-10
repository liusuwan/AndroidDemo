package liusuwan.androiddemo.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.animation.DetailTransition;

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
                ChildFragment childFragment = new ChildFragment();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    childFragment.setSharedElementEnterTransition(new DetailTransition());
                    setExitTransition(new Fade());
                    childFragment.setEnterTransition(new Fade());
                    childFragment.setSharedElementReturnTransition(new DetailTransition());
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addSharedElement(view, "SE")
                        .replace(R.id.frame_root, childFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
