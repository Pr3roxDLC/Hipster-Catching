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
        }catch (Exception ignored){
            throw  new RuntimeException();
        }
    }

    //Takes in a Runnable and prints the stackTrace if any Exceptions were thrown by it
    public static void stacktrace(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Takes in a Runnable r and lets a Consumer c consume any Exceptions thrown by it
    public static void catchIfThrown(ThrowingRunnable r, Consumer<Exception> c){
        try{
            r.run();
        }catch (Exception e){
            c.accept(e);
        }
    }

    //Takes in a Runnable and returns an Optional<Exception> if one was thrown
    public static Optional<Exception> asOptional(ThrowingRunnable runnable){
        Optional<Exception> optionalException = Optional.empty();
        try{
            runnable.run();
        }catch (Exception e){
            optionalException = Optional.of(e);
        }
        return  optionalException;
    }

    //Takes in a Runnable and returns a boolean if an Exception was thrown
    public static boolean didThrow(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Exception e){
            return true;
        }
        return false;
    }

    //Returns a Nullable Exception if any Exception was thrown, returns null if none were thrown
    public static @Nullable Exception getException(ThrowingRunnable runnable){
        try{
            runnable.run();
        }catch (Exception e){
            return e;
        }
        return null;
    }

    //Takes in a Runnable first and a Runnable then, which will only be run if no Exceptions were thrown while running first
    public static void ifNoneThrown(ThrowingRunnable first, Runnable then){
        try {
            first.run();
        }catch (Exception e){
            return;
        }
        then.run();
    }


    public static void tryCatchRethrow(ThrowingRunnable runnable, Consumer<Exception> consumer) throws Exception {
        try{
            runnable.run();
        }catch (Exception e){
            consumer.accept(e);
            throw e;
        }
    }




}
