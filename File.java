package chrisr.nz.ac.ara.eyeball_maze_3;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class File {

    MainActivity main;
    String getCoords;
    public File(MainActivity newMain){
        this.main = newMain;
        getCoords = "";
    }
    private void readALevel() {

        // files can be stored in assets or raw folders in the app
        final String originalFileSrc = "textfile.txt";
        StringBuilder fileContent = new StringBuilder();
        try {
            InputStream inputStream = main.getAssets().open(originalFileSrc);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }

            //don't forget to close!
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //editText1.setText(fileContent.toString());
        String output = fileContent.toString();
    }

    public String readFromAFile(View view) {

        StringBuilder fileContent = new StringBuilder();

        try {
            final String fileName = "gameSave.txt";
            FileInputStream openFileInput = main.openFileInput(fileName);

            int c;
            while ((c = openFileInput.read()) != -1) {
                fileContent.append(Character.toString((char) c));
            }

            openFileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = fileContent.toString();

        Toast.makeText(main, "Loading your steps", Toast.LENGTH_LONG).show();
        return output;
    }

    public void writeToAFile( String input) {
        // https://developer.android.com/training/data-storage/files
        String fileName = "gameSave.txt";
        String fileContents = input;
        FileOutputStream outputStream;

        try {
            outputStream = main.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        Toast.makeText(main, "Saving your steps", Toast.LENGTH_LONG).show();
    }
}
