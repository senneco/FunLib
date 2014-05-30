package net.senneco.funlib.app;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by senneco on 29.05.2014
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}