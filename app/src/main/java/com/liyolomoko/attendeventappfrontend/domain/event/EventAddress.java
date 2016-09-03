package com.liyolomoko.attendeventappfrontend.domain.event;

import java.io.Serializable;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventAddress implements Serializable
{
        private String street;
        private String sub;
        private String country;
        private String city;
        private Long id;

        public EventAddress()
        {

        }

    public Long getId() {
        return id;
    }

    public String getCity() {
            return city;
        }

        public String getSub() {
            return sub;
        }

        public String getCountry() {
            return country;
        }

        public String getStreet() {
            return street;
        }

        public EventAddress (Builder builder)
        {
            this.street = builder.street;
            this.city = builder.city;
            this.country = builder.country;
            this.sub = builder.sub;
            this.id = builder.id;
        }

public static class Builder
{
    private String street;
    private String sub;
    private String country;
    private String city;
    private Long id;
    public Builder street (String value)
    {
        this.street = value;
        return this;
    }

    public Builder sub(String value)
    {
        this.sub = value;
        return this;
    }

    public Builder id(Long value) {
        this.id = value;
        return this;
    }

    public Builder country(String value)
    {
        this.country = value;
        return this;
    }
    public Builder city(String value)
    {
        this.city = value;
        return this;
    }

    public Builder copy(EventAddress eventAddress)
    {
        this.street = eventAddress.street;
        this.sub = eventAddress.sub;
        this.city = eventAddress.city;
        this.country = eventAddress.country;
        this.id=eventAddress.id;
        return this;
    }
    public EventAddress build()
    {
        return new EventAddress(this);
    }

}

}
