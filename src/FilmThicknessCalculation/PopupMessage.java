package FilmThicknessCalculation;

import javax.swing.*;

public class PopupMessage
{
	public static void showErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message, " Error", JOptionPane.ERROR_MESSAGE);
	}
}
