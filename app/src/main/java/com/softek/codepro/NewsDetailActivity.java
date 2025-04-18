package com.softek.codepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    String title,desc,content,image,url;
    private TextView titleTV,subDescTV,contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        image=getIntent().getStringExtra("image");
        content=getIntent().getStringExtra("content");

        url=getIntent().getStringExtra("url");
        titleTV=findViewById(R.id.idTVTitle);
        subDescTV=findViewById(R.id.idTVSubDesc);
        contentTV=findViewById(R.id.idTVContent);
        newsIV=findViewById(R.id.idIVNews);
        readNewsBtn=findViewById(R.id.idBtnReadNews);
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(image).into(newsIV);
      readNewsBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              startActivity(i);
          }
      });





    }
}