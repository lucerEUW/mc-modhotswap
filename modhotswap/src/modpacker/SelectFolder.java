package modpacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SelectFolder {
    
    private String selectedFolder;
    
    public SelectFolder(ModHotSwap frame, int yheight, String action, String presetFolder) {
        selectedFolder = presetFolder;
        
        // Text field to display selected folder path
        JTextField folderDisplayField = new JTextField();
        folderDisplayField.setBounds(5, yheight, 260, 30);
        folderDisplayField.setText(selectedFolder);
        frame.add(folderDisplayField);
        folderDisplayField.setVisible(true);
        
        // Button to open file chooser dialog
        JButton selectFolderButton = new JButton("Browse");
        selectFolderButton.setBounds(270, yheight, 110, 30);
        frame.add(selectFolderButton);
        selectFolderButton.setVisible(true);
        
        // Action listener for browse button
        selectFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateFolderButton(frame, folderDisplayField);
                    ContentDisplay.updateDisplayedContent(selectedFolder);
                    handleFolders(action);
                } catch (Exception ex) {
                    System.err.println("Error handling folder selection: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        frame.repaint();
    }
    
    private void handleFolders(String action) {
        switch (action) {
            case "McModsFolderButton":
                ModHotSwap.setCurrentMcModsFolder(selectedFolder);
                break;
            case "NodpackFolder":
                ModHotSwap.setCurrentModpackFolder(selectedFolder);
                break;
        }
    }
    
    private void updateFolderButton(ModHotSwap frame, JTextField folderDisplayField) {
        // Open file chooser dialog and update selected folder
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(frame);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            setSelectedFolder(folderDisplayField, fileChooser.getSelectedFile());
        }
    }
    
    public String getSelectedFolder() {
        return selectedFolder;
    }
    
    public void setSelectedFolder(JTextField folderDisplayField, File selectedFolderFile) {
        // Set selected folder path and update text field
        selectedFolder = selectedFolderFile.getAbsolutePath();
        folderDisplayField.setText(selectedFolder);
    }
}
