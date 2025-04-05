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

public class AddMenu {
	private Stage stage;
	private Scene scene;
	private GridPane root;
	
	public AddMenu(Stage stage) {
		this.stage = stage;
		root = new GridPane();
		
		scene = new Scene(root, 400, 300);
		setcomponent();
	}
	
	public void setcomponent() {
		Label headlb = new Label("Add New Item");
		Label namelb = new Label("Name");
		Label pricelb = new Label("Price");
		Label stocklb = new Label("Stock");
		
		TextField nameTa = new TextField();
		TextField priceTa = new TextField();
		TextField stockTa = new TextField();

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
				String id = idgen();
				String name = nameTa.getText();
				int price = Integer.parseInt(priceTa.getText());
				int stock = Integer.parseInt(stockTa.getText());
				String query = "INSERT INTO item(id, name, price, stock) VALUES(?, ?, ?, ?)";
				
				try(Connection con = Database.getConnection();
						PreparedStatement ps = con.prepareStatement(query)) {
					ps.setString(1, id);
					ps.setString(2, name);
					ps.setInt(3, price);
					ps.setInt(4, stock);
					
					if(ps.executeUpdate() > 0) {
						System.out.println("add success");
					} else {
						System.out.println("add failed");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
		stage.setTitle("Add Menu");
		stage.show();
	}
	
	public String idgen() {
		StringBuilder id = new StringBuilder();
		String num = "0123456789";
		id.append("PD-");
		
		for(int i=0 ; i<3 ; i++){
			id.append(num.charAt((int) (Math.random() * 10)));
		}
		
		return id.toString();
	}
}
