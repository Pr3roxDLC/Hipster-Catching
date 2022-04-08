import catcher.Catcher;
import catcher.CatcherUtil;

import static catcher.Catcher.TRY;

public class Main {
    public static void main(String[] args) {

        //Simply ignore exceptions with less boilerplate code
        TRY(() -> Thread.sleep(1000));

        //Catch a specified Exception
        TRY(() -> Thread.sleep(1000)).CATCH(InterruptedException.class, Throwable::printStackTrace);

        //Catch any Exception
        TRY(() -> Thread.sleep(1000)).CATCH(Throwable::printStackTrace);

        //Split Exception catching into multiple blocks and catch the rest with a generic Catch block
        TRY(() -> Thread.sleep(1000))
                .CATCH(ArrayIndexOutOfBoundsException.class, throwable -> System.out.println("Do This"))
                .CATCH(InterruptedException.class, throwable -> System.out.println("Do That"))
                .CATCH(throwable -> System.out.println("Do Everything Else"));

        //Simple try/catch/finally clause replacement
        TRY(() -> Thread.sleep(1000)).CATCH(Throwable::printStackTrace).FINALLY(() -> System.out.println("Finished"));

        //Chain finally/catch statements ??
        TRY(() -> Thread.sleep(1000))
                .CATCH(Throwable::printStackTrace)
                .FINALLY(() -> Thread.sleep(1000))
                .CATCH(Throwable::printStackTrace)
                .FINALLY(() -> System.out.println("Finished"));


        //Catcher Utility
        boolean bool1 = CatcherUtil.didThrow(() -> Thread.sleep(1000));

        CatcherUtil.asOptional(() -> Thread.sleep(1000)).ifPresent(Throwable::printStackTrace);

        CatcherUtil.ifNoneThrown(() -> System.out.println("First this, "), () -> System.out.println(" then this if nothing went wrong"));

        CatcherUtil.catchIfThrown(() -> Thread.sleep(1000), Throwable::printStackTrace);

    }
}
