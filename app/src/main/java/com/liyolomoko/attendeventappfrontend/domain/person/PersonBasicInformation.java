package com.liyolomoko.attendeventappfrontend.domain.person;

import java.util.Date;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonBasicInformation {

    private String sex;
    private String hometown;
    private int nowMonth, nowYear;
    private int age;
    private String interestedIn;
    private String id;

    public String getId() {
        return id;
    }

    public PersonBasicInformation()
    {

    }

    public String getSex() {
        return sex;
    }

    public String getHometown() {
        return hometown;
    }

    public String getInterestedIn() {
        return interestedIn;
    }

    public int getNowMonth() {
        return nowMonth;
    }

    public int getNowYear() {
        return nowYear;
    }

    public void setDateofBirth(int year, int month, int day) {
        Date now = new Date();
        nowMonth = now.getMonth() + 1;
        nowYear = now.getYear() + 1900;
        age = nowYear - year;

        if (month > nowMonth) {
            age--;
        } else if (month == nowMonth) {
            int nowDay = now.getDate();

            if (day > nowDay) {
                age--;
            }
        }
    }

    public int getAge() {
        return age;
    }

    public PersonBasicInformation(Builder builder)
    {
        this.age = builder.age;
        this.sex=builder.sex;
        this.interestedIn = builder.interestedIn;
        this.nowMonth = builder.nowMonth;
        this.nowYear = builder.nowYear;
        this.hometown =builder.hometown;
    }

    public static class Builder
    {
        private String sex;
        private String hometown;
        private int nowMonth, nowYear;
        private int age;
        private String interestedIn;
        private String id;

        public Builder age(int value)
        {
            this.age=value;
            return this;
        }

        public Builder sex (String value)
        {
            this.sex = value;
            return this;
        }

        public Builder hometown(String value)
        {
            this.hometown = value;
            return this;
        }

        public Builder nowMonth (int value)
        {
            this.nowMonth = value;
            return this;
        }

        public Builder id (String value)
        {
            this.id = value;
            return this;
        }

        public Builder nowYear(int value)
        {
            this.nowYear = value;
            return this;
        }

        public Builder interestedIn(String value)
        {
            this.interestedIn = value;
            return this;
        }

        public Builder copy (PersonBasicInformation personBasicInformation)
        {
            this.interestedIn = personBasicInformation.interestedIn;
            this.sex = personBasicInformation.sex;
            this.nowMonth= personBasicInformation.nowMonth;
            this.nowYear= personBasicInformation.nowYear;
            this.age=personBasicInformation.age;
            this.hometown=personBasicInformation.hometown;
            this.id=personBasicInformation.id;
            return this;
        }

        public PersonBasicInformation build()
        {
            return new PersonBasicInformation(this);
        }

    }
}
