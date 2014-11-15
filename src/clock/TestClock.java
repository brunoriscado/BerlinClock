package clock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestClock {

    /**
     * Input Test Results:
     * 00:00:00 Y OOOO OOOO OOOOOOOOOOO OOOO
     * 13:17:01 O RROO RRRO YYROOOOOOOO YYOO
     * 23:59:59 O RRRR RRRO YYRYYRYYRYY YYYY
     * 24:00:00 Y RRRR RRRR OOOOOOOOOOO OOOO
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        do {
            System.out.println("\nPlease insert an hour using the correct format (hh:mm:ss) or enter the word exit to leave:");
            input = br.readLine();
            int[] hourArray = getHourArray(input);
            if (hourArray != null) {
                BerlinClock bc = new BerlinClock(hourArray[0], hourArray[1], hourArray[2]);
                System.out.print(bc.toString());
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Leaving the test...");
            } else {
                System.out.println("Incorrect hour format input! Please use the correct format (hh:mm:ss)");
            }
        } while(!input.equalsIgnoreCase("exit"));
        input = null;
        br.close();
        isr.close();
        is.close();
        System.exit(0);
    }

    public static int[] getHourArray(String inputHour) {
        String[] hourArray = null;
        int[] newHourArray = null;
        if (inputHour != null && !inputHour.equals("")) {
            hourArray = inputHour.split(":");
        }
        if (!validateInputHour(hourArray)) {
            newHourArray = null;
        } else {
            newHourArray = new int[3];
            newHourArray[0] = Integer.parseInt(hourArray[0]);
            newHourArray[1] = Integer.parseInt(hourArray[1]);
            newHourArray[2] = Integer.parseInt(hourArray[2]);
        }
        return newHourArray;
    }

    private static boolean validateInputHour(String[] hourArray) {
        boolean valid = false;
        if (hourArray.length == 3) {
            int hours = Integer.parseInt(hourArray[0]);
            int minutes = Integer.parseInt(hourArray[1]);
            int seconds = Integer.parseInt(hourArray[2]);
            if (hourArray[0].length() == 2 && hours >= 0 && hours <= 24 && 
                    hourArray[1].length() == 2 && minutes >= 0 && minutes <= 59 &&
                    hourArray[2].length() == 2 && seconds >= 0 && seconds <= 59 ) {
                valid = true;
            }
        }
        return valid;
    }
}