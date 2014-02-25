package com.kelsonprime.cardtree.example;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.glass.app.Card;
import com.kelsonprime.cardtree.CardTreeActivity;
import com.kelsonprime.cardtree.DynamicMenu;
import com.kelsonprime.cardtree.Level;
import com.kelsonprime.cardtree.Node;
import com.kelsonprime.cardtree.Tree;

public class MainActivity extends CardTreeActivity {
    private static final String TAG = "MainActivity";
    private int dynamicOptionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicMenu sampleMenu = new DynamicMenu(R.menu.sample);
        dynamicOptionId = sampleMenu.add("dynamic option!"); //You really shouldn't mix non-resource backed items with a resource inflated menu, as IDs could conflict

        Tree tree = getTree();
        Level root = tree.getRoot();
        Level twoChild = new Level(tree);
        Level threeChild = new Level(tree, sampleMenu); //This one has a level-wide menu. Down the road we will be able to be more efficient and not constantly reinflate things

        //Card One
        Card one = new Card(this);
        one.setText("Dead End");
        root.add(new Node(one.toView()));

        // Card Two
        Card two = new Card(this);
        two.setText("Submenu");
        root.add(new Node(two.toView(), twoChild));

        Card a = new Card(this);
        a.setText("Click Listened to");
        View viewA = a.toView();
        viewA.setOnClickListener(new ClickListener());
        twoChild.add(new Node(viewA));

        Card b = new Card(this);
        b.setText("I have a menu");
        View viewB = b.toView();
        viewB.setOnFocusChangeListener(new FocusListener());
        twoChild.add(new Node(viewB, sampleMenu));

        //Card Three
        Card three = new Card(this);
        three.setText("Level wide submenu in here");
        root.add(new Node(three.toView(), threeChild));

        Card repeat = new Card(this);
        repeat.setText("I have a menu");
        threeChild.add(new Node(repeat.toView()));
        threeChild.add(new Node(repeat.toView()));

        tree.showRoot();
        setContentView(tree);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection. Menu items typically start another
        // activity, start a service, or broadcast another intent.
        if(item.getItemId() == dynamicOptionId){
            Log.d(TAG, "dynamic option selected");
            return true;
        }else if(item.getItemId() == R.id.exit){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
