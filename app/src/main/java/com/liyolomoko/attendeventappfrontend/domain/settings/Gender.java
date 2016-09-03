package com.liyolomoko.attendeventappfrontend.domain.settings;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/04/09.
 */
public class Gender implements Serializable {

    private String id;
    private String name;

    private Gender(){}
    public Gender(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static class Builder{
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {

            return this;
        }
        public Builder copy(Gender gender){
            this.id = gender.id;
            this.name = gender.name;


            return this;
        }
        public Gender build(){
            return new Gender(this);
        }
    }
}
