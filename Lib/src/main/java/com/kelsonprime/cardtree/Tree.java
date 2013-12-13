package com.kelsonprime.cardtree;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollView;

public class Tree extends CardScrollView{
    private ScrollAdapter adapter;
    private Level root;
    private Level currentLevel;

    public Tree(Context context) {
        super(context);
        this.root = new Level(this);
        this.currentLevel = root;
        adapter = new ScrollAdapter(this);
        this.setAdapter(adapter);
        this.setOnItemClickListener(new TapListener(this));
        this.activate();
    }

    public Level getCurrentLevel(){

        return currentLevel;
    }

    public Level getRoot() { return root; }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
        adapter.notifyDataSetChanged();
        this.updateViews(true);
    }

}

class TapListener implements AdapterView.OnItemClickListener{
    private Tree tree;

    TapListener(Tree tree){
        this.tree = tree;
    }

    public void onItemClick (AdapterView<?> parent, View view, int position, long id){
        tree.getCurrentLevel().click(view, position, id);
    }
}
