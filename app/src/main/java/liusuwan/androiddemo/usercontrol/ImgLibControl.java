package liusuwan.androiddemo.usercontrol;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.AnimationHelper;

/**
 * Created by Jack on 2017-01-13.
 */

public class ImgLibControl extends RelativeLayout {


    public Context context;
    Random random = new Random();
    Handler baseHandler = new Handler();
    ImageView imageView1, imageView2;
    ViewGroup relRoot;
    int currentView = 0;

    public List<String> urlList = new ArrayList<>();

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
        if (urlList != null) {
            if (urlList.size() > 0) {
                Glide.with(context)
                        .load(urlList.get(0))
                        .into(imageView2);
                if (urlList.size() > 1) {
                    startChange();
                }
            }
        }
    }

    public ImgLibControl(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ImgLibControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public ImgLibControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.uc_imglib, this, true);
        relRoot = (ViewGroup) view.findViewById(R.id.rel_root);
        imageView1 = (ImageView) view.findViewById(R.id.img1);
        imageView2 = (ImageView) view.findViewById(R.id.img2);
    }

    public void startChange() {
        changeImage();
    }

    //切换图片
    public void changeImage() {
        int delay = random.nextInt(4000) + 1000;
        Log.d("delay", String.valueOf(delay));
        baseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentView++;
                if (currentView == urlList.size()) {
                    currentView = 0;
                }
                imageView1.setImageDrawable(null);
                imageView1.setImageDrawable(imageView2.getDrawable());
                Glide.with(context)
                        .load(urlList.get(currentView))
                        .skipMemoryCache(true)
                        .into(imageView2);
                AnimationHelper.startAnim(imageView1, imageView2);
//                if (imageView1.getVisibility() == INVISIBLE) {
//                    Glide.with(context)
//                            .load(urlList.get(currentView))
//                            .skipMemoryCache(true)
//                            .into(imageView1);
//                    imageView1.setVisibility(VISIBLE);
//                    AnimationHelper.startAnim(imageView2, imageView1);
//                    imageView2.setVisibility(INVISIBLE);
//                } else {
//                    Glide.with(context)
//                            .load(urlList.get(currentView))
//                            .skipMemoryCache(true)
//                            .into(imageView2);
//                    imageView2.setVisibility(VISIBLE);
//                    AnimationHelper.startAnim(imageView1, imageView2);
//                    imageView1.setVisibility(INVISIBLE);
//                }
                changeImage();
            }
        }, 4000);
    }
}
