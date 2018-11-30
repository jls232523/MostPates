package org.mostpates.system;

import javafx.scene.control.TextField; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

import org.mostpates.people.Customer;
import org.mostpates.shops.Restaurant;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    public Scene homepage;
    public Scene signUp;
    public Scene logIn;
    public Scene restaurant;
    public static PrintWriter userFile;
    public static String path;
    public static Scanner in;
    public static int userCheck;
	Color eggshell = Color.web("0xfffff4");
    public static Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
    public static Alert errorAlert = new Alert(AlertType.WARNING);
    public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		File currentDir = new File("");
		path = currentDir.getAbsolutePath() + "/src/OutputFiles/users.txt";
		userFile = new PrintWriter(new BufferedWriter(new FileWriter(path,true)));
		r = null;
		c1 = new Customer();
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button sign = new Button("Sign Up");
        Button log = new Button("Log In");
        Button back = new Button("Back");
        Button backL = new Button("Back");
        Button backR = new Button("Log Out");
        Button submit = new Button("Submit");
        Button submitLog = new Button("Submit");
        TextField name = new TextField();
        TextField nameL = new TextField();
        TextField addr = new TextField();
        TextField phone = new TextField();
		GraphicsContext gc = setupStage(primaryStage, SIZE_A, SIZE_D,sign,log);

        gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        primaryStage.show();
    		mySystem = new Systems();//make system
		mySystem.buildSystem();
		signUp = buildSignUp(back,name,addr,phone,submit);
		String style = getClass().getResource("HomeButtonStyle.css").toExternalForm();
        signUp.getStylesheets().add(style);
        logIn = buildLogin(backL,nameL,submitLog);
        logIn.getStylesheets().add(style);
		restaurant = buildRestaurantScreen(backR);
		restaurant.getStylesheets().add(style);
		
        back.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        });
        backL.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        });
        backR.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        });
        sign.setOnAction((event) -> {
        	primaryStage.setScene(signUp);
			//Driver2.makeNewCustomer(userFile, c1, in, mySystem); //makes new customer 
        });
        submit.setOnAction((event2)->{
    		if(allFieldsFilled(name,addr,phone)) {
    			Alert errorAlert = new Alert(AlertType.WARNING);
    			errorAlert.setHeaderText("Input not valid");
    			errorAlert.setContentText("Please Fill Out All Fields");
    			errorAlert.showAndWait();
    		}
    		else {
    			Driver.makeNewCustomer(name.getText(), addr.getText(), phone.getText(), userFile, c1, in, mySystem);	
    			confirmAlert.setHeaderText("Welcome To MostPates");
    			confirmAlert.setContentText("User Successfully Created");
    			confirmAlert.showAndWait();
    		}
    	});
        submitLog.setOnAction((event2)->{
    		if(allFieldsFilled(nameL)) {
    			Alert errorAlert = new Alert(AlertType.WARNING);
    			errorAlert.setHeaderText("Input not valid");
    			errorAlert.setContentText("Please Fill Out All Fields");
    			errorAlert.showAndWait();
    			
    		}
    		else {
    			try {
					userCheck = Driver.checkExistingCustomer(nameL.getText().toLowerCase().replaceAll("\\s+",""), c1, userCheck);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
    			if(userCheck==0) { //loop to keep asking for username
    				Alert errorAlert = new Alert(AlertType.WARNING);
        			errorAlert.setHeaderText("User not Found");
        			errorAlert.setContentText("Seems like there is not an account with that name enter another or press n to create an account");
        			errorAlert.showAndWait();
    				try {
						userCheck = Driver.checkExistingCustomer(nameL.getText().toLowerCase().replaceAll("\\s+",""), c1, userCheck);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
    				while(userCheck == 0) {
    				errorAlert.showAndWait();
    				try {
						userCheck = Driver.checkExistingCustomer(nameL.getText().toLowerCase().replaceAll("\\s+",""), c1, userCheck);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
    				}
    				}
    			confirmAlert.setHeaderText("Welcome To MostPates");
    			confirmAlert.setContentText("User Successfully Created");
    			confirmAlert.showAndWait();
    			primaryStage.setScene(restaurant);
    			Collections.sort(Systems.restaurantList);
    			try {
					mySystem.getDistances(c1);
				} catch (IOException e) {
					
				}
    			primaryStage.setScene(restaurant);
    			Collections.sort(Systems.restaurantList);
    			
    		}
    	});
        log.setOnAction((event) -> {
        	primaryStage.setScene(logIn);
        });
	}

	private Scene buildRestaurantScreen(Button backR) {
		StackPane sp = new StackPane();
		GridPane p = new GridPane();
		GridPane p2 = new GridPane();
		p2.setPadding(new Insets(16,0,0,0));
		p2.setAlignment(Pos.TOP_CENTER);
		p.setAlignment(Pos.TOP_LEFT);
        p.setPadding(new Insets(16));
        p.setHgap(3);
        p.setVgap(20);
        p.setGridLinesVisible(false);
        p.setBorder(new Border(
	                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
	                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Canvas canvas = new Canvas(SIZE_A, SIZE_D);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
     	p.add(backR, 0, 0);
        sp.getChildren().add(canvas);
       	sp.getChildren().add(p2);
       	sp.getChildren().add(p);
       	sp.setPadding(new Insets(16));
       	
        int i = 0;
		return (new Scene(sp));
	}
	private Scene buildLogin(Button back, TextField name, Button submitLog) throws FileNotFoundException {
		StackPane sp = new StackPane();
		GridPane p = new GridPane();
		GridPane p2 = new GridPane();
		
		p2.setPadding(new Insets(16,0,0,0));
		p2.setAlignment(Pos.TOP_CENTER);
		 p.setAlignment(Pos.TOP_LEFT);
           p.setPadding(new Insets(16));
           p.setHgap(3);
           p.setVgap(20);
           p.setGridLinesVisible(false);
           p.setBorder(new Border(
	                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
	                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Canvas canvas = new Canvas(SIZE_A, SIZE_D);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Label n = new Label("Name:");
		Label a = new Label("Address:");
		Label ph = new Label("Phone:");
        File currentDir = new File("");
	 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/logo.jpg";
        Image image = new Image(new FileInputStream(path));
        ImageView imageView = new ImageView(image);
        p2.add(imageView, 0, 0);
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        sp.getChildren().add(canvas);
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        p.add(back, 0, 0);
        p.add(name, 21,10,20,1);
        p.add(submitLog,34,15);
        p.add(n,13,10);
        submitLog.setAlignment(Pos.CENTER_RIGHT);
        sp.setPadding(new Insets(16));
        
		return new Scene(sp);
	}

	private Scene buildSignUp(Button back, TextField name, TextField addr, TextField phone,Button submit) throws FileNotFoundException {
		StackPane sp = new StackPane();
		GridPane p = new GridPane();
		GridPane p2 = new GridPane();
		p2.setPadding(new Insets(16,0,0,0));
		p2.setAlignment(Pos.TOP_CENTER);
		 p.setAlignment(Pos.TOP_LEFT);
           p.setPadding(new Insets(16));
           p.setHgap(3);
           p.setVgap(20);
           p.setGridLinesVisible(false);
           p.setBorder(new Border(
	                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
	                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Canvas canvas = new Canvas(SIZE_A, SIZE_D);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Label n = new Label("Name:");
		Label a = new Label("Address:");
		Label ph = new Label("Phone:");
        File currentDir = new File("");
	 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/logo.jpg";
        Image image = new Image(new FileInputStream(path));
        ImageView imageView = new ImageView(image);
        p2.add(imageView, 0, 0);
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        sp.getChildren().add(canvas);
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        p.add(back, 0, 0);
        p.add(name, 21,10,20,1);
        p.add(submit,38,22);
        p.add(addr, 21,15,20,1);
        p.add(phone,21,20,20,1);
        p.add(n,13,10);
        p.add(a, 13,15);
        p.add(ph,13,20);
        submit.setAlignment(Pos.CENTER_RIGHT);

        
        sp.setPadding(new Insets(16));
        
		return new Scene(sp);
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
	        homepage = new Scene(p);   
	        String style = getClass().getResource("HomeButtonStyle.css").toExternalForm();
	        homepage.getStylesheets().add(style);
	        primaryStage.setScene(homepage);
	        return canvas.getGraphicsContext2D();
	    }
	private boolean allFieldsFilled(TextField name, TextField addr, TextField phone) {
		return name.getText().isEmpty() || addr.getText().isEmpty()||phone.getText().isEmpty();
	}
	private boolean allFieldsFilled(TextField nameL) {
		return nameL.getText().isEmpty();
	}
}

