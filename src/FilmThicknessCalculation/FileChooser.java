package FilmThicknessCalculation;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import goodJ.Annotation.Service;


@Service(name="fileChooser")
public class FileChooser
{


	public   File getFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");

		chooser.setFileFilter(filter);
		File file = null;
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}

	public void loadFileA() {

		File file = this.getFile();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			ArrayList<Double> numbers = new ArrayList<Double>();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				numbers.add(Double.valueOf(line));
			}

			DataStorage.setFileA(numbers);

			if (numbers.size() !=5){
				PopupMessage.showErrorMessage("Wrong polymer file format");
				DataStorage.resetDataA();
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}


	public void loadFileB() {

		File file = this.getFile();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			ArrayList<Double> numbers = new ArrayList<Double>();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				numbers.add(Double.valueOf(line));


			}
			DataStorage.setFileB(numbers);
			if (numbers.size() !=4){
				PopupMessage.showErrorMessage("Wrong solution file format");
				DataStorage.resetDataB();
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}





	}


}

		
    	
 
 

    
