package com.kelsonprime.cardtree;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

/**
 * Activity that holds a treeing version of the stock GDK CardScrollView.
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
     * Dirty hack to allow swipe down to go back.
     * Make sure you call super if overridden.
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

    /**
     * Inflates the correct menu resource depending on what the active level and node are.
     * @param menu to inflate into
     * @return if a menu should be shown at all
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //#TODO We should be able to make this more efficient and not constantly reinflate.
        if(getCurrentNode().hasMenu()){
            Log.d(TAG, "Preparing Node menu");
            getCurrentNode().getMenu().build(getMenuInflater(), menu);
            return true;
        }else if(getCurrentLevel().hasMenu()){
            Log.d(TAG, "Preparing level menu");
            getCurrentLevel().getMenu().build(getMenuInflater(), menu);
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
