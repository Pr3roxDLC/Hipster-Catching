package catcher;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class Catcher {

    private Exception exception = null;

    public static Catcher TRY(ThrowingRunnable runnable){
        Catcher catcher = new Catcher();
        try{
            runnable.run();
        } catch (Exception e) {
            catcher.exception = e;
        }
        return catcher;
    }

    public Catcher CATCH(Consumer<Exception> consumer){
        if(exception!=null)consumer.accept(exception);
        return this;
    }

    public Catcher FINALLY(ThrowingRunnable runnable){
        exception = null;
        try{
            runnable.run();
        } catch (Exception e) {
            exception = e;
        }
        return this;
    }

}
