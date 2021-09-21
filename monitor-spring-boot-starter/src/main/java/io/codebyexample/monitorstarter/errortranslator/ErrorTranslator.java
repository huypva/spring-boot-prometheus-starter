package io.codebyexample.monitorstarter.errortranslator;

import java.util.Optional;

/**
 * @author huypva
 */
public interface ErrorTranslator {

  String translate(Optional<Throwable> err);
}
