import presentation.Menu;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MeinService;
import service.MeinServiceImpl;

public class LibraryApp {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl();
        BookRepository bookRepository = new BookRepositoryImpl();

        MeinService service = new MeinServiceImpl(bookRepository, userRepository);

        Menu menu = new Menu(service);

        menu.start();
    }

}
