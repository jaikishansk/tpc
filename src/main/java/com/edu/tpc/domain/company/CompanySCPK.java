package com.edu.tpc.domain.company;

/**
 *
 * @author Jaikishan
 * 10th Feb 2013
 */

import javax.persistence.Column; 
import javax.persistence.Embeddable;

@Embeddable
public class CompanySCPK implements java.io.Serializable {
        @Column private int orgId;
        @Column private int streamId;
        @Column private int companyId; 

        public CompanySCPK() {
        } 
        
        public CompanySCPK(int orgId, int companyId, int streamId) {
            this.orgId = orgId;
            this.companyId = companyId;
            this.streamId = streamId;
        } 
        
        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getStreamId() {
            return streamId;
        }

        public void setStreamId(int streamId) {
            this.streamId = streamId;
        } 
        
        @Override
        public boolean equals(Object object) { 
            if (!(object instanceof CompanySCPK)) {
                return false;
            } 
            CompanySCPK other = (CompanySCPK) object;
            return  orgId==other.getOrgId()
                        &&
                        companyId==other.getCompanyId()
                        &&
                        streamId==other.getStreamId();
        }
        
        @Override
        public int hashCode() { 
                int hash=5 ;
                hash=31*hash+orgId; 
                hash=31*hash+companyId;
                hash=31*hash+streamId;
                return hash;
        } 
}
