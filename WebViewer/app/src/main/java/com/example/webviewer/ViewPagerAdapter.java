package com.example.webviewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            WebViewFragment webViewFragment = new WebViewFragment("https://sports.news.naver.com/index");
            return webViewFragment;
//            AFragment aFragment = new AFragment();
//            return  aFragment;
        }
        else if(position==1){
            WebViewFragment webViewFragment = new WebViewFragment("https://entertain.naver.com/home");
            return webViewFragment;
//            BFragment bFragment = new BFragment();
//            return  bFragment;
        }
        else{
            WebViewFragment webViewFragment = new WebViewFragment("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101");
            return webViewFragment;
//            CFragment cFragment = new CFragment();
//            return  cFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 탭의 개수 반드시 바꿔주어야 함.
    }
}
