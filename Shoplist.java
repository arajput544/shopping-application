package MODEL;



public class Shoplist {
	
	int id;
	String item_name;
	String store_name;
	
	public Shoplist(int id, String item_name, String store_name) {
		this.id = id;
		this.item_name = item_name;
		this.store_name = store_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

}
