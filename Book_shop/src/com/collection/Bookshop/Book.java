package com.collection.Bookshop;
public class Book {
	private String name;
	private String edition;
	private String isbn;
	private String id;
	private int price;
	public Book()
	{
		this.name=null;
		this.edition=null;
		this.isbn=null;
		this.id=null;
		this.price=0;
		
	}
    public String getName()
    {
    	return name;
    }
    public void setName(String name)
    {
    	this.name=name;
    }
    public String getEdition() {
    	return edition;
    }
    public void setEdition(String edition)
    {
    	this.edition=edition;
    }
    public String getId() {
		return id;
	}
     
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setId(String id) {
		this.id = id;
	}
    public int getPrice() {
    	return price;
    }
    public void SetPrice(int price)
    {
    	this.price=price;
    }
	@Override
	public String toString() {
		return "Book [name=" + name + ", edition=" + edition + ", isbn=" + isbn + ", id=" + id + ", price=" + price
				+ "]";
	}
}
