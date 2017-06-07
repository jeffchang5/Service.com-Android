package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.ui.common.BaseFragment;

/**
 * Created by chang on 6/6/2017.
 */

public class UserFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    public UserFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.user_recycler_view);
//        mRecyclerView.setAdapter(new UserAdapter());
        return v;
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }
}
