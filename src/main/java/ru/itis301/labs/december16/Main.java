package ru.itis301.labs.december16;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> list = Files.readAllLines(new File("C:\\Users\\poloy\\Desktop\\ИТИС\\Java\\ControlWork2semest\\src\\main\\java\\ru\\itis301\\labs\\december16\\schedule.txt").toPath(), Charset.defaultCharset());
            TVDatabase tvDatabase = new TVDatabase(list);

            //tvDatabase.printallPrograms();
            //tvDatabase.printnowPrograms();

            //System.out.println(tvDatabase.findProgram("#Первый"));

            //System.out.println(tvDatabase.findProgramNowOfCanal("#Первый"));

            /*BroadcastsTime t1 = new BroadcastsTime("10:10");
            BroadcastsTime t2 = new BroadcastsTime("19:10");
            System.out.println(tvDatabase.findProgramNowOfCanalTime("#Первый", t1, t2));*/
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }

}
