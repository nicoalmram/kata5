package kata5.pkg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReader {
    
    public static List<String> read(String fileName){
        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader fichero = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fichero);
            System.out.println("Exito");
            while(br.readLine() != null){
                String aux =br.readLine();
                if(aux.contains("@")){
                    list.add(aux);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No se pudo abrir el fichero: " + fileName);
        }
        return list;
    }
    
}
