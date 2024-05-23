package com.Superlee.HR.Frontend;

import com.Superlee.HR.Backend.Service.HRService;

import java.util.Scanner;

import com.google.gson.Gson;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HRService hrService = HRService.getInstance();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String input, output;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit"))
                break;

            output = hrService.call(input);
            System.out.println(gson.toJson(output));
        }
    }
}
