package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jeffreychang.xyz.servicecom_android_challenge.R;

/**
 * Created by chang on 6/6/2017.
 */

public class UserFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    public UserFragment () {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_users);

        return view;
    }

    public static UserFragment getInstance() {
        return new UserFragment();
    }
}
