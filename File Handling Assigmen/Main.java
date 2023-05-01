import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            String name = args[0];
            ArrayList<Container> containers = new ArrayList<>();

            // Read the Exist file;
            File x1 = new File(name);
            if (!x1.exists())
                System.exit(0);
            if(!args[0].endsWith(".arxml")){
                throw new NotVaildAutosarFileException("Invalid file Extension");
            }
            Scanner input = new Scanner(x1);
            if (x1.length() == 0)
                throw new EmptyAutosarFileException("Empty Autosar File!");

            //stringBuilder
            StringBuilder S1 =new StringBuilder();
            while(input.hasNext()) {
                S1.append(input.nextLine()+"\n");
            }
            //System.out.println(S1.toString());
            String Data = S1.toString();
            Scanner x2 = new Scanner(Data);
            while(x2.hasNext()){
                String line = x2.nextLine();
                if(line.contains("<CONTAINER") ){
                    String UUID = line.substring(line.indexOf("=")+1,line.indexOf(">"));
                    line = x2.nextLine();
                    String shortName = line.substring(line.indexOf("<S")+12,line.indexOf("</S"));
                    line = x2.nextLine();
                    String longName = line.substring(line.indexOf("<L")+11,line.indexOf("</L"));
                    Container A = new Container();
                    A.set_UUID(UUID);
                    A.set_shortName(shortName);
                    A.set_longName(longName);
                    containers.add(A);
                }
            }
            Collections.sort(containers);

            //Create new file
            String newName = name.substring(0,name.indexOf("."))+"_mod.arxml";
            File y1 = new File(newName);
            if (y1.exists())
                System.exit(1);
            PrintWriter output = new PrintWriter(y1);

            //Write in the New File;
            output.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            output.println("<AUTOSAR>");

            //-----------------Sorting------------------------
            for(int i =0;i < containers.size();i++){
                output.println(containers.get(i).toString());
            }
            output.print("</AUTOSAR>");
            output.close();

        }
        catch ( NotVaildAutosarFileException x){
        }
        catch (EmptyAutosarFileException x){
        }
        catch (FileNotFoundException ex){
            System.out.println("file not Exist");
        }
        catch(IndexOutOfBoundsException ex) {
            System.out.println("error");
        }
    }
}
