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
        //#TODO We should be able to make this more efficient and not constantly reinflate.
        if(getCurrentNode().hasMenu()){
            Log.d(TAG, "Preparing Node menu");
            menu.clear();
            Integer activeMenu = getCurrentNode().getMenuRes();
            getMenuInflater().inflate(activeMenu, menu);
            return true;
        }else if(getCurrentLevel().hasMenu()){
            Log.d(TAG, "Preparing level menu");
            menu.clear();
            Integer activeMenu = getCurrentLevel().getMenuRes();
            getMenuInflater().inflate(activeMenu, menu);
            return true;
        }
        return false;
    }

    private Level getCurrentLevel(){
        return getTree().getCurrentLevel();
    }
    private Node getCurrentNode(){
        return getTree().getCurrentNode();
    }
}
