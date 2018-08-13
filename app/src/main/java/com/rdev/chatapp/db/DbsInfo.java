package com.rdev.chatapp.db;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

public class DbsInfo {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DbInfo {

    }
}
