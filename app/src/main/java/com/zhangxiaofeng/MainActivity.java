package com.zhangxiaofeng;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhangxiaofeng.base.Base;
import com.zhangxiaofeng.databinding.ActivityMainBinding;
import com.zhangxiaofeng.fragment.*;


public class MainActivity extends Base<ActivityMainBinding> {
    @Override
    protected ActivityMainBinding getBind() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

        SparseArray<Fragment> spa = new SparseArray<>();
        spa.put(R.id.mainNav1, new Fragment1());
        spa.put(R.id.mainNav2, new Fragment2());

        final Fragment[] current = {spa.get(R.id.mainNav1)};

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (int i = 0; i < spa.size(); i++) {
            int key = spa.keyAt(i);
            Fragment value = spa.valueAt(i);
            fragmentTransaction.add(R.id.main, value);
            if (value != current[0])
                fragmentTransaction.hide(value);
        }

        fragmentTransaction.commit();

        bd.nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = spa.get(item.getItemId());
                if (fragment != current[0]) {
                    getSupportFragmentManager().beginTransaction().show(fragment).hide(current[0]).commit();
                    current[0] = fragment;
                }
                return true;
            }
        });
    }
}