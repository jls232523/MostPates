package org.mostpates.system;

import java.awt.TextField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.mostpates.people.Customer;
import org.mostpates.shops.Restaurant;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MostPatesGUI extends Application {
    private static int SIZE_A = 500, SIZE_D = 750;
    public static Systems mySystem;
    public static Restaurant r;
    public static Customer c1;
    public static void main(String[] args) throws IOException {
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button sign = new Button("SIGN UP");
        Button log = new Button("LOG IN");
		GraphicsContext gc = setupStage(primaryStage, SIZE_A, SIZE_D,sign,log);
        gc.setFill(Color.PEACHPUFF);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        primaryStage.show();
    	mySystem = new Systems();//make system
		mySystem.buildSystem();
		Scanner in = null;
		in = new Scanner(System.in);
		File currentDir = new File("");
		String path = currentDir.getAbsolutePath() + "/src/OutputFiles/users.txt";
		PrintWriter userFile = new PrintWriter(new BufferedWriter(new FileWriter(path,true)));
		Restaurant r = null;
		Customer c1 = new Customer();
        sign.setOnAction((event) -> {
        	primaryStage.hide();
			//Driver2.makeNewCustomer(userFile, c1, in, mySystem); //makes new customer 


        });
        log.setOnAction((event) -> {
           
        });
	}
	public GraphicsContext setupStage(Stage primaryStage, int canvas_width,int canvas_height,Button sign,Button log) throws FileNotFoundException {
			GridPane p = new GridPane();
			 p.setAlignment(Pos.CENTER);
	            p.setPadding(new Insets(16));
	            p.setHgap(16);
	            p.setVgap(8);
	            p.setGridLinesVisible(true);

	        Canvas canvas = new Canvas(SIZE_A, SIZE_D);
	        p.setBorder(new Border(
	                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
	                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	        final int num_items = 8;
	        VBox input_box = new VBox(num_items);
	        File currentDir = new File("");
		 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/homeImage.png";
	        Image image = new Image(new FileInputStream(path));
	        ImageView imageView = new ImageView(image);
			Label t1 = new Label();
			Label t2 = new Label();
			Label t3 = new Label();
			Label t4 = new Label();
			Label t5 = new Label();
			Label t6 = new Label();
	        input_box.setSpacing(30);	
	        input_box.getChildren().add(imageView);
	        input_box.getChildren().add(t2);
	        input_box.getChildren().add(t3);
	        input_box.getChildren().add(t5);
	        input_box.getChildren().add(t6);
	        input_box.getChildren().add(sign);
	        input_box.getChildren().add(log);
	        sign.setMaxSize(100, 100);
	        log.setMaxSize(100, 100);      
	        sign.setShape( new Rectangle(100, 100, 100,100));
	        log.setShape( new Rectangle(100, 100, 100,100));
	        input_box.setAlignment(Pos.CENTER);
			
	        p.getChildren().add(canvas);
	        p.getChildren().add(input_box);
	     

	        primaryStage.setTitle("MostPates");
	        Scene t = new Scene(p);   
	        String style = getClass().getResource("HomeButtonStyle.css").toExternalForm();
	        t.getStylesheets().add(style);
	        primaryStage.setScene(t);
	        return canvas.getGraphicsContext2D();
	    }

}

