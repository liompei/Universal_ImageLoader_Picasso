package blm.newandroid.cn.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_img= (ImageView) findViewById(R.id.iv_img);
//        Picasso.with(this).load("http://newandroid.cn/image/blm_two.jpg").into(iv_img);

//        Picasso.with(this)
//                .load("http://newandroid.cn/image/blm_two.jpg")
//                .resize(1000,1000)
//                .centerCrop()
//                .into(iv_img);

        Picasso.with(this)
                .load("http://newandroid.cn/image/blm_two.jpg")
                .error(R.mipmap.ic_launcher)
                .into(iv_img);

    }
}
