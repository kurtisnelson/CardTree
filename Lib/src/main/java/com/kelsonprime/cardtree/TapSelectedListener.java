package com.kelsonprime.cardtree;

import android.view.View;
import android.widget.AdapterView;

public class TapSelectedListener implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    protected Tree tree;

    public TapSelectedListener(Tree tree) {
        this.tree = tree;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tree.getCurrentLevel().click(position);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}