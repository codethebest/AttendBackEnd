package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.user.CommentOnPost;
import com.example.leo.tpassignment61.factories.user.CommentOnPostFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/24/2016.
 */
public class CommentFactoryTest {
    @Test
    public void testPerson()throws Exception
    {
        CommentOnPost post = CommentOnPostFactory.getCommentPost("I cant wait hey");
        Assert.assertNotNull(post.getPost());
    }
}
