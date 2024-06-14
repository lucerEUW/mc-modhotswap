package modpacker;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContentDisplay {
    
    private String selectedFolder;
    private static JTextArea contentDisplayBox; // Renamed for Java naming convention
    private static JScrollPane scrollPane;
    
    public ContentDisplay(JFrame frame, int yheight) {
        // Initialize text area for content display
        contentDisplayBox = new JTextArea(375, 300);
        contentDisplayBox.setEditable(false);
        contentDisplayBox.setVisible(true);   
        
        // Add scroll pane to display text area
        scrollPane = new JScrollPane(contentDisplayBox);
        scrollPane.setBounds(5, yheight, 375, 300);
        frame.add(scrollPane);
        scrollPane.setVisible(true);      
    }
    
    public static void updateDisplayedContent(String selectedFolder) {
        contentDisplayBox.setText(""); // Clear existing content
        
        try {
            File directory = new File(selectedFolder);
            File[] files = directory.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    contentDisplayBox.append(file.getName() + "\n"); // Append file names to text area
                }
            } else {
                System.out.println("Verzeichnis leer"); // Directory is empty
            }
        } catch (NullPointerException e) {
            System.err.println("Error updating content display: " + e.getMessage());
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("Security error accessing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setFolderToDisplay(String folderToDisplay) {
        selectedFolder = folderToDisplay;
    }
}
