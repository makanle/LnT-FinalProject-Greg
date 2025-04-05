package model;

public class Item {
	private int price;
	private int stock;
	private String id;
	private String name;
	
	public Item(String name, String id, int price, int stock) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
