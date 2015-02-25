package com.innovativemobileapps.ngujjari.squareapp;

import android.app.Application;

/**
 * Created by ngujjari on 2/21/15.
 */
public class MatchApplication extends Application {

    public MatchSequence getMs() {
        return ms;
    }

    public void setMs(MatchSequence ms) {
        this.ms = ms;
    }

    private MatchSequence ms = null;

}
