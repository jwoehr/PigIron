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

/* Autogenerated Thu Nov 26 04:57:01 UTC 2015
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * <tt>Image_Disk_Create_DM</tt> VSMAPI Function
 */
public class ImageDiskCreateDM extends BuilderFunctionProxy {


    /** CYLINDERS */
    public static final int ALLOCATION_UNIT_SIZE_CYLINDERS = 1;

    /** BLK0512 */
    public static final int ALLOCATION_UNIT_SIZE_BLK0512 = 2;

    /** BLK1024 */
    public static final int ALLOCATION_UNIT_SIZE_BLK1024 = 3;

    /** BLK2048 */
    public static final int ALLOCATION_UNIT_SIZE_BLK2048 = 4;

    /** BLK4096 */
    public static final int ALLOCATION_UNIT_SIZE_BLK4096 = 5;

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

    /** Unspecified */
    public static final int IMAGE_DISK_FORMATTING_UNSPECIFIED = 0;

    /** Unformatted */
    public static final int IMAGE_DISK_FORMATTING_NONE = 1;

    /** CMS formatted with 512 bytes per block */
    public static final int IMAGE_DISK_FORMATTING_CMS0512 = 2;

    /** CMS formatted with 1024 bytes per block */
    public static final int IMAGE_DISK_FORMATTING_CMS1024 = 3;

    /** CMS formatted with 2048 bytes per block */
    public static final int IMAGE_DISK_FORMATTING_CMS2048 = 4;

    /** CMS formatted with 4096 bytes per block */
    public static final int IMAGE_DISK_FORMATTING_CMS4096 = 5;

    /** CMS formatted with the default block size for the allocated device type */
    public static final int IMAGE_DISK_FORMATTING_CMS = 6;

    /**
     * Constructor does nothing.
     */ 
    public ImageDiskCreateDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("image_disk_number", "", "The virtual device address of the disk to be copied"),
            new Parameter("image_disk_device_type", "", "The device type of the volume to which the disk is assigned"),
            new Parameter("image_disk_allocation_type", "", "Location or allocation"),
            new Parameter("allocation_area_name_or_volser", "", "Like it says"),
            new Parameter("allocation_unit_size", 0, "Like it says"),
            new Parameter("image_disk_size", 0, "Like it says"),
            new Parameter("image_disk_mode", "", "The access mode requested for the disk"),
            new Parameter("image_disk_formatting", 0, "Like it says"),
            new Parameter("image_disk_label", "", "The disk label to use when formatting the new extent"),
            new Parameter("read_password", "", "Defines the read password that will be used for accessing the disk"),
            new Parameter("write_password", "", "Defines the write password that will be used for accessing the disk"),
            new Parameter("multi_password", "", "Defines the multi password that will be used for accessing the disk") } ;
    }
}
