
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Basharat Ali
 */
public class MyFileHandler {
    public String readMyFile()
    {
        String str="";
    try{
     File f = new File("Memory.txt");   
     FileReader fr = new FileReader(f);
     BufferedReader br = new BufferedReader(fr);
      str = br.readLine();
     
     br.close();
     
     fr.close();
          return str;

    }
    catch(Exception ex)
    {
            /*Logger.getLogger(MyFileHandler.class.getName()).log(Level.SEVERE, null, ex);*/
            
            ex.printStackTrace();
        }
        return str;
    }
    
    public void writeMyFile(String s)
    {
        try{
            File f = new File("Memory.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            //pw.print("ashb");
            pw.print(s);
            pw.close();
            fw.close();
           }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
