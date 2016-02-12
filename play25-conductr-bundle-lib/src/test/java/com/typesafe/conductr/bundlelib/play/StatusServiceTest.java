package com.typesafe.conductr.bundlelib.play;

import akka.util.Timeout;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import java.util.Optional;

import play.libs.concurrent.HttpExecution;
import play.test.WithApplication;
import scala.concurrent.duration.Duration;
import com.typesafe.conductr.lib.play.ConnectionContext;

import static org.junit.Assert.assertEquals;

public class StatusServiceTest extends WithApplication {

    @Test
    public void return_None_when_running_in_development_mode() throws Exception {
        ConnectionContext cc = ConnectionContext.create(HttpExecution.defaultContext());
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));

        assertEquals(
            StatusService.getInstance().signalStartedOrExitWithContext(cc).toCompletableFuture().get(timeout.duration().toMillis(), TimeUnit.MILLISECONDS),
            Optional.empty()
        );

        assertEquals(
            StatusService.getInstance().signalStartedWithContext(cc).toCompletableFuture().get(timeout.duration().toMillis(), TimeUnit.MILLISECONDS),
                Optional.empty()
        );
    }
}
