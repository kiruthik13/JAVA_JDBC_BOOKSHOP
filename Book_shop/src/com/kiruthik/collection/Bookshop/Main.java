package com.kiruthik.collection.Bookshop;
import com.collection.Bookshop.Book;
import com.collection.Bookshop.Inventory;
import com.collection.Bookshop.invalidpassexception;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main 
{
    static List<Login> logins = new ArrayList<>();
    public static void main(String[] args) {
        Login l;
        Inventory inv = new Inventory();
        Scanner sc = new Scanner(System.in);
        int opt;
        do {
            System.out.print("BOOK SHOP\n");
            System.out.print("1. Login 2. Signup 3. Exit: ");
            opt = sc.nextInt();
            switch (opt) {

                case 1: {

                    System.out.print("Enter your Username: ");

                    l = checkrole(sc.next());

                    if (l != null) {

                        if (l.getRole().equalsIgnoreCase("Admin")) {

                            ainventory(inv);

                        } else {

                            cinventory(inv);

                        }

                    } else {

                        System.out.println("You have no account");

                    }

                    break;

                }

                case 2: {

                    System.out.print("Enter your Username: ");

                    String name = sc.next();

                    if (logins.stream().anyMatch(login -> login.getUname().equals(name))) {

                        System.out.println("You already have an account");

                    } else {

                        l = new Login();

                        l.setUname(name);

                        System.out.print("Enter your password: ");

                        l.setpassword(sc.next());

                        System.out.print("Enter your role: ");

                        l.setRole(sc.next());

                        logins.add(l);

                    }

                    break;

                }

                default:

                    if (opt != 3) {

                        System.out.println("Invalid option");

                    }

            }

        } while (opt != 3);

    }



    public static Login checkrole(String name) {

        Scanner sc = new Scanner(System.in);

        Login l = null;

        if (logins.isEmpty()) {

            return null;

        } else {

            for (Login login : logins) {

                if (name.equals(login.getUname())) {

                    l = login;

                    break;

                }

            }

            if (l != null) {

                do {

                    System.out.print("\nEnter the password correctly: ");

                    String pwd = sc.next();

                    try {

                        if (pwd.equals(l.getPassword())) {

                            return l;

                        } else {

                            throw new invalidpassexception("Your password is incorrect. Please try again.");

                        }

                    } catch (invalidpassexception e) {

                        System.out.print(e);

                    } finally {

                        System.out.print("\nAttempt completed.");

                    }

                } while (true);

            } else {

                return null;

            }

        }

    }



    public static void ainventory(Inventory inv) {

        Book b;

        Scanner sc = new Scanner(System.in);

        int op;

        do {

            System.out.print("\n1. Add 2. Update 3. Remove 4. Search 5. Show 6. Logout: ");

            op = sc.nextInt();

            switch (op) {

                case 1: {

                    b = new Book();

                    System.out.print("Enter the Book name, Edition, ISBN, Price: ");

                    b.setName(sc.next());

                    b.setEdition(sc.next());

                    b.setIsbn(sc.next());

                    b.SetPrice(sc.nextInt());

                    inv.add(b);

                    break;

                }

                case 2: {

                    System.out.print("Enter the id to update: ");

                    String id = sc.next();

                    b = inv.search(id);

                    if (b == null) {

                        System.out.println("ID not found for update");

                    } else {

                        System.out.print("Enter new details (name edition ISBN price): ");

                        b.setName(sc.next());

                        b.setEdition(sc.next());

                        b.setIsbn(sc.next());

                        b.SetPrice(sc.nextInt());

                        inv.update(b);

                    }

                    break;

                }

                case 3: {

                    System.out.print("Enter the id to remove: ");

                    String id = sc.next();

                    inv.remove(id);

                    break;

                }

                case 4: {

                    System.out.print("Enter the id to display: ");

                    b = inv.search(sc.next());

                    if (b == null) {

                        System.out.print("The given id does not exist");

                    } else {

                        System.out.println(b);

                    }

                    break;

                }

                case 5: {

                    inv.show();

                    break;

                }

                default:

                    if (op != 6) {

                        System.out.println("Invalid option");

                    }

            }

        } while (op != 6);

    }



    public static void cinventory(Inventory inv) {

        Book b;

        Scanner sc = new Scanner(System.in);

        int op;

        do {

            System.out.print("\n1. Sell 2. Buy 3. Search 4. Show 5. Logout: ");

            op = sc.nextInt();

            switch (op) {

                case 1: {

                    b = new Book();

                    System.out.print("Enter the Book name, Edition, ISBN, Price: ");

                    b.setName(sc.next());

                    b.setEdition(sc.next());

                    b.setIsbn(sc.next());

                    b.SetPrice(sc.nextInt());

                    inv.add(b);

                    break;

                }

                case 2: {

                    System.out.print("Enter the Edition to buy: ");

                    String edition = sc.next();

                    // Assuming you have a buy logic implemented

                    break;

                }

                case 3: {

                    System.out.print("Enter the Edition or ISBN to display: ");

                    b = inv.search(sc.next());

                    if (b != null) {

                        System.out.println(b);

                    } else {

                        System.out.println("Book not found.");

                    }

                    break;

                }

                case 4: {

                    inv.show();

                    break;

                }

                default:

                    if (op != 5) {

                        System.out.println("Invalid option");

                    }

            }

        } while (op != 5);

    }

}