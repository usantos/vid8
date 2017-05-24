package br.com.cinesky;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.cinesky.adapter.FilmeAdapter;
import br.com.cinesky.model.Filme;
import br.com.cinesky.parser.JSONParser;
import br.com.cinesky.utils.Keys;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Filme> list;
    private FilmeAdapter adapter;
    private ImageView imgSemConexao;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (GridView) findViewById(R.id.gridFilmes);
        imgSemConexao = (ImageView)findViewById(R.id.imgSemConexao);

        setSupportActionBar(toolbar);

        carregarListaFilmes();

        FloatingActionButton btnRecarregarFilmes = (FloatingActionButton) findViewById(R.id.btnRecarregarFilmes);
        btnRecarregarFilmes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                carregarListaFilmes();
            }
        });
    }

    protected void carregarListaFilmes(){
        try {
            list = new ArrayList<>();
            new GetDataTask().execute();
            gridView.setAdapter(null);
            adapter = new FilmeAdapter(MainActivity.this, list);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getBaseContext(), VideoActivity.class);
                    TextView tv =(TextView) view.findViewById(R.id.tvDescription);
                    i.putExtra("nomeFilme",tv.getText().toString());
                    startActivity(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            carregarListaFilmes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this,R.style.MyTheme);
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.setIndeterminateDrawable(getDrawable(R.drawable.custom_progress_dialog));
            dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                JSONArray jsonArray = JSONParser.getDataFromWeb();

                if (jsonArray != null) {
                    if(jsonArray.length() > 0) {

                        int lenArray = jsonArray.length();
                        if(lenArray > 0) {
                            for(int jIndex = 0; jIndex < lenArray; jIndex++) {

                                Filme model = new Filme();

                                JSONObject innerObject = jsonArray.getJSONObject(jIndex);

                                String backDropsUrl = innerObject.getString(Keys.KEY_BACKDROPS_URL);
                                String cover = innerObject.getString(Keys.KEY_COVER_URL);
                                String duration = innerObject.getString(Keys.KEY_DURATION);
                                String id = innerObject.getString(Keys.KEY_ID);
                                String overview = innerObject.getString(Keys.KEY_OVERVIEW);
                                String releaseYear = innerObject.getString(Keys.KEY_RELEASE_YEAR);
                                String title = innerObject.getString(Keys.KEY_TITLE);

                                model.setBackdrops_url(backDropsUrl);
                                model.setCover_url(cover);
                                model.setDuration(duration);
                                model.setId(id);
                                model.setOverview(overview);
                                model.setRelease_year(releaseYear);
                                model.setTitle(title);

                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            dialog.dismiss();

            if(list.size() > 0) {
                imgSemConexao.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            } else {
                imgSemConexao.setVisibility(View.VISIBLE);
                Snackbar.make(findViewById(R.id.gridFilmes), "Ooops! Nenhum filme encontrado!", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}