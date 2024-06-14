package modpacker;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ModHotSwap extends JFrame {
    
    private static String mcModsFolder;
    private static String modpackFolder;
    
    public static void main(String[] args) {
    	
    	checkForModpacksFolder();
        // Initialize ModHotSwap instance
        ModHotSwap modpacker = new ModHotSwap();
        modpacker.setMainWindow();
        
        // Create labels and GUI components
        modpacker.createLabel("Path to desired Minecraft installation mods folder:", 5);     
        SelectFolder modsFolderGUI = new SelectFolder(modpacker, 25, "McModsFolderButton", "C:\\Users\\Anwender\\AppData\\Roaming\\.minecraft\\mods");
        
        modpacker.createLabel("Select your modpack:", 60);
        SelectFolder modpackerFolderGUI = new SelectFolder(modpacker, 80, "NodpackFolder", "C:\\Users\\Anwender\\Documents\\Modpacks\\");
        ContentDisplay contentDisplay = new ContentDisplay(modpacker, 120); 
        Launcher launcher = new Launcher(modpacker);
        
        // Get selected folder paths
        try {
            mcModsFolder = modsFolderGUI.getSelectedFolder();
            modpackFolder = modpackerFolderGUI.getSelectedFolder();
        } catch (NullPointerException e) {
            System.err.println("No folder selected: " + e.getMessage());
        }
    }
    
    private static void checkForModpacksFolder() {
    	File directory = new File("C:\\Users\\Anwender\\Documents\\Modpacks");
    	
    	if(!directory.exists()) {
    		directory.mkdirs();
    	}
    }
    private void setMainWindow() {
        // Set up main window properties
        this.setLayout(null);
        this.setTitle("ModHotSwap");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.gray);
        this.setVisible(true);
    }
    
    private void createLabel(String title, int yheight) {
        // Create and add a label to the main window
        JLabel label = new JLabel(title);
        label.setBounds(5, yheight, 400, 20);
        label.setForeground(Color.white);
        this.add(label);
    }
    
    public static String getCurrentMcModsFolder() {
        return mcModsFolder;
    }
    
    public static String getCurrentModpackFolder() {
        return modpackFolder;
    }
    
    public static void setCurrentMcModsFolder(String newFolder) {
        mcModsFolder = newFolder;
    }
    
    public static void setCurrentModpackFolder(String newFolder) {
        modpackFolder = newFolder;
    }
}
