package com.droidmentor.mouroujnaClean;

/**
 * Created by lenovo on 10/04/2018.
 */

public class requete {

        private int id;
        private String nom;
        private String prenom;
        private String req;
        private String date;
        private String etat;
        private String image;

        public requete(int id, String nom, String req,String date, String etat, String image) {
            this.id = id;
            this.nom = nom;
            this.date = date;
            this.req = req;
            this.etat = etat;
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }



        public String getReq() {
            return req;
        }
        public String getDate() {
        return date;
    }

        public String getEtat() {
            return etat;
        }

        public String getImage() {
            return image;
        }

}
