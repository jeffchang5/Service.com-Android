package jeffreychang.xyz.servicecom_android_challenge;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import jeffreychang.xyz.servicecom_android_challenge.models.User;
import jeffreychang.xyz.servicecom_android_challenge.ui.common.BaseActivity;
import jeffreychang.xyz.servicecom_android_challenge.ui.users.DetailFragment;
import jeffreychang.xyz.servicecom_android_challenge.ui.users.UserAdapter;
import jeffreychang.xyz.servicecom_android_challenge.ui.users.UserFragment;
import jeffreychang.xyz.servicecom_android_challenge.ui.users.UserViewHolder;


public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        UserViewHolder.OnItemClickListener {

    private ActionBarDrawerToggle mActionToggle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (checkUser()) {
            ((TextView) mNavigationView.getHeaderView(0).findViewById(R.id.header_name))
                    .setText(getName());

        };

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_service_chat);
        mActionToggle = createActionToggle();
        mDrawerLayout.addDrawerListener(mActionToggle);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout: clearUser();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public ActionBarDrawerToggle createActionToggle() {
        return new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_users: switchFragments(UserFragment.newInstance());
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    public void switchFragments(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void startDetailFragment(User user) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.newInstance(user))
                .addToBackStack("DETAIL")
                .commit();
    }

    @TargetApi(21)
    public void changeColorTheme(int color) {
        assert getSupportActionBar() != null;
        mNavigationView.getHeaderView(0).setBackgroundColor(color);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setStatusBarColor(color);
        }
    }


}
