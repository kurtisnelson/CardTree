package com.kelsonprime.cardtree;

import android.view.View;

import java.util.ArrayList;

public class Level extends ArrayList<Node> {
    private Tree parent;
    public Level(Tree parent){
        super();
        this.parent = parent;
    }

    void click(View v, int pos, long id){
        click(this.get(pos));
    }

    void click(Node v){
        if(v.hasChild()){
            parent.setCurrentLevel(v.getChild());
        }
        v.click();
    }
}
