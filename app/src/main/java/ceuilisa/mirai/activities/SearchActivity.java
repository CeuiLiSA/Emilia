package ceuilisa.mirai.activities;

import android.content.Intent;

import com.mancj.materialsearchbar.MaterialSearchBar;

import ceuilisa.mirai.R;
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
                        Common.showToast(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_singer:
                    if (searchType != 1) {
                        searchType = 1;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                        Common.showToast(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_album:
                    if (searchType != 2) {
                        searchType = 2;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                        Common.showToast(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_playlist:
                    if (searchType != 3) {
                        searchType = 3;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                        Common.showToast(Constant.SEARCH_TYPE[searchType]);
                    }
                    break;
                case R.id.search_user:
                    if (searchType != 4) {
                        searchType = 4;
                        searchBar.setPlaceHolder(Constant.SEARCH_TYPE[searchType]);
                        Common.showToast(Constant.SEARCH_TYPE[searchType]);
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
        if (searchBar.getText().trim().length() == 0) {
            Common.showToast(mContext, "请输入搜索关键字");
        } else {
            Common.hideKeyboard(mActivity);
            if (searchType == 0) {//搜歌曲
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                intent.putExtra("dataType", "搜索歌曲");
                startActivity(intent);
            } else if (searchType == 1) {//搜歌手
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                intent.putExtra("dataType", "搜索歌手");
                startActivity(intent);
            } else if (searchType == 2) {//搜专辑 done
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                intent.putExtra("dataType", "搜索专辑");
                startActivity(intent);
            } else if (searchType == 3) {//搜歌单 done
                Intent intent = new Intent(mContext, PlayListActivity.class);
                intent.putExtra("dataType", "根据类型搜索歌单");
                intent.putExtra("key", searchBar.getText().trim());
                startActivity(intent);
            } else if (searchType == 4) {//搜用户
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra("key", searchBar.getText().trim());
                intent.putExtra("dataType", "搜索用户");
                startActivity(intent);
            }

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
