package com.learning.jms.jmsstarter.util;

import java.util.Scanner;
import java.util.function.Consumer;

public class UtilScanner {
    public static void consoleLineScanner(Consumer<String> consumer){
        boolean go = true;
        Scanner in = new Scanner(System.in);
        while(go){
            String message = null;
            if(in.hasNext() && "EXIT".equalsIgnoreCase(message=in.nextLine())) break;
            consumer.accept(message);
        }

    }
}
