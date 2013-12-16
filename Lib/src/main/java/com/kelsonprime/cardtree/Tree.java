package com.kelsonprime.cardtree;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollView;

import java.util.Stack;

public class Tree extends CardScrollView {
    private static final String TAG = "Tree";
    private ScrollAdapter adapter;
    private Level root;
    private Level currentLevel;
    private Stack<Level> backStack;
    private Activity activity;

    public Tree(Activity activity) {
        super(activity);
        this.activity = activity;
        this.backStack = new Stack<Level>();
        this.root = new Level(this);
        this.currentLevel = root;
        this.adapter = new ScrollAdapter(this);

        this.setAdapter(adapter);
        this.setOnItemClickListener(new TapListener(this));
    }

    public void showRoot() {
        enterLevel(this.root);
        activate();
        Log.d(TAG, "Activated with " + adapter.getCount() + " cards");
    }

    public boolean isRootCurrent(){
        return currentLevel.equals(root);
    }

    public Level getCurrentLevel() {

        return currentLevel;
    }

    public Level getRoot() {
        return root;
    }

    public void enterLevel(Level level) {
        if (level == this.root) {
            backStack.clear();
        } else {
            backStack.push(this.currentLevel);
        }
        setLevel(level);
    }

    public void back() {
        Log.d(TAG, "Recieved back");
        if (backStack.isEmpty()) {
            Log.e(TAG, "Already at root");
        } else {
            setLevel(backStack.pop());
        }
    }

    private void setLevel(Level level) {
        this.currentLevel = level;
        adapter.setCurrentLevel(level);
        setSelection(0);
        this.updateViews(true);
    }

    // DON'T LOOK UNDER THIS LINE, I NEED TO MAKE THIS MESSAGE PASSING STUFF GO AWAY

    /**
     * Really gross way of doing this, I don't want to have to track the activity just for this.
     */
    void showMenu() {
        activity.openOptionsMenu();
    }

    Node getCurrentNode() { return currentLevel.get(getSelectedItemPosition());}

    @Override
    protected void onUnsettled(int position) {
        super.onUnsettled(position);
        getCurrentLevel().focus(position, false);
    }

    @Override
    protected void onSettled(int position) {
        super.onSettled(position);
        getCurrentLevel().focus(position, true);
    }
}

class TapListener implements AdapterView.OnItemClickListener {
    private Tree tree;

    TapListener(Tree tree) {
        this.tree = tree;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tree.getCurrentLevel().click(position);
    }
}
