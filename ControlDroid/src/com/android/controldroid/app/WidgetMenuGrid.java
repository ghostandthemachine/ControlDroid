package com.android.controldroid.app;

import com.droidgraph.util.Shared;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class WidgetMenuGrid extends Fragment {

	private String TAG = "WidgetMenuGrid";
	private boolean DEBUG = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (DEBUG) {
			Shared.p(TAG, "onCreateView(), start");
		}
		super.onCreate(savedInstanceState);

		GridView gridview = new GridView(this.getActivity());
		gridview.setAdapter(new ImageAdapter(this.getActivity()));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(WidgetMenuGrid.this.getActivity(),
						"" + position, Toast.LENGTH_SHORT).show();
			}
		});
		if (DEBUG) {
			Shared.p(TAG, "onCreateView(), end");
		}
		return gridview;
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}

		// references to our images
		private Integer[] mThumbIds = { R.drawable.sample_2,
				R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
				R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0,
				R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
				R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
				R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
				R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
				R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7 };
	}
}