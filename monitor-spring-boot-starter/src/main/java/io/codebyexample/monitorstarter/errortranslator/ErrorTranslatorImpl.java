package io.codebyexample.monitorstarter.errortranslator;

import java.util.Optional;
import lombok.extern.log4j.Log4j2;

/**
 * @author huypva
 * @date 2021-03-30
 */
@Log4j2
public class ErrorTranslatorImpl implements ErrorTranslator {

  private static String ERR_NONE = "None";

  @Override
  public String translate(Optional<Throwable> err) {
    if (!err.isPresent()) {
      return ERR_NONE;
    }

    return err.get().getClass().getSimpleName();
  }
}
