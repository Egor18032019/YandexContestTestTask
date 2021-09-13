import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class MonitorLogos {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));
        int timeLimit = 0;
        short erorrsLimit = 0;
        LinkedList <Long> timeList = new LinkedList<>();
//        LinkedList <String> statusList = new LinkedList <>();
        int errors = 0;
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int findErros = -1;
        String str = "read";

        do {
            str = r.readLine();

            if (str == null || str.length() == 0 || !r.ready()) {
                continue;
            }

            if (timeLimit == 0 && erorrsLimit == 0) {
                String[] foo = str.split(" ");
                timeLimit = Integer.parseInt(foo[0]) * 1000;
                erorrsLimit = Short.parseShort((foo[1]));
            } else {
                if (str.contains("ERROR")) {
//                    String status = str.substring(21);
//                    statusList.add(status);
                    String temp = str.substring(1, 20);
                    long timeError = formatForDateNow.parse(temp).getTime();
                    timeList.add(timeError);
                }
            }
            for (int i = 0; i < timeList.size(); i++) {
                errors = 0;
                for (int j = i; j < timeList.size(); j++) {
                    if ((timeList.get(j) - timeList.get(i)) < timeLimit) {
                        errors++;
                        if (errors >= erorrsLimit) {
//                        System.out.println(erorrsLimit + " "+ erorrsLimit);
                            long timeForCriticalError = timeList.get(j);
                            Date answer = new Date();
                            answer.setTime(timeForCriticalError);
                            System.out.println(formatForDateNow.format(answer));
                            return;
                        }
                    }
                }
            }
        }
        while (r.ready());



        System.out.println(findErros);


        r.close();


    }

}
