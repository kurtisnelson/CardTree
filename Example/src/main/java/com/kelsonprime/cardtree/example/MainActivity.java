package com.kelsonprime.cardtree.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.glass.app.Card;
import com.kelsonprime.cardtree.Level;
import com.kelsonprime.cardtree.Node;
import com.kelsonprime.cardtree.Tree;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Tree tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tree = new Tree(this);
        Level root = tree.getRoot();
        Level second = new Level(tree);

        Card one = new Card(this);
        one.setText("One");
        root.add(new Node(one.toView()));

        Card two = new Card(this);
        two.setText("Two");
        root.add(new Node(two.toView(), second));

        Card a = new Card(this);
        a.setText("A");
        View viewA = a.toView();
        viewA.setOnClickListener(new ClickListener());
        second.add(new Node(viewA));

        Card b = new Card(this);
        b.setText("B");
        View viewB = b.toView();
        viewB.setOnFocusChangeListener(new FocusListener());
        second.add(new Node(viewB));


        tree.showRoot();
        setContentView(tree);
    }

    /**
     * Kind of dirty hack to allow swipe down to go back.
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "Back pressed");
        if(tree.isRootCurrent()){
            super.onBackPressed();
        }else{
            tree.back();
        }
    }

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Got click: "+ view.toString());
        }
    }

    class FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            Log.d(TAG, "Focus changed to " + b + " on "+view.toString());
        }
    }
}
