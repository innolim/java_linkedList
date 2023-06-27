
package linkedlists;

/**
 * Sunlim Lee
 * COP 2805 - HW 5-1
 * Sunlim lee
 * 03/13/2023
 * This program lets the user enter a string of words and displays them in a text
 * area. 
 */

import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LinkedLists extends Application {
    private Label label = new Label("Enter a string: "); // Create a new Label
    private TextField textField = new TextField(); // Create a new TextField
    private List<String> words = new LinkedList<>(); // Create a linkedlist
    private LinkedHashSet<String> newSet = new LinkedHashSet(); // Create a linkedHashSet
    private TextArea textArea = new TextArea(); // Create a new TextArea
    
    @Override
    public void start(Stage primaryStage) {
  
        HBox hb = new HBox(); // Create a new HBox
        hb.setSpacing(10); // Set a spacing for HBox
        // Create buttons for sort, shuffle, and reverse
        Button sortButton = new Button("Sort"); 
        Button shuffleButton = new Button("Shuffle");
        Button reverseButton = new Button("Reverse");
        // Add buttons to the HBox
        hb.getChildren().addAll(sortButton, shuffleButton, reverseButton);
        hb.setAlignment(Pos.CENTER); // Set alignment
        /* Create event handler for pressing enter after putting words in text field
        Upon pressing enter key, words are splited and stored in a list
        */
        textField.setOnAction(e-> add()); 
        // Create event handler for clicking sort button
        sortButton.setOnAction(e-> {Collections.sort(words);
            displayText(); // display sorted words in natural order  in text area
        });
        // Create event handler for clicking shuffle button
        shuffleButton.setOnAction(e-> {Collections.shuffle(words);
        System.out.println(words);
            displayText(); // display shuffled words (in randomly order)
        });
        // Create event handler for clicking reverse button
        reverseButton.setOnAction(e-> {Collections.sort(words, Collections.reverseOrder());
            System.out.println(words);
            displayText(); // display words in reversed order
        });
        
        GridPane gdPane = new GridPane(); // Create a new GridPane
        gdPane.setAlignment(Pos.CENTER); // Set the value of property alignment
        
        gdPane.add(label, 0, 0); // Put label in the first column
        gdPane.add(textField, 1, 0); // Put textField in the second column
        
        textArea.setWrapText(true); // Text will wrap on the another line
        
        ScrollPane scrollpane = new ScrollPane(); // Create a new scorllpane
        scrollpane.setContent(textArea); // Put textArea in the scrollpane
        scrollpane.setHbarPolicy(ScrollBarPolicy.ALWAYS); // display horizontal scroll bar
        scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS); // display vertical scroll bar
        
        BorderPane root = new BorderPane(); // Create a new borderpane
        root.setTop(gdPane); // Put gridpane on the top section of borderpane
        root.setBottom(hb); // Put HBox on the bottom of borderpane
        root.setCenter(scrollpane); // Put scrollpane in the center of borderpane
        Scene scene = new Scene(root, 300, 250); // Create a new scene 
        primaryStage.setTitle("Homework 5_1"); // Set the title of stage
        primaryStage.setScene(scene); // Put a secne in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
// Define add function 
private void add(){
    String text1 = textField.getText(); // Create a variable to store contents from a textField
    // Split the string by whitespace and store them in an array named splitText
    String[] splitText = text1.split(" ");  // Remove whitespace and convert string to array
    // Get sorted array by callling function removeDuplicate()
    String[] newArray = removeDuplicate(splitText); 
    words = Arrays.asList(newArray); // Convert the array to the LinkedList
    displayText(); // Call displayText function to display the list 
}
// Define displayText function to display elements of list in the textArea
private void displayText(){
    // Create a variable to store elements from list, and initialize to empty string
    String text2 = ""; 
    for(String values : words) // Use enhanced for-loop to traverse the list elements
       text2 += values + " "; 
    textArea.setText(text2); // Display the elements in the textArea
    textField.setText(""); // Clear previous elements in textField
}
/* Define removeDuplicate function to remove any duplicates from the array
Create a linkedHashSet to remove duplicates, then convert it to a new string array
*/
private String[] removeDuplicate(String[] array){
    LinkedHashSet<String> newSet = new LinkedHashSet<String>(Arrays.asList(array));
    String[] newArray = newSet.toArray(new String[newSet.size()]);
    return newArray;
}
}