package com.kelsonprime.cardtree;

import android.view.View;

/**
 * Tree node that holds the view to render, optional options menu, and a possible child level. Should probably not be extended, use a custom {@link android.view.View} instead.
 */
public class Node {
    private static final String TAG = "Node";
    private Level child;
    private final View view;
    private final Integer menuRes;

    /**
     * Create view for a level
     * @param view
     */
    public Node(View view) {
        this(view, null, null);
    }

    /**
     * Create a view with a resource that will be inflated via #{@link android.view.MenuInflater} on tap.
     * @param view
     * @param menuRes R class Id of menu. It will override menuRes on the parent #{@link com.kelsonprime.cardtree.Level} for this node only.
     */
    public Node(View view, Integer menuRes) {
        this(view, null, menuRes);
    }

    /**
     * Create a view with a child #{@link com.kelsonprime.cardtree.Level} that will be moved to on tap.
     * @param view
     * @param child
     */
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

    /**
     *
     * @return if this has a child level
     */
    public boolean hasChild() {
        return child != null;
    }
    
    /**
     * @param child
     */
    public void setChild(Level child){
        this.child = child;
    }

    /**
     *
     * @return if this has an options menu
     */
    public boolean hasMenu() {
        return menuRes != null;
    }

    /**
     * @return child level
     */
    public Level getChild(){
        return child;
    }

    /**
     * @return view that will be inserted
     */
    public View getView(){
        return view;
    }

    /**
     * @return menu that will be inflated
     */
    public int getMenuRes() { return menuRes; }

    void click(){
        view.performClick();
    }

    void setFocus(boolean focus){
        if(focus)
            view.requestFocus();
    }
}
