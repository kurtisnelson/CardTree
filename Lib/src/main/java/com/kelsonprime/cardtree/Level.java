package com.kelsonprime.cardtree;

import android.util.Log;

import java.util.ArrayList;

/**
 * Represents a level containing {@link com.kelsonprime.cardtree.Node}s, either at the root or nested under another level.
 */
public class Level extends ArrayList<Node> {
    private static final String TAG = "Level";
    private Tree owner;
    private int startPosition;
    private DynamicMenu menu;

    /**
     * Create a new empty level
     * @param owner Receives events from this level
     */
    public Level(Tree owner){
        this(owner, null);
    }

    /**
     * Create a level that has a menu resource applied by default to all nodes.
     * @param owner Receives events from this level
     * @param menu menu to inflate
     */
    public Level(Tree owner, DynamicMenu menu){
        super();
        this.startPosition = 0;
        this.owner = owner;
        this.menu = menu;
    }

    /**
     * @return if this level has a default menu applied
     */
    public boolean hasMenu(){
        return menu != null;
    }

    /**
     * Recieves click events from {@link com.kelsonprime.cardtree.CardTreeActivity}
     * @param position
     */
    void click(int position){
        click(this.get(position));
    }

    private void click(Node node){
        if(node.hasChild()){
            owner.enterLevel(node.getChild());
        }else if(node.hasMenu() || hasMenu()){
            Log.d(TAG, "Sending showMenu");
            owner.showMenu();
        }else{
            node.click();
        }
    }

    /**
     * Recieves focus events from the {@link com.kelsonprime.cardtree.Tree}
     * @param position
     * @param hasFocus
     */
    void focus(int position, boolean hasFocus){
        focus(this.get(position), hasFocus);
    }

    private void focus(Node node, boolean hasFocus) {
        node.setFocus(hasFocus);
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public void setStartPosition(int startPosition){
        this.startPosition = startPosition;
    }

    public DynamicMenu getMenu() {
        return menu;
    }
}
