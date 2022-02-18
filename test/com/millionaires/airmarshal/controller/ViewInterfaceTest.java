package com.millionaires.airmarshal.controller;

import org.junit.jupiter.api.Test;

class ViewInterfaceTest {

    @Test
    public void testToggleMusic() throws Exception {
        ViewInterface api = ViewInterface.getInstance();
        long start = System.currentTimeMillis();

        api.toggleMusic();
        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms to load and start audio");

        Thread.sleep(2000);

        System.out.println("Turning off now...");
        api.toggleMusic();
        System.out.println("Did turn off. Will start again in 2s");

        Thread.sleep(2000);

        start = System.currentTimeMillis();
        api.toggleMusic();
        System.out.println("Second toggle took " + (System.currentTimeMillis() - start) + "ms to start the second time");

        Thread.sleep(2000);

        api.toggleMusic();
        System.out.println("Turned off again");
    }

    @Test
    public void testCapLetterThing(){
        String result = ViewInterface.getInstance().getCompartmentName();
        System.out.println(result);
    }
}