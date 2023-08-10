package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxServices;
import services.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String model = scan.nextLine();
		System.out.println("Pickup (dd/MM/yyyy hh:ss): ");
		LocalDateTime start = LocalDateTime.parse(scan.nextLine(), fmt);
		System.out.println("Return (dd/MM/yyyy hh:ss): ");
		LocalDateTime finish = LocalDateTime.parse(scan.nextLine(), fmt);
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.println("Enter price per hour: ");
		double pricePerHour = scan.nextDouble();
		System.out.println("Enter price per day: ");
		double pricePerDay = scan.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxServices());
		rentalService.processInvoice(cr);
		
		System.out.println("INVOICE: ");
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		scan.close();
			
	}

}
