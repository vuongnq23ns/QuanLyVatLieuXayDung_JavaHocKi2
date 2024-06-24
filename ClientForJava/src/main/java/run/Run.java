package run;

import javax.swing.UIManager;
import view.*;

public class Run {
	public static void main(String[] args) {
		try {
			String src = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
			UIManager.setLookAndFeel(src);
			new LoginView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}