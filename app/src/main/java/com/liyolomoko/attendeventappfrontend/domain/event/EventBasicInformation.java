package com.liyolomoko.attendeventappfrontend.domain.event;

import java.util.Date;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventBasicInformation {

    private Date start;
    private Date end;
    private String eventtype;
    private String id;
    public EventBasicInformation ()
    {

    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getEventtype() {
        return eventtype;
    }

    public EventBasicInformation (Builder builder)
    {
        this.end = builder.end;
        this.eventtype=builder.eventtype;
        this.start=builder.start;
        this.id= builder.id;
    }

    public static class Builder
    {
        private Date start;
        private Date end;
        private String eventtype;
        private String id;
        public Builder start (Date value)
        {
            this.start = value;
            return this;
        }
        public Builder end (Date value)
        {
            this.end = value;
            return this;
        }
        public Builder id(String value) {
            this.id = value;
            return this;
        }
        public Builder eventtye(String value)
        {
            this.eventtype = value;
            return this;
        }

        public Builder copy(EventBasicInformation  eventBasicInformation)
        {
            this.eventtype = eventBasicInformation.eventtype;
            this.start = eventBasicInformation.start;
            this.end = eventBasicInformation.end;
            this.id=eventBasicInformation.id;
            return this;
        }

        public EventBasicInformation build()
        {
            return new EventBasicInformation(this);
        }
    }

}
