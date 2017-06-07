package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jeffreychang.xyz.servicecom_android_challenge.MainActivity;
import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;

/**
 * Created by chang on 6/6/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final Context mContext;
    private ArrayList<User> mUserList;
    private OnDataBindListener mCallback;

    public interface OnDataBindListener {
        void getUserPhotoResource(int id);
    }


    UserAdapter(Context context, ArrayList<User> list) {
        mContext = context;
        mUserList = list;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_users, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        User currentUser = mUserList.get(i);
        String role = currentUser.getRole();
        userViewHolder.nameTextView.setText(currentUser.getName());
        userViewHolder.roleTextView.setText(String.format("%s%s", role.substring(0,1).toUpperCase(), role.substring(1)));
        Toast.makeText(mContext, currentUser.getProfilePic(), Toast.LENGTH_SHORT).show();
//        mCallback = (MainActivity) mContext;
//        Picasso.with(mContext)
//                .load(currentUser.)
//                .placeholder(R.color.colorAccent)
//                .into(holder.imageView);
//        mCallback.getUserPhotoResource(currentUser.getId());
        //        userViewHolder.userImageView
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
