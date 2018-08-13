package com.rdev.chatapp.preferences;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


//para dizer onde posso usar. Se for no background tinha outro nome. Para correr em runtime....
public class PreferencesInfo {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PreferenceInfo {

    }
}
