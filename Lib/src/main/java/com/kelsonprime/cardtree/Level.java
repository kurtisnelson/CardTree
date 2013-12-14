package com.kelsonprime.cardtree;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Level extends ArrayList<Node> {
    private Tree parent;
    public Level(Tree parent){
        super();
        this.parent = parent;
    }

    void click(int pos){
        click(this.get(pos));
    }

    private void click(Node v){
        if(v.hasChild()){
            parent.enterLevel(v.getChild());
        }
        v.click();
    }

    void focus(int pos, boolean hasFocus){
        focus(this.get(pos), hasFocus);
    }

    private void focus(Node node, boolean hasFocus) {
        node.setFocus(hasFocus);
    }
}
