package com.hsl.imtpmd.imtpmd.database;

/**
 * Created by Job Vink on 15-6-2017.
 *
 */

public class DatabaseInfo {

    public class VerplichtvakTables {
        public static final String VERPLICHTVAK = "verplichtvak";
    }

    public class KeuzevakTables {
        public static final String KEUZEVAK = "keuzevak";
    }

    public class SpecialisatievakTables {
        public static final String SPECIALISATIEVAK = "specialisatievak";
    }

    public class VerplichtvakColumn {
        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String NAAM = "naam";
        public static final String EC = "ec";
        public static final String JAAR_ID = "jaar_id";
        public static final String PERIODE = "periode";
    }

    public class KeuzevakColumn {
        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String NAAM = "naam";
        public static final String EC = "ec";
    }

    public class SpecialisatievakColumn {
        public static final String ID = "id";
        public static final String CODE = "code";
        public static final String NAAM = "naam";
        public static final String EC = "ec";
        public static final String SPECIALISATIE_ID = "specialisatie_id";
    }



    public class UserTables {
        public static final String USER = "user";
    }

    public class UserColumn {
        public static final String GEBRUIKERSNAAM = "gebruikersnaam";
        public static final String WACHTWOORD = "wachtwoord";
    }

}

