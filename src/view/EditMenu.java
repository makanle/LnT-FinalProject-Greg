package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Database;
import model.Item;

public class EditMenu {
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private Item selected;
	Database db = new Database();

	public EditMenu(Stage stage, Item selected) {
		this.stage = stage;
		this.selected = selected;
		root = new GridPane();
		
		scene = new Scene (root, 400, 300);
		setComponent();
	}
	
	public void setComponent() {
		Label headlb = new Label("Edit Item");
		Label namelb = new Label("Name");
		Label pricelb = new Label("Price");
		Label stocklb = new Label("Stock");
		
		TextField nameTa = new TextField();
		TextField priceTa = new TextField();
		TextField stockTa = new TextField();
		
		nameTa.setText(selected.getName());
		nameTa.setEditable(false);
		priceTa.setText(Integer.toString(selected.getPrice()));
		stockTa.setText(Integer.toString(selected.getStock()));
		
		Button submitBt = new Button("Submit");
		Button cancelBt = new Button("Cancel");
		
		root.add(headlb, 0, 0, 2, 1);
		root.add(namelb, 0, 1);
		root.add(nameTa, 1, 1);
		root.add(pricelb, 0, 2);
		root.add(priceTa, 1, 2);
		root.add(stocklb, 0, 3);
		root.add(stockTa, 1, 3);
		root.add(cancelBt, 0, 4);
		root.add(submitBt, 1, 4);
		
		headlb.setStyle("-fx-font-size: 24; -fx-font-family: 'Times New Roman';");
		
		root.setAlignment(Pos.CENTER);
		root.setVgap(10);
		root.setHgap(40);
		
		submitBt.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String name = selected.getName();
				String id = selected.getId();
				int price = Integer.parseInt(priceTa.getText());
				int stock = Integer.parseInt(stockTa.getText());
				String query = "UPDATE item SET price = ?, stock = ? WHERE name = ? AND id = ?";
				
				try(Connection con = Database.getConnection();
						PreparedStatement ps = con.prepareStatement(query)) {
					ps.setInt(1, price);
					ps.setInt(2, stock);
					ps.setString(3, name);
					ps.setString(4, id);
					
					if(ps.executeUpdate() > 0) {
						System.out.println("update success");
					} else {
						System.out.println("update fail");
					}
					
				}catch(SQLException e){
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
		stage.setTitle("Edit Menu");
		stage.show();
		
	}
	
}
