package com.james.chargescreen;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class About extends ActionBarActivity {

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        counter = 0;

        Resources res = getResources();
        final String[] header = res.getStringArray(R.array.name);
        final String[] content = res.getStringArray(R.array.desc);
        final String[] url = res.getStringArray(R.array.url);

        while(counter<5) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.repos);
            final CardView lastused = new CardView(About.this);
            lastused.setId(counter);
            lastused.setClickable(true);
            lastused.setForeground(getSelectedItemDrawable());
            LinearLayout vertical = new LinearLayout(About.this);
            vertical.setOrientation(LinearLayout.VERTICAL);
            lastused.setUseCompatPadding(true);
            TextView txt1 = new TextView(About.this);
            TextView txt2 = new TextView(About.this);
            txt1.setText(header[counter]);
            txt1.setPadding(40, 30, 40, 0);
            txt1.setTextSize(20);
            txt2.setBackgroundColor(getResources().getColor(R.color.yellow));
            txt2.setTextColor(getResources().getColor(R.color.black));
            txt2.setText(content[counter]);
            txt2.setPadding(40, 30, 40, 40);
            linearLayout.addView(lastused);
            lastused.addView(vertical);
            vertical.addView(txt1);
            vertical.addView(txt2);
            lastused.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(url[lastused.getId()]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            counter = counter + 1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Uri uri = Uri.parse("http://theandroidmaster.github.io/cscreen");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Drawable getSelectedItemDrawable() {
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray ta = About.this.obtainStyledAttributes(attrs);
        Drawable selectedItemDrawable = ta.getDrawable(0);
        ta.recycle();
        return selectedItemDrawable;
    }
}
