import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        login();
    }

    public static void login() {
        System.out.println("Selamat Datang Di Ambatu Cashier App");
        System.out.println("=======================================");
        System.out.println("Log in");
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Masukkan Username:   ");
        String username = scanner.nextLine();

        System.out.printf("Masukkan password:   ");
        String password = scanner.nextLine();

        if (validateLogin(username, password)) {
            System.out.println("Login berhasil. Masukkan kode CAPTCHA! ");
            System.out.println(" ");

            String captcha = generateRandomCaptcha(6);
            System.out.println("Generated CAPTCHA: " + captcha);

            System.out.printf("Masukkan CAPTCHA: ");
            String userInput = scanner.nextLine();

            if (userInput.equals(captcha)) {
                System.out.println("Kode CAPTCHA benar!");
                System.out.println("---------------------------------------");
                System.out.println("silakan entry pembelian costumer");
                System.out.println(" ");
                mainApp();
            } else {
                System.out.println("Kode CAPTCHA salah! Login dibatalkan.");
            }
        } else {
            System.out.println("Login gagal. Username atau Password salah.");
        }

        scanner.close();
    }

    public static void mainApp() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nama Pelanggan:       ");
            String name = scanner.nextLine();
            System.out.print("No HP:                ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Alamat:               ");
            String address  = scanner.nextLine();
            System.out.print("Nama Barang:          ");
            String item = scanner.nextLine();
            System.out.print("Harga Barang:         ");
            double itemPrice = scanner.nextDouble();
            System.out.print("Jumlah:               ");
            int quantity = scanner.nextInt();

            try { 
                Receipt r = new Receipt(name, phoneNumber, address);
                r.beliBarang(item, itemPrice, quantity);
                r.tampilStruk1();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
        }
    }

    public static boolean validateLogin(String username, String password) {
        String correctUsername = "admin";
        String correctPassword = "admin123";
        
        return username.equals(correctUsername) && password.equals(correctPassword);
    }
    
    public static String generateRandomCaptcha(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captcha.append(alphabet.charAt(random.nextInt(alphabet.length())));
    }
        return captcha.toString();
    }
}

