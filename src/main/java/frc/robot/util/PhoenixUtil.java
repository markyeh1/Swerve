package frc.robot.util;

import com.ctre.phoenix6.StatusCode;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class PhoenixUtil {
  protected static final Executor asyncExecutor = Executors.newFixedThreadPool(4);

  /** Attempts to run the command until no error is produced. */
  public static void tryUntilOk(int maxAttempts, Supplier<StatusCode> command) {
    for (int i = 0; i < maxAttempts; i++) {
      var error = command.get();
      if (error.isOK()) break;
    }
  }

  /** Attempts to run the command until no error is produced. */
  public static void tryUntilOkAsync(int maxAttempts, Supplier<StatusCode> command) {
    asyncExecutor.execute(() -> tryUntilOk(maxAttempts, command));
  }
}
