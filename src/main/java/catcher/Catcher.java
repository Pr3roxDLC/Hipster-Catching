package catcher;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class Catcher {

    private Throwable exception = null;

    public static Catcher TRY(ThrowingRunnable runnable){
        Catcher catcher = new Catcher();
        try{
            runnable.run();
        } catch (Throwable e) {
            catcher.exception = e;
        }
        return catcher;
    }

    public Catcher CATCH(Consumer<Throwable> consumer){
        if(exception!=null)consumer.accept(exception);
        exception=null;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <U extends Throwable> Catcher CATCH(Class<U> type, Consumer<U> consumer){
        if(exception==null)return this;
        if(type.isInstance(exception)){
            consumer.accept((U) exception);
            exception = null;
        }
        return this;
    }

    public Catcher FINALLY(ThrowingRunnable runnable){
        try{
            runnable.run();
        } catch (Throwable e) {
            exception = e;
        }
        return this;
    }

}
