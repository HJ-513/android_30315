package com.example.webviewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webviewer.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

    FragmentWebViewBinding binding;

    String webUrl;
    WebViewFragment(String webUrl){
        this.webUrl = webUrl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWebViewBinding.inflate(inflater);

        //웹페이지 띄우기
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl("https://www.google.co.kr");

        return  binding.getRoot();
    }
}