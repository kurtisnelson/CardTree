package com.kelsonprime.cardtree;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollView;

import java.util.Stack;

/**
 * Manages the levels and dispatches events.
 */
public class Tree extends CardScrollView {
    private static final String TAG = "Tree";
    private ScrollAdapter adapter;
    private Level root;
    private Level currentLevel;
    private Stack<Level> backStack;
    private Stack<Integer> backPositionStack;
    private Activity activity;
    private AudioManager systemAudio;

    /**
     * Create a tree with an empty root level.
     * @param activity
     */
    public Tree(Activity activity) {
        super(activity);
        this.activity = activity;
        this.systemAudio = (android.media.AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        this.backStack = new Stack<Level>();
        this.backPositionStack = new Stack<Integer>();
        this.root = new Level(this);
        this.currentLevel = root;
        this.adapter = new ScrollAdapter(this);

        this.setAdapter(adapter);
        this.setOnItemClickListener(new TapListener(this));
    }

    /**
     * Makes the root level visible on the view
     */
    public void showRoot() {
        enterLevel(this.root);
        activate();
        Log.d(TAG, "Activated with " + adapter.getCount() + " cards");
    }

    /**
     * @return if root level is the visible level
     */
    public boolean isRootCurrent() {
        return currentLevel.equals(root);
    }

    Level getCurrentLevel() {

        return currentLevel;
    }

    /**
     * @return root level that nodes and child levels should branch from
     */
    public Level getRoot() {
        return root;
    }

    /**
     * Sets a level as the currently visible one and manages the back stack
     * @param level
     */
    public void enterLevel(Level level) {
        if (level.isEmpty()) {
            systemAudio.playSoundEffect(Sounds.DISALLOWED);
            return;
        }
        if (level == this.root) {
            backStack.clear();
        } else {
            backStack.push(this.currentLevel);
            backPositionStack.push(getSelectedItemPosition());
        }
        setLevel(level);
    }

    /**
     * Pops off of the backstack and renders
     */
    public void back() {
        Log.d(TAG, "Recieved back");
        if (backStack.isEmpty()) {
            Log.e(TAG, "Already at root");
        } else {
            setLevel(backStack.pop(), backPositionStack.pop());
        }
    }

    private void setLevel(Level level){
        setLevel(level, level.getStartPosition());
    }
    private void setLevel(Level level, Integer position) {
        this.currentLevel = level;
        adapter.setCurrentLevel(level);
        setSelection(position);
        this.updateViews(true);
    }

    // DON'T LOOK UNDER THIS LINE, I NEED TO MAKE THIS MESSAGE PASSING STUFF GO AWAY

    /**
     * Really gross way of doing this, I don't want to have to track the activity just for this.
     */
    void showMenu() {
        activity.openOptionsMenu();
    }

    Node getCurrentNode() {
        return currentLevel.get(getSelectedItemPosition());
    }

    /**
     * Event when a position is no longer at rest.
     * @param position
     */
    @Override
    protected void onUnsettled(int position) {
        super.onUnsettled(position);
        getCurrentLevel().focus(position, false);
    }

    /**
     * Event when a view is rested in view
     * @param position
     */
    @Override
    protected void onSettled(int position) {
        super.onSettled(position);
        getCurrentLevel().focus(position, true);
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
}
