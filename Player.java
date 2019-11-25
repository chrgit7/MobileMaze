package chrisr.nz.ac.ara.eyeball_maze_3;

import android.widget.Toast;

import java.util.Arrays;

public class Player {

    private Direction playerDirection;
    private int[] playerPos;
    private String playerOnColour;
    private String playerOnSymbol;
    private MainActivity main;

    public Player(MainActivity newMain) {
        this.playerPos = new int[] {0,0};
        this.playerDirection = Direction.up;
        this.playerOnColour = "";
        this.playerOnSymbol = "";
        this.main = newMain;
    }

    public Direction getDirection() {
        return this.playerDirection;
    }

    public void setDirection(Direction input) {
        switch (input) {
            case up:
            case down:
            case left:
            case right: this.playerDirection = input;
                break;
            default: throw new IllegalArgumentException("Can not set a a direction that is not up,down,left,right");
        }
    }

    public int[] getPosition() {
        return this.playerPos;
    }

    public void setPosition(int[] input) {
        if (input[0] > 10 || input [1] > 10) {
            throw new IllegalArgumentException("Can not set a position x,y value greater than 10");
        }else if (input[0] < 0 || input [1] < 0) {
            throw new IllegalArgumentException("Can not set a position x,y value less than 0");
        }else {
            this.playerPos = input;
        }
    }

    public String getPlayerOnColour() {
        return this.playerOnColour;
    }

    public void setPlayerOnColour(String input) {
        switch (input) {
            case "g":
            case "r":
            case "y":
            case "b": this.playerOnColour = input;
                break;
            default: throw new IllegalArgumentException("There is no colour that exists for that letter input");
        }
    }

    public String getPlayerOnSymbol() {
        return this.playerOnSymbol;
    }

    public void setPlayerOnSymbol(String input) {
        switch (input) {
            case "f":
            case "d":
            case "s":
            case "c": this.playerOnSymbol = input;
                break;
            default: throw new IllegalArgumentException("There is no symbol that exists for that letter input");
        }
    }

