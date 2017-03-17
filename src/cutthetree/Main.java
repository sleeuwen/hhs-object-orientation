package cutthetree;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        JFrame window = new PlayFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("CutThaTree");
        window.setResizable(false);
        window.setVisible(true);
    }
}
