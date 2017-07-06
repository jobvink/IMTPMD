package com.hsl.imtpmd.imtpmd.database;

/**
 * Created by Job Vink on 15-6-2017.
 *
 */

public class DatabaseInfo {

    public class VerplichtvakTables {
        public static final String VERPLICHTVAK = "specialisatievakModel";
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
        public static final String JAAR_ID = "jaar_id";
        public static final String SPECIALISATIE_ID = "specialisatie_id";
    }



    public class UserTables {
        public static final String USER = "user";
    }

    public class UserColumn {
        public static final String ID = "id";
        public static final String GEBRUIKERSNAAM = "gebruikersnaam";
        public static final String WACHTWOORD = "wachtwoord";
        public static final String SPECIALISATIE = "specialisatie";
        public static final String PROPEDEUZE_EC = "propedeuze_ed";
        public static final String HOOFDFASE_EC = "hoofdfase_ed";
    }

    public class User_VerplichtvakTables {
        public static final String USER_VERPLICHTVAK = "user_verplichtvak";
    }

    public class User_verplichtvakColumn {
        public static final String USER_ID = "user_id";
        public static final String VERPLICHTVAK_ID = "verplichtvak_id";
        public static final String BEHAALD = "behaald";
        public static final String CIJFER = "cijfer";
    }

    public class User_specialisatievakTables {
        public static final String USER_SPECIALISATIEVAK = "user_specialisatievak";
    }

    public class User_specialisateivakColumn {
        public static final String USER_ID = "user_id";
        public static final String SPECIALISATIEVAK_ID = "specialisatievak_id";
        public static final String BEHAALD = "behaald";
        public static final String CIJFER = "cijfer";
    }

    public class User_keuzevakTables {
        public static final String USER_KEUZEVAK = "user_keuzevak";
    }

    public class User_keuzevakColumn {
        public static final String USER_ID = "user_id";
        public static final String KEUZEVAK_ID = "keuzevak_id";
        public static final String BEHAALD = "behaald";
        public static final String CIJFER = "cijfer";
    }

}

