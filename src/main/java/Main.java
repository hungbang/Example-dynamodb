import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try{

//            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
//            BufferedWriter out = new BufferedWriter(
//                    new FileWriter("/Users/KAI/workstation/aws-scripts/dynamodb/get_students_list.py"));
//            out.write(prg);
//            out.close();
//            int number1 = 10;
//            int number2 = 32;
            Process p = Runtime.getRuntime().exec("/Users/KAI/workstation/aws-scripts/dynamodb/get_students_list.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = new Integer(in.readLine()).intValue();
            System.out.println("value is : "+ret);
        }catch(Exception e){}
    }

}
