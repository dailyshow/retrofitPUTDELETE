package com.example.retrofitpractiec6;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String BASEURL = "http://jsonplaceholder.typicode.com/";
    private TextView textViewResult;

    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

//        updatePost();
        deletePost();
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderAPI.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.message());
                    return;
                }
                String content = "";
                content += "code: " + response.code()+"\n";
                content += "정상적으로 삭제되었습니다.";

                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void updatePost(){
        Post post = new Post(10, null, "새로운 내용");

        Call<Post> call = jsonPlaceHolderAPI.putPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("code: " + response.message());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "Id : " + postResponse.getId() + "\n";
                content += "User Id : " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
