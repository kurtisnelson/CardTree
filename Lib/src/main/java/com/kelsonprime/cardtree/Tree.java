package com.kelsonprime.cardtree;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollView;

import java.util.Stack;

public class Tree extends CardScrollView {
    private static final String TAG = "Tree";
    private ScrollAdapter adapter;
    private Level root;
    private Level currentLevel;
    private Stack<Level> backStack;

    public Tree(Context context) {
        super(context);
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

    public Level getCurrentLevel() {

        return currentLevel;
    }

    public Level getRoot() {
        return root;
    }

    /**
     * Pass a MotionEvent into the gesture detector
     */
    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_CANCEL){
            if(getCurrentLevel() != getRoot()){
                back();
                Log.d(TAG, "Dispatched back");
                return true;
            }
        }
        return super.dispatchGenericFocusedEvent(event);
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
