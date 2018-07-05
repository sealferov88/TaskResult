/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;

import java.io.InputStream;

/**
 *
 * @author Alex
 */
public interface ProgramFileAware {
    void executeProgramFile(InputStream is);
    
    
}
