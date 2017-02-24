
import org.python.util.PythonInterpreter;

import javax.script.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try{

        callPythonScript();
        }catch(Exception e){}
    }


    public static void callPythonScript() throws FileNotFoundException, ScriptException {
        StringWriter writer = new StringWriter(); //ouput will be stored here
        PythonInterpreter interpreter = new PythonInterpreter();
//        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();

        context.setWriter(writer); //configures output redirection
//        ScriptEngine engine = manager.getEngineByName("jython");
//        interpreter.eval(new FileReader("D:\\HOME\\aws-scripts\\dynamodb\\get_students_list.py"), context);
        System.out.println(writer.toString());
    }

}
