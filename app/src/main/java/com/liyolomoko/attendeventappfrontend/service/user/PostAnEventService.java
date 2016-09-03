package com.liyolomoko.attendeventappfrontend.service.user;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.user.PostAnEvent;

/**
 * Created by Leo on 5/8/2016.
 */
public interface PostAnEventService {
    void postEvent(Context context, PostAnEvent postAnEvent);
    void editPost(Context context, PostAnEvent postAnEvent);
}
