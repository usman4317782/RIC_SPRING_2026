class Book {
    String title;
    
    void printTitle() {
        System.out.println("Book Title: " + title);
    }
}

public class ObjectReferenceDemo {
    public static void main(String[] args) {
        // 'myBook' is an object reference variable
        Book myBook = new Book();
        
        // Accessing the object via the reference variable
        myBook.title = "Java Programming Guide";
        
        // Another reference variable pointing to the SAME object
        Book anotherRef = myBook;
        
        System.out.println("Title from myBook: " + myBook.title);
        System.out.println("Title from anotherRef: " + anotherRef.title);
        
        // Modifying via one reference affects the other
        anotherRef.title = "Advanced Java";
        System.out.println("\nAfter modification via anotherRef:");
        System.out.println("Title from myBook: " + myBook.title);
    }
}
