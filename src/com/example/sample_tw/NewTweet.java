package com.example.sample_tw;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewTweet extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newtweet);

		// newtweet.xmlのbuttonの遷移先を指定！！
		Button btnMove = (Button) findViewById(R.id.tweet_after);
		btnMove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// future task を利用してスレッドをつくる
				ExecutorService exec = Executors.newSingleThreadExecutor();
				exec.submit(new Callable<Status>() {

					@Override
					public Status call() throws Exception {

						Status status = null; // 初期化
						Twitter twitter = TwitterFactory.getSingleton();
						EditText NewTweetEdit = (EditText) findViewById(R.id.newtweet); // tweet内容をとってくる
						NewTweetEdit.getText().toString(); // String型にする
						String latestStatus = NewTweetEdit.getText().toString();

						status = twitter.updateStatus(latestStatus); // statusに代入

						System.out.println("ツイート「" + status.getText()
								+ "」を投稿しました");
						return status;

					}

				});

				// もとのfragment_mainにもどる
				Intent intent = new Intent(NewTweet.this, MainActivity.class);
				startActivity(intent);

			}
		});
	}

}
