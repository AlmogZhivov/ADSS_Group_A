package com.Superlee.HR.Frontend;

import com.Superlee.HR.Backend.Service.HRService;

import java.util.Scanner;

//import com.google.gson.Gson;

/**
 * Command Line Interface for interacting with the HR system
 * everything is hardcoded and no error handling is done
 * this is a naive implementation and will be replaced with a GUI
 */
public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HRService hrService = HRService.getInstance();
//    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String input, output;
        System.out.println("Type 'help' for a list of available commands, or 'exit' to quit");
        do {
            input = scanner.nextLine();
            if (input.equals("exit"))
                break;

            output = call(input);
            if (!output.isEmpty())
                System.out.println(output);
        } while (true);
    }

    public static String call(String command) {
        if (command == null || command.isEmpty()) {
            System.out.println("Invalid command");
            return "";
        }

        String[] parts = command.split("\\s+");

        if (parts.length == 0) {
            System.out.println("Invalid command");
            return "";
        }

        String action = parts[0];
        switch (action) {
            case "help":
                System.out.println("""
                        List of available commands:

                        workers
                            get a list of all workers

                        workers -r <role>
                            get a list of all workers with the specified role

                        workers -n <firstname> <surname>
                            get a worker with the specified name

                        workers -i <id>
                            get a worker with the specified id

                        workers -s <id>
                            get a list of all workers assigned to the specified shift

                        addw <id> <firstname> <surname>
                            add a new worker with the specified id, first name and second name

                        assw <worker_id> <shift_id> <role>
                            assign a worker to a shift

                        unassw <worker_id> <shift_id>
                            unassign a worker from a shift

                        shift <id>
                            get a shift with the specified id

                        shift -a <id>
                            get a list of all workers assignable to the specified shift

                        shift -r <id> <role> <amount>
                            sets the amount of workers with the specified role needed for a shift

                        adds <start> <end>
                            add a new shift with the specified start and end time
                            must be formatted as yyyy-MM-ddTHH:mm (e.g. 1996-04-15T00:00)
                        """);
                return "";

            case "workers":
                if (parts.length == 1)
                    return hrService.getAllWorkers();
                else if (parts.length >= 3) {
                    switch (parts[1]) {
                        case "-r":
                            return hrService.getWorkersByRole(parts[2]);
                        case "-n":
                            if (parts.length == 4)
                                return hrService.getWorkersByName(parts[2], parts[3]);
                            else {
                                System.out.println("Invalid number of args");
                                return "";
                            }
                        case "-i":
                            return hrService.getWorkerById(parts[2]);
                        case "-s":
                            if (parts.length == 3)
                                return tryParseInt(parts[2]) ? hrService.getWorkersByShift(Integer.parseInt(parts[2])) : "Invalid ID";
                            else {
                                System.out.println("Invalid number of args");
                                return "";
                            }

                        default: {
                            System.out.println("Invalid flag");
                            return "";
                        }

                    }
                }
            case "addw":
                if (parts.length == 4)
                    return hrService.addNewWorker(parts[1], parts[2], parts[3]);
                else {
                    System.out.println("Invalid number of args");
                    return "";
                }


            case "assw":
                if (parts.length == 4)
                    return tryParseInt(parts[2]) ? hrService.assignWorker(parts[1], Integer.parseInt(parts[2]), parts[3]) : "Invalid ID";
                else {
                    System.out.println("Invalid number of args");
                    return "";
                }

            case "unassw":
                if (parts.length == 3)
                    return tryParseInt(parts[2]) ? hrService.unassignWorker(parts[1], Integer.parseInt(parts[2])) : "Invalid ID";
                else {
                    System.out.println("Invalid number of args");
                    return "";
                }

            case "shift":
                if (parts.length == 2)
                    if (parts[1].equals("-a") || parts[1].equals("-r")) {
                        System.out.println("Invalid number of args");
                        return "";
                    } else
                        return tryParseInt(parts[1]) ? hrService.getShift(Integer.parseInt(parts[1])) : "Invalid ID";
                else if (parts.length == 3) {
                    if (parts[1].equals("-a"))
                        return tryParseInt(parts[2]) ? hrService.getAssignableWorkersForShift(Integer.parseInt(parts[2])) : "Invalid ID";
                    System.out.println("Invalid number of args");
                    return "";
                } else if (parts.length == 5) {
                    if (parts[1].equals("-r")) {
                        if (tryParseInt(parts[2]) && tryParseInt(parts[4]))
                            return hrService.setShiftRequiredWorkersOfRole(Integer.parseInt(parts[2]), parts[3], Integer.parseInt(parts[4]));
                        else {
                            System.out.println("Invalid ID or amount");
                            return "";
                        }
                    } else {
                        System.out.println("Invalid something");
                        return "";
                    }
                } else {
                    System.out.println("Invalid number of args");
                    return "";
                }

            case "adds":
                if (parts.length == 3)
                    if (tryParseDT(parts[1]) && tryParseDT(parts[2]))
                        return hrService.addNewShift(parts[1], parts[2]);
                    else {
                        System.out.println("Invalid datetime format");
                        return "";
                    }

            default:
                System.out.println("Invalid command");
                return "";
        }
    }

    private static boolean tryParseDT(String part) {
        return part.length() == 16 &&
               part.charAt(4) == '-' &&
               part.charAt(7) == '-' &&
               part.charAt(10) == 'T' &&
               part.charAt(13) == ':' &&
               tryParseInt(part.substring(0, 4)) &&
               tryParseInt(part.substring(5, 7)) &&
               tryParseInt(part.substring(8, 10)) &&
               tryParseInt(part.substring(11, 13)) &&
               tryParseInt(part.substring(14, 16));
    }

    private static boolean tryParseInt(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
