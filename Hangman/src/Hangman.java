import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    private static final String[] WORDS = {
            "программа", "компьютер",
            "алгоритм", "елка",
            "игра", "интерфейс",
            "абстракция", "переменная",
            "функция", "система",
            "матрица", "компиляция",
            "инкапсуляция", "наследование",
            "полиморфизм", "информация",
            "массив", "структура",
            "интернет", "операция",
            "платформа", "модель",
            "сеть", "поток",
            "класс", "метод",
            "конструктор", "библиотека",
            "архитектура", "блокчейн",
            "сервер", "клиент",
            "виртуализация", "навигация",
            "база", "данные",
            "шаблон", "автоматизация",
            "искусственный", "интеллект"
    };

    private String currentWord;
    private StringBuilder currentGuess;
    private Set<Character> guessedLetters;
    private int remainingLives;

    public Hangman() {
        Random random = new Random();
        this.currentWord = WORDS[random.nextInt(WORDS.length)];
        this.currentGuess = new StringBuilder("_".repeat(currentWord.length()));
        this.remainingLives = 6;
        this.guessedLetters = new HashSet<>();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.println("Игра \"Виселица\"\nУгадайте слово по буквам.\n" +
                "Выберите уровень сложности:\n1 - Легкий (9 жизней)\n2 - Средний (6 жизней)\n" +
                "3 - Сложный (4 жизни)");
        System.out.print("Введите номер уровня сложности: ");
        int difficult = scanner.nextInt();
        switch (difficult) {
            case 1:
                remainingLives = 9;
                break;
            case 2:
                remainingLives = 6;
                break;
            case 3:
                remainingLives = 4;
                break;
            default:
                System.out.println("Неверный ввод! Устанавливается уровень по умолчанию — Средний.");
                break;
        }

        while (remainingLives > 0 && currentGuess.indexOf("_") != -1) {
            System.out.println("\nТекущее слово: " + currentGuess);
            System.out.println("Оставшиеся жизни: " + remainingLives);
            System.out.print("Введите букву: ");

            String input = scanner.next().toLowerCase();
            if (input.length() != 1 || !input.matches("[а-яё]")) {
                System.out.println("Неверный ввод! Введите только одну русскую букву.");
                continue;
            }
            char guess = input.replace('ё', 'е').charAt(0);
            if (guessedLetters.contains(guess)) {
                System.out.println("Вы уже вводили букву " + guess + ". Попробуйте другую.");
                continue;
            } else {
                guessedLetters.add(guess);
            }

            if (currentWord.indexOf(guess) >= 0) {
                for (int i = 0; i < currentWord.length(); ++i) {
                    if (currentWord.charAt(i) == guess) {
                        currentGuess.setCharAt(i, input.charAt(0));
                    }
                }
                System.out.println("Есть такая буква!");
            } else {
                remainingLives--;
                System.out.println("Такой буквы нет.");
            }
        }

        if (remainingLives > 0) {
            System.out.println("\nПобеда! Вы угадали слово: " + currentWord);
        } else {
            System.out.println("\nВы проиграли. Загаданное слово: " + currentWord);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.play();
    }
}
