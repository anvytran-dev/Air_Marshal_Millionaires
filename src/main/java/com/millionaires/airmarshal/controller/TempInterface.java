package com.millionaires.airmarshal.controller;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import java.util.Map;

public class TempInterface {

    private Map<String, CompartmentData> compartmentData = Map.of();

    // Only copy the methods, not the variables (unless error occurs)
    public void startGame(){


    }

    public void loadGame(){

    }

    public void quitGame(){
        System.exit(0);
    }

    public String getInstructions(){
        return "These are instructions";
    }

    public CompartmentData goDirection(CompartmentData currentCompartment, String direction){
        String nextCompartmentName = currentCompartment.getNextCompartmentName(direction);
        return compartmentData.get(nextCompartmentName);
    }

    public String talkTo(InteractableData character){
        return "Hello. We have not implemented talking to characters yet.";
    }

    public void takeItem(InteractableData item){

    }

    public void toggleMusic(){

    }
}
