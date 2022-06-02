package com.company;

import TileSet.Player;
import TileSet.TYPE;
import TileSet.Tile;
import TileSet.TileController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void integrationTestCompletedRoad() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});

        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        assertEquals("ROAD COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);

    }

    @Test
    void integrationTestIncompleteRoad() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});

        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        assertEquals("ROAD INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);

    }

    @Test
    void integrationTestCompletedMonastery() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -1});


        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        assertEquals("MONASTERY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestIncompleteMonastery() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});


        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        assertEquals("MONASTERY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED CITY
    @Test
    void integrationTestSimpleCompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestSimpleIncompleteConnectedCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestSimpleCompleteUnconnectedCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestComplexCompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }


    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestSimpleIncompleteConnectedCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestSimpleCompleteUnconnectedCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexCompleteCityConnectedStartConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}