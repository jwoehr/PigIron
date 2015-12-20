/*
 * Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Sun Dec 20 02:40:31 UTC 2015
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * <tt>Image_Disk_Copy_DM</tt> VSMAPI Function
 */
public class ImageDiskCopyDM extends BuilderFunctionProxy {


    /** Read-only (R/O) access */
    public static final String IMAGE_DISK_MODE_R = "R";

    /** Read-only (R/O) access is desired even if the owner or another user has a link to the minidisk in write status */
    public static final String IMAGE_DISK_MODE_RR = "RR";

    /** Write access */
    public static final String IMAGE_DISK_MODE_W = "W";

    /** Write access is desired. Only R/O access allowed if the owner or any other user has a link to the minidisk in read or write status. */
    public static final String IMAGE_DISK_MODE_WR = "WR";

    /** Multiple access is desired' */
    public static final String IMAGE_DISK_MODE_M = "M";

    /** Write or any exclusive access is allowed to the minidisk unless another user already has write access to it. */
    public static final String IMAGE_DISK_MODE_MR = "MR";

    /** Write access is allowed to the disk unconditionally except for existing stable or exclusive links */
    public static final String IMAGE_DISK_MODE_MW = "MW";

    /**
     * Constructor does nothing.
     */ 
    public ImageDiskCopyDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("image_disk_number", "", "The virtual device address of the disk to be copied"),
            new Parameter("source_image_name", "", "The name of the virtual image that owns the image disk being copied"),
            new Parameter("source_image_disk_number", "", "The image disk number of the virtual image that owns the disk being copied"),
            new Parameter("image_disk_allocation_type", "", "Location or allocation"),
            new Parameter("allocation_area_name_or_volser", "", "Like it says"),
            new Parameter("image_disk_mode", "", "The access mode requested for the disk"),
            new Parameter("read_password", "", "Defines the read password that will be used for accessing the disk"),
            new Parameter("write_password", "", "Defines the write password that will be used for accessing the disk"),
            new Parameter("multi_password", "", "Defines the multi password that will be used for accessing the disk") } ;
    }
}
