package com.itenf.jsonapplicationcopy.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itenf.jsonapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    View view;
    private RecyclerView dogRecyclerView;
    private ArrayList<StringBuffer> dogFacts;
    private JSONArray serverList;
    DogListAdapter adapterDog;

    public static MainFragment newInstance() {
        return new MainFragment();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        dogFacts = new ArrayList<StringBuffer>();
       yourDataTask fetchJsonData = new yourDataTask();
       fetchJsonData.execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        dogRecyclerView = view.findViewById(R.id.dogRecyclerView);
       adapterDog = new DogListAdapter(view.getContext());
        dogRecyclerView.setAdapter(adapterDog);
        dogRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //adapterDog.setHondenLijst(dogFacts);
       // adapterDog.setHondenLijst(dogFacts);
        return view;
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //class to get data from https://cat-fact.herokuapp.com
        protected class yourDataTask extends AsyncTask<Void, Void, JSONArray>
    {
        @Override
        //protected JSONObject doInBackground(Void... params)
        protected JSONArray doInBackground(Void... params)
        {

           String str="https://cat-fact.herokuapp.com/facts/random?animal_type=dog&amount=20";
            //String str="https://cat-fact.herokuapp.com/facts";

            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try
            {
                Log.i("App" , "doInBackground");
                URL url = new URL(str);
                urlConn = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    Log.i("App" , "een regel is " + line);
                    stringBuffer.append(line);
                }
                Log.i("App" , "gelukt");
                serverList = new JSONArray(stringBuffer.toString());
                return serverList;

            }
            catch(Exception ex)
            {
                Log.e("App", "yourDataTask", ex);
                //if no results from  server make some results

                StringBuffer hond1 = new StringBuffer("Jaap");
                dogFacts.add(hond1);
                StringBuffer hond2 = new StringBuffer("Kees");
                dogFacts.add(hond2);
                StringBuffer hond3 = new StringBuffer("Mien");
                dogFacts.add(hond3);
                StringBuffer hond4 = new StringBuffer("Klazien");
                dogFacts.add(hond4);
                StringBuffer hond5 = new StringBuffer("Vla");
                dogFacts.add(hond5);
                Log.i("App" , dogFacts.get(3).toString());
                return null;
            }
            finally
            {
                if(bufferedReader != null)
                {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(JSONArray response)
        {
            if(response != null)
            //put all dogfacts in a StringBuffer ArrayList that can be used in the recyclerview
            {
                getDogFacts(response);
                adapterDog.setHondenLijst(dogFacts);
            }else{
                adapterDog.setHondenLijst(dogFacts);
            }
        }
    }

    //can be used to put all dogfacts in a StringBuffer ArrayList that can be used in the recyclerview
    public ArrayList<StringBuffer> getDogFacts(JSONArray list){

        for (int i=0 ; i< list.length() ; i++){
            try {
                JSONObject oneDog = list.getJSONObject(i);
                String oneDogFact =  oneDog.get("text").toString();
                StringBuffer oneDogFactBuffer = new StringBuffer(oneDogFact);
                dogFacts.add(oneDogFactBuffer);
                Log.i("App" , "getDogFacts gelukt");

            }catch (Exception e){
                Log.i("App" , "getDogFacts niet gelukt");
            }
        }
        return dogFacts;

    }



}