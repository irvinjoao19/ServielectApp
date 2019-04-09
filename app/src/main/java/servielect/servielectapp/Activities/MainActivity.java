package servielect.servielectapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import servielect.servielectapp.API.API;
import servielect.servielectapp.Fragments.InfoFragment;
import servielect.servielectapp.Fragments.TabFragment;
import servielect.servielectapp.R;
import servielect.servielectapp.Utils.Util;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences prefs;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView textViewNombre, textViewEmail;
    private ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        View view = navigationView.getHeaderView(0);
        setCredentialsIfExist(view);
        setFragmentByDefault();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_main:
                        fragment = new TabFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                }
                if (fragmentTransaction) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .commit();
                    item.setCheckable(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new TabFragment())
                .commit();
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setCheckable(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void setCredentialsIfExist(View view) {
        imageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogo);
        textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
        textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
        String nombre = Util.getNombrePrefs(prefs);
        String email = Util.getEmailPrefs(prefs);
        int id = Util.getIdPrefs(prefs);
        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(email)) {
            textViewNombre.setText(nombre);
            textViewEmail.setText(email);
            Picasso.with(getBaseContext()).load(API.BASE_URL_LOGO + id + API.FORMATO).into(imageViewLogo);
        } else {
            removeSharedPreferences();
            logOut();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_forget_logout:
                removeSharedPreferences();
                logOut();
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        // FLAG_ACTIVITY_NEW_TASK  | FLAG_ACTIVITY_CLEAR_TASK (NO ATRAS)
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removeSharedPreferences() {
        prefs.edit().clear().apply();
    }


}
