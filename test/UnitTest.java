import akka.actor.ActorSystem;
import controllers.CompareFacesController;
import org.junit.Test;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class UnitTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    // Unit test a controller
    @Test
    public void testCompare() {
        final CompareFacesController controller = new CompareFacesController();
        Result result = controller.compare("abhinav");
        assertThat(contentAsString(result)).isEqualTo("OK");
    }

    // Unit test a controller with async return
    /*@Test
    public void testAsync() {
        final ActorSystem actorSystem = ActorSystem.create("test");
        try {
            final ExecutionContextExecutor ec = actorSystem.dispatcher();
            final AsyncController controller = new AsyncController(actorSystem, ec);
            final CompletionStage<Result> future = controller.message();

            // Block until the result is completed
            await().until(() -> {
                assertThat(future.toCompletableFuture()).isCompletedWithValueMatching(result -> {
                    return contentAsString(result).equals("Hi!");
                });
            });
        } finally {
            actorSystem.terminate();
        }
    }*/

}
