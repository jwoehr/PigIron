/*
 * Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 * PO Box 51, Golden, Colorado 80402-0051 USA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the
 *         distribution.
 *     * Neither the name of the PigIron Project nor the names of its
 *         contributors may be used to endorse or promote products derived
 *         from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/* Autogenerated Thu Jan  8 23:23:39 UTC 2009
 * by pigbuilderfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * {@code Image_SCSI_Characteristics_Define_DM} VSMAPI Function
 */
public class ImageSCSICharacteristicsDefineDM extends BuilderFunctionProxy {


    /** Unspecified */
    public static final int SCP_DATA_TYPE_UNSPECIFIED = 0;

    /** delete the SCP_data for the image */
    public static final int SCP_DATA_TYPE_DELETE = 1;

    /** EBCDIC (codepage 924) data */
    public static final int SCP_DATA_TYPE_EBCDIC = 2;

    /** UTF-8 encoded hex data */
    public static final int SCP_DATA_TYPE_HEX = 3;

    /**
     * Constructor does nothing.
     */ 
    public ImageSCSICharacteristicsDefineDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("boot_program", "", "The boot program number, or the keyword DELETE to delete the existing boot program number. If null, the boot program number will be unchanged."),
            new Parameter("BR_LBA", "", "The logical-block address of the boot record, or the keyword DELETE to delete the existing logical-block address. If null, the logical-block address will be unchanged."),
            new Parameter("LUN", "", "The logical unit number, or the keyword DELETE to delete the existing logical unit number. If null, the logical unit number will be unchanged."),
            new Parameter("port_name", "", "The port name, or the keyword DELETE to delete the existing port name. If null, the port name will be unchanged."),
            new Parameter("SCP_data_type", 0, "The type of data specified in the SCP_data parameter"),
            new Parameter("SCP_data", "", "The SCP data") } ;
    }
}
