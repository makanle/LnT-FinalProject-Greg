package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Database;
import model.Item;

public class DeleteMenu {
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private Item selected;

	public DeleteMenu(Stage stage, Item selected) {
		this.stage = stage;
		this.selected = selected;
		root = new GridPane();
		
		scene = new Scene (root, 400, 300);
		setComponent();
	}
	
	public void setComponent() {
		Label warningLb = new Label("Are you sure?");
		Button yesBt = new Button("Confirm");
		Button cancelBt = new Button("Cancel");
		
		root.add(warningLb, 0, 0, 2, 1);
		root.add(yesBt, 0, 1);
		root.add(cancelBt, 1, 1);
		root.setAlignment(Pos.CENTER);
		root.setVgap(10);
		root.setHgap(40);
		
		yesBt.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String query = "DELETE FROM item WHERE name = ? AND id = ?";
				try (Connection con = Database.getConnection();
						PreparedStatement ps = con.prepareStatement(query)){
					
					ps.setString(1, selected.getName());
					ps.setString(2, selected.getId());
					
					if(ps.executeUpdate() > 0) {
						System.out.println("delete success");
					} else {
						System.out.println("delete fail");
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				stage.close();
			}
			
		});
		
		
		cancelBt.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
			}
			
		});
		
		stage.setScene(scene);
		stage.setTitle("Delete Menu");
		stage.show();
	}
}
