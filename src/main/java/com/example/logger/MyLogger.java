package com.example.logger;
import java.time.LocalDateTime;

import static java.lang.System.*;
public class MyLogger
{

    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    public static void debug(Object o){out.println(GREEN+LocalDateTime.now()+" [DEBUG]: "+o.toString()+RESET);}
    public static void info(Object o){out.println(BLUE+LocalDateTime.now()+" [INFO]: "+o.toString()+RESET);}
    public static void warn(Object o){out.println(YELLOW+LocalDateTime.now()+" [WARNING]: "+o.toString()+RESET);}
    public static void error(Object o){out.println(RED+LocalDateTime.now()+" [ERROR]: "+o.toString()+RESET);}
    public static void fatal(Object o){err.println(LocalDateTime.now()+" [FATAL]: "+o.toString()+RESET);}



}
