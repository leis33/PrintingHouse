package org.exercises;

import org.exercises.entities.Manager;
import org.exercises.entities.Operator;
import org.exercises.exceptions.PrintingException;
import org.exercises.printing.PrintingHouse;
import org.exercises.printing.PrintingMachine;
import org.exercises.editions.Book;
import org.exercises.editions.Newspaper;
import org.exercises.editions.Paper;
import org.exercises.editions.Poster;
import org.exercises.editions.enums.PageSize;
import org.exercises.editions.enums.PaperType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws PrintingException, IOException, ClassNotFoundException {
        // ---- CREATE INSTANCES -----
        Book book1 = new Book("Don Quixote (short edition)", 280, new Paper(PaperType.REGULAR, PageSize.A5));
        Book book2 = new Book("The Lord of the Rings", 531, new Paper(PaperType.REGULAR, PageSize.A5));
        Book book3 = new Book("The Hobbit", 301, new Paper(PaperType.REGULAR, PageSize.A5));
        Book book4 = new Book("Pod Igoto", 380, new Paper(PaperType.REGULAR, PageSize.A5));

        Newspaper newspaper1 = new Newspaper("24 Hours", 32, new Paper(PaperType.NEWSPAPER, PageSize.A4));
        Newspaper newspaper2 = new Newspaper("Word", 36, new Paper(PaperType.NEWSPAPER, PageSize.A4));
        Newspaper newspaper3 = new Newspaper("Capital", 44, new Paper(PaperType.NEWSPAPER, PageSize.A4));

        Poster poster1 = new Poster("DM", 2, new Paper(PaperType.GLOSSY, PageSize.A3));
        Poster poster2 = new Poster("Ozone", 2, new Paper(PaperType.GLOSSY, PageSize.A3));
        Poster poster3 = new Poster("Paradise mall", 2, new Paper(PaperType.GLOSSY, PageSize.A2));
        Poster poster4 = new Poster("NBU", 2, new Paper(PaperType.GLOSSY, PageSize.A2));
        Poster poster5 = new Poster("Arena Sofia", 2, new Paper(PaperType.GLOSSY, PageSize.A1));

        double baseSalary1 = 1800;
        double baseSalary2 = 2100;

        Operator operator1 = new Operator("Ivan", baseSalary1);
        Operator operator2 = new Operator("Petar", baseSalary1);
        Operator operator3 = new Operator("Georgi", baseSalary2);
        Operator operator4 = new Operator("Maria", baseSalary2);

        Manager manager1 = new Manager("Tanya", baseSalary1);
        Manager manager2 = new Manager("Ivaylo", baseSalary2);

        PrintingMachine machine1 = new PrintingMachine(false, 50000, 12);
        PrintingMachine machine2 = new PrintingMachine(true, 40000, 10);
        PrintingMachine machine3 = new PrintingMachine(false, 100000, 16);
        PrintingMachine machine4 = new PrintingMachine(true, 80000, 12);

        PrintingHouse house1 = new PrintingHouse(
                100,
                10,
                0.03,
                5,
                500,
                20);

        PrintingHouse house2 = new PrintingHouse(
                150,
                12,
                0.05,
                7,
                900,
                25);
        // ---- END -----

        // ---- PRINT EDITIONS ----
        machine1.loadPaper(50000);
        machine2.loadPaper(40000);
        machine3.loadPaper(100000);
        machine4.loadPaper(80000);

        house1.addMachine(machine1);
        house1.addMachine(machine2);
        house1.hire(operator1);
        house1.hire(operator2);
        house1.hire(manager1);

        house2.addMachine(machine3);
        house2.addMachine(machine4);
        house2.hire(operator3);
        house2.hire(operator4);
        house2.hire(manager2);

        house1.printEdition(book1, 40, machine1, false);
        house1.printEdition(book3, 30, machine2, false);
        house1.printEdition(newspaper1, 200, machine1, false);
        house1.printEdition(newspaper3, 120, machine2, false);
        house1.printEdition(poster4, 170, machine2, true);
        house1.printEdition(poster5, 160, machine2, true);

        house2.printEdition(book2, 50, machine3, false);
        house2.printEdition(book4, 35, machine4, false);
        house2.printEdition(newspaper2, 200, machine3, false);
        house2.printEdition(newspaper3, 100, machine4, false);
        house2.printEdition(poster1, 200, machine3, false);
        house2.printEdition(poster2, 300, machine4, true);
        house2.printEdition(poster3, 150, machine4, true);
        // ---- END ----

        // ---- SAVE FILES ----
        house1.saveRecordsToFile("./recordsHouse1.txt");
        house2.saveRecordsToFile("./recordsHouse2.txt");

        house1.serializeEmployees("./employeesHouse1.txt");
        house2.serializeEmployees("./employeesHouse2.txt");
        // ---- END ----

        System.out.println(house1.deserializeEmployeesAndGetNames("./employeesHouse1.txt"));
        System.out.println(house2.deserializeEmployeesAndGetNames("./employeesHouse2.txt"));
    }
}