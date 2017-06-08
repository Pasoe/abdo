package com.abdo.patrick.abdo.Views.Shared;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestoreFromEmailFragment extends Fragment implements View.OnClickListener {


    public RestoreFromEmailFragment() {
        // Required empty public constructor
    }

    public TextView toolbarSave;
    private Boolean saveEmail = false;
    public EditText editText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restore_from_email, container, false);

        if(getArguments() != null){
            saveEmail = getArguments().getBoolean("save", false);
        }

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.title_restore));

        if(!saveEmail) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }else{
            TextView restoreHeader = (TextView) view.findViewById(R.id.restore_header);
            TextView restoreHelpText = (TextView) view.findViewById(R.id.restore_helptext);

            restoreHeader.setText("Tilnyt din email");
            restoreHelpText.setText("Tilknytter du en email til din abdo bruger, kan du gendanne dine indstillinger på andre enheder.");
        }

        //TODO - uncomment this when implementing restore functionality
//        Toast.makeText(getActivity(), "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
        toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
        toolbarSave.setVisibility(View.VISIBLE);
        toolbarSave.setOnClickListener(this);

        editText = (EditText) view.findViewById(R.id.editTextInputEmail);
        if(saveEmail && Application.getInstance().get_anonymous().getEmail() != null){
            editText.setText(Application.getInstance().get_anonymous().getEmail());
        }

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(editText, 0);
                else
                    keyboard.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.toolbar_save)){

            if(saveEmail) {
                SaveEmail saveMail = new SaveEmail();
                saveMail.execute(editText.getText().toString());
            }else{
                Toast.makeText(Application.getInstance(), "Denne funktion er endnu ikke implementeret.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPause() {
        //TODO - Uncomment when functionality is implemented
//        toolbarSave.setVisibility(View.INVISIBLE);
        super.onPause();
    }

    private void ApplyEmail(){
        Application.getInstance().get_anonymous().setEmail(editText.getText().toString());
        Application.getInstance().addItemToPreference("Anonymous", Application.getInstance().get_anonymous());
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }

    private void FailedToPostEmail(){
        Toast.makeText(Application.getInstance().getApplicationContext(), "Kunne ikke gemme emailen", Toast.LENGTH_SHORT).show();
    }

    private class SaveEmail extends AsyncTask<String, Void, Response> {

        @Override
        protected Response doInBackground(String... args) {
            try
            {
                OkHttpClient client = new OkHttpClient();
                String emailApiURL = getString(R.string.api_email);
                String deviceId = Application.getAndroidId(getContext().getApplicationContext());
                String email = args[0];
                String url = emailApiURL + deviceId + "/" + "?email=" + email;
                String json = "";

                RequestBody body = RequestBody.create(OkHttp.JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                return client.newCall(request).execute();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response response) {

            if (response != null)
            {
                if (response.code() == HttpURLConnection.HTTP_OK)
                {
                    ApplyEmail();
                    OkHttp.LogSuccess(response, "POST", "No data");
                }
                else
                {
                    FailedToPostEmail();
                    OkHttp.LogFailure(response, "POST");
                }
            }
            else Log.i("AsyncTask/OkHttp", "Response is null");
        }
    }

//    private class GetChildByShareCode extends AsyncTask<String, Void, Void>{
//
//        private String responseData;
//        private int code;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            try
//            {
//                OkHttpClient client = new OkHttpClient();
//                String sharecodeApiURL = getString(R.string.api_sharecode);
//                String deviceId = Application.getAndroidId(getContext().getApplicationContext());
//                String sharecode_text = params[0];
//                String url = sharecodeApiURL + deviceId + "?sharecode=" + sharecode_text;
//
//                Request request = new Request.Builder()
//                        .url(url)
//                        .build();
//                Response response = client.newCall(request).execute();
//
//                responseData = response.body().string();
//                code = response.code();
//
//                Log.i("OkHttp",
//                        "Failure : {GET}                         \n" +
//                                "Protocol: "+response.protocol()+       "\n" +
//                                "Code    : "+response.code()           +"\n" +
//                                "Message : "+response.message()        +"\n" +
//                                "Response: "+responseData              +"\n" +
//                                "URL     : "+response.networkResponse().request().url());
//
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void voids) {
//
//            if (code == HttpURLConnection.HTTP_OK)
//            {
//                if (responseData != null && !responseData.equals("null"))
//                {
//                    Gson gson = new Gson();
//                    Log.i("AsyncTask", "Parsing with Gson");
//
//                    Child child = gson.fromJson(responseData, Child.class);
//
//                    Log.i("AsyncTask", "Parsing with Gson done!");
//                    Log.i("AsyncTask", "Response: "+responseData);
//                    Log.i("AsyncTask", "Child: "+child.toString());
//
//                    Application.getInstance().addNewChildToAnonymous(child);
//
//                    Intent i =  new Intent(getActivity(), MainActivity.class);
//                    getActivity().startActivity(i);
//                    getActivity().finish();
//                }
//                else Toast.makeText(getActivity(), "Ugyldig kode", Toast.LENGTH_SHORT).show();
//
//
//            }
//            else Toast.makeText(getActivity(), "Ugyldig kode", Toast.LENGTH_SHORT).show();
//
//        }
//    }

}
