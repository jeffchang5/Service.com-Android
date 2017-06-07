package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;
import jeffreychang.xyz.servicecom_android_challenge.network.RestClient;
import jeffreychang.xyz.servicecom_android_challenge.network.BaseCallback;

/**
 * Created by chang on 6/6/2017.
 */

public class UserFragment extends BaseFragment {

    private ArrayList<User> mUserList;
    private UserAdapter mUserAdapter;

    public UserFragment () {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserList = new ArrayList<>();
        loadAllUsers();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        // All setup for RecyclerView

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_users);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // This sets up a divider between each ViewHolder.

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mUserAdapter = new UserAdapter(getContext(), mUserList);
        recyclerView.setAdapter(mUserAdapter);

        return view;
    }

    public static UserFragment getInstance() {
        return new UserFragment();
    }
    public void loadAllUsers() {
        RestClient.getInstance().getAllUsers(new BaseCallback<List<User>>() {
            @Override
            public void success(List<User> response) {
                mUserList.clear();
                mUserList.addAll(response);
                mUserAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(Throwable error) {
                Toast.makeText(getContext(), "Could not get a list of users.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
