package com.abdo.patrick.abdo.Views.Shared;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.ShareCode;
import com.abdo.patrick.abdo.R;
import com.abdo.patrick.abdo.Views.MainActivity;
import com.abdo.patrick.abdo.Views.SetupActivity;
import com.google.gson.Gson;

import org.joda.time.Period;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeCodeFragment extends Fragment implements View.OnClickListener {


    public TypeCodeFragment() {
        // Required empty public constructor
    }

    private static boolean timerIsRunning;
    CountDownTimer count;

    private TextView toolbarSave;
    private Button generateCodeButton;
    private TextView generatedCode;
    private Boolean generateCode = false;
    private ProgressBar progressBar;
    private String sharecode = "";
    private EditText editText;


    private void ApplyGeneratedCode(){
        progressBar.setVisibility(View.INVISIBLE);

        generatedCode.setText(sharecode);
        generatedCode.setVisibility(View.VISIBLE);

        Long now = new Date().getTime();
        Child currentChild = Application.getInstance().getCurrentChild();
        ShareCode childSharecode = currentChild.getShareCode();

        childSharecode.setCode(sharecode);
        childSharecode.setGeneratedTime(now);

        Application.getInstance().updateCurrentChildData(currentChild);
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

            editText = (EditText) view.findViewById(R.id.editTextInputCode);

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
            //Setup normal view
            LinearLayout inputField =  (LinearLayout) view.findViewById(R.id.inputShareCodeLayout);
            inputField.setVisibility(View.GONE);

            RelativeLayout generateField =  (RelativeLayout) view.findViewById(R.id.generateShareCodeLayout);
            generateField.setVisibility(View.VISIBLE);

            TextView sharecodeHeader = (TextView) view.findViewById(R.id.sharecode_header);
            sharecodeHeader.setText("Få en delekode");

            TextView sharecodeHelpText = (TextView) view.findViewById(R.id.sharecode_helptext);

            //Initialize view components
            generatedCode = (TextView) view.findViewById(R.id.generatedCodeField);
            generateCodeButton =  (Button) view.findViewById(R.id.generatedCodeButton);
            generateCodeButton.setOnClickListener(this);

            //Check for valid sharecode
            Long now = new Date().getTime();
            Child currentChild = Application.getInstance().getCurrentChild();
            ShareCode childSharecode = currentChild.getShareCode();
            boolean valid = false;
            if (!childSharecode.getCode().isEmpty())
            {
                Period interval = new Period(childSharecode.getGeneratedTime(), now);
                valid = interval.getMinutes() < 5;
            }
            if (valid)
            {
                generateCodeButton.setVisibility(View.INVISIBLE);
                generatedCode.setText(childSharecode.getCode());
                generatedCode.setVisibility(View.VISIBLE);
                sharecodeHelpText.setText(getString(R.string.sharecode_generated));
            }
            else sharecodeHelpText.setText(getString(R.string.sharecode_generate_full));

        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == v.findViewById(R.id.toolbar_save)){
            String sharecode_text = editText.getText().toString();


            if(!sharecode_text.isEmpty())
            {
                GetChildByShareCode getChildByShareCode = new GetChildByShareCode();
                getChildByShareCode.execute(sharecode_text);

            }
            else Toast.makeText(getActivity(), "Delekoden kan ikke være tom", Toast.LENGTH_SHORT).show();
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
                    ApplyGeneratedCode();
                    OkHttp.LogSuccess(response, "POST", "No data");
                }
                else
                {
                    FailureGeneratedCode();
                    OkHttp.LogFailure(response, "POST");
                }
            }
            else Log.i("AsyncTask/OkHttp", "Response is null");
        }
    }

    private class GetChildByShareCode extends AsyncTask<String, Void, Void>{

        private String responseData;
        private int code;

        @Override
        protected Void doInBackground(String... params) {
            try
            {
                OkHttpClient client = new OkHttpClient();
                String sharecodeApiURL = getString(R.string.api_sharecode);
                String deviceId = Application.getAndroidId(getContext().getApplicationContext());
                String sharecode_text = params[0];
                String url = sharecodeApiURL + deviceId + "?sharecode=" + sharecode_text;

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();

                responseData = response.body().string();
                code = response.code();

                Log.i("OkHttp",
                        "Failure : {GET}                         \n" +
                        "Protocol: "+response.protocol()+       "\n" +
                        "Code    : "+response.code()           +"\n" +
                        "Message : "+response.message()        +"\n" +
                        "Response: "+responseData              +"\n" +
                        "URL     : "+response.networkResponse().request().url());

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {

            if (code == HttpURLConnection.HTTP_OK)
            {
                if (responseData != null && !responseData.equals("null"))
                {
                    Gson gson = new Gson();
                    Log.i("AsyncTask", "Parsing with Gson");

                    Child child = gson.fromJson(responseData, Child.class);

                    Log.i("AsyncTask", "Parsing with Gson done!");
                    Log.i("AsyncTask", "Response: "+responseData);
                    Log.i("AsyncTask", "Child: "+child.toString());

                    Application.getInstance().addNewChildToAnonymous(child);

                    Intent i =  new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(i);
                    getActivity().finish();
                }
                else Toast.makeText(getActivity(), "Ugyldig kode", Toast.LENGTH_SHORT).show();


            }
            else Toast.makeText(getActivity(), "Ugyldig kode", Toast.LENGTH_SHORT).show();

        }
    }
}
