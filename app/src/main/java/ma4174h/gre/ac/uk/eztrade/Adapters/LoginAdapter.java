package ma4174h.gre.ac.uk.eztrade.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ma4174h.gre.ac.uk.eztrade.Fragments.LoginFragment;
import ma4174h.gre.ac.uk.eztrade.Fragments.RegisterFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int numOfTabs;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int numOfTabs) {
        super(fm);
        this.context = context;
        this.numOfTabs = numOfTabs;
    }

//    public LoginAdapter(FragmentManager fm, int tabCount) {
//        super(fm);
//    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                RegisterFragment registerFragment = new RegisterFragment();
                return registerFragment;
            default:
                return null;
        }
    }
}
