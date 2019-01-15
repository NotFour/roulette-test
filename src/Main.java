import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.nio.file.Files.exists;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int iterations = 0;
        int countMoreThen26 = 0;
        int betsCount = 0;

        do {
            //System.out.println("Введите начальный капитал, $");
            //int bank = Integer.parseInt(reader.readLine());
            int bank = 1000000;

            //System.out.println("Введите ставку, $");
            //int startbet = Integer.parseInt(reader.readLine());
            int startbet = 100;

            int nowbet = startbet;

            //System.out.println("Максимальное кол-во ставок");
            //int count = Integer.parseInt(reader.readLine());
            int count = 999999999;

            //System.out.println("Максимальное цвет");
            //String color = reader.readLine();
            String color = "красный";

            int[] variants = null;
            int[] red = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
            int[] black = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};

            if (color.equals("красный")) {
                variants = red;
            } else {
                variants = black;
            }

            Random random = new Random();
            int number;
            boolean win;


            int maxBank = 0;
            int maxBankCount = 0;
            int i=0;

            for (i = 0; i < count; i++) {
                if (bank - nowbet < 0) {
                    //System.out.println("Последняя ставка #" + (i - 1));
                    break;
                }
                win = false;
                number = random.nextInt(38);
                for (int y = 0; y < variants.length; y++) {
                    if (number == variants[y]) {
                        win = true;
                        break;
                    }
                }
                if (win) {
                    bank += nowbet;
                    nowbet = startbet;
                } else {
                    bank -= nowbet;
                    nowbet *= 2;
                }
                if (bank > maxBank) {
                    maxBank = bank;
                    maxBankCount = i;
                }
                //System.out.println("банк: "+bank+", ставка: "+nowbet+ ", ставка#" + i);
            }
            //System.out.println("максимальный банк = " + maxBank + ", ставка #" + maxBankCount);
            iterations++;
            betsCount += (i-1);
            if(Math.sqrt((int)(nowbet/startbet))>26) {
                countMoreThen26++;
                System.out.println(nowbet+" "+startbet+" "+ Math.sqrt(8192));
            }
        } while (iterations < 10000);
        System.out.println(betsCount/iterations);
        System.out.println("Последовательностей длиннее 26: "+countMoreThen26);
    }
}
