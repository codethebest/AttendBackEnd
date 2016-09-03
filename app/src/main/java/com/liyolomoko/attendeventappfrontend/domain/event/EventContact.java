package com.liyolomoko.attendeventappfrontend.domain.event;

import java.io.Serializable;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventContact implements Serializable{

    private Long id;
    private String phone;
    private String website;
    private String email;

    public EventContact ()
    {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }


    public EventContact (Builder builder)
    {
        this.email = builder.email;
        this.phone = builder.phone;
        this.website = builder.website;
        this.id = builder.id;
    }

    public static class Builder
    {
        private String phone;
        private String website;
        private String email;
        private Long id;

        public Builder phone(String value)
        {
            this.phone = value;
            return this;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder website(String value)
        {
            this.website = value;
            return this;
        }
        public Builder email(String value)
        {
            this.email = value;
            return this;
        }

        public Builder copy(EventContact eventContact)
        {
            this.phone = eventContact.phone;
            this.website = eventContact.website;
            this.email = eventContact.email;
            this.id=eventContact.id;
            return this;
        }

        public EventContact build()
        {
            return new EventContact(this);
        }

    }
}
