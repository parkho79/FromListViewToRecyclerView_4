package com.parkho.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhMainActivity extends AppCompatActivity {

    // List item
    private List<PhCountryItem> mItemList;

    // ListView adapter
    private PhCountryArrayAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu a_menu, View a_view, ContextMenuInfo a_menuInfo) {
        getMenuInflater().inflate(R.menu.main_list_menu, a_menu);
        super.onCreateContextMenu(a_menu, a_view, a_menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem a_item) {
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) a_item.getMenuInfo();
        final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(info.position);

        switch (a_item.getItemId()) {
            case R.id.action_insert:
                Toast.makeText(this, item.getCountry() + " " + getString(R.string.insert), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_delete:
                Toast.makeText(this, item.getCountry() + " " + getString(R.string.delete), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_modify:
                Toast.makeText(this, item.getCountry() + " " + getString(R.string.modify), Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_info:
                Toast.makeText(this, item.getCountry() + " " + getString(R.string.info), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return true;
    };

    private void bindList() {
        mItemList = new ArrayList<>();
        mItemList.add(new PhCountryItem(R.drawable.ico_southkorea, "Korea"));
        mItemList.add(new PhCountryItem(R.drawable.ico_china, "China"));
        mItemList.add(new PhCountryItem(R.drawable.ico_japan, "Japan"));
        mItemList.add(new PhCountryItem(R.drawable.ico_unitedstates, "America"));
        mItemList.add(new PhCountryItem(R.drawable.ico_newzealand, "NewZealand"));

        mCountryAdapter = new PhCountryArrayAdapter(this, mItemList);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mCountryAdapter);

        // Context menu 등록
        registerForContextMenu(listView);

        // List item click event 처리
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                Toast.makeText(PhMainActivity.this, item.getCountry() + " Click event", Toast.LENGTH_SHORT).show();
            }
        });
    }
}