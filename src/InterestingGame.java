import java.io.*;
// Интересная игра
public class InterestingGame {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String J = r.readLine();
        String S = r.readLine();

        String[] foo = J.split(" ");
        String[] bar = S.split(" ");
        int VasyaWantScore = Integer.parseInt(foo[0]);//5,
        int numberOfCards = Integer.parseInt(foo[1]);
        int VasyaScore = 0;//5
        int PetyaScore = 0;//3
        boolean win = false;
        String result = "Draw";
        for (int i = 0; i < bar.length; ++i) {
            int ch = Integer.parseInt(bar[i]);
            if (ch < 3) {

            } else if (ch % 5 == 0 && ch % 3 == 0) {

            } else if (ch % 5 == 0) {
                ++VasyaScore;
                if (VasyaScore == VasyaWantScore) {
                    win = true;
                    result = "Vasya";
                    break;
                }
            } else if (ch % 3 == 0) {
                ++PetyaScore;
                if (PetyaScore == VasyaWantScore) {
                    win = true;
                    result = "Petya";
                    break;
                }
            }
        }

        if (!win) {
            if (VasyaScore < PetyaScore) {
                result = "Petya";
            }
            if (VasyaScore > PetyaScore) {
                result = "Vasya";
            }
        }
        System.out.println(result);
    }
}