package hu.ppke.itk.java2018.jozgy.hf04;

import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ClientFrontEnd extends Application {
	
	static private Command commandMessage;
	
	private TextField playerName = new TextField();
	private TextField chatMessage = new TextField();
	
	private class eventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			try {
				new Server(commandMessage, 80);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				new Client ("127.0.0.1", 35556, commandMessage);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FlowPane chatLayout = new FlowPane();
			chatLayout.setHgap(10);
			chatLayout.setVgap(10);
			
			TextArea chatText = new TextArea();
			chatText.setEditable(false);
			chatLayout.getChildren().add(chatText);
			
			Button sendButton = new Button("Send");
			chatLayout.getChildren().addAll(chatMessage, sendButton);
			
			Stage chatWindow = new Stage();
			chatWindow.setTitle("Chat");
			chatWindow.setScene(new Scene(chatLayout, 800, 600));
			chatWindow.show();
			
			commandMessage.engineWrite(new Command.Package("setPlayerName " + playerName));
		}
		
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {

	    FlowPane root = new FlowPane();
	    root.setHgap(10);
	    root.setVgap(10);

	    root.getChildren().add(new Label("Playername:"));
	    Button startButton = new Button("Start");

	    startButton.setOnMousePressed(new eventHandler());
	    
	    root.getChildren().addAll(playerName, startButton);
	    
	   
	    Scene scene = new Scene(root, 350, 200);
	    primaryStage.setTitle("BattleShip");
	    primaryStage.setScene(scene);
	    primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
