package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Database;
import model.Item;

public class ViewMenu {
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private ScrollPane sp;
	
	public ViewMenu(Stage stage) {
		this.stage = stage;
		root = new GridPane();
		
		sp = new ScrollPane();
		sp.setPrefHeight(500);
		sp.setPrefWidth(400);
		sp.setFitToWidth(true);
		
		scene = new Scene(root, 700, 500);
		stage.setTitle("Item Viewer");
		setComponent();
	}
	
	public void setComponent() {
		Label headLb = new Label("View Menu");
		
		Button addBtn = new Button("Add Item");
		Button editBtn = new Button("Edit Item");
		Button delBtn = new Button("Delete Item");
		Button refreshBtn = new Button("Refresh View");
		
		TableView<Item> itemtable = new TableView<>();
		TableColumn<Item, String> nameCol = new TableColumn<Item, String>("Name");
		TableColumn<Item, String> idCol = new TableColumn<Item, String>("id");
		TableColumn<Item, Integer> priceCol = new TableColumn<Item, Integer>("Price");
		TableColumn<Item, Integer> stockCol = new TableColumn<Item, Integer>("Stock");
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
//		nameCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
//		idCol.setPrefWidth(8);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
//		priceCol.setPrefWidth(10);
		stockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
//		stockCol.setPrefWidth(10);
		itemtable.getColumns().addAll(nameCol, idCol, priceCol, stockCol);
		
		ArrayList<Item> itemlist = getItems();
		// for(int i=0 ; i<itemlist.size() ; i++) {
		// 	System.out.println(itemlist.get(i).getName());
		// 	System.out.println(itemlist.get(i).getId());
		// 	System.out.println(itemlist.get(i).getPrice());
		// 	System.out.println(itemlist.get(i).getStock());
		// }
		
		itemtable.setItems(FXCollections.observableArrayList(itemlist));
		
		headLb.setStyle("-fx-font-size: 33; -fx-font-family: 'Times New Roman';");
		itemtable.setPrefHeight(500);
		sp.setContent(itemtable);
		root.add(headLb, 0, 0);
		root.add(addBtn, 0, 1);
		root.add(editBtn, 0, 2);
		root.add(delBtn, 0, 3);
		root.add(sp, 1, 1, 1, 4);
		root.add(refreshBtn, 0, 4);
		
		root.setAlignment(Pos.CENTER);
		root.setVgap(10);
		root.setHgap(40);
		
		addBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Stage newstage = new Stage();
				new AddMenu(newstage);
				ArrayList<Item> itemlist = getItems();
				itemtable.setItems(FXCollections.observableArrayList(itemlist));
			}
		});
		
		editBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Item selected = itemtable.getSelectionModel().getSelectedItem();
				if(selected != null) {
					Stage newstage = new Stage();
					new EditMenu(newstage, selected);
				} else {
					System.out.println("no item selected");
				}
//				ArrayList<Item> itemlist = getItems();
//				itemtable.setItems(FXCollections.observableArrayList(itemlist));
			}
			
		});
		
		delBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Item selected = itemtable.getSelectionModel().getSelectedItem();
				if(selected != null) {
					Stage newstage = new Stage();
					new DeleteMenu(newstage, selected);
				} else {
					System.out.println("no item selected");
				}
//				ArrayList<Item> itemlist = getItems();
//				itemtable.setItems(FXCollections.observableArrayList(itemlist));
			}
		});
		refreshBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<Item>itemlist = getItems();
				itemtable.setItems(FXCollections.observableArrayList(itemlist));
			}
			
		});
		
		stage.setScene(scene);
	}
	
	
	
	
	//GANTI JADI ARRAYLIST COBA
	
	public static ArrayList<Item> getItems(){
		ArrayList<Item> itemlist = new ArrayList<>();
		
		String query = "SELECT * FROM item";
		
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				Item theitem = new Item(name, id, price, stock);
				itemlist.add(theitem);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return itemlist;
	}
	
}
