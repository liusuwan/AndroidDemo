package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import me.gujun.android.taggroup.TagGroup;

public class TagActivity extends AppCompatActivity {
    @BindView(R.id.tag_group)
    TagGroup tagGroup;
    @BindView(R.id.tag_all)
    TagGroup tagAll;

    List<String> selectTags = new ArrayList<>();
    List<String> allTags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        allTags.add("Tag1");
        allTags.add("Tag2");
        allTags.add("Tag3");
        allTags.add("Tag4");
        tagAll.setTags(allTags);
        tagAll.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String s) {
                if (!selectTags.contains(s)) {
                    selectTags.add(s);
                    tagGroup.setTags(selectTags);
                }
            }
        });
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String s) {
                selectTags.remove(s);
                tagGroup.setTags(selectTags);
            }
        });
    }
}
