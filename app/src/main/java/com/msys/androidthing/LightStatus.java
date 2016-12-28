package com.msys.androidthing;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Chokkar on 28/12/2016.
 */

@IgnoreExtraProperties
public class LightStatus {

        public int delay;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public LightStatus() {
        }

        public LightStatus(int delay) {
            this.delay = delay;
        }
}
