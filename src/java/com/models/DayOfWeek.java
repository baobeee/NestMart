    package com.models;

    public class DayOfWeek {
        private int dayID;
        private String dayName;

        // Constructors
        public DayOfWeek() {}

        public DayOfWeek(int dayID, String dayName) {
            this.dayID = dayID;
            this.dayName = dayName;
        }

        // Getters and setters
        public int getDayID() {
            return dayID;
        }

        public void setDayID(int dayID) {
            this.dayID = dayID;
        }

        public String getDayName() {
            return dayName;
        }

        public void setDayName(String dayName) {
            this.dayName = dayName;
        }
    }
