package com.liyolomoko.attendeventappfrontend.service.event;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.event.EventAddress;

/**
 * Created by Leo on 5/8/2016.
 */
public interface EventAddressService {
    void addPEventAddress(Context context, EventAddress address);
    void updateEventAddress(Context context, EventAddress address);
}
