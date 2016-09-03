package com.liyolomoko.attendeventappfrontend.factories.user;

import com.liyolomoko.attendeventappfrontend.domain.user.CommentOnPost;

/**
 * Created by Leo on 4/24/2016.
 */
public class CommentOnPostFactory {
    public static CommentOnPost getCommentPost(String postText)
    {
        return new CommentOnPost.Builder()
                .post(postText)
                .build();
    }
}
