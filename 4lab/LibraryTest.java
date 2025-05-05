public class LibraryTest {
    public static void main(String[] args) {

        Library library = new Library();

        Book book1 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book book2 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book3 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book4 = new Book("Идиот", "Фёдор Достоевский", 1869);
        Book book5 = new Book("1984", "Джордж Оруэлл", 1949);
        Book book6 = new Book("Скотный двор", "Джордж Оруэлл", 1945);
        Book book7 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);

        System.out.println("=== Тестирование системы управления библиотекой ===");

        library.printAllBooks();

        library.printUniqueAuthors();

        library.printAuthorStatistics();

        System.out.println("\nКниги Льва Толстого:");
        library.findBooksByAuthor("Лев Толстой").forEach(System.out::println);

        System.out.println("\nКниги 1869 года:");
        library.findBooksByYear(1869).forEach(System.out::println);

        System.out.println("\nУдаляем книгу 'Идиот'...");
        library.removeBook(book4);

        System.out.println("\nОбновленный список книг:");
        library.printAllBooks();

        System.out.println("\nОбновленная статистика по авторам:");
        library.printAuthorStatistics();

        Book testBook = new Book("1984", "Джордж Оруэлл", 1949);
        System.out.println("\nПроверка equals и hashCode:");
        System.out.println("book5.equals(testBook): " + book5.equals(testBook));
        System.out.println("Хэш-коды: book5=" + book5.hashCode() + ", testBook=" + testBook.hashCode());
    }
}
