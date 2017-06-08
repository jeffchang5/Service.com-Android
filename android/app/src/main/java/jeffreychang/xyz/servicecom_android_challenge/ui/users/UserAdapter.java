package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;

/**
 * Created by chang on 6/6/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final Context mContext;

    private ArrayList<User> mUserList;



    UserAdapter(Context context, ArrayList<User> list) {
        mContext = context;
        mUserList = list;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        UserViewHolder userViewHolder = new UserViewHolder(mContext,
                LayoutInflater.from(mContext).inflate(R.layout.viewholder_users, viewGroup, false));

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        User currentUser = mUserList.get(i);
        userViewHolder.bindUser(currentUser);
        Picasso.with(mContext)
                .load(currentUser.getProfilePic())
                .placeholder(R.color.colorAccent)
                .into(userViewHolder.userImageView);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
