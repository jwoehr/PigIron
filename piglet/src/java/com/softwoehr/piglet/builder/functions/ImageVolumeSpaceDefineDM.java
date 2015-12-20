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

/* Autogenerated Sun Dec 20 02:40:32 UTC 2015
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * {@code Image_Volume_Space_Define_DM} VSMAPI Function
 */
public class ImageVolumeSpaceDefineDM extends BuilderFunctionProxy {


    /** Define region as specified */
    public static final int FUNCTION_TYPE_DEFINE = 1;

    /** Define region as specified and add to group */
    public static final int FUNCTION_TYPE_DEFINE_AND_ADD = 2;

    /** Define region as full volume */
    public static final int FUNCTION_TYPE_FULL_VOLUME = 3;

    /** Define region as full volume and add to group */
    public static final int FUNCTION_TYPE_FULL_VOLUME_AND_ADD = 4;

    /** Add existing region to group */
    public static final int FUNCTION_TYPE_ADD_EXISTING = 5;

    /** Unspecified */
    public static final int DEVICE_TYPE_UNSPECIFIED = 0;

    /** 3390 */
    public static final int DEVICE_TYPE_3390 = 1;

    /** 9336 */
    public static final int DEVICE_TYPE_9336 = 2;

    /** 3380 */
    public static final int DEVICE_TYPE_3380 = 3;

    /** FB-512 */
    public static final int DEVICE_TYPE_FB_512 = 4;

    /**
     * Constructor does nothing.
     */ 
    public ImageVolumeSpaceDefineDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("function_type", 0, "function_type"),
            new Parameter("region_name", "", "The region to be defined"),
            new Parameter("image_vol_id", "", "The DASD volume label"),
            new Parameter("start_cylinder", 0, "The starting point of the region"),
            new Parameter("size", 0, "The number of cylinders to be used by region"),
            new Parameter("group_name", "", "The name of the group to which the region is assigned"),
            new Parameter("device_type", 0, "The device type designation") } ;
    }
}
