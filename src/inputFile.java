import java.io.*;

public class inputFile {

    public static void main(String[] args) throws Exception {
        StreamTokenizer in = new StreamTokenizer(
                new BufferedInputStream(
                        new FileInputStream(new File("input.txt"))));
        ;
        PrintStream out = new PrintStream(new File("output.txt"));
        int a, b;
        in.nextToken();
        a = (int) in.nval;
        in.nextToken();
        b = (int) in.nval;
        out.println(a + b);
        out.flush();
        out.close();
    }
}

