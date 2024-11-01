import java.util.*;

public class CurrencyConverter {
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("RUB", 1.0);
        exchangeRates.put("USD", 0.011);
        exchangeRates.put("EUR", 0.0095);
        exchangeRates.put("PLN", 0.042);
        exchangeRates.put("GBR", 0.0085);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Конвертер валют.\nДоступные валюты: " + exchangeRates.keySet());

        System.out.print("\nВведите сумму для конвертации: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод! Введите корректное число.");
            return;
        }

        System.out.print("Введите код валюты, из которой хотите конвертировать (например, RUB): ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        if (!exchangeRates.containsKey(fromCurrency)) {
            System.out.println("Неверный ввод! Введена недоступная валюта.");
            return;
        }

        System.out.println("Конвертация суммы " + amount + " " + fromCurrency + " в другие валюты:");
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            String toCurrency = entry.getKey();
            double rate = entry.getValue();

            if (!toCurrency.equals(fromCurrency)) {
                double convertedAmount = amount * rate / exchangeRates.get(fromCurrency);
                System.out.printf(" - %s: %.2f%n", toCurrency, convertedAmount);
            }
        }

        scanner.close();
    }
}
