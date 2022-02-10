package com.millionaires.airmarshal.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    // Singleton boilerplate -------------------
    private static final Player instance = new Player();

    /**
     * Private constructor to prevent instantiation
     */
    private Player(){}

    /**
     * @return the singleton Player instance
     */
    public Player getInstance(){
        return instance;
    }

    // Instance methods and variables ----------
    private List<InteractableData> inventory = new ArrayList<>();
    private String name = "noname";

    /**
     * Adds an {@code InteractableData} object (item) to the player's inventory.
     * The caller must manually remove the item from the origin collection.
     * @param item - the item to add to the player's inventory
     */
    public void addItemToInventory(InteractableData item){
        inventory.add(item);
    }

    /**
     * @return An unmodifiable copy of the player's inventory
     */
    public List<InteractableData> getInventory(){
        return Collections.unmodifiableList(inventory);
    }

    /**
     * @param name - the new name of the player instance
     */
    public void setName(String name){
        if(name == null) {
            System.out.println("The entered name was null. Setting to a default value");
            name = "Leo";
        }

        this.name = name;
    }

    /**
     * @return the name of the player
     */
    public String getName(){
        return name;
    }
}
