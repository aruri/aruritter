package com.example.sample_tw;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
// ArrayListとListViewをくっつけるためにある

// ArrayAdapterは渡されたデータ(items)をTextViewのtextにセットして、Activityに返し、Activityが渡されたビューを表示する、という処理を内部でしています。
//その処理を行うのはArrayAdapter#getViewメソッドです。なので、この処理をオーバーライドしてあげれば、自分の好きなビューを表示できるようになります。
//というわけで、ArrayAdapterを継承して、Twitter風にデータとビューをマッピングさせるTwitterAdapterクラスを作成します。

public class TwitterAdapter extends ArrayAdapter {

	private ArrayList items;
	private LayoutInflater inflater;

	public TwitterAdapter(Context context, int textViewResourceId,
			ArrayList items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ビューを受け取る
		View view = convertView;
		if (view == null) {
			// 受け取ったビューがnullなら新しくビューを生成
			view = inflater.inflate(R.layout.twitter_row, null);
			// 背景画像をセットする
			view.setBackgroundResource(R.drawable.back);
		}
		// 表示すべきデータの取得
		TwitterStatus item = (TwitterStatus) items.get(position);
		if (item != null) {
			TextView screenName = (TextView) view.findViewById(R.id.name);
			screenName.setTypeface(Typeface.DEFAULT_BOLD);

			// スクリーンネームをビューにセット
			TextView text = (TextView) view.findViewById(R.id.tweet);
			if (screenName != null) {
				screenName.setText(item.getScreenName());
			}

			// テキストをビューにセット
			if (text != null) {
				text.setText(item.getText());
			}
		}
		return view;
	}
}
