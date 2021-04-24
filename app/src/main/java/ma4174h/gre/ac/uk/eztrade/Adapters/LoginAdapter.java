package ma4174h.gre.ac.uk.eztrade.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ma4174h.gre.ac.uk.eztrade.Fragments.LoginFragment;
import ma4174h.gre.ac.uk.eztrade.Fragments.RegisterFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    private int numOfTabs;

    public LoginAdapter(FragmentManager fm, Context context, int numOfTabs) {
        super(fm);
        this.context = context;
        this.numOfTabs = numOfTabs;
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
            fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Login";
            case 1:
                return "Register";
            default:
                return null;
        }
//        return super.getPageTitle(position);
    }
}
