package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import jeffreychang.xyz.servicecom_android_challenge.models.User;

/**
 * Created by chang on 6/6/2017.
 */

class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> mUsers;

    private UserAdapter(ArrayList<User> userList) {
        mUsers = userList;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        mUsers.get(i);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
