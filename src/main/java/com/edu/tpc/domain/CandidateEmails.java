 package com.edu.tpc.domain;

 // Date: 12/1/2013
/**
 *
 * @author Jaikishan
 */
public class CandidateEmails {
    
        private int candidateUID;
        private String[] emailIds;

        public int getCandidateUID() {
            return candidateUID;
        }

        public void setCandidateUID(int candidateUID) {
            this.candidateUID = candidateUID;
        }

        public String[] getEmailIds() {
            return emailIds;
        }

        public void setEmailIds(String[] emailIds) {
            this.emailIds = emailIds;
        } 
}
