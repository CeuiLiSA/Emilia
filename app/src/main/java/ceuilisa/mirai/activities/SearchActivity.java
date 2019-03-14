package ceuilisa.mirai.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentSearch;
import ceuilisa.mirai.fragments.FragmentSingleRecy;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;

public class SearchActivity extends WithPanelActivity implements MaterialSearchBar.OnSearchActionListener {

    private MaterialSearchBar searchBar;
    private int searchType = 0;

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return false;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_search;
    }

    @Override
    void initView() {
        super.initView();
        searchBar = findViewById(R.id.searchBar);
        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
        searchBar.inflateMenu(R.menu.search_type);
        searchBar.setHint("请输入关键字");
        searchBar.getMenu().setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search_song:
                    if (searchType != 0) {
                        searchType = 0;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_singer:
                    if (searchType != 1) {
                        searchType = 1;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_album:
                    if (searchType != 2) {
                        searchType = 2;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_playlist:
                    if (searchType != 3) {
                        searchType = 3;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_user:
                    if (searchType != 3) {
                        searchType = 3;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                default:
                    break;
            }
            return false;
        });
        searchBar.setOnSearchActionListener(this);
    }

    @Override
    void initData() {

    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        if(searchBar.getText().trim().length() == 0){
            Common.showToast(mContext, "请输入搜索关键字");
        }else {

            if(searchType == 2){
                Intent intent = new Intent(mContext, SingleFragmentActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                intent.putExtra("title", "搜索专辑 " + searchBar.getText().trim());
                startActivity(intent);
            }
            else if(searchType == 3){
                Intent intent = new Intent(mContext, PlayListActivity.class);
                intent.putExtra("dataType", "根据类型搜索歌单");
                intent.putExtra("key", searchBar.getText().trim());
                startActivity(intent);
            }else if(searchType == 4){
                Intent intent = new Intent(mContext, SearchUserActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                startActivity(intent);
            }else {
                BaseFragment fragment = FragmentSearch.newInstance(searchType, searchBar.getText().trim());
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .show(fragment)
                        .commit();
            }
            Common.hideKeyboard(mActivity);
        }
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();
                break;
            default:
                break;
        }
    }
}
