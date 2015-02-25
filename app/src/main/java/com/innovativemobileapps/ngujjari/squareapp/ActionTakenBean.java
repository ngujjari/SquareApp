package com.innovativemobileapps.ngujjari.squareapp;

/**
 * Created by ngujjari on 2/21/15.
 */
public class ActionTakenBean {
    private String player;

    private Integer fromNd;
    private Integer toNd;

    public ActionTakenBean(String player, Integer fromNd, Integer toNd)
    {
        this.player = player;
        this.fromNd = fromNd;
        this.toNd = toNd;
    }

    public void reset(String player)
    {
        this.player = player;
        this.fromNd = -1;
        this.toNd = -1;
    }
    public Integer getFromNd() {
        return fromNd;
    }

    public void setFromNd(Integer fromNd) {
        this.fromNd = fromNd;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getToNd() {
        return toNd;
    }

    public void setToNd(Integer toNd) {
        this.toNd = toNd;
    }




}