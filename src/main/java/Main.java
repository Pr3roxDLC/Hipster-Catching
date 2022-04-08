import static catcher.Catcher.TRY;
import catcher.Catcher;

public class Main {
    public static void main(String[] args) {
        TRY(()->Thread.sleep(1000))
                .CATCH(Throwable::printStackTrace)
                .FINALLY(()->Thread.sleep(1000));

    }
}
