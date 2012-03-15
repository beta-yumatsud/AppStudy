package example.android.contextmenusample;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContextMenuSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmenusample);
        
        // リストビューに表示するリスト作成
        List<String> list = new ArrayList<String>();
        list.add("選択1");
        list.add("選択2");
        list.add("選択3");
        
        // リストアダプタ生成
        ListAdapter adapter = new ArrayAdapter<String>(this
        		,android.R.layout.simple_list_item_1
        		,list);
        
        // リストビューにアダプタ設定
        ListView listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
        
        // コンテキストメニューにリストビューを登録
        registerForContextMenu(listview);
    }
    
    // コンテキストメニュー生成
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	// コンテキストメニュー生成
    	menu.setHeaderTitle("コンテキストメニュー");
    	menu.add("メニュー1");
    	menu.add("メニュー2");
    	menu.add("メニュー3");
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    
    // コンテキストメニュー選択
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	// 選択されたメニューアイテムをテキストビューに表示
    	TextView textview = (TextView)findViewById(R.id.textview);
    	textview.setText("コンテキストメニューで選択：" + item.getTitle());
    	return super.onContextItemSelected(item);
    }
}