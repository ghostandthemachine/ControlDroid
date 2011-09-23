//apackage com.android.controldroid.app;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import android.app.Fragment;
//import android.content.Context;
//import android.inputmethodservice.Keyboard.Row;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.TextView;
//
//public class TestGridFrag extends Fragment {
//	private static final String TAG = "BulkScanChosenAttributes";
//	private ArrayList<Row> items;
//	private GridAttributeAdapter adapter;
//
//	public TestGridFrag() {
//		items = null;
//		adapter = null;
//	}
//
//	public TestGridFrag(ArrayList<Row> items) {
//		this.items = items;
//		adapter = null;
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstance) {
//		super.onActivityCreated(savedInstance);
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.bulk_scan_chosen, container, false);
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstance) {
//		super.onCreate(savedInstance);
//
//		adapter = new GridAttributeAdapter(getActivity()
//				.getApplicationContext(), R.layout.bulk_scan_chosen, items);
//	}
//
//	@Override
//	public void onStart() {
//		super.onStart();
//
//		GridView v = (GridView) getView();
//
//		v.setAdapter(adapter);
//	}
//
//	public class GridAttributeAdapter extends ArrayAdapter<Row> {
//		private int count;
//		private LayoutInflater inflater;
//		private Context context;
//		private ArrayList<Row> items;
//
//		public GridAttributeAdapter(Context context, int resource,
//				ArrayList<Row> items) {
//			super(context, resource);
//			int count = 0;
//			this.context = context;
//			this.items = items;
//			inflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		}
//
//		@Override
//		public int getCount() {
//			return count;
//		}
//
//		@Override
//		public Row getItem(int position) {
//			return items.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		public void setItems(ArrayList<Row> items) {
//			this.items = items;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			Log.d(TAG, "calling getView in the grid adapter");
//			View v = convertView;
//			// try to inflate the view
//			try {
//				v = inflater.inflate(R.layout.grid_item_layout, null);
//			} catch (NullPointerException npe) {
//				npe.printStackTrace();
//				Log.e(TAG, "view is null");
//			}
//
//			Row row = items.get(position);
//
//			try {
//				TextView name = (TextView) v.findViewById(R.id.grid_att_name);
//				TextView value = (TextView) v.findViewById(R.id.grid_att_value);
//
//				if (ListMetaData.withDates
//						.contains(ListMetaData.reverseScreenNames.get(row
//								.getRowName()))) {
//					try {
//						Date date = new Date(new Long(row.getRowValue()));
//						value.setText(date.getMonth() + "/" + date.getDate()
//								+ "/" + date.getYear());
//					} catch (NumberFormatException nfe) {
//						value.setText(row.getRowValue());
//					}
//				} else {
//					value.setText(row.getRowValue());
//				}
//
//				name.setText(row.getRowName());
//			} catch (NullPointerException npe) {
//				npe.printStackTrace();
//				Log.e(TAG, "something went wrong in TextView assignment");
//			}
//
//			return v;
//		}
//
//	}
//}