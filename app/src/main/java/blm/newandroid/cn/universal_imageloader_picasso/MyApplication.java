package blm.newandroid.cn.universal_imageloader_picasso;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * Created by BLM on 2016/4/21.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(400,800)  //每个文件的最大长宽
                .diskCacheExtraOptions(400,800,null)  //保存到硬盘的缓存最大长宽
                .threadPoolSize(3)  //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY-2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2*1024*1024))
                .memoryCacheSize(2*1024*1024)    //硬盘的缓存大小,超出后会自动释放
                .discCacheSize(50*1024*1024)    //最大缓存数
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())     //使用md5加密图片名称
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCacheFileCount(100)    //缓存文件数量
                .diskCache(     //缓存路径
                        new UnlimitedDiskCache(new File(
                                Environment.getExternalStorageDirectory()+"myApp/imgCache"))
                )
                .defaultDisplayImageOptions(getDisplayOptions())    //加载图片时默认的配置信息
                .imageDownloader(
                        new BaseImageDownloader(this,5*1000,30*1000)        //图片加载超时时间,连接时间
                )
                .writeDebugLogs()   //错误写入日志
                .build();   //开始构建
        ImageLoader.getInstance().init(config);

    }

    public DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options;
        options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)   //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)     //设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)      //设置图片加载解码过程中错误时候显示的图片
                .cacheInMemory(true)    //设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)      //设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)       //是否考虑JPEG图像EXIF参数(旋转,翻转)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)       //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)        //设置图片的解码类型//
//                .delayBeforeLoading(int delayInMillis)  //int,为你设置的下载前的延迟时间
//                .preProcessor(BitmapProcessor preProcessor)     //图片加入缓存前,对bitmap进行设置
                .resetViewBeforeLoading(true)   //设置图片在下载前是否重置,复位
                .displayer(new RoundedBitmapDisplayer(20))  //是否设置为圆角,弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))  //设置键入的加载动画的时间
                .build();   //构建完成



        return options;
    }
}
