package com.smarthouse.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.smarthouse.activity.BannerDomain;
import com.smarthouse.activity.R;
import com.smarthouse.activity.R.color;

import android.R.integer;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * door fragment inlcuding banner
 * @author liyangchao
 *
 */
public class DoorFragment extends Fragment {
	
	public static String IMAGE_CACHE_PATH = "imageloader/Cache";
	
	public ViewPager viewPager;
	public List<ImageView> imageViews;// 滑动的图片集合
	public List<View> dotList;
	public List<View> dotS;  // 图片标题正文的那些点
	public View dots0, dots1, dots2;
	public TextView tv_date, tv_title, tv_topic, tv_topic_from;
	
	public int currentItem; 
	
	private ScheduledExecutorService scheduledExecutorService;
	
	// 异步加载图片
	private ImageLoader mImageLoader;
	private DisplayImageOptions options;

	
	private List<BannerDomain> bannerList;
	
	private Handler handler = new Handler(){
		public void handlerMessage(Message msg) {
			viewPager.setCurrentItem(currentItem);
		}
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_door, null);
		viewPager = (ViewPager) view.findViewById(R.id.vp);
		
		initData();
		return view;
	}

	private void initImageLoader() {
		File cacheDir = StorageUtils.getOwnCacheDirectory(getActivity(), IMAGE_CACHE_PATH);
		DisplayImageOptions defaultImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
		
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(defaultImageOptions).memoryCache(new LruMemoryCache(12 * 1024 * 1024))
				.memoryCacheSize(12 * 1024 * 1024)
				.discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		
		ImageLoader.getInstance().init(configuration);
	}
	
	private void initData() {
		
		bannerList = getBannerDomains();
		imageViews = new ArrayList<ImageView>();
		
		dotS = new ArrayList<View>();
		dots0 = viewPager.findViewById(R.id.v_dot0);
		dots1 = viewPager.findViewById(R.id.v_dot1);
		dots2 = viewPager.findViewById(R.id.v_dot2);
		
		viewPager.setAdapter(new MyAdapter());
		
		tv_date = (TextView) viewPager.findViewById(R.id.tv_date);
		tv_title = (TextView) viewPager.findViewById(R.id.tv_title);
		tv_topic = (TextView) viewPager.findViewById(R.id.tv_topic);
		tv_topic_from = (TextView) viewPager.findViewById(R.id.tv_topic_from);

		dotS.add(dots0);
		dotS.add(dots1);
		dotS.add(dots2);
		
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		addDynamicView();
	}
	
	
	private void addDynamicView() {
		// TODO Auto-generated method stub
		
	}


	private class MyPageChangeListener implements OnPageChangeListener{

		private int oldPosition = 0;
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			currentItem = position;
			BannerDomain bannerDomain = bannerList.get(position);
			tv_title.setText(bannerDomain.getTitle());
			tv_topic.setText(bannerDomain.getDescription());
			tv_topic_from.setText(bannerDomain.getImgUrl());
			dotS.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dotS.get(position).setBackgroundResource(R.drawable.dot_focused);
		}
		
	}
	
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return bannerList.size();
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			ImageView imageView = imageViews.get(position);
			((ViewPager)container).addView(imageView);
			final BannerDomain bannerDomain = bannerList.get(position);
			//设置图片的点击事件
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					
				}
			});
			
			return imageView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView((View)object);
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}
	
	public static List<BannerDomain> getBannerDomains() {
		
		List<BannerDomain> adList = new ArrayList<BannerDomain>();
		
		BannerDomain bannerDomain = new BannerDomain();
		bannerDomain.setId("1234567");
		bannerDomain.setTitle("l am Louis");
		bannerDomain.setDescription("我想知道和你有什么关系？");
		bannerDomain.setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
		bannerDomain.setAd(false);
		adList.add(bannerDomain);
		
		BannerDomain bannerDomain1 = new BannerDomain();
		bannerDomain.setId("12345678");
		bannerDomain.setTitle("l am Cathy");
		bannerDomain.setDescription("我要去跳广场舞");
		bannerDomain.setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
		bannerDomain.setAd(true);
		adList.add(bannerDomain1);
		
		
		return adList;
	}
}
