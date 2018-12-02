package org.mostpates.system;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

import org.mostpates.people.Customer;
import org.mostpates.shops.Item;
import org.mostpates.shops.Restaurant;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MostPatesGUI extends Application {
    private static int SIZE_A = 500, SIZE_D =750;
    public static Systems mySystem;
    public static Restaurant r;
    public static Customer c1;
    public Scene homepage;
    public Scene signUp;
    public Scene logIn;
    public Scene menu;
    public Scene restaurant;
    public Scene userCart;
    public static PrintWriter userFile;
    public static String path;
    public static Scanner in;
    public static int userCheck;
    public static ArrayList<Button> menuButtons;
    static ArrayList<TextField> menuItems;
    TableView<Restaurant> table = new TableView<Restaurant>();
	Color eggshell = Color.web("0xfffff4");
    public static Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
    public static Alert errorAlert = new Alert(AlertType.WARNING);
    public WebEngine webEngine;
    public WebView webView;
    public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		File currentDir = new File("");
		path = currentDir.getAbsolutePath() + "/src/OutputFiles/users.txt";
		userFile = new PrintWriter(new BufferedWriter(new FileWriter(path,true)));
		r = null;
		c1 = new Customer();
		menuItems = new ArrayList<TextField>();
		menuButtons = new ArrayList<Button>();
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button sign = new Button("Sign Up");
        Button log = new Button("Log In");
        Button back = new Button("Back");
        Button backL = new Button("Back");
        Button backM = new Button("Back");
        Button backR = new Button("Log Out");
        Button submit = new Button("Submit");
        Button order = new Button("Order");
        Button submitLog = new Button("Submit");
        Button add = new Button("Add Item");
        Button cart = new Button("Cart");
        Button cartR = new Button("Cart");
        Button backC = new Button("Back");
        Button placeOrder = new Button("Place Order");
        Button coupon = new Button("Coupon");
        TextField name = new TextField();
        TextField nameL = new TextField();
        TextField addr = new TextField();
        TextField phone = new TextField();
        TextField addField = new TextField();
		GraphicsContext gc = setupStage(primaryStage, SIZE_A, SIZE_D,sign,log);

        gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        primaryStage.setResizable(false);
        primaryStage.show();     
    		mySystem = new Systems();//make system
		mySystem.buildSystem();
		signUp = buildSignUp(back,name,addr,phone,submit);
		String style = getClass().getResource("HomeButtonStyle.css").toExternalForm();
        signUp.getStylesheets().add(style);
        logIn = buildLogin(backL,nameL,submitLog);
        logIn.getStylesheets().add(style);
        homepage.getStylesheets().add(style);

        back.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        });
        backL.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        });
        backR.setOnAction((event) -> {
        	primaryStage.setScene(homepage);
        	c1 = new Customer();
        	r= new Restaurant();
        });
        backM.setOnAction((event) -> {
        	primaryStage.setScene(restaurant);
        });
        backC.setOnAction((event) -> {
        	primaryStage.setScene(menu);
        });
        sign.setOnAction((event) -> {
        	primaryStage.setScene(signUp);
        });
        coupon.setOnAction((event) -> {
        	TextInputDialog dialog = new TextInputDialog("Coupon");      	 
        	dialog.setTitle("Coupon Code");
        	dialog.setHeaderText("Enter your code");
        	dialog.setContentText("Code:");
        	Optional<String> result = dialog.showAndWait();
        	String code = result.get();
        	Driver.coupon(c1, code,confirmAlert);
        	try {
				userCart = buildCart(placeOrder,backC,r,c1, primaryStage,coupon);
			} catch (IOException e) {
				e.printStackTrace();
			}
            userCart.getStylesheets().add(style);
            	primaryStage.setScene(userCart);  	
        });
        placeOrder.setOnAction((event) -> {
        		ArrayList<Label> label = c1.placeOrder(r);
        		Alert errorAlert = new Alert(AlertType.INFORMATION);
    			errorAlert.setHeaderText("ORDER PLACED");
    			errorAlert.setContentText(label.get(0).getText() +"\n" + label.get(1).getText()+"\n"  + label.get(2).getText()+"\n"  + label.get(3).getText()+"\n"+label.get(4).getText()+"\n"+label.get(5).getText() );
    			errorAlert.showAndWait();
    			c1.getCart().eraseCart();
    			primaryStage.setScene(homepage);
        		
        });
        cart.setOnAction((event)->{
        	try {
				userCart = buildCart(placeOrder,backC,r,c1, primaryStage,coupon);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        	userCart.getStylesheets().add(style);
        	primaryStage.setScene(userCart);
        
        });
        cartR.setOnAction((event)->{
        	try {
        	if(Driver.checkCart(c1, r)) {
        	userCart = buildCart(placeOrder,backC,r,c1, primaryStage,coupon);
        userCart.getStylesheets().add(style);
        	primaryStage.setScene(userCart);
        	}
        	else {
        		Alert errorAlert = new Alert(AlertType.INFORMATION);
    			errorAlert.setHeaderText("Cart will be emptied");
    			errorAlert.setContentText("Your cart has been emptied");
    			errorAlert.showAndWait();
        	}
        	}
        	catch(Exception e){
        		Alert errorAlert = new Alert(AlertType.WARNING);
    			errorAlert.setHeaderText("Empty Cart");
    			errorAlert.setContentText("Your cart is empty. Please add an item to view.");
    			errorAlert.showAndWait();
        	}     	
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
        			errorAlert.setContentText("Seems like there is not an account with that name. Please enter another");
        			errorAlert.show();
    				}
    			else {
    			confirmAlert.setHeaderText("Welcome To MostPates");
    			confirmAlert.setContentText("Login Successful. Welcome Back!");
    			confirmAlert.showAndWait();
    			primaryStage.setScene(restaurant);
    			Collections.sort(Systems.restaurantList);
    			try {
					mySystem.getDistances(c1);
				} catch (IOException e) {
					
				}
    			restaurant = buildRestaurantScreen(backR,cartR);
    			restaurant.getStylesheets().add(style);
    			primaryStage.setScene(restaurant);
    			Collections.sort(Systems.restaurantList);
    			}
    		}
           	table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
           	    if (newSelection != null) {
           	       r = newSelection;
           	       try {
					menu = buildMenu(r,backM,order,add,addField,cart);
					menu.getStylesheets().add(style);
				} catch (FileNotFoundException e) {
					errorAlert.setHeaderText("Restaurant Unavailable");
					errorAlert.setContentText("Sorry this Restaurant is not in service right now");
	    				errorAlert.showAndWait();
				}
           	       primaryStage.setScene(menu);
           	    }
           	});
    	});
        log.setOnAction((event) -> {
        	primaryStage.setScene(logIn);
        });
	}

	private Scene buildCart(Button placeOrder, Button backC, Restaurant r, Customer c,Stage primaryStage, Button coupon) throws IOException {
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
       
        sp.getChildren().add(canvas);
       
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        p.add(backC, 0, 0,1,2);
        p.add(placeOrder, 0, 3);
        Label rst = new Label(r.getName());
        int i = 0;
        int j = 4;
        p2.getChildren().add(rst);
        rst.setAlignment(Pos.TOP_CENTER);
        for(Item i1 : c.cart.getItems()) {
        	Label item = new Label (i1.getName());
        	Label price = new Label ("		$" + i1.getPrice());
        	Button temp = new Button("-");
        	temp.setId("delete");
    		temp.setOnAction((event) -> { 
    			String name = i1.getName();
    			Driver.remove(c, i1, r,confirmAlert);
    			try {
					userCart = buildCart(placeOrder,backC,r,c1, primaryStage,coupon);
					String style = getClass().getResource("HomeButtonStyle.css").toExternalForm();
					userCart.getStylesheets().add(style);
					primaryStage.setScene(userCart);	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		});
    		
        	p.add(item, i, j);
        	p.add(price, i+4, j);
        	p.add(temp, i+5, j);
        	j++;
        }
        ArrayList<Label> totals = c.order(r);
        for(int q = 0; q<totals.size();q++) {
        	totals.get(q).setId("money");
        	if(q==2) {
        		
        		p.add(totals.get(q), 0, j+1);
        	}
        	j++;
        	if(q==3) {
        		p.add(totals.get(q), 0, j,4,1);
        	}
        	if(q==4) {
        		p.add(totals.get(q), 0, j);
        	}
         	if(q==5) {
        		p.add(totals.get(q), 0, j);
        	}
        }
        URL direct;
		URLConnection direcConnect;
        String url =("https://maps.googleapis.com/maps/api/staticmap?&size=300x200&maptype=roadmap&markers=color:blue%7Blabel:C%7C"+r.getAddress().replaceAll("\\s+", "")+"&markers=color:red%7Alabel:C%7C"+c1.getAddress().replaceAll("\\s+", "") + "&path=color:0x0000ff|weight:5|"+c1.getAddress().replaceAll("\\s+", "")+"|"+r.getAddress().replaceAll("\\s+", "")+"+&key=AIzaSyBl49PQg0nL_4KAEjWXMB1hFT0xqjdTjco");
        	Image mapp = new Image(url);
        	ImageView mappp = new ImageView(mapp);
        p.add(mappp,0,j+2,10,10);    
        sp.setPadding(new Insets(16));
        p.add(coupon, 0, 2);
		return new Scene(sp);
	}

	private Scene buildMenu(Restaurant r2, Button backM, Button order, Button add, TextField addField, Button cart) throws FileNotFoundException {
		StackPane sp = new StackPane();
		GridPane p = new GridPane();
		GridPane p2 = new GridPane();
		p2.setPadding(new Insets(16,0,0,0));
		p2.setAlignment(Pos.TOP_CENTER);
		 p.setAlignment(Pos.TOP_LEFT);
           p.setPadding(new Insets(16));
           p.setHgap(5);
           p.setVgap(2);
           p.setGridLinesVisible(false);
           p.setBorder(new Border(
	                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
	                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Canvas canvas = new Canvas(SIZE_A, SIZE_D);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        File currentDir = new File("");
        int count = 0;
        int xPos = 2;
        int yPos = 100;
        Label t3 = null;
        int j = 0;
        p.add(backM, 0, 0,1,2);
        for (Item i : r2.getMenu()) {
        		if(count % 2 == 0) {
        			xPos = 2;
        			yPos += 4;  			
        		}
        		if((count+1)%2==0) {
        			
        			
        			Label t = new Label(" " + i.getName());
        			if(String.valueOf(i.getPrice()).endsWith("0")) {
        			t3 = new Label(" $"+i.getPrice() + "0");
        			}
        			else {
        				t3 = new Label(" $"+i.getPrice());
        			}
            		t.setId("item");
            		t3.setId("price");
            		t.setAlignment(Pos.CENTER);
            		p.add(t, xPos, yPos,1,1);         		
            		p.add(t3, xPos, yPos+1);
            		Button temp = new Button("+");
            		temp.setOnAction((event) -> { 
            			if(Driver.checkCart(c1, r2)) {
            			Driver.addItem(i, r2, c1,confirmAlert);
            			}
            			else {
            				Alert errorAlert = new Alert(AlertType.WARNING);
                			errorAlert.setHeaderText("Cart Emptied"); 
                			errorAlert.setContentText("Your cart has been emptied.");
                			errorAlert.showAndWait();
            			}
                    });
            		temp.setMaxSize(5, 5);
            		p.add(temp,xPos-1, yPos,1,2
            				);
            		TextField t2 = new TextField();
            		t2.setId(i.getName());
            		menuItems.add(t2);
            		xPos += 1;
            		count+=1;
            		menuButtons.add(temp);
        		}
        		else {
        			Label t = new Label(i.getName());
        			if(String.valueOf(i.getPrice()).endsWith("0")) {
            			t3 = new Label(" $"+i.getPrice() + "0");
            			}
            			else {
            				t3 = new Label(" $"+i.getPrice());
            			}

        			j+=1;
        			t3.setId("price");
            		t.setId("item");
            		t.setAlignment(Pos.CENTER);
            		p.add(t, xPos, yPos,1,1);
            		p.add(t3, xPos, yPos+1);
            		Button temp = new Button("+");
            		temp.setOnAction((event) -> { 
            			if(Driver.checkCart(c1, r2)) {
            			Driver.addItem(i, r2, c1,confirmAlert);
            			}
            			else {
            				Alert errorAlert = new Alert(AlertType.WARNING);
                			errorAlert.setHeaderText("Cart Emptied"); 
                			errorAlert.setContentText("Your cart has been emptied.");
                			errorAlert.showAndWait();
                			c1.getCart().eraseCart();
                			Driver.addItem(i, r2, c1,confirmAlert);
            			}
                    });
            		p.add(temp, 1, yPos,1,2);
            		TextField t2 = new TextField();
            		t2.setId(i.getName());
            		menuItems.add(t2);
            		xPos += 4;
            		count+=1;
            		menuButtons.add(temp);
        		}
        		

        }
	
      
        p2.add(r2.getImage(), 0, 0);
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        sp.getChildren().add(canvas);
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        
        p.add(cart, 0, 2);
        sp.setPadding(new Insets(16));
        
		return new Scene(sp);
	}
	private Scene buildRestaurantScreen(Button backR, Button cartR) {
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
       	
        ObservableList<Restaurant> restList = FXCollections.observableArrayList(Systems.restaurantList);
        table.setItems(restList);
        TableColumn<Restaurant,String> firstNameCol = new TableColumn<Restaurant, String>("Restaurant");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("name"));
        TableColumn<Restaurant, String> distanceCol = new TableColumn<Restaurant,String>("Distance");
        distanceCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("distance"));
        table.getColumns().setAll(firstNameCol, distanceCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       	p.add(table,8,8);
       	table.maxWidth(3000);
       	table.setMaxHeight(500);
       	table.setEditable(false);
       	p.add(cartR, 0, 1);
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
        File currentDir = new File("");
        name.setPromptText("Name");
        name.setAlignment(Pos.CENTER);
        
	 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/logoman.png";
        Image image = new Image(new FileInputStream(path));
        ImageView imageView = new ImageView(image);
        p2.add(imageView, 0, 0);
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        sp.getChildren().add(canvas);
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        p.add(back, 0, 0);
        p.add(name, 34,17,20,1);
        p.add(submitLog,51,22);
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
		
        File currentDir = new File("");
	 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/logoman.png";
        Image image = new Image(new FileInputStream(path));
        ImageView imageView = new ImageView(image);
        p2.add(imageView, 0, 0);
		gc.setFill(eggshell);
        gc.fillRect(0, 0, SIZE_A, SIZE_D);
        sp.getChildren().add(canvas);
        sp.getChildren().add(p2);
        sp.getChildren().add(p);
        p.add(back, 0, 0);
        p.add(name, 34,10,20,1);
        p.add(submit,51,22);
        p.add(addr, 34,15,20,1);
        p.add(phone,34,20,20,1);
        submit.setMaxSize(100,100);
        name.setPromptText("Name");
        name.setAlignment(Pos.CENTER);
        addr.setPromptText("Address");
        addr.setAlignment(Pos.CENTER);
        phone.setPromptText("Phone");
        phone.setAlignment(Pos.CENTER);
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
		 	String path = currentDir.getAbsolutePath() + "/src/InputFiles/logo.png";
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

