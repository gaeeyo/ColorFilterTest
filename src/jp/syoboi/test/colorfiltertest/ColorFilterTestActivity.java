package jp.syoboi.test.colorfiltertest;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ColorFilterTestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        for (PorterDuff.Mode mode: PorterDuff.Mode.class.getEnumConstants()) {
        	items.add(new ListItem(0x77ff0000, mode));
        	items.add(new ListItem(0xffff0000, mode));
        	items.add(new ListItem(0x77ffffff, mode));
        	items.add(new ListItem(0xffffffff, mode));
        	items.add(new ListItem(0x77000000, mode));
        	items.add(new ListItem(0xff000000, mode));
        }

        ListItemAdapter adpater = new ListItemAdapter(this, items);
        
        GridView grid = (GridView)findViewById(R.id.grid);
        grid.setAdapter(adpater);
    }
    
    public static class ListItem {
    	public int color;
    	public PorterDuff.Mode mode;
    	public ListItem(int color, PorterDuff.Mode mode) {
    		this.color = color;
    		this.mode = mode;
    	}
    }
    
    public static class ListItemAdapter extends ArrayAdapter<ListItem> {
    	
    	private final LayoutInflater mInflater;
    	
    	public ListItemAdapter(Context context, List<ListItem> items) {
    		super(context, 0, items);
    		mInflater = LayoutInflater.from(context);
    	}
    	
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (convertView == null) {
				v = mInflater.inflate(R.layout.list_item, null);
			}
			
			ListItem i = getItem(position);
			
	        ImageView image = (ImageView)v.findViewById(R.id.image);
	        image.setColorFilter(i.color, i.mode);
	        
	        TextView text = (TextView)v.findViewById(R.id.text);
	        text.setText(String.format("%08X\n%s", i.color, i.mode.toString()));
	        
			return v;
		}
    	
    }
}