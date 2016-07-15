package com.example.mukhtaryusuf.stockfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String LOG_TAG = MainActivity.class.getSimpleName();
    ArrayList<String> symbols;

    ListView companyListView;
    TextView results;
    Button processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        results = (TextView) findViewById(R.id.textViewResult);
        processButton = (Button) findViewById(R.id.buttonProcess);
        companyListView = (ListView) findViewById(R.id.listViewResult);

        symbols = new ArrayList<String>(Arrays.asList("GOOG","AAPL","YHOO","MSFT","ADBE"));

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Synchronizer synchronizer = new Synchronizer(symbols);
                synchronizer.execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class Synchronizer extends CompaniesData {
        public Synchronizer(ArrayList<String> arrayList) {
            super(arrayList);
        }

        public void execute(){
            //super.execute();
            SynchronizedDownloader sd = new SynchronizedDownloader();
            sd.execute();
        }

        public class SynchronizedDownloader extends Downloader {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.i(LOG_TAG, data);
                //results.setText(data);
                for(Company c : companyList){
                    Log.i(LOG_TAG, c.toString());
                }

                CompanyAdapter companyAdapter = new CompanyAdapter(getApplicationContext(), R.layout.company_row, R.id.company_name, companyList);
                companyListView.setAdapter(companyAdapter);
            }
        }
    }
}