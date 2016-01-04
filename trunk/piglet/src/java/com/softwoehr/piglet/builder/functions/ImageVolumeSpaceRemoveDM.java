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

/* Autogenerated Mon Jan  4 02:27:38 UTC 2016
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * {@code Image_Volume_Space_Remove_DM} VSMAPI Function
 */
public class ImageVolumeSpaceRemoveDM extends BuilderFunctionProxy {


    /** Remove named region */
    public static final int FUNCTION_TYPE_REMOVE_NAMED = 1;

    /** Remove named region from group */
    public static final int FUNCTION_TYPE_REMOVE_NAMED_FR0M_GROUP = 2;

    /** Remove named region from all groups */
    public static final int FUNCTION_TYPE_REMOVE_NAMED_FR0M_GROUPS = 3;

    /** Remove all regions from specific volume */
    public static final int FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME = 4;

    /** Remove all regions from specific volume and group */
    public static final int FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME_AND_GROUP = 5;

    /** Remove all regions from specific volume and all groups */
    public static final int FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME_AND_ALL_GROUPS = 6;

    /** Remove entire group */
    public static final int FUNCTION_TYPE_REMOVE_ENTIRE_GROUP = 7;

    /**
     * Constructor does nothing.
     */ 
    public ImageVolumeSpaceRemoveDM() { super();}

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
            new Parameter("group_name", "", "The name of the group to which the region is assigned") } ;
    }
}
