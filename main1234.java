import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class main1234 {
    public static void main1234(String[] args) throws IOException {
        DataHub data = new DataHub();
        while (args.length > 0) {
            if(args[0].toLowerCase().equals("help")){
                System.out.println("Problem description is given in the help.txt...");
                showHelpMessage();
                break;
            }
            if(args[0].toLowerCase().equals("generatedata")){
                System.out.println("Problem data is shown in the GeneratedData.txt...");
                data.output();
                break;
            }
            else {
                System.out.println(" To get more information about our problem, you can type the following commands; help or generatedata." );
                System.out.println(" Help-for business problem , Generatedata-for viewing randomly generated output." );
                break;
            }
        }

    }
    private static void showHelpMessage() {
        File file = new File("help.txt");

        try {

            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
 }
