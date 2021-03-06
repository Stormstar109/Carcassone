package TileSet;

import java.util.ArrayList;

public class Tile {
    //North is 0, East is 1, South is 2, West is 3
    private ArrayList<TYPE> faces = new ArrayList<>();
    private boolean monastery, connectedCity, shield, roadEnd;
    private Tile northConnect, eastConnect, southConnect, westConnect;
    private int[] coords = {0, 0};
    private Meeple meeple = null;
    private Meeple[] placedMeeples = {null, null, null, null};


    //Empty Constructor to be used as a default
    public Tile() {
        this.faces.add(TYPE.EMPTY);
        this.faces.add(TYPE.EMPTY);
        this.faces.add(TYPE.EMPTY);
        this.faces.add(TYPE.EMPTY);
    }

    //Complete Constructor
    public Tile(TYPE north, TYPE east, TYPE south, TYPE west, boolean monastery, boolean connectedCity, boolean shield, boolean roadEnd) {
        this.faces.add(north);
        this.faces.add(east);
        this.faces.add(south);
        this.faces.add(west);
        this.monastery = monastery;
        this.connectedCity = connectedCity;
        this.shield = shield;
        this.roadEnd = roadEnd;
    }


    //Rotation of the faces
    public void rotateLeft() {
        TYPE temp = faces.get(0);
        faces.set(0, faces.get(1));
        faces.set(1, faces.get(2));
        faces.set(2, faces.get(3));
        faces.set(3, temp);

    }

    public void rotateRight() {
        TYPE temp = faces.get(3);
        faces.set(3, faces.get(2));
        faces.set(2, faces.get(1));
        faces.set(1, faces.get(0));
        faces.set(0, temp);

    }

    //Add cardinal connections
    public void addNorthConnection(Tile northConnect) {
        if (!this.hasNorthConnect() && !northConnect.hasSouthConnect()) {
            if (this.faces.get(0) == northConnect.getSouthSide()) {
                this.northConnect = northConnect;
                northConnect.reverseLinkSouth(this);
            } else {
                System.out.println("Sides do not match");
            }

        } else {
            System.out.println("Tile already connected");
        }
    }

    public void addEastConnection(Tile eastConnect) {
        if (!this.hasEastConnect() && !eastConnect.hasWestConnect()) {
            if (this.faces.get(1) == eastConnect.getWestSide()) {
                this.eastConnect = eastConnect;
                eastConnect.reverseLinkWest(this);
            } else {
                System.out.println("Sides do not match");
            }
        } else {
            System.out.println("Invalid placement");
        }
    }

    public void addSouthConnection(Tile southConnect) {
        if (!this.hasSouthConnect() && !southConnect.hasNorthConnect()) {
            if (this.faces.get(2) == southConnect.getNorthSide()) {
                this.southConnect = southConnect;
                southConnect.reverseLinkNorth(this);

            } else {
                System.out.println("Sides do not match");
            }

        } else {
            System.out.println("Invalid placement");
        }
    }

    public void addWestConnection(Tile westConnect) {
        if (!this.hasWestConnect() && !westConnect.hasEastConnect()) {
            if (this.faces.get(3) == westConnect.getEastSide()) {
                this.westConnect = westConnect;
                westConnect.reverseLinkEast(this);
            } else {
                System.out.println("Sides do not match");
            }

        } else {
            System.out.println("Invalid placement");
        }
    }

    //Reverse an already established link. Used in the cardinal connections
    private void reverseLinkNorth(Tile northConnect) {
        this.northConnect = northConnect;
    }

    private void reverseLinkEast(Tile eastConnect) {
        this.eastConnect = eastConnect;
    }

    private void reverseLinkSouth(Tile southConnect) {
        this.southConnect = southConnect;
    }

    private void reverseLinkWest(Tile westConnect) {
        this.westConnect = westConnect;
    }

    //Checks if a connection has been made
    public boolean hasNorthConnect() {
        return this.northConnect != null;
    }

    public boolean hasEastConnect() {
        return this.eastConnect != null;
    }

    public boolean hasSouthConnect() {
        return this.southConnect != null;
    }

