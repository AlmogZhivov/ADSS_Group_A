package Presentation;

import java.util.Scanner;

import com.google.gson.Gson;

import Service.PresentService;

public class CLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PresentService prService = PresentService.getInstance();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String input, output;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit"))
                break;
            output = prService.call(input);
            System.out.println(gson.toJson(output));
        }
    }
}
