import java.util.*;

public class Library {
    private List<Book> books;
    private Set<String> authors;
    private Map<String, Integer> authorStatistics;

    public Library() {
        books = new ArrayList<>();
        authors = new HashSet<>();
        authorStatistics = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        authorStatistics.merge(book.getAuthor(), 1, Integer::sum);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            // Проверяем, остались ли еще книги этого автора
            boolean authorHasMoreBooks = books.stream()
                    .anyMatch(b -> b.getAuthor().equals(book.getAuthor()));

            if (!authorHasMoreBooks) {
                authors.remove(book.getAuthor());
            }

            // Обновляем статистику
            authorStatistics.computeIfPresent(book.getAuthor(), (k, v) -> v > 1 ? v - 1 : null);
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        System.out.println("\nВсе книги в библиотеке:");
        books.forEach(System.out::println);
    }

    public void printUniqueAuthors() {
        System.out.println("\nУникальные авторы в библиотеке:");
        authors.forEach(System.out::println);
    }

    public void printAuthorStatistics() {
        System.out.println("\nСтатистика по авторам:");
        authorStatistics.forEach((author, count) ->
                System.out.println(author + ": " + count + " книг(и)"));
    }
}
