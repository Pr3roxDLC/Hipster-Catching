import catcher.Catcher;
import catcher.CatcherUtil;

import static catcher.Catcher.TRY;

public class Main {
    public static void main(String[] args) {

        //Catch any Exception
        TRY(() -> Thread.sleep(1000)).CATCH(Throwable::printStackTrace);

        //Simply ignore exceptions with less boilerplate code
        TRY(() -> Thread.sleep(1000));

        //Simple try/catch/finally clause replacement
        TRY(() -> Thread.sleep(1000)).CATCH(Throwable::printStackTrace).FINALLY(() -> System.out.println("Finished"));

        //Chain chained finally/catch statements
        TRY(() -> Thread.sleep(1000))
                .CATCH(Throwable::printStackTrace)
                .FINALLY(() -> Thread.sleep(1000))
                .CATCH(Throwable::printStackTrace)
                .FINALLY(() -> System.out.println("Finished"));



        //Catcher Utility
        boolean bool1 = CatcherUtil.didThrow(() -> Thread.sleep(1000));

        CatcherUtil.asOptional(() -> Thread.sleep(1000)).ifPresent(Throwable::printStackTrace);

        CatcherUtil.ifNoneThrown(() -> System.out.println("First this, "), () -> System.out.println(" then this if nothing went wrong"));

        CatcherUtil.catchIfThrown(()->Thread.sleep(1000), Throwable::printStackTrace);

    }
}
