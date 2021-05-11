package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma4174h.gre.ac.uk.eztrade.Activities.MainActivity;
import ma4174h.gre.ac.uk.eztrade.Adapters.ExpandableListAdapter;
import ma4174h.gre.ac.uk.eztrade.R;

public class ChooseCategoryFragment extends Fragment {

    private Context context;
    private ExpandableListView expandableListView;
    List<String> listDataParent;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = this.getActivity();
        return inflater.inflate(R.layout.fragment_choose_category, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView = (ExpandableListView) view.findViewById(R.id.category_list_view);
        createListData();
        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO GroupClickListener work
                return false;
            }
        });
        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO GroupExpandListener work
            }
        });
        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                // TODO GroupCollapseListener work
            }
        });
        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText( context,
                        "Category: "+listDataChild.get(listDataParent.get(groupPosition)).get(childPosition),
                        Toast.LENGTH_SHORT).show();
                //Get the category chosen and return to previous fragment
                AddListingFragment addListingFragment = new AddListingFragment();

                //Get arguments from previous fragment (add listing)
                Bundle args = getArguments();
                //New bundle for adding category
                Bundle args2 = new Bundle();
                if (args != null) {
                    //Get arguments from previous fragment (add listing)
                    String title = args.getString("title", "");
                    String description = args.getString("description", "");
                    Double price = args.getDouble("price");
                    HashMap<Integer,Uri> selectedPhotos = (HashMap<Integer,Uri>) args.getSerializable("selectedPhotos");
//                    ArrayList<Bitmap> selectedPhotos = (ArrayList<Drawable>) args.getSerializable("selectedPhotos");

                    args2.putString("title",title);
                    args2.putString("description",description);
                    args2.putDouble("price",price);
                    args2.putSerializable("selectedPhotos",selectedPhotos);
                }

                args2.putString("category", listDataChild.get(listDataParent.get(groupPosition)).get(childPosition));

                addListingFragment.setArguments(args2);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_main, addListingFragment).commit();

//                Intent intent = new Intent(getActivity().getBaseContext(),
//                        MainActivity.class);
//                intent.putExtra("message", listDataChild.get(listDataParent.get(groupPosition)).get(childPosition));
//                intent.putExtra("fragment", listDataChild.get(listDataParent.get(groupPosition)).get(childPosition));
//                getActivity().startActivity(intent);
                return false;
            }
        });
    }
    private void createListData() {
        listDataParent = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        listDataParent.add("Home & garden");
        listDataParent.add("Electronics");
        listDataParent.add("Sports & Leisure");
        listDataParent.add("Other");
        // Adding child data List one
        List<String> HomeProducts = new ArrayList<String>();
        HomeProducts.add("Bedroom Furniture");
        HomeProducts.add("Dining and Living Room Furniture");
        HomeProducts.add("Kitchenware");
        HomeProducts.add("Garden & Patio");
        HomeProducts.add("Other Household Goods");
        // Adding child data List two
        List<String> Electronics  = new ArrayList<String>();
        Electronics.add("Phones");
        Electronics.add("Cameras");
        Electronics.add("Computers");
        Electronics.add("Gaming");
        Electronics.add("Drones");
        Electronics.add("Smart Watches");
        Electronics.add("Other Electronic Goods");
        // Adding child data List three
        List<String> sportProducts = new ArrayList<String>();
        sportProducts.add("Bikes");
        sportProducts.add("Camping");
        sportProducts.add("Fishing");
        sportProducts.add("Gym");
        sportProducts.add("Ball and racket");
        sportProducts.add("Other Sport Goods");
        //Other
        List<String> otherProducts = new ArrayList<String>();
        otherProducts.add("Services");
        otherProducts.add("Other goods");
        listDataChild.put(listDataParent.get(0), HomeProducts); // Header, Child data
        listDataChild.put(listDataParent.get(1), Electronics); // Header, Child data
        listDataChild.put(listDataParent.get(2), sportProducts); // Header, Child data
        listDataChild.put(listDataParent.get(3), otherProducts); // Header, Child data
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(context, listDataParent, listDataChild);
        expandableListView.setAdapter(listAdapter);
    }
}
