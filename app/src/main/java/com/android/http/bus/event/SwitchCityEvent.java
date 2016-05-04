package com.android.http.bus.event;

/**
 * Created by liyanan on 16/5/4.
 */
public class SwitchCityEvent {
    private String city;

    public SwitchCityEvent(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
