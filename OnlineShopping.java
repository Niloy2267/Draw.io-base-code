import java.util.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class ShoppingCart {
    private final Map<Product, Integer> cart = new LinkedHashMap<>();

    void addProduct(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n--- Cart Items ---");
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subTotal = p.price * qty;
            System.out.println(p.name + " x" + qty + " = $" + subTotal);
            total += subTotal;
        }
        System.out.println("Total: $" + total);
    }

    void checkout() {
        viewCart();
        System.out.println("Thank you for shopping with us!");
        cart.clear();
    }

    boolean isEmpty() {
        return cart.isEmpty();
    }
}

public class OnlineShopping {
    static List<Product> productList = new ArrayList<>();
    static ShoppingCart cart = new ShoppingCart();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeProducts();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Online Shopping ---");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> addToCart();
                case 3 -> cart.viewCart();
                case 4 -> {
                    if (!cart.isEmpty()) cart.checkout();
                    else System.out.println("Your cart is empty!");
                }
                case 5 -> {
                    System.out.println("Exiting the app. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void initializeProducts() {
        productList.add(new Product(1, "Laptop", 850.00));
        productList.add(new Product(2, "Headphones", 50.00));
        productList.add(new Product(3, "Smartphone", 599.99));
        productList.add(new Product(4, "Book", 12.99));
        productList.add(new Product(5, "Water Bottle", 5.49));
    }

    static void viewProducts() {
        System.out.println("\n--- Available Products ---");
        for (Product p : productList) {
            System.out.printf("%d. %s - $%.2f%n", p.id, p.name, p.price);
        }
    }

    static void addToCart() {
        viewProducts();
        System.out.print("Enter product ID to add: ");
        int id = scanner.nextInt();
        Product selectedProduct = null;

        for (Product p : productList) {
            if (p.id == id) {
                selectedProduct = p;
                break;
            }
        }

        if (selectedProduct != null) {
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            if (qty > 0) {
                cart.addProduct(selectedProduct, qty);
                System.out.println(qty + " x " + selectedProduct.name + " added to cart.");
            } else {
                System.out.println("Quantity must be greater than 0.");
            }
        } else {
            System.out.println("Invalid product ID.");
        }
    }
}
