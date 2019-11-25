package chrisr.nz.ac.ara.eyeball_maze_3;

public class Token {

    /* colours: green red yellow blue
     * symbols: diamond flower star cross
     *
     */

    private String colour;
    private String symbol;
    private int[] position;
    private boolean isFinish;
    private boolean isStart;

    public Token (String newColour, String newSymbol, int[] newPosition) {
        this.colour = newColour;
        this.symbol = newSymbol;
        this.position = newPosition;
        this.isFinish = false;
        this.isStart = false;
    }
    public int[] getPosition() {
        int[] output = this.position;
        return output;
    }

    public void setPosition(int[] input) {
        if (input[0] < 0 || input[1] < 0) {
            throw new IllegalArgumentException("Token position can not be less than 0");
        }else if (input[0] > 10 || input[1] > 10) {
            throw new IllegalArgumentException("Token position can not be larger than 10");
        }else {
            this.position = input;
        }


    }

    public String getSymbol() {
        String output = this.symbol;
        return output;
    }

    public void setSymbol(String input) {
        switch (input) {
            case "f":
            case "d":
            case "s":
            case "c": this.symbol = input;
                break;
            default: throw new IllegalArgumentException("There is no symbol that exists for that letter input");
        }
    }

    public String getColour() {
        String output = this.colour;
        return output;
    }

    public void setColour(String input) {
        switch (input) {
            case "g":
            case "r":
            case "y":
            case "b": this.colour = input;
                break;
            default: throw new IllegalArgumentException("There is no colour that exists for that letter input");
        }

    }

    public boolean getIsFinish() {
        boolean output = this.isFinish;
        return output;
    }

    public void setIsFinish(boolean input) {
        this.isFinish = input;
    }

    public boolean getIsStart() {
        boolean output = this.isStart;
        return output;
    }

    public void setIsStart(boolean input) {
        this.isStart = input;
    }
}
