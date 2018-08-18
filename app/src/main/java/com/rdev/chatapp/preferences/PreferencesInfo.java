package com.rdev.chatapp.preferences;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


public class PreferencesInfo {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PreferenceInfo {

    }
}
