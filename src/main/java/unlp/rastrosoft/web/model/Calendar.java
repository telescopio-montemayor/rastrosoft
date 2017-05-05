/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.util.ArrayList;

/**
 *
 * @author ip300
 */
public class Calendar {
    private int id, id_user, enabled, public_val;
    private String datetime, live_key;

    public Calendar(int id, int id_user, String datetime, int enabled, String live_key, int public_val) {
        this.id = id;
        this.id_user = id_user;
        this.enabled = enabled;
        this.public_val = public_val;
        this.datetime = datetime;
        this.live_key = live_key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getPublic_val() {
        return public_val;
    }

    public void setPublic_val(int public_val) {
        this.public_val = public_val;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLive_key() {
        return live_key;
    }

    public void setLive_key(String live_key) {
        this.live_key = live_key;
    }

    public ArrayList<String> getListOfStrings(){
        ArrayList<String> shift = new ArrayList<>();
        shift.add(String.valueOf(this.getId()));
        shift.add(String.valueOf(this.getId_user()));
        shift.add(this.getDatetime());
        shift.add(String.valueOf(this.getEnabled()));
        shift.add(this.getLive_key());
        shift.add(String.valueOf(this.getPublic_val()));
        return shift;
    }
    
}
