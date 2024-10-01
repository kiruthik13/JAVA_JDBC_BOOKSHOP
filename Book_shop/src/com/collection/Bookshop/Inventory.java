package com.collection.Bookshop;
import com.kiruthik.collection.Bookshop.dbconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    List<Book> list = new ArrayList<>();
    Connection con;
    dbconnection db;
    Statement stmt;
    public Inventory() 
    {
        db = new dbconnection();
    }
    public void add(Book a) 
    {
        a.setId(generateId(a));
        String qry = null;
        int count;
        try {
            con = db.getDBConnection();
            stmt = con.createStatement();
            qry = "INSERT INTO book(Name, Edition, ISBN, Price) VALUES('" + a.getName() + "', '" + a.getEdition() + "', '" + a.getIsbn() + "', " + a.getPrice() + ")";
            count = stmt.executeUpdate(qry);
            if (count == 1) 
            {
                System.out.println("1 row is inserted");
                list.add(a);
            } 
            else
            {
                throw new Exception("No row is inserted");
            }
        } catch (Exception e) 
        {
            System.out.println(e);
        } finally
        {
            closeResources();
        }
    }
    public void update(Book a) 
    {
        String qry = null;
        int count;
        try 
        {
            con = db.getDBConnection();
            stmt = con.createStatement();
            qry = "UPDATE book SET Name='" + a.getName() + "', Edition='" + a.getEdition() + "', ISBN='" + a.getIsbn() + "', Price=" + a.getPrice() + " WHERE ID='" + a.getId() + "'";

            count = stmt.executeUpdate(qry);

            if (count == 1) {

                System.out.println("1 row is updated");

                // Optionally update local list if necessary

            } else {

                throw new Exception("No row is updated");

            }

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            closeResources();

        }

    }



    public void remove(String id) {

        String qry = null;

        int count;

        try {

            con = db.getDBConnection();

            stmt = con.createStatement();

            qry = "DELETE FROM book WHERE ID='" + id + "'";

            count = stmt.executeUpdate(qry);

            if (count == 1) {

                System.out.println("Successfully removed");

                list.removeIf(book -> book.getId().equals(id));

            } else {

                System.out.println("ID does not match to remove");

            }

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            closeResources();

        }

    }



    public Book search(String id) {

        for (Book book : list) {

            if (id.equals(book.getId())) {

                return book;

            }

        }



        try {

            con = db.getDBConnection();

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE ID='" + id + "'");

            if (rs.next()) {

                Book book = new Book();

                book.setId(id);

                book.setName(rs.getString("Name"));

                book.setEdition(rs.getString("Edition"));

                book.setIsbn(rs.getString("ISBN"));

                book.SetPrice(rs.getInt("Price"));

                return book;

            }

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            closeResources();

        }

        return null; 
    }



    public void show() {

        ResultSet rs = null;

        try {

            con = db.getDBConnection();

            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM book");

            while (rs.next()) {

                System.out.println("ID: " + rs.getString("ID") + ", Name: " + rs.getString("Name") + ", Edition: " + rs.getString("Edition") + ", ISBN: " + rs.getString("ISBN") + ", Price: " + rs.getInt("Price"));

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            closeResources();

        }

    }



    public String generateId(Book obj) {

        String n = obj.getName().substring(0, 2);

        return n.toUpperCase() + "@" + obj.getIsbn();

    }



    private void closeResources() {

        try {

            if (stmt != null) stmt.close();

            if (con != null) con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

