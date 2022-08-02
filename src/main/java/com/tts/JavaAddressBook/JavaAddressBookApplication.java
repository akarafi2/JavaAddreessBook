package com.tts.JavaAddressBook;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tts.JavaAddressBook.domain.Address;
import com.tts.JavaAddressBook.repository.AddressRepository;

@SpringBootApplication
public class JavaAddressBookApplication {
	private static final Logger log = LoggerFactory.getLogger(JavaAddressBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaAddressBookApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AddressRepository repository) {
		return (args) -> {
			Integer userInput = 0;
			Scanner scanner = new Scanner(System.in);

			while (userInput != 6) {
				String prompt = " 1) Add an entry\n 2) Remove an entry\n 3) Search for a specific entry\n 4) Print the contents of the address book\n 5) Delete the contents of the address book\n 6) Quit the program\n";
				System.out.println("\n Welcome to our address book.\n");
				System.out.println(prompt);
				System.out.println("\n\nPlease choose what you'd like to do with the database:");

				userInput = scanner.nextInt();
				scanner.nextLine();

				switch (userInput) {
				case 1:
					Address address = new Address();
					System.out.println("First Name: ");
					address.setFirstName(scanner.nextLine());
					System.out.println("Last Name: ");
					address.setLastName(scanner.nextLine());
					System.out.println("Phone Number: ");
					address.setPhoneNumber(scanner.nextLine());
					System.out.println("Email Address: ");
					address.setEmailAddress(scanner.nextLine());
					System.out.println("\n Added new entry!");
					repository.save(address);
//					repository.findAll().forEach((a) -> {
//						System.out.println(a.toString());
//					});

					break;
				case 2:
					System.out.println("\n Enter an entry's email to remove: ");
					String emailAddress = scanner.nextLine();

					if (repository.findByEmailAddress(emailAddress).isEmpty())
						System.out.println("\nemail does not exist in the repository, Please try again!");

					repository.findAll().forEach((a) -> {

						if (a.getEmailAddress().equals(emailAddress)) {
							System.out.println("\nDeleted the following entry:");
							System.out.println("************");
							System.out.println(a.toString());
							System.out.println("************");
							repository.delete(a);
						}
						;
					});

					break;

				case 3:
					Integer userInput3 = 0;
					while (userInput3 == 0) {

						String prompt3 = " 1) First Name\n 2) Last Name\n 3) Phone Number\n 4) Email Address\n";
						System.out.println(prompt3);
						System.out.println("\n\nChoose a search type: ");

						userInput3 = scanner.nextInt();
						scanner.nextLine();
						
						switch (userInput3) {
						case 1:
							System.out.println("Enter your search: ");
							String fName = scanner.nextLine();

							if (repository.findByFirstName(fName).isEmpty())
								System.out.println("\nThe first name you provided does not exist!");
							else {
								repository.findByFirstName(fName).forEach((a) -> {

									System.out.println("\n************");
									System.out.println(a.toString());
									System.out.println("************");
									System.out.println("************\n");
								});
							}
							
							break;
						case 2:
							System.out.println("Enter your search: ");
							String lName = scanner.nextLine();

							if (repository.findByLastName(lName).isEmpty())
								System.out.println("\nThe last name you provided does not exist!");
							else {
								repository.findByLastName(lName).forEach((a) -> {

									System.out.println("\n************");
									System.out.println(a.toString());
									System.out.println("************\n");
								});
							}

							break;
						case 3:
							System.out.println("Enter your search: ");
							String pNumber = scanner.nextLine();

							if (repository.findByPhoneNumber(pNumber).isEmpty())
								System.out.println("\nThe phone number you provided does not exist!");
							else {
								repository.findByFirstName(pNumber).forEach((a) -> {

									System.out.println("\n************");
									System.out.println(a.toString());
									System.out.println("************");
									System.out.println("************\n");
								});
							}
							break;
						case 4:
							System.out.println("Enter your search: ");
							String eAddress = scanner.nextLine();

							if (repository.findByEmailAddress(eAddress).isEmpty())
								System.out.println("\nThe email address you provided does not exist!");
							else {
								repository.findByEmailAddress(eAddress).forEach((a) -> {

									System.out.println("\n************");
									System.out.println(a.toString());
									System.out.println("************");
									System.out.println("************\n");
								});
							}
							break;
						default:
							System.out.println("Incorrect choice entry, Please enter a number between 1 and 4 to select an option from the following Menue.");
							break;

						}
					}

					break;
				case 4:
					repository.findAll().forEach((a) -> {

						System.out.println("\n************");
						System.out.println(a.toString());
						System.out.println("************");
						System.out.println("************\n");
					});
					break;
				case 5:
					
					repository.deleteAll();
					System.out.println("\"Address Book cleared!");
					break;
				case 6:
					
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println(
							"Incorrect choice entry, Please enter a number from 1 to 6 to select an option from the following Menue.");
					break;

				}

			}
			scanner.close();
			System.exit(0);
		};
	}

}