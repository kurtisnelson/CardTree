package com.kelsonprime.cardtree;

import android.view.View;

public class Node {
    private final Level child;
    private final View view;

    public Node(View view) {
        this.child = null;
        this.view = view;
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
}
