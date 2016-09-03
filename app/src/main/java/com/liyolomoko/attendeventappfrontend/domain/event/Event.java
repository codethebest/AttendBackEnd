package com.liyolomoko.attendeventappfrontend.domain.event;

import java.io.Serializable;

/**
 * Created by Leo on 4/18/2016.
 */
public class Event implements Serializable{
    private String name ;
    private String tagline;
    private String host;
    private String description;
    private byte[] eventFlyerImage;
    private Long id;
    public Event ()
    {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getHost() {
        return host;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getEventFlyerImage() {
        return eventFlyerImage;
    }

    public Event (Builder builder)
    {
        this.tagline = builder.tagline;
        this.description = builder.description;
        this.eventFlyerImage = builder.eventFlyerImage;
        this.name = builder.name;
        this.host = builder.host;
        this.id = builder.id;
    }
    public static class Builder
    {
        private String name ;
        private String tagline;
        private String host;
        private String description;
        private byte[] eventFlyerImage;
        private Long id;
        public Builder name (String value)
        {
            this.name = value;
            return this;
        }
        public Builder tagline (String name)
        {
            this.tagline = name;
            return this;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder host (String value)
        {
            this.host = value;
            return this;
        }

        public Builder description (String value)
        {
            this.description = value;
            return this;
        }

        public Builder eventFlyerImage (byte[] value)
        {
            this.eventFlyerImage = value;
            return this;
        }

        public Builder copy(Event event)
        {
            this.description = event.description;
            this.tagline = event.tagline;
            this.host = event.host;
            this.name =event.name;
            this.eventFlyerImage= event.eventFlyerImage;
            this.id=event.id;
            return this;
        }

        public Event build()
        {
            return new Event(this);
        }


    }
}
