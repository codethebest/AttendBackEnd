package com.liyolomoko.attendeventappfrontend.factories.user;

import com.liyolomoko.attendeventappfrontend.domain.user.PostAnEvent;

/**
 * Created by Leo on 4/24/2016.
 */
public class PostAnEventFactory {
    public static PostAnEvent getPostAnEvent (String postText,String hashTag)
    {
        return new PostAnEvent.Builder()
                .post(postText)
                .tagline(hashTag)
                .build();
}
}
