import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CinemaTicketSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static boolean isAdmin = false;

    public static void main(String[] args) {
        initializeTestData();
        showMainMenu();
    }

    private static void initializeTestData() {
        // Тестовые пользователи
        users.add(new User("admin", "admin123", true));
        users.add(new User("user1", "password1", false));
        users.add(new User("user2", "password2", false));

        Cinema cinema1 = new Cinema("Кинотеатр 'Премьер'", "ул. Кирова, 15");
        Cinema cinema2 = new Cinema("Кинотеатр 'Синема'", "пр. Ленина, 42");

        Hall hall1 = new Hall("Зал 1", 5, 10);
        Hall hall2 = new Hall("Зал 2", 8, 12);
        cinema1.addHall(hall1);
        cinema1.addHall(hall2);

        Hall hall3 = new Hall("Зал 1", 6, 8);
        Hall hall4 = new Hall("Зал VIP", 4, 6);
        cinema2.addHall(hall3);
        cinema2.addHall(hall4);

        cinemas.add(cinema1);
        cinemas.add(cinema2);

        Movie movie1 = new Movie("Интерстеллар", 169);
        Movie movie2 = new Movie("Крестный отец", 175);
        Movie movie3 = new Movie("Форрест Гамп", 142);

        LocalDateTime now = LocalDateTime.now();
        hall1.addSession(new Session(movie1, now.plusHours(2), 350));
        hall1.addSession(new Session(movie2, now.plusHours(5), 400));
        hall2.addSession(new Session(movie3, now.plusHours(3), 300));
        hall3.addSession(new Session(movie1, now.plusHours(1), 320));
        hall4.addSession(new Session(movie2, now.plusDays(1), 500));
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Билетная система кинотеатров ===");
            System.out.println("1. Вход");
            System.out.println("2. Регистрация");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void login() {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                isAdmin = user.isAdmin();
                System.out.println("Вход выполнен успешно!");
                if (isAdmin) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
                return;
            }
        }
        System.out.println("Неверный логин или пароль.");
    }

    private static void register() {
        System.out.print("Введите новый логин: ");
        String username = scanner.nextLine();

        // Проверка на существование пользователя
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Пользователь с таким логином уже существует.");
                return;
            }
        }

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        users.add(new User(username, password, false));
        System.out.println("Регистрация завершена успешно!");
    }

    private static void showAdminMenu() {
        while (currentUser != null) {
            System.out.println("\n=== Меню администратора ===");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал в кинотеатр");
            System.out.println("3. Создать сеанс");
            System.out.println("4. Просмотреть все кинотеатры");
            System.out.println("5. Выйти из системы");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCinema();
                    break;
                case 2:
                    addHall();
                    break;
                case 3:
                    createSession();
                    break;
                case 4:
                    viewAllCinemas();
                    break;
                case 5:
                    currentUser = null;
                    isAdmin = false;
                    System.out.println("Выход из системы выполнен.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showUserMenu() {
        while (currentUser != null) {
            System.out.println("\n=== Меню пользователя ===");
            System.out.println("1. Найти ближайший сеанс");
            System.out.println("2. Купить билет");
            System.out.println("3. Просмотреть план зала");
            System.out.println("4. Просмотреть все кинотеатры");
            System.out.println("5. Выйти из системы");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    findNearestSession();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    viewHallLayout();
                    break;
                case 4:
                    viewAllCinemas();
                    break;
                case 5:
                    currentUser = null;
                    isAdmin = false;
                    System.out.println("Выход из системы выполнен.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addCinema() {
        System.out.print("Введите название кинотеатра: ");
        String name = scanner.nextLine();
        System.out.print("Введите адрес кинотеатра: ");
        String address = scanner.nextLine();

        Cinema cinema = new Cinema(name, address);
        cinemas.add(cinema);
        System.out.println("Кинотеатр успешно добавлен!");
    }

    private static void addHall() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров. Сначала добавьте кинотеатр.");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).getName());
        }
        System.out.print("Введите номер: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }

        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        System.out.print("Введите количество рядов: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество мест в ряду: ");
        int seatsPerRow = scanner.nextInt();
        scanner.nextLine();

        Hall hall = new Hall(hallName, rows, seatsPerRow);
        cinemas.get(cinemaIndex).addHall(hall);
        System.out.println("Зал успешно добавлен в кинотеатр!");
    }

    private static void createSession() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).getName());
        }
        System.out.print("Введите номер: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }

        Cinema cinema = cinemas.get(cinemaIndex);
        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов. Сначала добавьте зал.");
            return;
        }

        System.out.println("Выберите зал:");
        List<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i + 1) + ". " + halls.get(i).getName());
        }
        System.out.print("Введите номер: ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }

        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Введите продолжительность фильма (в минутах): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите дату и время сеанса (гггг-мм-дд чч:мм): ");
        String dateTimeStr = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.print("Введите цену билета: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Movie movie = new Movie(movieTitle, duration);
        Session session = new Session(movie, dateTime, price);
        halls.get(hallIndex).addSession(session);
        System.out.println("Сеанс успешно создан!");
    }

    private static void viewAllCinemas() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (Cinema cinema : cinemas) {
            System.out.println(cinema);
            for (Hall hall : cinema.getHalls()) {
                System.out.println("  " + hall);
                for (Session session : hall.getSessions()) {
                    System.out.println("    " + session);
                }
            }
        }
    }

    private static void findNearestSession() {
        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();

        Session nearestSession = null;
        Cinema foundCinema = null;
        Hall foundHall = null;
        LocalDateTime now = LocalDateTime.now();

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.getHalls()) {
                for (Session session : hall.getSessions()) {
                    if (session.getMovie().getTitle().equalsIgnoreCase(movieTitle)
                            && session.getDateTime().isAfter(now)
                            && session.hasAvailableSeats()) {
                        if (nearestSession == null || session.getDateTime().isBefore(nearestSession.getDateTime())) {
                            nearestSession = session;
                            foundCinema = cinema;
                            foundHall = hall;
                        }
                    }
                }
            }
        }

        if (nearestSession != null) {
            System.out.println("\nБлижайший сеанс:");
            System.out.println("Фильм: " + nearestSession.getMovie().getTitle());
            System.out.println("Кинотеатр: " + foundCinema.getName() + ", " + foundCinema.getAddress());
            System.out.println("Зал: " + foundHall.getName());
            System.out.println("Время: " + nearestSession.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("Длительность: " + nearestSession.getMovie().getDuration() + " мин");
            System.out.println("Цена: " + nearestSession.getPrice() + " руб");
            System.out.println("Свободных мест: " + (foundHall.getTotalSeats() - nearestSession.getSoldTickets().size()));
        } else {
            System.out.println("Сеансы на выбранный фильм не найдены или все билеты проданы.");
        }
    }

    private static void buyTicket() {
        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();

        List<Session> availableSessions = new ArrayList<>();
        Map<Session, Cinema> sessionCinemaMap = new HashMap<>();
        Map<Session, Hall> sessionHallMap = new HashMap<>();

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.getHalls()) {
                for (Session session : hall.getSessions()) {
                    if (session.getMovie().getTitle().equalsIgnoreCase(movieTitle)
                            && session.hasAvailableSeats()) {
                        availableSessions.add(session);
                        sessionCinemaMap.put(session, cinema);
                        sessionHallMap.put(session, hall);
                    }
                }
            }
        }

        if (availableSessions.isEmpty()) {
            System.out.println("Нет доступных сеансов на этот фильм.");
            return;
        }

        System.out.println("\nДоступные сеансы:");
        for (int i = 0; i < availableSessions.size(); i++) {
            Session session = availableSessions.get(i);
            Cinema cinema = sessionCinemaMap.get(session);
            Hall hall = sessionHallMap.get(session);
            System.out.println((i + 1) + ". " + cinema.getName() + ", " + hall.getName() +
                    ", " + session.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    ", Свободных мест: " + (hall.getTotalSeats() - session.getSoldTickets().size()));
        }

        System.out.print("Выберите сеанс (номер): ");
        int sessionChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (sessionChoice < 0 || sessionChoice >= availableSessions.size()) {
            System.out.println("Неверный выбор сеанса.");
            return;
        }

        Session selectedSession = availableSessions.get(sessionChoice);
        Hall selectedHall = sessionHallMap.get(selectedSession);

        selectedHall.printHallLayout(selectedSession);

        System.out.print("Введите номер ряда: ");
        int row = scanner.nextInt();
        System.out.print("Введите номер места: ");
        int seat = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (row < 1 || row > selectedHall.getRows() || seat < 1 || seat > selectedHall.getSeatsPerRow()) {
            System.out.println("Неверный номер ряда или места.");
            return;
        }

        if (selectedSession.isSeatOccupied(row, seat)) {
            System.out.println("Это место уже занято.");
            return;
        }

        // Продаем билет
        Ticket ticket = new Ticket(currentUser.getUsername(), selectedSession, row, seat);
        selectedSession.addSoldTicket(ticket);
        System.out.println("Билет успешно куплен!");
        System.out.println(ticket);
    }

    private static void viewHallLayout() {
        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();

        List<Session> availableSessions = new ArrayList<>();
        Map<Session, Hall> sessionHallMap = new HashMap<>();

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.getHalls()) {
                for (Session session : hall.getSessions()) {
                    if (session.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                        availableSessions.add(session);
                        sessionHallMap.put(session, hall);
                    }
                }
            }
        }

        if (availableSessions.isEmpty()) {
            System.out.println("Нет сеансов на этот фильм.");
            return;
        }

        System.out.println("\nДоступные сеансы:");
        for (int i = 0; i < availableSessions.size(); i++) {
            Session session = availableSessions.get(i);
            Hall hall = sessionHallMap.get(session);
            System.out.println((i + 1) + ". " + hall.getCinema().getName() + ", " + hall.getName() +
                    ", " + session.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }

        System.out.print("Выберите сеанс (номер): ");
        int sessionChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (sessionChoice < 0 || sessionChoice >= availableSessions.size()) {
            System.out.println("Неверный выбор сеанса.");
            return;
        }

        Session selectedSession = availableSessions.get(sessionChoice);
        Hall selectedHall = sessionHallMap.get(selectedSession);
        selectedHall.printHallLayout(selectedSession);
    }
}

