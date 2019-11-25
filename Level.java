package chrisr.nz.ac.ara.eyeball_maze_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Level {
    /* colours: green red yellow blue
     * symbols: diamond flower star cross
     * bs = blue star and so on
     * no = no token in this square
     * nl = new line
     */
    protected Player myPlayer;

    public Level(Player newPlayer) {
        this.myPlayer = newPlayer;
    }

    List<Token> allMyTokens = new ArrayList<Token>();


    private final String level1 = "no bd no no nl" +
            "bs rd bf bd nl" +
            "rf bf rs gf nl" +
            "gf rs gs yd nl" +
            "bc yf yd gc nl" +
            "no no rf no";

    private final String level2 = "no no gf no nl" +
            "rd bs bf bf nl" +
            "yf gs gf rs nl" +
            "rs yf gc bc nl" +
            "no bs no no";

    public String getLevel(int input) {
        switch (input) {
            case 1: return this.level1;
            case 2: return this.level2;
            default: throw new IllegalArgumentException("There is no level that exists for that number input");
        }
    }

    public void makeLevel(int newLevel) {
        //myView.say("== Starting level creation ==");

        int x = 0, y = 0;
        String levelString;
        int[] isStart;
        int[] isFinish;

        //argument exceptions
        if (newLevel > 2) {
            throw new IllegalArgumentException("Can not enter an input higher than 2");
        }else if (newLevel < 1) {
            throw new IllegalArgumentException("Can not enter an input lower than 1");
        }

        switch (newLevel) {
            case 1: levelString = this.level1;
                isStart = new int[] {1,0};
                isFinish = new int[] {2,5};
                this.myPlayer.setPosition(new int[] {1,0});
                this.myPlayer.setPlayerOnColour("b");
                this.myPlayer.setPlayerOnSymbol("d");
                break;
            case 2: levelString = this.level2;
                isStart = new int[] {2,0};
                isFinish = new int[] {1,4};
                break;
            case 3: throw new IllegalArgumentException("Level 3 does not exist");
            default: levelString = "";
                isStart = new int[] {};
                isFinish = new int[] {};
                break;
        }
        levelString = levelString.replaceAll("\\s+",""); // removing whitespace

        for (int i = 0; i < levelString.length(); i += 2) {
            String colour = Character.toString(levelString.charAt(i));
            String symbol = Character.toString(levelString.charAt((i + 1)));
            int[] position = {x,y};



            x++;
            String yPosition = colour + symbol;

            if (yPosition.equals("nl")) { // if nl (newline) is encountered then a new level of symbols is created by going up the y axis by 1
                y++;
                x = 0;
            }else if (yPosition.equals("no")) {
                //do nothing
            }else {
                Token newToken = new Token(colour,symbol,position);
                myPlayer.setPlayerOnColour(colour);
                myPlayer.setPlayerOnSymbol(symbol);

                // setting start and finish before inserting token in to allMyTokens
                if (Arrays.equals(position,isStart)) {
                    newToken.setIsStart(true);
                    myPlayer.setPlayerOnColour(colour); //setting the colour and symbol the player is on
                    myPlayer.setPlayerOnSymbol(symbol);

                    this.myPlayer.setPosition(position); // if it is the starting token then the player will start here
                }else if (Arrays.equals(position,isFinish)) {
                    newToken.setIsFinish(true);
                }



                allMyTokens.add(newToken);
            }
        }
        //myView.say("== Finished level creation ==");
    }

    public void solution(int input) {
        if (input == 1) {
            myPlayer.setPosition(new int[] {2,5});
            myPlayer.setPlayerOnColour("r");
            myPlayer.setPlayerOnSymbol("f");
        }else if (input == 2) {
            myPlayer.setPosition(new int[] {1,4});
            myPlayer.setPlayerOnColour("b");
            myPlayer.setPlayerOnSymbol("s");
        }else {
            throw new IllegalArgumentException("Solutions only exist for levels 1 and 2");
        }

    }

}

