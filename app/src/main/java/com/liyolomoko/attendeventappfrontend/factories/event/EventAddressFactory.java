package com.liyolomoko.attendeventappfrontend.factories.event;

import com.liyolomoko.attendeventappfrontend.domain.event.EventAddress;

/**
 * Created by Leo on 4/18/2016.s
 */
public class EventAddressFactory {
    public static EventAddress getEventAddress(String street , String sub,String country ,String city)
    {
        return new EventAddress.Builder()
                .street(street)
                .sub(sub)
                .country(country)
                .city(city)
                .build();
    }
}
