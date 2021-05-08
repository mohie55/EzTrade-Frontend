//package ma4174h.gre.ac.uk.eztrade.Adapters;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import ma4174h.gre.ac.uk.eztrade.Fragments.ChooseCategoryFragment;
//
//public class CategoryTabPagerAdapter extends FragmentPagerAdapter {
//
//    public CategoryTabPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//    @Override
//    public Fragment getItem(int position) {
//        Fragment fragment = null;
//        if (position == 0)
//        {
//            fragment = new ChooseCategoryFragment();
//        }
//        return fragment;
//    }
//    @Override
//    public int getCount() {
//        return 1;
//    }
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = null;
//        if (position == 0)
//        {
//            title = "List";
//        }
//        return title;
//    }
//}
