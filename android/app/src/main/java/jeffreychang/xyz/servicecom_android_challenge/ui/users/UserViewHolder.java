package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;

/**
 * Created by chang on 6/6/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    private View mParentView;
    private Context mContext;
    final ImageView userImageView;
    private final TextView nameTextView;
    private final TextView roleTextView;


    public interface OnItemClickListener {
        void startDetailFragment(User user);
    }

    public UserViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mParentView = itemView;
        nameTextView = (TextView) itemView.findViewById(R.id.user_name);
        roleTextView = (TextView) itemView.findViewById(R.id.user_role);
        userImageView = (ImageView) itemView.findViewById(R.id.user_photo);
    }
    public void bindUser(final User user) {
        String role = user.getRole();
        nameTextView.setText(user.getName());
        roleTextView.setText(String.format("%s%s", role.substring(0,1).toUpperCase(), role.substring(1)));
        mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnItemClickListener) mContext).startDetailFragment(user);
            }
        });
    }
}
