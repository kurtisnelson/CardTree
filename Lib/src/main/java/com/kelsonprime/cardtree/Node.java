package com.kelsonprime.cardtree;

import android.view.Menu;
import android.view.View;

public class Node {
    private static final String TAG = "Node";
    private final Level child;
    private final View view;
    private final Integer menuRes;

    public Node(View view) {
        this(view, null, null);
    }

    public Node(View view, Integer menuRes) {
        this(view, null, menuRes);
    }

    public Node(View view, Level child){
        this(view, child, null);
    }

    private Node(View view, Level child, Integer menuRes){
        this.child = child;
        this.view = view;
        this.menuRes = menuRes;
        view.setFocusable(true);
        view.clearFocus();
    }

    public boolean hasChild() {
        return child != null;
    }

    public boolean hasMenu() {
        return menuRes != null;
    }

    public Level getChild(){
        return child;
    }

    public View getView(){
        return view;
    }

    public int getMenuRes() { return menuRes; }

    void click(){
        view.performClick();
    }

    void setFocus(boolean focus){
        if(focus)
            view.requestFocus();
    }
}
