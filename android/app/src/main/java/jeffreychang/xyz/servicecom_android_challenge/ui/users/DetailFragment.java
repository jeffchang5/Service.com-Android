package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jeffreychang.xyz.servicecom_android_challenge.MainActivity;
import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.ui.common.BaseFragment;

/**
 * Created by chang on 6/7/2017.
 */

public class DetailFragment extends BaseFragment {
    public DetailFragment () {}

    public static DetailFragment newInstance() {

        DetailFragment detailFragment = new DetailFragment();

        Bundle args = new Bundle();
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).changeColorTheme(R.color.pink_400);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        return view;
    }

}
