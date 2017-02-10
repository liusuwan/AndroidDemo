package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import liusuwan.androiddemo.R;

public class LottieActivity extends AppCompatActivity {

    public LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        lottieAnimationView= (LottieAnimationView) findViewById(R.id.animation_view);
    }
}