    public boolean hasWestConnect() {
        return this.westConnect != null;
    }

    //Getters for Sides
    public TYPE getNorthSide() {
        return faces.get(0);
    }

    public TYPE getEastSide() {
        return faces.get(1);
    }

    public TYPE getSouthSide() {
        return faces.get(2);
    }

    public TYPE getWestSide() {
        return faces.get(3);
    }

    public TYPE getSide(int i) {
        if (i < 4 && i > -1) {
            return faces.get(i);
        }
        return null;
    }


    public void setCoordsX(int coords) {
        this.coords[0] = coords;
    }

    public void setCoordsY(int coords) {
        this.coords[1] = coords;
    }

    public int[] getCoords() {
        return coords;
    }

    //Convert a tile object into a string
    public String toString() {
        String outNorthConnect = (northConnect == null) ? "null" : Integer.toString(northConnect.hashCode());
        String outEastConnect = (eastConnect == null) ? "null" : Integer.toString(eastConnect.hashCode());
        String outSouthConnect = (southConnect == null) ? "null" : Integer.toString(southConnect.hashCode());
        String outWestConnect = (westConnect == null) ? "null" : Integer.toString(westConnect.hashCode());

        return "Tile{" +
                "thisTile =" + this.hashCode() +
                ", Faces: [North=" + faces.get(0) +
                ", East=" + faces.get(1) +
                ", South=" + faces.get(2) +
                ", West=" + faces.get(3) +
                "], monastery=" + monastery +
                ", northConnect=" + outNorthConnect +
                ", eastConnect=" + outEastConnect +
                ", southConnect=" + outSouthConnect +
                ", westConnect=" + outWestConnect +
                ", connectedCity=" + connectedCity +
                ", shield=" + shield +
                '}';
    }

    public String toOutputString() {

        return "Tile{" +
                " Faces: [North=" + faces.get(0) +
                ", East=" + faces.get(1) +
                ", South=" + faces.get(2) +
                ", West=" + faces.get(3) +
                "], monastery=" + monastery +
                ", connectedCity=" + connectedCity +
                ", shield=" + shield +
                '}';
    }

    //Convert a tile objects connections to strings for easier viewing
    public String toConnectString() {
        ArrayList<String> out = new ArrayList<>();

        if (northConnect != null) {
            out.add("North: " + northConnect.hashCode());
        }

        if (eastConnect != null) {
            out.add("East: " + eastConnect.hashCode());
        }

        if (southConnect != null) {
            out.add("South: " + southConnect.hashCode());
        }

        if (westConnect != null) {
            out.add("West: " + westConnect.hashCode());
        }

        return out.toString();
    }

    public void setMeeple(Meeple meeple, int direction) {
        if (direction >= 0 && direction <= 3) {
            this.placedMeeples[direction] = meeple;
        }
    }

    public void freeMeeple(Meeple meeple)
    {
        for (int i = 0; i <placedMeeples.length;i++)
        {
            if(meeple.equals(placedMeeples[i]))
            {
                placedMeeples[i] = null;
            }
        }
    }

    public int getMeepleDirection(Meeple meeple)
    {
        for (int i = 0; i <placedMeeples.length;i++)
        {
            if(meeple.equals(placedMeeples[i]))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean hasMonastery() {
        return this.monastery;
    }

    public boolean isRoadEnd() {
        return this.roadEnd;
    }

    public boolean hasConnectedCity(){
        return this.connectedCity;
    }

    public Tile getNorthConnect() {
        return northConnect;
    }

    public Tile getEastConnect() {
        return eastConnect;
    }

    public Tile getSouthConnect() {
        return southConnect;
    }

    public Tile getWestConnect() {
        return westConnect;
    }


    public Tile getConnect(int i) {
        return switch (i) {
            case 0 -> northConnect;
            case 1 -> eastConnect;
            case 2 -> southConnect;
            case 3 -> westConnect;
            default -> null;
        };
    }
    public boolean hasShield(){return shield;}

    public boolean hasNoMeepleAt(int dir)
    {
        if (dir < 4 && dir > -1) {
            return placedMeeples[dir]==null;
        }
        return false;
    }
}


