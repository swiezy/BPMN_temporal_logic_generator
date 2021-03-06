/*
 * Copyright (c) 2015, pl.edu.agh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package pl.edu.agh.kis.core.data;

/**
 *
 * @author Adam Świeżowski, Jakub Piotrowski
 * Represents BPMN pattern type
 */
public enum StructNodeType {

    SEQUENCE, SYNCHRONIZATION, SIMPLE_MERGE, MULTIPLE_CHOICE, PARALLEL_SPLIT, 
    EXCLUSIVE_CHOICE, IMPLICIT_TERMINATION, MULTIPLE_MERGE, DISCRIMINATOR, 
    SYNCHRONIZING_MERGE;
    
    /**
     * 
     * @param type pattern type
     * @return the number of nodes in the pattern
     */
    public static int getArgsCount(StructNodeType type){
        int args = 2;
        if(type==SYNCHRONIZING_MERGE || type==DISCRIMINATOR || type==MULTIPLE_MERGE){
            args = 4;
        }
        if(type == IMPLICIT_TERMINATION || type == MULTIPLE_CHOICE || type == EXCLUSIVE_CHOICE || type == PARALLEL_SPLIT || type == SYNCHRONIZATION || type == SIMPLE_MERGE){
            args = 3;
        }
        return args;
    }
}
