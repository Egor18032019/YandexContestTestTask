import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MonitorLogos {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));

        int timeLimit = 0;
        int erorrsLimit = 0;
        ArrayList<Long> timeList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();
        int errors = 0;
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int findErros = -1;
        String str = "read";

        do {
            str = r.readLine();

            if (str == null || str.length() == 0|| !r.ready()) {
                System.out.println("continue");
                continue;
            }


            if (str.length() <= 10) {
                String[] foo = str.split(" ");
                timeLimit = Integer.parseInt(foo[0]) * 1000;
                System.out.println();
                System.out.println("timeLimit " + timeLimit);
                erorrsLimit = Integer.parseInt(foo[1]);
            }
            else {
                if (str.contains("ERROR")) {
                    String status = str.substring(21);
                    statusList.add(status);
                    String temp = str.substring(1, 20);
                    long timeError = formatForDateNow.parse(temp).getTime();
                    timeList.add(timeError);
                }
            }

/*
            for (int i = 0; i < timeList.size(); i++) {
                System.out.println(statusList);

                errors = 0;
                for (int j = i; j < timeList.size() ; j++) {
                    //[1584357325000, 1584357325000, 1584357389000, 1584357402000, 1584357403000]
                    System.out.println(timeList.get(j) - timeList.get(i) + " Разница j-i");
                    if ((timeList.get(j) - timeList.get(i)) < timeLimit) {
                        errors++;
                        System.out.println("ErRors-> "+errors + " " + erorrsLimit + " i = " + i +" j= "+ j);

                        if (errors >= erorrsLimit) {
                            System.out.println(timeList);
                            Long timeForCriticalError = timeList.get(j);
                            Date answer = new Date();
                            answer.setTime(timeForCriticalError);

                            findErros = 1;
                            System.out.println(formatForDateNow.format(answer));
                            return;
//                            break;
                        }

                    }
                }
            }
*/


        }
        while (r.ready());

        for (int i = 0; i < timeList.size(); i++) {
            System.out.println(statusList);

            errors = 0;
            for (int j = i; j < timeList.size() ; j++) {
//                 System.out.println(timeList.get(j) - timeList.get(i) + " Разница j-i");
                if ((timeList.get(j) - timeList.get(i)) < timeLimit) {
                    errors++;
//                    System.out.println("ErRors-> "+errors + " " + erorrsLimit + " i = " + i +" j= "+ j);

                    if (errors >= erorrsLimit) {
                        System.out.println(timeList);
                        Long timeForCriticalError = timeList.get(j);
                        Date answer = new Date();
                        answer.setTime(timeForCriticalError);

                        System.out.println(formatForDateNow.format(answer));
                        return;
//                            break;
                    }

                }
            }
        }


            System.out.println(findErros);


        r.close();


    }

}
