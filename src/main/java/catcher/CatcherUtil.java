package catcher;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class CatcherUtil {

    //UTILITY METHODS
    //Takes in a Runnable and ignores any Exceptions thrown by it
    public static void ignore(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Throwable ignored){}
    }

    //Takes in a Runnable and prints the stackTrace if any Exceptions were thrown by it
    public static void stacktrace(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    //Takes in a Runnable r and lets a Consumer c consume any Exceptions thrown by it
    public static void catchIfThrown(ThrowingRunnable r, Consumer<Throwable> c){
        try{
            r.run();
        }catch (Throwable e){
            c.accept(e);
        }
    }

    //Takes in a Runnable and returns an Optional<Exception> if one was thrown
    public static Optional<Throwable> asOptional(ThrowingRunnable runnable){
        Optional<Throwable> optionalException = Optional.empty();
        try{
            runnable.run();
        }catch (Throwable e){
            optionalException = Optional.of(e);
        }
        return  optionalException;
    }

    //Takes in a Runnable and returns a boolean if an Exception was thrown
    public static boolean didThrow(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Throwable e){
            return true;
        }
        return false;
    }

    //Returns a Nullable Exception if any Exception was thrown, returns null if none were thrown
    public static @Nullable Throwable getException(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Throwable e){
            return e;
        }
        return null;
    }

    //Takes in a Runnable first and a Runnable then, which will only be run if no Exceptions were thrown while running first
    public static void ifNoneThrown(ThrowingRunnable first, Runnable then){
        try {
            first.run();
        }catch (Throwable e){
            return;
        }
        then.run();
    }


    public static void tryCatchRethrow(ThrowingRunnable runnable, Consumer<Throwable> consumer) throws Throwable {
        try{
            runnable.run();
        }catch (Throwable e){
            consumer.accept(e);
            throw e;
        }
    }




}