class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

class Cinema {
    private String name;
    private String address;
    private List<Hall> halls;

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
        this.halls = new ArrayList<>();
    }

    public void addHall(Hall hall) {
        hall.setCinema(this);
        halls.add(hall);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    @Override
    public String toString() {
        return name + " (" + address + ")";
    }
}

class Hall {
    private String name;
    private int rows;
    private int seatsPerRow;
    private List<Session> sessions;
    private Cinema cinema;

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void printHallLayout(Session session) {
        System.out.println("\nПлан зала '" + name + "' (" + cinema.getName() + ")");
        System.out.println("Фильм: " + session.getMovie().getTitle());
        System.out.println("Время: " + session.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("Экран");
        System.out.println("-------------------");

        for (int row = 1; row <= rows; row++) {
            System.out.printf("Ряд %2d: ", row);
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                if (session.isSeatOccupied(row, seat)) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------");
        System.out.println("Легенда: [ ] - свободно, [X] - занято");
    }

    public int getTotalSeats() {
        return rows * seatsPerRow;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return name + " (" + rows + " рядов x " + seatsPerRow + " мест = " + getTotalSeats() + " мест)";
    }
}

class Movie {
    private String title;
    private int duration; // в минутах

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}

class Session {
    private Movie movie;
    private LocalDateTime dateTime;
    private double price;
    private List<Ticket> soldTickets;

    public Session(Movie movie, LocalDateTime dateTime, double price) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.price = price;
        this.soldTickets = new ArrayList<>();
    }

    public boolean isSeatOccupied(int row, int seat) {
        for (Ticket ticket : soldTickets) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAvailableSeats() {
        // Предполагаем, что зал всегда один и тот же для сеанса
        // В реальной системе нужно было бы хранить ссылку на зал
        return soldTickets.size() < 100; // Просто пример, нужно адаптировать
    }

    public void addSoldTicket(Ticket ticket) {
        soldTickets.add(ticket);
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " (" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", " + movie.getDuration() + " мин, " + price + " руб)";
    }
}

class Ticket {
    private String username;
    private Session session;
    private int row;
    private int seat;
    private LocalDateTime purchaseTime;

    public Ticket(String username, Session session, int row, int seat) {
        this.username = username;
        this.session = session;
        this.row = row;
        this.seat = seat;
        this.purchaseTime = LocalDateTime.now();
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Билет на фильм: " + session.getMovie().getTitle() +
                "\nКинотеатр: " + session.getMovie().getTitle() +
                "\nВремя: " + session.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nМесто: ряд " + row + ", место " + seat +
                "\nЦена: " + session.getPrice() + " руб" +
                "\nВремя покупки: " + purchaseTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
