import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RentalSystem implements Rentable, Returnable{
    private List<Mobil> cars;
    private List<Pelanggan> customers;
    private List<Rental> rentals;

    public RentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Mobil car) {
        cars.add(car);
    }

    public void addCustomer(Pelanggan customer) {
        customers.add(customer);
    }

    public void rentCar(Mobil car, Pelanggan customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

        } else {
            System.err.println("Mobil tidak tersedia untuk disewa.");
        }
    }

    public void returnCar(Mobil car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.err.println("Mobil tidak disewa.");
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/rental_system";
    private static final String USER = "root";
    private static final String PASSWORD = " ";

    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mobil> getCars() {
        List<Mobil> cars = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mobil car = new Mobil(
                        resultSet.getString("car_id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("price_per_day")
                );
                car.setAvailable(resultSet.getBoolean("is_available"));
                cars.add(car);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public void addCar1(Mobil car) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars (car_id, manufacturer, model, price_per_day, is_available) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, car.getCarId());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setDouble(4, car.getPricePerDay());
            statement.setBoolean(5, car.isAvailable());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(Mobil car) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE cars SET manufacturer = ?, model = ?, price_per_day = ?, is_available = ? WHERE car_id = ?");
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setDouble(3, car.getPricePerDay());
            statement.setBoolean(4, car.isAvailable());
            statement.setString(5, car.getCarId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(String carId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE car_id = ?");
            statement.setString(1, carId);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("===== Sewa Mobil Amasing =====");
                System.out.println("1. Sewa Mobil");
                System.out.println("2. Pengembalian Mobil Sewa");
                System.out.println("3. Keluar");
                System.out.print("Masukkan Opsi Anda: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice == 1) {
                    System.out.println("\n== Sewa Mobil ==\n");
                    System.out.print("Masukkan Nama Anda: ");
                    String customerName = scanner.nextLine();

                    System.out.println("\nMobil yang Tersedia:");
                    for (Mobil car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                        }
                    }

                    System.out.print("\nMasukkan ID mobil yang ingin Anda sewa: ");
                    String carId = scanner.nextLine();

                    System.out.print("Masukkan jumlah hari sewa: ");
                    int rentalDays = scanner.nextInt();
                    scanner.nextLine(); 

                    Pelanggan newCustomer = new Pelanggan("CUS" + (customers.size() + 1), customerName);
                    addCustomer(newCustomer);

                    Mobil selectedCar = null;
                    for (Mobil car : cars) {
                        if (car.getCarId().equals(carId) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar != null) {
                        double totalPrice = selectedCar.calculatePrice(rentalDays);
                        System.out.println("\n== Informasi Sewa ==\n");
                        System.out.println("ID Customer: " + newCustomer.getCustomerId());
                        System.out.println("Nama Customer: " + newCustomer.getName());
                        System.out.println("mobil: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Sewa: " + rentalDays + " Hari.");
                        System.out.printf("Total harga: Rp%.2f%n", totalPrice);

                        System.out.print("\nKonfirmasi sewa (Y/N): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("Y")) {
                            rentCar(selectedCar, newCustomer, rentalDays);
                            System.out.println("\nMobil berhasil disewa.");
                        } else {
                            System.out.println("\nPenyewaan dibatalkan.");
                        }
                    } else {
                        System.out.println("\nPilihan mobil tidak valid atau mobil tidak tersedia untuk disewa.");
                    }
                } else if (choice == 2) {
                    System.out.println("\n== Pengembalian Mobil Sewa ==\n");
                    System.out.print("Masukkan ID mobil yang ingin Anda kembalikan: ");
                    String carId = scanner.nextLine();

                    Mobil carToReturn = null;
                    for (Mobil car : cars) {
                        if (car.getCarId().equals(carId)) {
                            carToReturn = car;
                            break;
                        }
                    }

                    if (carToReturn != null) {
                        Pelanggan customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }

                        if (customer != null) {
                            returnCar(carToReturn);
                            System.out.println("Mobil berhasil dikembalikan oleh " + customer.getName());
                        } else {
                            System.out.println("Mobil tidak disewa atau informasi persewaan hilang.");
                        }
                    } else {
                        System.out.println("ID mobil tidak valid atau mobil tidak disewa.");
                    }
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan masukkan opsi yang valid.");
                }
            }
        }

        System.out.println("\nTerima Kasih Telah Menggunakan Sewa Mobil Amasing");
        System.out.println("Semoga Anda Puas Dengan Pelayanan Kami :)");
    }

    
    @Override
    public void returnCar(String car) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnCar'");
    }

    @Override
    public void rentCar(String car, String customer, int days) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rentCar'");
    }
}
