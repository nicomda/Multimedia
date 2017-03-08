package org.danico.whoru;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;



public class SliderPagerActivity extends FragmentActivity
        implements FaceRecognitionFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        MyFavsFragment.OnFragmentInteractionListener
{
    private ViewPager viewPager;
    private FragmentPagerItemAdapter adapter;
    private SmartTabLayout viewPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_pager);
        loadViewPager();



    }
    public void loadViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Mis Profesores", MyFavsFragment.class)
                .add("Escanear", FaceRecognitionFragment.class)
                .add("Búsqueda", SearchFragment.class)

                .create());
        viewPager.setAdapter(adapter);
        viewPagerTab=(SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
