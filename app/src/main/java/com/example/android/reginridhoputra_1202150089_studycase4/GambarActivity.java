package com.example.android.reginridhoputra_1202150089_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class GambarActivity extends AppCompatActivity {
    //Deklarasi private variable
    private ImageView downloadedImage;
    private ProgressDialog mProgressDialog;
    private EditText linkUrl;
    private Button imageDownloaderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);

        imageDownloaderButton = (Button) findViewById(R.id.button_startAsyncTask);
        downloadedImage = (ImageView) findViewById(R.id.ImageView);
        linkUrl = (EditText)findViewById(R.id.urlText);

        //Eksekusi ketika button diklik
        imageDownloaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String downloadUrl = linkUrl.getText().toString();
                if(downloadUrl.isEmpty()){
                    //Menampilkan toast ketika button diklik namun edit text url kosong
                    Toast.makeText(GambarActivity.this,"Masukkan URL gambar terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else {
                    // Execute DownloadImage AsyncTask
                    new ImageDownloader().execute(downloadUrl);
                }
            }
        });
    }
    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        //Dipanggil di thread UI sebelum tugas dijalankan
        //Langkah ini biasanya digunakan untuk mensetup tugas, misalnya dengan menampilkan progress bar pada user interface
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(GambarActivity.this);
            // Set judul progress dialog
            mProgressDialog.setTitle("Search Image");
            // Set pesan pada progress dialog
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progress dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image dari URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set bitmap ke dalam ImageView
            downloadedImage.setImageBitmap(result);
            // Tutup progress dialog
            mProgressDialog.dismiss();
        }
    }
}