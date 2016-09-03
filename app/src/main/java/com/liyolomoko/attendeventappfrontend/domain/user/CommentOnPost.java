package com.liyolomoko.attendeventappfrontend.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leo on 4/24/2016.
 */
public class CommentOnPost implements Serializable{

    private Long id;
    private String post;
    private Date date;


    public CommentOnPost ()
    {

    }

    public Long getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public Date getDate() {
        return date;
    }

    public CommentOnPost (Builder builder)
    {this.date=builder.date;
        this.id=builder.id;
        this.post=builder.post;
    }

    public static class Builder
    {
        private Long id;
        private String post;
        private Date date;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder post (String value)
        {
            this.post = value;
            return this;
        }
        public Builder date (Date value)
        {
            this.date = value;
            return this;
        }

        public Builder copy (CommentOnPost commentOnPost)
        {
            this.date =commentOnPost.date;
            this.id=commentOnPost.id;
            this.post=commentOnPost.post;
            return this;
        }

        public CommentOnPost build()
        {
            return new CommentOnPost(this);
        }
    }
}
