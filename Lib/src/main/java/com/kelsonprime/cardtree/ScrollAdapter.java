package com.kelsonprime.cardtree;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollAdapter;

/**
 * Holds and bookkeeps the active Level
 * @see com.kelsonprime.cardtree.Level
 */
class ScrollAdapter extends CardScrollAdapter {
    private final Tree parent;
    private Level currentLevel;

    ScrollAdapter(Tree parent){
        this.parent = parent;
        this.currentLevel = parent.getCurrentLevel();
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return currentLevel.size();
    }

    @Override
    public Object getItem(int i) {
        return currentLevel.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            return setItemOnCard(this, currentLevel.get(i).getView());
        } else {
            return setItemOnCard(this, view);
        }
    }

    @Override
    public int findIdPosition(Object id) {
        for(int i = 0; i < currentLevel.size(); i++){
            if(currentLevel.get(i).getView().getId() == (Integer) id){
                return i;
            }
        }
        return AdapterView.INVALID_POSITION;
    }

    @Override
    public int findItemPosition(Object item) {
        for (int i = 0; i < currentLevel.size(); i++) {
            if (currentLevel.get(i).equals(item)) {
                return i;
            }
        }
        return AdapterView.INVALID_POSITION;
    }
}
