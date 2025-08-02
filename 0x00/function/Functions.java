import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

public class Functions {

    private IncomeRepository inRepo;
    private ExpenseRepository outRepo;
    private UserService userService;
    private UserRepository userRepository;
    private VersionService versionService;
    private EnvironmentService environmentService;
    private ReleaseService releaseService;

    // ✅ Faça somente uma coisa
    public void saveIncome(Income income) {
        inRepo.save(income);
    }

    public void saveExpense(Expense expense) {
        outRepo.save(expense);
    }

    // ✅ Um nível de abstração
    @PostMapping("/user")
    public User saveUser(User user) {
        return userService.save(user);
    }

    // ✅ Argumentos simples e claros
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // ✅ Sem efeitos colaterais
    public User saveUserWithRole(User user) {
        setRole(user);
        return userRepository.save(user);
    }

    public void setRole(User user) {
        user.setRole(user);
    }

    // ✅ Evite duplicação
    public Environment getEnvironment() {
        return environmentService.getEnvironment(getVersion());
    }

    public Release getRelease() {
        return releaseService.getRelease(getVersion());
    }

    private String getVersion() {
        return versionService.getVersion();
    }

    // Classes auxiliares (simuladas)
    public static class Income {}
    public static class Expense {}
    public class User {
        private boolean isAdmin;

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public void setRole(User user) {
            // lógica para definir o papel do usuário
        }
    }

    public interface IncomeRepository {
        void save(Income income);
    }

    public interface ExpenseRepository {
        void save(Expense expense);
    }

    public interface UserService {
        User save(User user);
    }

    public interface UserRepository {
        void save(User user);
        User save(User user);
    }

    public interface VersionService {
        String getVersion();
    }

    public interface EnvironmentService {
        Environment getEnvironment(String version);
    }

    public interface ReleaseService {
        Release getRelease(String version);
    }

    public static class Environment {}
    public static class Release {}
}