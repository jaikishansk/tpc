package com.edu.tpc.domain.company;

/**
 *
 * @author Jaikishan
 * 10th Feb 2013
 */

import javax.persistence.Column; 
import javax.persistence.Embeddable;

@Embeddable
public class CompanyQCPK implements java.io.Serializable {
        @Column private int orgId;
        @Column private int courseId;
        @Column private Integer companyId; 

        public CompanyQCPK() {
        } 
        
        public CompanyQCPK(int orgId, int companyId, int courseId) {
            this.orgId = orgId;
            this.companyId = companyId;
            this.courseId = courseId;
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

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        } 
        
        @Override
        public boolean equals(Object object) { 
            if (!(object instanceof CompanyQCPK)) {
                return false;
            } 
            CompanyQCPK other = (CompanyQCPK) object;
            return  orgId==other.getOrgId()
                        &&
                        companyId==other.getCompanyId()
                        &&
                        courseId==other.getCourseId();
        }
        
        @Override
        public int hashCode() { 
                int hash=5 ;
                hash=31*hash+orgId; 
                hash=31*hash+companyId;
                hash=31*hash+courseId;
                return hash;
        }

		@Override
		public String toString() {
			return "CompanyQCPK [orgId=" + orgId + ", courseId=" + courseId
					+ ", companyId=" + companyId + "]";
		} 
}
