package com.kelsonprime.cardtree;

import android.util.Log;
import android.view.View;

public class Node {
    private static final String TAG = "Node";
    private final Level child;
    private final View view;

    public Node(View view) {
        this.child = null;
        this.view = view;
        view.setFocusable(true);
        view.clearFocus();
    }

    public Node(View view, Level child){
        this.child = child;
        this.view = view;
    }

    public boolean hasChild() {
        return child != null;
    }

    public Level getChild(){
        return child;
    }

    public View getView(){
        return view;
    }

    void click(){
        view.performClick();
    }

    void setFocus(boolean focus){
        if(focus)
            view.requestFocus();
    }
}
