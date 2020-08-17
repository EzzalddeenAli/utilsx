package org.kingtec.utils.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.EzzalddeenAli.utilsx.R;
import org.kingtec.utils.slidr.Slidr;
import org.kingtec.utils.tools.Tools;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class SingleFragmentActivity extends zBaseActivity {
    public final static String FRAGMENT_PARAM = "fragment";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_single_fragment);
        Slidr.attach(this);
        Bundle b = getIntent().getExtras();
        Class<?> fragmentClass = null;
        if (b != null) {
            fragmentClass = (Class<?>) b.get(FRAGMENT_PARAM);
        } else finish();
        if (bundle == null) {
            Fragment f;
            if (fragmentClass != null) {
                f = Fragment.instantiate(this, fragmentClass.getName());
                f.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, f, fragmentClass.getName()).commit();

            } else finish();
        }
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        try {
            if (isValid(getIntent().getStringExtra("title"))) {

                ((TextView) findViewById(R.id.tvTitle)).setText(getIntent().getStringExtra("title"));
            }
//            if(isValid(getIntent().getStringExtra("subTitle"))) {
//
//            //    findViewById(R.id.tvSubTitle).setVisibility(View.VISIBLE);
//              //  ((TextView) findViewById(R.id.tvSubTitle)).setText(getIntent().getStringExtra("subTitle"));
//            }

        } catch (Exception ignored) {

        }
        try {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(null);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            Tools.setSystemBarColor(this, R.color.grey_5);
            Tools.setSystemBarLight(this);
        } catch (Exception ignored) {

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, Class<?> fragment, String title) {
        context.startActivity(new Intent(context, SingleFragmentActivity.class)
                .putExtra(SingleFragmentActivity.FRAGMENT_PARAM, fragment)
                .putExtra("title", title));
    }
}
