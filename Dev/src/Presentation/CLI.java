package Presentation;

import java.util.Objects;
import java.util.Scanner;

import com.google.gson.Gson;

import Service.PresentService;
import Service.Responses.Response;

public class CLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PresentService prService = PresentService.getInstance();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String input;
        Response output;
        prService.printMenu();
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("29")) {
                System.out.println("Goodbye!");
                break;
            }
            output = prService.call(input);
            // print the output response as string
            if (!Objects.equals(gson.toJson(output), "{}"))
                System.out.println(gson.toJson(output));
        }
    }
}
