package com.example.carlo.starwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView) findViewById(R.id.listViewHeroes);
        getHeroes();
    }
    private void getHeroes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api api= retrofit.create(api.class);
        Call<List<hero>> call= api.getHeroes();
        call.enqueue(new Callback<List<hero>>() {
            @Override
            public void onResponse(Call<List<hero>> call, Response<List<hero>> response) {
                List<hero> heroList = response.body();
                Gson gson = new Gson();

                String[] heroes = new String[heroList.size()];

                gson.toJson(heroes);

                for(int i=0;i<heroList.size(); i++){
                    heroes[i]=heroList.get(i).getName() + " - "+heroList.get(i).getGender()+ " - "+heroList.get(i).getBirth_year();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes ));

            }

            @Override
            public void onFailure(Call<List<hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
