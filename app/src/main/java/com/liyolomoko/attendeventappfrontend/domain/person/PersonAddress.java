package com.liyolomoko.attendeventappfrontend.domain.person;

import java.io.Serializable;

/**
 * Created by Leo on 4/17/2016.
 */
public class PersonAddress implements Serializable{

    private String street;
    private String sub;
    private String country;
    private String city;
    private Long id;


    public PersonAddress()
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

    public PersonAddress (Builder builder)
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
        public Builder Id(Long value) {
            this.id = value;
            return this;
        }

        public Builder copy(PersonAddress personAddress)
        {
            this.street = personAddress.street;
            this.sub = personAddress.sub;
            this.city = personAddress.city;
            this.country = personAddress.country;
            this.id = personAddress.id;
            return this;
        }
        public PersonAddress build()
        {
            return new PersonAddress(this);
        }

    }

}
