package cn.adeerlongneck.app.smartschoolapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.adeerlongneck.app.smartschoolapp.Activity.MainActivity;
import cn.adeerlongneck.app.smartschoolapp.Fragment.LoginFragment;
import cn.adeerlongneck.app.smartschoolapp.Fragment.RegisterFragment;

/**
 * Created by 长脖鹿 on 2017/11/21.
 */

public class LoginFragmentAdapter extends FragmentPagerAdapter {


    private final int PAGER_COUNT = 2;
    private LoginFragment loginFragment = null;
    private RegisterFragment  registerFragment = null;
    private ArrayList<String> titleLists;
    private int myposition;

    public LoginFragmentAdapter(FragmentManager fm,ArrayList<String> titleLists) {
        super(fm);
        this.titleLists=titleLists;
        loginFragment =new LoginFragment();
        registerFragment =new RegisterFragment();

    }
    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        //传入Fragment
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = loginFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = registerFragment;
                break;

        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //用List传入title，这里写一个大小能够变换的
        //TODO

        this.myposition=position;
        return titleLists.get(position);
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
