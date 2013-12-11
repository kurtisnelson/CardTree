package com.kelsonprime.cardtree.example;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.kelsonprime.cardtree.Level;
import com.kelsonprime.cardtree.Node;
import com.kelsonprime.cardtree.Tree;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CardTreeFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class CardTreeFragment extends Fragment {

        public CardTreeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            Tree tree = new Tree(this.getActivity());
            Level root = tree.getRoot();
            Level second = new Level(tree);

            Card one = new Card(this.getActivity());
            one.setText("One");
            root.add(new Node(one.toView()));

            Card two = new Card(this.getActivity());
            two.setText("Two");
            root.add(new Node(two.toView(), second));

            Card a = new Card(this.getActivity());
            a.setText("A");
            second.add(new Node(a.toView()));
            return tree;
        }
    }

}
