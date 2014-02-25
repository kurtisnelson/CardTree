package com.kelsonprime.cardtree;

import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DynamicMenu {
    private static int itemCounter = 0; //Start at 100 to not interfere with
    private static Map<Integer, CharSequence> menuItems = new HashMap<Integer, CharSequence>();
    private ArrayList<Integer> items;
    private Integer resourceId;

    public DynamicMenu(){
        items = new ArrayList<Integer>();
    }

    public DynamicMenu(int resourceId){
        this.resourceId = resourceId;
        items = new ArrayList<Integer>();
    }

    public int add(CharSequence description) {
        int id = itemCounter;
        itemCounter++;
        menuItems.put(id, description);
        items.add(id);
        return id;
    }

    public Menu build(MenuInflater inflater, Menu menu){
        menu.clear();
        if(resourceId != null){
            inflater.inflate(resourceId, menu);
        }
        if(items != null){
            for(int id : items){
                menu.add(Menu.NONE, id, Menu.NONE, menuItems.get(id));
            }
        }
        return menu;
    }
}
