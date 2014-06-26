package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 10th Feb 2013
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ResumeUpload { 
        private CommonsMultipartFile file; 
        
        public CommonsMultipartFile getFile() {
            return file;
        } 
        public void setFile(CommonsMultipartFile file) {
            this.file = file; 
        }  
}
