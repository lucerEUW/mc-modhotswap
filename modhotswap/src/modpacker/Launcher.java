package modpacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

import javax.swing.JButton;

public class Launcher {
	
	public Launcher(ModHotSwap frame) {
		// Create "Launch" button
        JButton button = new JButton("Launch");
        button.setBounds(270, 425, 110, 30);
        frame.add(button);
        button.setVisible(true);
        
        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		copyOverModpack();
            		launchMinecraft();
            	} catch (IOException ex) {
            		System.err.println("Error during button action: " + ex.getMessage());
            		ex.printStackTrace();
            	}
            }
        });
        frame.repaint();
	}
	
	private void copyOverModpack() throws IOException {
		Path targetDir = Paths.get(ModHotSwap.getCurrentMcModsFolder());
		Path sourceDir = Paths.get(ModHotSwap.getCurrentModpackFolder());
		
		// Copy modpack files to Minecraft mods folder
		if (Files.exists(targetDir)) {
			deleteDirectoryContents(targetDir);
		}
		
		Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Path targetPath = targetDir.resolve(sourceDir.relativize(dir));
				if (!Files.exists(targetPath)) {
					Files.createDirectory(targetPath);
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, targetDir.resolve(sourceDir.relativize(file)));
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	private static void deleteDirectoryContents(Path directory) throws IOException {
		// Delete all files and directories within a given directory
		try (Stream<Path> entries = Files.walk(directory)) {
			entries.sorted(Comparator.reverseOrder())
			       .forEach(path -> {
			           try {
			               Files.delete(path);
			           } catch (IOException e) {
			               System.err.println("Error occurred while deleting: " + e.getMessage());
			           }
			       });
		}
	}
	
	private static void launchMinecraft() throws IOException {
		// Launch Minecraft executable
		try {
			String minecraftLauncherPath = "C:\\Users\\Anwender\\Twitch\\Minecraft\\Install\\minecraft.exe";
			
			ProcessBuilder processBuilder = new ProcessBuilder(minecraftLauncherPath);
			
			File workingDirectory = new File("C:\\Users\\Anwender\\Twitch\\Minecraft\\Install\\");
			processBuilder.directory(workingDirectory);
			
			Process process = processBuilder.start();
			
			System.out.println("Minecraft started successfully.");
		} catch (IOException e) {
			System.err.println("Error launching Minecraft: " + e.getMessage());
			throw e;
		}
	}
}