package chrisr.nz.ac.ara.eyeball_maze_3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Player myPlayer;
    private Level myLevel;
    ImageView[][] imageViews = new ImageView[36][36];
    ImageView imageViewSound;
    int[][] imageSrcs = new int[36][36];
    public MainActivity main;
    private int selection;
    boolean levelCreated;
    int steps;
    int hiddenSteps;
    TextView stepsText;
    TextView goalText;
    boolean musicStatus;
    boolean fileSaved;
    Token positionToken;


    public MainActivity() {
        selection = -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        fileSaved = false;
        positionToken = new Token("", "", new int[] {});
        musicStatus = true;
        main = this;
        setImageViews();
        setImageSources();
        levelCreated = false;
        View v = findViewById(R.id.textView4);
        initialise(v);
        soundButton();


    }

    public void initialise(View v) {
        if (levelCreated){
            //replace getPlayerCoords with myPlayer.getPosition
            int[] coords = myPlayer.getPosition();
            Drawable myDrawable = getResources().getDrawable(imageSrcs[coords[1]][coords[0]]);
            imageViews[coords[1]][coords[0]].setImageDrawable(myDrawable);
        }
        levelCreated = true;
        steps = 0;
        hiddenSteps = 0;
        stepsText = findViewById(R.id.textView4);
        stepsText.setText( "Steps: 0");

        myPlayer = new Player(main);
        myLevel = new Level(myPlayer);
        myLevel.makeLevel(1);

        goalText = findViewById(R.id.textViewGoal);
        String inputText = "Goals: " + getGoalAmount();
        goalText.setText(inputText);

        //setting player
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), imageSrcs[0][1]);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        Bitmap mergedImages = createSingleImageFromMultipleImages(image1, image2);
        imageViews[0][1].setImageBitmap(mergedImages);

        //setting the goal
        image1 = BitmapFactory.decodeResource(getResources(), imageSrcs[5][2]);
        image2 = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        mergedImages = createSingleImageFromMultipleImages(image1, image2);
        imageViews[5][2].setImageBitmap(mergedImages);
    }

    private void soundButton() {
        //programmatically added
        ConstraintLayout mainLayout = findViewById(R.id.main_activity_layout);
        ConstraintSet constraintSet = new ConstraintSet();
        imageViewSound = new ImageView(this);
        imageViewSound.setId((int)20192);
        imageViewSound.setImageResource(R.drawable.sound_on);
        imageViewSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicStatus){
                    musicStatus = false;
                    Drawable myDrawable = getResources().getDrawable(R.drawable.sound_off);
                    imageViewSound.setImageDrawable(myDrawable);
                }else{
                    musicStatus = true;
                    Drawable myDrawable = getResources().getDrawable(R.drawable.sound_on);
                    imageViewSound.setImageDrawable(myDrawable);
                }
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int viewWidth = (int)(width/4);

        imageViewSound.setLayoutParams(new ConstraintLayout.LayoutParams(150, 150));
        mainLayout.addView(imageViewSound);

        constraintSet.clone(mainLayout);
        constraintSet.connect(imageViewSound.getId(), ConstraintSet.TOP, mainLayout.getId(), ConstraintSet.TOP, 16);
        constraintSet.connect(imageViewSound.getId(), ConstraintSet.END, mainLayout.getId(), ConstraintSet.END, 16);
        constraintSet.applyTo(mainLayout);
    }

    public Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage) {
        Bitmap result = Bitmap.createBitmap(secondImage.getWidth(), secondImage.getHeight(), secondImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 10, 10, null);
        return result;
    }

    private String getLocationImageView(ImageView imageView) {
        // https://stackoverflow.com/questions/10137692/how-to-get-resource-name-from-resource-id
        String name = getResources().getResourceEntryName(imageView.getId());
        name = name.replace("imageView", "");
        int switchInput = Integer.parseInt(name);
        String output = "";
        switch (switchInput){
            case 1: output = "0,0";
                break;
            case 2: output = "0,1";
                break;
            case 3: output = "0,2";
                break;
            case 4: output = "0,3";
                break;
            case 5: output = "0,4";
                break;
            case 6: output = "0,5";
                break;
            case 7: output = "1,0";
                break;
            case 8: output = "1,1";
                break;
            case 9: output = "1,2";
                break;
            case 10: output = "1,3";
                break;
            case 11: output = "1,4";
                break;
            case 12: output = "1,5";
                break;
            case 13: output = "2,0";
                break;
            case 14: output = "2,1";
                break;
            case 15: output = "2,2";
                break;
            case 16: output = "2,3";
                break;
            case 17: output = "2,4";
                break;
            case 18: output = "2,5";
                break;
            case 19: output = "3,0";
                break;
            case 20: output = "3,1";
                break;
            case 21: output = "3,2";
                break;
            case 22: output = "3,3";
                break;
            case 23: output = "3,4";
                break;
            case 24: output = "3,5";
                break;
            case 25: output = "4,0";
                break;
            case 26: output = "4,1";
                break;
            case 27: output = "4,2";
                break;
            case 28: output = "4,3";
                break;
            case 29: output = "4,4";
                break;
            case 30: output = "4,5";
                break;
            case 31: output = "5,0";
                break;
            case 32: output = "5,1";
                break;
            case 33: output = "5,2";
                break;
            case 34: output = "5,3";
                break;
            case 35: output = "5,4";
                break;
            case 36: output = "5,5";
                break;
        }

        return output;
    }

    public void onClick(View v) {
        ImageView nextImageView = (ImageView) v;
        int[] getCurPos = myPlayer.getPosition();
        int intX = getCurPos[1];
        int intY = getCurPos[0];
        int prevX = getCurPos[1];
        int prevY = getCurPos[0];

        imageViews[intX][intY].setImageBitmap(BitmapFactory.decodeResource(getResources(), imageSrcs[intX][intY]));

        //swap around for x by y rather than y by x
        String[] moveTo = getLocationImageView(nextImageView).split(",");
        int posX = Integer.parseInt(moveTo[1]);
        int posY = Integer.parseInt(moveTo[0]);


        String symCol = getSymCol(getLocationImageView(nextImageView));
        String[] symColArr = symCol.split(",");

        String colour = symColArr[0];
        String symbol = symColArr[1];

        positionToken = new Token(colour, symbol, new int[] { posX, posY });
        myPlayer.move(positionToken);

        //getting new position
        getCurPos = myPlayer.getPosition();
        intX = getCurPos[1];
        intY = getCurPos[0];
        int nextX = getCurPos[1];
        int nextY = getCurPos[0];

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), imageSrcs[intX][intY]);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        Bitmap mergedImages = createSingleImageFromMultipleImages(image1, image2);
        imageViews[intX][intY].setImageBitmap(mergedImages);


        //if player has reached goal
        for (Token element : myLevel.allMyTokens) {
            if (element.getIsFinish() && Arrays.equals(myPlayer.getPosition(),element.getPosition())) { // checking if the player's position is on a token that has the finish property
                if (musicStatus){
                    final MediaPlayer mp = MediaPlayer.create(this, R.raw.success);
                    mp.start();
                }
                View newView = findViewById(R.id.textView4);
                fireSuccessDialog(newView, this);
            }
        }

        if (prevX != nextX || prevY != nextY){
            steps++;
            String inputText = "Steps: " + steps;
            stepsText.setText(inputText);
            if (musicStatus){
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.step);
                mp.start();
            }
        }

        hiddenSteps++;
        if (hiddenSteps > 10){
            if (musicStatus){
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.fail);
                mp.start();
            }
            //Toast.makeText(this, "Game Over! You took to long!", Toast.LENGTH_LONG).show();
            View newView = findViewById(R.id.textView4);
            fireFailDialog(newView, this);
        }

    }

    private int getGoalAmount() {
        int output = 0;
        for (Token element : myLevel.allMyTokens) {
            if (element.getIsFinish()) {
                output++;
            }
        }
        return output;
    }

    public void setImageViews() {
        imageViews[0][0] = findViewById(R.id.imageView1);
        imageViews[0][1] = findViewById(R.id.imageView2);
        imageViews[0][2] = findViewById(R.id.imageView3);
        imageViews[0][3] = findViewById(R.id.imageView4);
        imageViews[0][4] = findViewById(R.id.imageView5);
        imageViews[0][5] = findViewById(R.id.imageView6);
        imageViews[1][0] = findViewById(R.id.imageView7);
        imageViews[1][1] = findViewById(R.id.imageView8);
        imageViews[1][2] = findViewById(R.id.imageView9);
        imageViews[1][3] = findViewById(R.id.imageView10);
        imageViews[1][4] = findViewById(R.id.imageView11);
        imageViews[1][5] = findViewById(R.id.imageView12);
        imageViews[2][0] = findViewById(R.id.imageView13);
        imageViews[2][1] = findViewById(R.id.imageView14);
        imageViews[2][2] = findViewById(R.id.imageView15);
        imageViews[2][3] = findViewById(R.id.imageView16);
        imageViews[2][4] = findViewById(R.id.imageView17);
        imageViews[2][5] = findViewById(R.id.imageView18);
        imageViews[3][0] = findViewById(R.id.imageView19);
        imageViews[3][1] = findViewById(R.id.imageView20);
        imageViews[3][2] = findViewById(R.id.imageView21);
        imageViews[3][3] = findViewById(R.id.imageView22);
        imageViews[3][4] = findViewById(R.id.imageView23);
        imageViews[3][5] = findViewById(R.id.imageView24);
        imageViews[4][0] = findViewById(R.id.imageView25);
        imageViews[4][1] = findViewById(R.id.imageView26);
        imageViews[4][2] = findViewById(R.id.imageView27);
        imageViews[4][3] = findViewById(R.id.imageView28);
        imageViews[4][4] = findViewById(R.id.imageView29);
        imageViews[4][5] = findViewById(R.id.imageView30);
        imageViews[5][0] = findViewById(R.id.imageView31);
        imageViews[5][1] = findViewById(R.id.imageView32);
        imageViews[5][2] = findViewById(R.id.imageView33);
        imageViews[5][3] = findViewById(R.id.imageView34);
        imageViews[5][4] = findViewById(R.id.imageView35);
        imageViews[5][5] = findViewById(R.id.imageView36);
    }

   public String getSymCol(String input) {
        String output = "";
        switch (input){
            case "0,0": output = "";
                break;
            case "0,1": output = "b,d";
                break;
            case "0,2": output = "";
                break;
            case "0,3": output = "";
                break;
            case "0,4": output = "";
                break;
            case "0,5": output = "";
                break;
            case "1,0": output = "b,s";
                break;
            case "1,1": output = "r,d";
                break;
            case "1,2": output = "b,f";
                break;
            case "1,3": output = "b,d";
                break;
            case "1,4": output = "";
                break;
            case "1,5": output = "";
                break;
            case "2,0": output = "r,f";
                break;
            case "2,1": output = "b,f";
                break;
            case "2,2": output = "r,s";
                break;
            case "2,3": output = "g,f";
                break;
            case "2,4": output = "";
                break;
            case "2,5": output = "";
                break;
            case "3,0": output = "g,f";
                break;
            case "3,1": output = "r,s";
                break;
            case "3,2": output = "g,s";
                break;
            case "3,3": output = "y,d";
                break;
            case "3,4": output = "";
                break;
            case "3,5": output = "";
                break;
            case "4,0": output = "b,c";
                break;
            case "4,1": output = "y,f";
                break;
            case "4,2": output = "y,d";
                break;
            case "4,3": output = "g,c";
                break;
            case "4,4": output = "";
                break;
            case "4,5": output = "";
                break;
            case "5,0": output = "";
                break;
            case "5,1": output = "";
                break;
            case "5,2": output = "r,f";
                break;
            case "5,3": output = "";
                break;
            case "5,4": output = "";
                break;
            case "5,5": output = "";
                break;
            default: output = "";
        }
        return output;
   }

    public void setImageSources() {
        imageSrcs[0][0] = R.drawable.blank;
        imageSrcs[0][1] = R.drawable.blue_diamond;
        imageSrcs[0][2] = R.drawable.blank;
        imageSrcs[0][3] = R.drawable.blank;
        imageSrcs[0][4] = R.drawable.blank;
        imageSrcs[0][5] = R.drawable.blank;
        imageSrcs[1][0] = R.drawable.blue_star;
        imageSrcs[1][1] = R.drawable.red_diamond;
        imageSrcs[1][2] = R.drawable.blue_plant;
        imageSrcs[1][3] = R.drawable.blue_diamond;
        imageSrcs[1][4] = R.drawable.blank;
        imageSrcs[1][5] = R.drawable.blank;
        imageSrcs[2][0] = R.drawable.red_plant;
        imageSrcs[2][1] = R.drawable.blue_plant;
        imageSrcs[2][2] = R.drawable.red_star;
        imageSrcs[2][3] = R.drawable.green_plant;
        imageSrcs[2][4] = R.drawable.blank;
        imageSrcs[2][5] = R.drawable.blank;
        imageSrcs[3][0] = R.drawable.green_plant;
        imageSrcs[3][1] = R.drawable.red_star;
        imageSrcs[3][2] = R.drawable.green_star;
        imageSrcs[3][3] = R.drawable.yellow_diamond;
        imageSrcs[3][4] = R.drawable.blank;
        imageSrcs[3][5] = R.drawable.blank;
        imageSrcs[4][0] = R.drawable.blue_cross;
        imageSrcs[4][1] = R.drawable.yellow_plant;
        imageSrcs[4][2] = R.drawable.yellow_diamond;
        imageSrcs[4][3] = R.drawable.green_cross;
        imageSrcs[4][4] = R.drawable.blank;
        imageSrcs[4][5] = R.drawable.blank;
        imageSrcs[5][0] = R.drawable.blank;
        imageSrcs[5][1] = R.drawable.blank;
        imageSrcs[5][2] = R.drawable.red_plant;
        imageSrcs[5][3] = R.drawable.blank;
        imageSrcs[5][4] = R.drawable.blank;
        imageSrcs[5][5] = R.drawable.blank;
    }

    public void fireSuccessDialog(View view, MainActivity newMain) {
        final String[] listItems = getResources().getStringArray(R.array.choice_array);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final TextView result = findViewById(R.id.tvResult);
        final CharSequence currentMsg = result.getText();
        final MainActivity main = newMain;

        builder.setTitle(R.string.dialog_title)

                .setSingleChoiceItems(R.array.choice_array, selection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0){
                            View newView = findViewById(R.id.tvResult);
                            initialise(newView);
                        }else if (which == 1) {
                            Toast.makeText(main, "Unfortunately Level 2 is unavailable", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        selection = Arrays.asList(listItems).indexOf(result.getText());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //result.setText(currentMsg);
                        //do nothing
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void fireFailDialog(View view, MainActivity newMain) {
        final String[] listItems = getResources().getStringArray(R.array.choice_array);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final TextView result = findViewById(R.id.tvResult);
        final CharSequence currentMsg = result.getText();
        final MainActivity main = newMain;

        builder.setTitle(R.string.failDialog_title)

                .setSingleChoiceItems(R.array.choice_array, selection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0){
                            View newView = findViewById(R.id.tvResult);
                            initialise(newView);
                        }else if (which == 1) {
                            Toast.makeText(main, "Unfortunately Level 2 is unavailable", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        selection = Arrays.asList(listItems).indexOf(result.getText());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //result.setText(currentMsg);
                        //do nothing
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void saveFileOnClick(View v) {
        fileSaved = true;
        File fileReader = new File(main);
        int[] coords = myPlayer.getPosition();
        String direction = String.valueOf(myPlayer.getDirection());
        String newSteps = String.valueOf(steps);
        String newHiddenSteps = String.valueOf(hiddenSteps);

        String output = coords[1] + "," + coords[0] + "," + direction + "," + newSteps + "," + newHiddenSteps;
        fileReader.writeToAFile(output);
    }

    public void readFileOnClick(View v) {
        //coords[0] + coords[1] + direction[2] + Steps[3] + HiddenSteps[4];
        if (!fileSaved){
            Toast.makeText(main, "You need to save before loading!", Toast.LENGTH_SHORT).show();
            return;
        }
        File fileReader = new File(main);
        String output = fileReader.readFromAFile(v);

        String[] load = output.split(",");
        this.steps = Integer.parseInt(load[3]);
        String textForView = "Steps: " + steps;
        this.hiddenSteps = Integer.parseInt(load[4]);

        TextView newView = findViewById(R.id.textView4);
        newView.setText(textForView);
    }

    public void upOnClick(View v){
        int[] coords = myPlayer.getPosition();
        int posX = coords[1] + 1;
        int posY = coords[0];
        onClick(imageViews[posX][posY]);
    }

    public void rightOnClick(View v){
        int[] coords = myPlayer.getPosition();
        int posX = coords[1];
        int posY = coords[0] + 1;
        onClick(imageViews[posX][posY]);
    }

    public void leftOnClick(View v){
        int[] coords = myPlayer.getPosition();
        int posX = coords[1];
        int posY = coords[0] - 1;
        onClick(imageViews[posX][posY]);
    }

    public void downOnClick(View v){
        int[] coords = myPlayer.getPosition();
        int posX = coords[1] - 1;
        int posY = coords[0];
        onClick(imageViews[posX][posY]);
    }
}
