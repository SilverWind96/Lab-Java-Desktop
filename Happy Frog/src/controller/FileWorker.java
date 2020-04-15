/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class FileWorker {

    private final File f = new File("save.txt");

    public void save(Data data) {
        try (FileWriter fileWriter = new FileWriter(f)) {
            String fileContent = data.getyFrog() + " "
                    + data.getxPipe1() + " " + data.getxPipe2() + " "
                    + data.getScore() + " " + data.getSpeed() + " "
                    + data.getGravity() + " " + data.getSpace1() + " " + data.getSpace2()
                    + " " + data.getyPipe1() + " " + data.getyPipe2();
            fileWriter.write(fileContent);
        } catch (IOException e) {
        }
    }

    public ArrayList<Integer> load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
            String line = bufferedReader.readLine();
            String s = "";
            ArrayList<Integer> value = new ArrayList<>();
            while (line != null) {
                s += line;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String s1[] = s.split(" ");
            for (String s11 : s1) {
                try {
                    value.add(Integer.parseInt(s11));
                } catch (NumberFormatException e) {
                    System.err.println("Number Format Exception");
                    f.delete();
                    return null;
                }
            }
            if (value.size() != 10) {
                System.err.println("Not Enough Data");
                f.delete();
                return null;
            }
            return value;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public void delete() {
        f.delete();
    }
}
