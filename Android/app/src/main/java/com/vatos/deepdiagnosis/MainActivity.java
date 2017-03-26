package com.vatos.deepdiagnosis;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> selectable, pic;
    FloatingActionButton fab;
    ProgressBar progressBar;
    TextView text;
    static String classification = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        classification = b.getString("classification");
        System.out.println(classification);

        text = (TextView) findViewById(R.id.progress_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectable = new ArrayList<String>();
                if (Build.VERSION.SDK_INT >= 23) {
                    int perm = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (perm != PackageManager.PERMISSION_GRANTED) {
                        requestPermission();
                    } else {
                        new FilePickerBuilder().setMaxCount(1)
                                .setSelectedFiles(selectable)
                                .setActivityTheme(R.style.AppTheme)
                                .pickPhoto(MainActivity.this);
                    }
                } else {
                    new FilePickerBuilder().setMaxCount(1)
                            .setSelectedFiles(selectable)
                            .setActivityTheme(R.style.AppTheme)
                            .pickPhoto(MainActivity.this);
                }
            }
        });
    }

    public void requestPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("DeepDiagnosis requires permission to access your photos")
                    .setPositiveButton("GRANT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new FilePickerBuilder().setMaxCount(1)
                            .setSelectedFiles(selectable)
                            .setActivityTheme(R.style.AppTheme)
                            .pickPhoto(MainActivity.this);
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (data != null) {
                    pic = new ArrayList<>();
                    pic.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                    new SendImageForDiagnosis(classification).execute();
                }
                break;
        }
    }

    class SendImageForDiagnosis extends AsyncTask<Void, Void, Void> {

        String response = null;
        String classify = null;

        public SendImageForDiagnosis(String classify) {
            this.classify = classify;
        }

        @Override
        protected void onPreExecute() {

            progressBar.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            text.setText("Uploading");
            fab.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (pic.size() > 0) {
                System.out.println(pic.get(0));
                String file_name;
                String file_dir[] = pic.get(0).split("/");
                int count = file_dir.length;
                file_name = file_dir[count - 1];
                System.out.println(file_name);

                final String uploadFilePath = pic.get(0);
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int serverResponseCode = 0;
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 4 * 1024 * 1024;
                File sourceFile = new File(uploadFilePath);

                if (!sourceFile.isFile()) {

                    Log.e("uploadFile", "Source File not exist :"
                            + uploadFilePath + "" + file_name);

                    runOnUiThread(new Runnable() {
                        public void run() {

                        }
                    });

                    return null;
                } else {
                    try {

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(sourceFile);
                        URL url = null;
                        switch (classify){
                            case "skin":
                                url = new URL("http://192.168.137.1:8000/upload/");
                                break;
                            case "diabetes":
                                url = new URL("http://192.168.137.1:8000/upload1/");
                                break;
                        }

                        // Open a HTTP  connection to  the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
                                + file_name + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of  maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math.min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        }

                        // send multipart form data necesssary after file data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        runOnUiThread(new Runnable() {
                            public void run() {
                            text.setText("Processing");
                            }

                        });

                        // Responses from the server (code and message)
                        serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn.getResponseMessage();

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        response = br.readLine();

                        Log.i("uploadFile", "HTTP Response is : "
                                + serverResponseMessage + ": " + serverResponseCode);

                        //close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (MalformedURLException ex) {

                        ex.printStackTrace();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "MalformedURLException",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (Exception e) {

                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "Got Exception : see logcat ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (response != null) {
                String arr[] = response.split(" ");
                float per = Float.parseFloat(arr[1]) * 100;
                String type = null;
                if (arr[0].equals("malignant")) {
                    type = "Cancerous(Malignant)";
                } else {
                    type = "Non-Cancerous(Benine)";
                }
                String main = "Category: " + type + " \nConfidence: " + String.format("%.2f", per) + "%";
                System.out.println(main);
                if (arr[0].equals("malignant")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Result")
                            .setMessage(main)
                            .setPositiveButton("Get Tips!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("Tips!")
                                            .setMessage(R.string.malignant)
                                            .setNegativeButton("Close", null)
                                            .show();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Result")
                            .setMessage(main)
                            .setNegativeButton("Cancel", null)
                            .show();
                }
                text.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
            fab.setVisibility(View.VISIBLE);
        }
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
}
