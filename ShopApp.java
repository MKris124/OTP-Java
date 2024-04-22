import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopApp {
     private List<Customer> customers = new ArrayList<>();
     private List<Payment> payments = new ArrayList<>();
    private Map<Integer, Integer> customerSpendings;
    public static void main(String[] args) {
        ShopApp app = new ShopApp();
        app.readData();
        app.generateCustomerReport();
        app.generateTopCustomersReport();
        app.generateWebshopReport();
    }
     public void readData() {
        readCustomers("customer.csv");
        readPayments("payments.csv");
        initializeCustomerSpendings();
     }

    public void readCustomers(String fileName) {
        Path path = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length == 4) {
                    Customer customer = new Customer(
                            Integer.parseInt(attributes[0].trim()),
                            Integer.parseInt(attributes[1].trim()),
                            attributes[2].trim(),
                            attributes[3].trim());
                    customers.add(customer);
                } else {
                    // Log to application.log
                    logError(line, "Invalid number of fields in customer record");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public void readPayments(String fileName) {
        Path path = Paths.get(fileName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length >= 5 && attributes.length <= 7) {
                    Payment payment = new Payment(
                            Integer.parseInt(attributes[0].trim()),
                            Integer.parseInt(attributes[1].trim()),
                            attributes[2].trim(),
                            Integer.parseInt(attributes[3].trim()),
                            (attributes[2].trim().equals("transfer") ? attributes[4].trim() : ""),
                            (attributes[2].trim().equals("card") ? attributes[5].trim() : ""),
                            LocalDateTime.parse(attributes[6].trim(),formatter)
                    );
                    payments.add(payment);
                } else {
                    logError(line, "Invalid number of fields in payment record");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }
    public void initializeCustomerSpendings() {
        customerSpendings = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCustomerId,  // Módosítva lambda kifejezésről metódusra hivatkozásra
                        Collectors.summingInt(Payment::getAmount)
                ));
    }
    public void generateCustomerReport() {
        List<String> lines = new ArrayList<>();
        lines.add("NAME,ADDRESS,TOTAL_SPENDING");
        for (Map.Entry<Integer, Integer> entry : customerSpendings.entrySet()) {
            customers.stream()
                    .filter(c -> c.getCustomerId() == entry.getKey())
                    .findFirst().ifPresent(customer -> lines.add(customer.getName() + "," + customer.getAddress() + "," + entry.getValue()));

        }
        writeToFile(lines, "report01.csv");
    }

    public void generateTopCustomersReport() {
        List<Map.Entry<Integer, Integer>> sortedSpendings = new ArrayList<>(customerSpendings.entrySet());
        sortedSpendings.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> topLines = sortedSpendings.stream()
                .limit(2)
                .map(entry -> {
                    Customer customer = customers.stream()
                            .filter(c -> c.getCustomerId()==entry.getKey())
                            .findFirst()
                            .orElse(null);
                    return customer != null ? customer.getName() + "," + customer.getAddress() + "," + entry.getValue() : "";
                })
                .collect(Collectors.toList());

        writeToFile(topLines, "top.csv");
    }

    public void generateWebshopReport() {
            Map<Integer, Map<String, Integer>> webshopSpendings = payments.stream()
                    .collect(Collectors.groupingBy(
                            Payment::getWebshopId,
                            Collectors.groupingBy(
                                    Payment::getPaymentMethod,
                                    Collectors.summingInt(Payment::getAmount)
                            )
                    ));

            List<String> webshopLines = new ArrayList<>();
            webshopLines.add("WEBSHOP,CARD_TOTAL,TRANSFER_TOTAL");
            for (Map.Entry<Integer, Map<String, Integer>> entry : webshopSpendings.entrySet()) {
                int cardTotal = entry.getValue().getOrDefault("card", 0);
                int transferTotal = entry.getValue().getOrDefault("transfer", 0);
                webshopLines.add(entry.getKey() + "," + cardTotal + "," + transferTotal);
            }

            writeToFile(webshopLines, "report02.csv");
        }

    private void writeToFile(List<String> lines, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Error writing the file: " + e.getMessage());
        }
    }
    private void logError(String line, String message) {
        try (FileWriter fw = new FileWriter("application.log", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message + ": " + line);
        } catch (IOException e) {
            System.err.println("Error logging the error: " + e.getMessage());
        }
    }
}

