import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(String stockName, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance to buy " + stockName);
            return;
        }
        holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " shares of " + stockName);
    }

    public void sellStock(String stockName, int quantity, double price) {
        if (!holdings.containsKey(stockName) || holdings.get(stockName) < quantity) {
            System.out.println("Not enough shares to sell");
            return;
        }
        holdings.put(stockName, holdings.get(stockName) - quantity);
        if (holdings.get(stockName) == 0) holdings.remove(stockName);
        balance += quantity * price;
        System.out.println("Sold " + quantity + " shares of " + stockName);
    }

    public void showPortfolio() {
        System.out.println("Portfolio Holdings: " + holdings);
        System.out.println("Current Balance: " + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000); // Initial balance
        Map<String, Stock> market = new HashMap<>();
        
        market.put("AAPL", new Stock("Apple", 150));
        market.put("GOOGL", new Stock("Google", 2800));
        market.put("TSLA", new Stock("Tesla", 750));

        while (true) {
            System.out.println("1. Buy Stock  2. Sell Stock  3. View Portfolio  4. Exit");
            int choice = scanner.nextInt();
            if (choice == 4) break;

            switch (choice) {
                case 1:
                    System.out.println("Enter stock symbol:");
                    String buyStock = scanner.next();
                    if (!market.containsKey(buyStock)) {
                        System.out.println("Stock not available");
                        break;
                    }
                    System.out.println("Enter quantity:");
                    int buyQty = scanner.nextInt();
                    portfolio.buyStock(buyStock, buyQty, market.get(buyStock).price);
                    break;
                case 2:
                    System.out.println("Enter stock symbol:");
                    String sellStock = scanner.next();
                    if (!market.containsKey(sellStock)) {
                        System.out.println("Stock not available");
                        break;
                    }
                    System.out.println("Enter quantity:");
                    int sellQty = scanner.nextInt();
                    portfolio.sellStock(sellStock, sellQty, market.get(sellStock).price);
                    break;
                case 3:
                    portfolio.showPortfolio();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }
}
