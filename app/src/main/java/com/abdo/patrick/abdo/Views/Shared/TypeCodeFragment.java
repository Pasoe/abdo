package com.abdo.patrick.abdo.Views.Shared;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Random;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeCodeFragment extends Fragment implements View.OnClickListener {


    public TypeCodeFragment() {
        // Required empty public constructor
    }

    private OkHttp okHttp;

    private TextView toolbarSave;
    private Button generateCodeButton;
    private TextView generatedCode;
    private Boolean generateCode = false;
    private ProgressBar progressBar;
    private String sharecode = "";

    private void ApplyGeneratedCode(){
        progressBar.setVisibility(View.INVISIBLE);

        generatedCode.setText(sharecode);
        generatedCode.setVisibility(View.VISIBLE);


        sharecode = "";
    }

    private void FailureGeneratedCode(){
        progressBar.setVisibility(View.INVISIBLE);

        generatedCode.setText(getString(R.string.error));
        generatedCode.setVisibility(View.VISIBLE);

        sharecode = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_code, container, false);

        okHttp = new OkHttp();
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        TextView toolbarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.title_code));

        if(getArguments() != null){
            generateCode = getArguments().getBoolean("generate", false);
        }

        if(!generateCode) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


            toolbarSave = (TextView) getActivity().findViewById(R.id.toolbar_save);
            toolbarSave.setVisibility(View.VISIBLE);
            toolbarSave.setOnClickListener(this);

            final EditText editText = (EditText) view.findViewById(R.id.editTextInputCode);

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
        }else{
            LinearLayout inputField =  (LinearLayout) view.findViewById(R.id.inputShareCodeLayout);
            inputField.setVisibility(View.GONE);

            RelativeLayout generateField =  (RelativeLayout) view.findViewById(R.id.generateShareCodeLayout);
            generateField.setVisibility(View.VISIBLE);

            generateCodeButton =  (Button) view.findViewById(R.id.generatedCodeButton);
            generateCodeButton.setOnClickListener(this);
            generatedCode = (TextView) view.findViewById(R.id.generatedCodeField);

            TextView sharecodeHeader = (TextView) view.findViewById(R.id.sharecode_header);
            sharecodeHeader.setText("Få en delekode");

            TextView sharecodeHelpText = (TextView) view.findViewById(R.id.sharecode_helptext);
            sharecodeHelpText.setText("Med en delekode, kan andre enheder få adgang til dette barns data. Koden er aktiv i 30 minutter efter generering.");
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.toolbar_save)){
            //TODO - SAVE CODE ON SERVER!!
            FragmentManager fm = getFragmentManager();
            fm.popBackStack();
        }
        if(v == generateCodeButton){

            generateCodeButton.setVisibility(View.INVISIBLE);
            generatedCode.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            sharecode = random();

            GenerateShareCode gCode = new GenerateShareCode();
            gCode.execute();
        }
    }

    public String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int tempChar;
        for (int i = 0; i < 6; i++){
            tempChar =  (generator.nextInt(10));
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    @Override
    public void onPause() {
        if(!generateCode){
            toolbarSave.setVisibility(View.INVISIBLE);
        }
        super.onPause();
    }

    private class GenerateShareCode extends AsyncTask<Void, Void, Response>{

        @Override
        protected Response doInBackground(Void... voids) {
            try
            {
                OkHttpClient client = new OkHttpClient();
                String sharecodeApiURL = getString(R.string.api_sharecode);
                String deviceId = Application.getAndroidId(getContext().getApplicationContext());
                String childGuid = Application.getInstance().getCurrentChild().getGuid();
                String url = sharecodeApiURL + deviceId + "/" + childGuid + "/" + "?sharecode=" + sharecode;
                String json = "";

                RequestBody body = RequestBody.create(OkHttp.JSON, json);
                final Request request = new Request.Builder()
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

            if (response.code() == HttpURLConnection.HTTP_OK)
            {
                ApplyGeneratedCode();
                OkHttp.LogSuccess(response, "POST", "No data");
            }
            else
            {
                FailureGeneratedCode();
                OkHttp.LogFailure(response, "POST");
            }

        }
    }
}
