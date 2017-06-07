package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jeffreychang.xyz.servicecom_android_challenge.R;

/**
 * Created by chang on 6/6/2017.
 */

class UserViewHolder extends RecyclerView.ViewHolder {
    public final ImageView userImageView;
    public final TextView nameTextView;
    public final TextView roleTextView;

    public UserViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.user_name);
        roleTextView = (TextView) itemView.findViewById(R.id.user_role);
        userImageView = (ImageView) itemView.findViewById(R.id.user_photo);
    }
}
