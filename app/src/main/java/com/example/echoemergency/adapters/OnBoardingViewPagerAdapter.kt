package com.example.echoemergency.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.echoemergency.R
import com.example.echoemergency.models.OnBoardingData

class OnBoardingViewPagerAdapter(private var context: Context, private var onBoardingDataList: List<OnBoardingData>): PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, container)
        val title: TextView = view.findViewById(R.id.tvTitle)
        val desc: TextView = view.findViewById(R.id.tvDesc)
        val onBoardingImage: ImageView = view.findViewById(R.id.ivOnBoardingImage)

        title.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc
        onBoardingImage.setImageResource(onBoardingDataList[position].imageUrl)

        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }
}