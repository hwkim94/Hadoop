package com.ybigta.euntaek;

import org.apache.avro.generic.GenericData;

import java.io.*;
import java.util.ArrayList;

public class module_test {
    public static void main(String[] args) {
        String[][] lines = new String[5000][3];

        try {
            File csv = new File("C:\\Users\\LG_\\Desktop\\와이빅타\\엔지니어링팀\\18년\\2018-여름-하둡 세션\\180804 MapReduce 심화-응용\\HW\\z_input.csv");
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line;
            int row =0 ;

            while ((line = br.readLine()) != null) {
                String[] token = line.split(",",    -1);
                for(int i=0;i<3;i++) {
                    lines[row][i] = token[i];
                }
                System.out.println(lines[row][0].split("기")[0]);
                row++;
            }
            br.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
