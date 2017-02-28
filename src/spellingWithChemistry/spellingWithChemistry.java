package spellingWithChemistry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class spellingWithChemistry {
	
	static ArrayList<String[]> periodicTable = new ArrayList<String[]>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String output;
		ArrayList<String> outputComponents = new ArrayList<String>();
		String input;
		Scanner in = new Scanner(System.in);
		boolean repeat = true;
		
		createTable();
		
		while (repeat){
			//Initialize
			input = takeInput();
			output = "";
			outputComponents.clear();
			
			//Loop through the input string
			outerloop:
			for (int i=0; i<input.length(); i++){

				//Loop through pt to find matching element
				for (int j=0; j<periodicTable.size(); j++){
					if(periodicTable.get(j)[0].toLowerCase().equals(input.substring(i,i+1).toLowerCase())){
						//Single char match
						output += periodicTable.get(j)[0];
						outputComponents.add(periodicTable.get(j)[1]);
						continue outerloop;
					} else if(input.length() >= i+2 && periodicTable.get(j)[0].toLowerCase().equals(input.substring(i,i+2).toLowerCase())){
						//Two char match
						output += periodicTable.get(j)[0];
						outputComponents.add(periodicTable.get(j)[1]);
						//Skip an iteration because two letters were matched
						i++;
						continue outerloop;
					}
				}
				
				output = "This string cannont be built from SI symbols.";
				outputComponents.clear();
				break;
			}
			
			System.out.println(output + " " + outputComponents);
			
			System.out.println("Would you like to enter a new string? (y/n)");
			if (in.nextLine().equals("y") == false){
				repeat = false;
			}
			

		}

		
		
	}
	
	public static void createTable(){
		String periodicTableCSV = "src/ptdata2.csv";
		String line = "";
		String cvsSplitBy = ",";
				
		try (BufferedReader br = new BufferedReader(new FileReader(periodicTableCSV))) {
			
			while ((line = br.readLine()) != null) {
                String[] ptLine = line.split(cvsSplitBy);				
				periodicTable.add(new String[] {ptLine[1].replaceAll("[^a-zA-Z0-9]", ""), ptLine[2].replaceAll("[^a-zA-Z0-9]", "")});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	private static String takeInput(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a word that you would like to spell with SI symbols:");
		return in.nextLine().replaceAll("[^a-zA-Z0-9]", "");
	}
	


}


