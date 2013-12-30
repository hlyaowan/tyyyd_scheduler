package com.tyyd.scheduler.engine;

import java.util.*;

public class JShell {

    static Scanner         in       = new Scanner(System.in);
    static List<String>    input;

    static Directory       root;
    static Directory       home;
    static Directory       pwd;
    static List<Directory> path     = new ArrayList<Directory>();

    static String          hostname = "TARDIS";
    static String          username = "Doctor";
    static String          mode     = "$";

    static boolean         running;

    public static void main(String[] args) {
        root = new Directory(System.getProperty("user.dir"), "/");
        home = new Directory(root, "/home");
        pwd = home;

        path.add(new Directory(root, "/Utilities"));

        running = true;
        while (running) {
            System.out.print(username + "@" + hostname + ":" + pwd.representativePath + " " + mode + " ");
            input = Arrays.asList(in.nextLine().split(" "));

            for (Directory d : path) {
                System.out.println(d);
            }

            if (input.get(0).contentEquals("exit")) {
                running = false;
            }
        }
    }
}
