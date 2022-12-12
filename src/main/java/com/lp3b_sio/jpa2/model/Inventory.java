package com.lp3b_sio.jpa2.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "inventories")
@NamedQueries({
	@NamedQuery(name="Inventory.getAllInventories", query="select iv from Inventory iv inner join fetch iv.product"),
	@NamedQuery(name="Inventory.getInventoryByProducts", query="select iv "
															+ "	from Inventory iv JOIN iv.product p "
															+ " where iv.inventoryID = :id")
})
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "inventory_id", length = 6)
	private Long inventoryID;
	
	/*@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="inventory_product"
				, joinColumns=@JoinColumn(name="inventory_id")
				, inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> products; */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "inventory_sku", length = 10)
	private String inventorySKU;
	
	@Column(name = "item_name", length = 30)
	private String itemName;
	
	@Column(name = "group_name", length = 30)
	private String inventoryGroupName;
	
	@Column(name = "stock_min", length = 4)
	private int stockMin;
	
	@Column(name = "stock_max", length = 4)
	private int stockMax;
	
	@Column(name = "stock_available", length = 4)
	private int stockAvailable;
	
	@Column(name = "item_bar_code", length = 100)
	private String itemBarCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	public Long getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(Long inventoryID) {
		this.inventoryID = inventoryID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getInventorySKU() {
		return inventorySKU;
	}

	public void setInventorySKU(String inventorySKU) {
		this.inventorySKU = inventorySKU;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getInventoryGroupName() {
		return inventoryGroupName;
	}

	public void setInventoryGroupName(String inventoryGroupName) {
		this.inventoryGroupName = inventoryGroupName;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public String getItemBarCode() {
		return itemBarCode;
	}

	public void setItemBarCode(String itemBarCode) {
		this.itemBarCode = itemBarCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/**
	 * Change Modified Date
	 */
	public void defineDateCreatedUpdated() {
		this.updatedAt = new Date();
		
		if (this.createdAt == null) {
			this.createdAt = new Date();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventoryID == null) ? 0 : inventoryID.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inventoryID == null) {
			if (other.inventoryID != null)
				return false;
		} else if (!inventoryID.equals(other.inventoryID))
			return false;
		return true;
	}
}
