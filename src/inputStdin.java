import java.io.*;

public class inputStdin {
    public static void main(String[] args) throws Exception {
        int x = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vvod = br.readLine();
        String[] tok = vvod.split(" ");
        for (int i = 0; i < tok.length; i++) {
            x += Integer.parseInt(tok[i]);

        }
        System.out.println(x);
    }
}

