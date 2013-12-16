package com.kelsonprime.cardtree;

import android.util.Log;

import java.util.ArrayList;

public class Level extends ArrayList<Node> {
    private static final String TAG = "Level";
    private Tree parent;
    private Integer menuRes;

    public Level(Tree parent){
        this(parent, null);
    }

    public Level(Tree parent, Integer menuRes){
        super();
        this.parent = parent;
        this.menuRes = menuRes;
    }

    public boolean hasMenu(){
        return menuRes != null;
    }
    public Integer getMenuRes() {
        return menuRes;
    }

    void click(int pos){
        click(this.get(pos));
    }

    private void click(Node node){
        if(node.hasChild()){
            parent.enterLevel(node.getChild());
        }else if(node.hasMenu() || hasMenu()){
            Log.d(TAG, "Sending showMenu");
            parent.showMenu();
        }else{
            node.click();
        }
    }

    void focus(int pos, boolean hasFocus){
        focus(this.get(pos), hasFocus);
    }

    private void focus(Node node, boolean hasFocus) {
        node.setFocus(hasFocus);
    }
}
