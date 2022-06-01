package com.example.duniamusik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<ProductModel> productModels = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public int[] products = {0,1,2,3};
    public String[][] detail_product = new String[4][6];

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ImageView cart = (ImageView) view.findViewById(R.id.cart);
        TextView[] names={
                (TextView) view.findViewById(R.id.product1Name),
                (TextView) view.findViewById(R.id.product2Name),
                (TextView) view.findViewById(R.id.product3Name),
                (TextView) view.findViewById(R.id.product4Name)
        };
        
        ImageView[] images={
                (ImageView) view.findViewById(R.id.product1),
                (ImageView) view.findViewById(R.id.product2),
                (ImageView) view.findViewById(R.id.product3),
                (ImageView) view.findViewById(R.id.product4)
        };

        TextView[] prices={
                (TextView) view.findViewById(R.id.product1Harga),
                (TextView) view.findViewById(R.id.product2Harga),
                (TextView) view.findViewById(R.id.product3Harga),
                (TextView) view.findViewById(R.id.product4Harga)
        };
        setUpProductModels();

        for(int i=0;i<4;i++){
            names[i].setText(productModels.get(i).nama);
            prices[i].setText(productModels.get(i).harga);
            Context context = images[i].getContext();
            int id = context.getResources().getIdentifier(productModels.get(i).thumbnail, "drawable", context.getPackageName());
            images[i].setImageResource(id);

            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.parseInt(view.getTag().toString())-1;

                    Intent intent = new Intent(getActivity(),DetailProduct.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",productModels.get(id).nama);
                    intent.putExtra("price",productModels.get(id).harga);
                    intent.putExtra("image",productModels.get(id).thumbnail);
                    intent.putExtra("description", productModels.get(id).description);
                    startActivity(intent);
                }
            });
        }

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
    private void setUpProductModels(){
        String[] productNames = getResources().getStringArray(R.array.product_name_strings);
        String[] productHargas = getResources().getStringArray(R.array.product_price_strings);
        String[] productThumbnails = getResources().getStringArray(R.array.product_thumbnail_strings);
        String[] productDescriptions = getResources().getStringArray(R.array.product_description_strings);

        for (int i=0;i< productNames.length;i++){
            productModels.add(new ProductModel(
                            productThumbnails[i],
                            productNames[i],
                            productHargas[i],
                            productDescriptions[i])
                    );
        }

    }
}