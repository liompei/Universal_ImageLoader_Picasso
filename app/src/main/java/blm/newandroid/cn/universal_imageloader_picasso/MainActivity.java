package blm.newandroid.cn.universal_imageloader_picasso;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class MainActivity extends AppCompatActivity {

    private ImageLoader loader;
    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader=ImageLoader.getInstance();   //实例化(调用Application的配置信息)
        iv_img= (ImageView) findViewById(R.id.iv_img);
        //加载网络图片
        loader.displayImage("http://newandroid.cn/image/blm_two.jpg",iv_img);
        //加载本地图片
        String uri="file:///"+"本地路径";
        //监听事件
        loader.displayImage("http://newandroid.cn/image/blm_two.jpg", iv_img, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                Log.i("加载开始","onLoadingStarted");
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                Log.i("加载失败","onLoadingFailed");
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                Log.i("加载完成","onLoadingComplete");
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                Log.i("加载取消","onLoadingCancelled");
            }
        });

    }
}
