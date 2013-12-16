package com.kelsonprime.cardtree;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by kurt on 12/16/13.
 */
public class CardTreeActivity extends Activity {
    private String TAG = "CardTreeActivity";
    private Tree tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tree = new Tree(this);
    }

    protected Tree getTree(){
        return tree;
    }

    /**
     * Kind of dirty hack to allow swipe down to go back.
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "Back pressed");
        if(tree.isRootCurrent()){
            super.onBackPressed();
        }else{
            tree.back();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(getCurrentNode().hasMenu()){
            menu.clear();
            Integer activeMenu = getCurrentNode().getMenuRes();
            getMenuInflater().inflate(activeMenu, menu);
            return true;
        }
        return false;
    }

    private Node getCurrentNode(){
        return getTree().getCurrentNode();
    }
}
