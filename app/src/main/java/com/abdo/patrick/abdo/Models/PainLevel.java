package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class PainLevel {

        public PainLevel() {
        }

        public PainLevel(int id, String level) {
                this.id = id;
                this.level = level;
        }

        private int id;
        private String level;
        private String createdTime;
        private String modifiedTime;
}