    public void move(Token myToken) {
        // declaring x,y positions of player and token that the player wants to move to
        int[] tokenPosition = myToken.getPosition();
        String tokenColour = myToken.getColour();
        String tokenSymbol= myToken.getSymbol();
        int playerPosX = this.playerPos[0];
        int playerPosY = this.playerPos[1];
        int tokenPosX = tokenPosition[0];
        int tokenPosY = tokenPosition[1];
        boolean playerIsBackwards = false; // ensuring the player can not move backwards



        if ( playerPosX == tokenPosX ) { // ensuring the player can only move in straight lines
            if (playerOnSymbol.equals(tokenSymbol) || playerOnColour.equals(tokenColour)) { // ensuring it is a valid move
                if (playerPosY < tokenPosY) { // i.e. if the player is moving up
                    if (this.playerDirection == Direction.down) {
                        ////myView.say("You can not move backwards!");
                        playerIsBackwards = true;
                        //throw new IllegalArgumentException("You can't move backwards!");
                        //MainActivity.say("You can't move backwards");
                        Toast.makeText(main, "You can not move backwards!", Toast.LENGTH_SHORT).show();

                    }else {
                        this.playerDirection = Direction.up;
                    }

                }else if (playerPosY > tokenPosY) {// i.e. if the player is moving down
                    if (this.playerDirection == Direction.up) {
                        //myView.say("You can not move backwards!");
                        Toast.makeText(main, "You can not move backwards!", Toast.LENGTH_SHORT).show();
                        playerIsBackwards = true;
                        //throw new IllegalArgumentException("You can't move backwards!");
                        //MainActivity.say("You can't move backwards");
                    }else {
                        this.playerDirection = Direction.down;
                    }

                }else {
                    Toast.makeText(main, "You are already on that token!", Toast.LENGTH_SHORT).show();
                    //throw new IllegalArgumentException("You can only move to a token that the player is not standing on!");
                    //MainActivity.say("You can only move to a token that the player is not standing on!");
                    // the player is in the same place as the token clicked
                    //myView.say("\t The player is already at " + Arrays.toString(this.playerPos));
                }
                //creating the new position of the player and moving them to it
                if (playerIsBackwards) {
                    // do nothing
                }else {
                    int[] newPlayerPos = {tokenPosX, tokenPosY};
                    this.playerPos = newPlayerPos;
                    this.setPlayerOnColour(tokenColour);
                    this.setPlayerOnSymbol(tokenSymbol);
                    //myView.say("\t The player successfully moved to: " + Arrays.toString(this.playerPos));
                }

            }else {
                Toast.makeText(main, "You must move to the same colour or symbol!", Toast.LENGTH_SHORT).show();
                //throw new IllegalArgumentException("You can only move to another token if the colour symbol is that same as the one you're on!");
                //MainActivity.say("You can only move to another token if the colour symbol is that same as the one you're on!");
                //myView.say("Error the token or symbol are moving to is not the same, you are on: " + getPlayerOnColour() + " " + getPlayerOnSymbol());
                //myView.say("You are trying to move to: " + tokenColour + " " + tokenSymbol);
            }
        }else if (playerPosY == tokenPosY) { // ensuring the player can only move in straight lines
            if (playerOnSymbol.equals(tokenSymbol) || playerOnColour.equals(tokenColour)) { // ensuring it is a valid move
                if (playerPosX < tokenPosX) { // i.e. if the player is moving to the right
                    if (this.playerDirection == Direction.left) {
                        Toast.makeText(main, "You can not move backwards!", Toast.LENGTH_SHORT).show();
                        //myView.say("You can not move backwards!");
                        playerIsBackwards = true;
                        //throw new IllegalArgumentException("You can't move backwards!");
                        //MainActivity.say("You can't move backwards!");
                    }else {
                        this.playerDirection = Direction.right;
                    }

                }else if (playerPosX > tokenPosX) {// i.e. if the player is moving to the left
                    if (this.playerDirection == Direction.right) {
                        Toast.makeText(main, "You can not move backwards!", Toast.LENGTH_SHORT).show();
                        //myView.say("You can not move backwards!");
                        playerIsBackwards = true;
                        //throw new IllegalArgumentException("You can't move backwards!");
                        //MainActivity.say("You can't move backwards!");
                    }else {
                        this.playerDirection = Direction.left;
                    }

                }else {
                    Toast.makeText(main, "You are already on that token!", Toast.LENGTH_SHORT).show();
                    //throw new IllegalArgumentException("You can only move to a token that the player is not standing on!");
                    //MainActivity.say("You can only move to a token that the player is not standing on!");
                    // the player is in the same place as the token clicked
                    //myView.say("\t The player is already at " + Arrays.toString(this.playerPos));
                }
                //creating the new positon of the player and moving them to it
                if (playerIsBackwards) {
                    // do nothing
                }else {
                    int[] newPlayerPos = {tokenPosX, tokenPosY};
                    this.playerPos = newPlayerPos;
                    this.setPlayerOnColour(tokenColour);
                    this.setPlayerOnSymbol(tokenSymbol);
                    //myView.say("\t The player successfully moved to: " + Arrays.toString(this.playerPos));
                }

            }else {
                Toast.makeText(main, "You must move to the same colour or symbol!", Toast.LENGTH_SHORT).show();
                //throw new IllegalArgumentException("You can only move to another token if the colour symbol is that same as the one you're on!");
                //MainActivity.say("You can only move to another token if the colour symbol is that same as the one you're on!");
                //myView.say("Error the token or symbol are moving to is not the same, you are on: " + getPlayerOnColour() + " " + getPlayerOnSymbol());
                //myView.say("You are trying to move to: " + tokenColour + " " + tokenSymbol);
            }

        }else {
            Toast.makeText(main, "You can only move in straight lines!", Toast.LENGTH_SHORT).show();

            //throw new IllegalArgumentException("You can only move in straight lines, x or y have to be the same!");
            //MainActivity.say("You can only move in straight lines, x or y have to be the same!");
            //myView.say("You can only move in straight lines!");
        }

    }
}

