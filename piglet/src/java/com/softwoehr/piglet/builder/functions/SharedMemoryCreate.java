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

/* Autogenerated Mon Jan  4 02:27:39 UTC 2016
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * <tt>Shared_Memory_Create</tt> VSMAPI Function
 */
public class SharedMemoryCreate extends BuilderFunctionProxy {


    /** Shared read/write access */
    public static final int PAGE_ACCESS_SW = 1;

    /** Exclusive read/write access */
    public static final int PAGE_ACCESS_EW = 2;

    /** Shared read/write access */
    public static final int PAGE_ACCESS_SR = 3;

    /** Exclusive read-only access */
    public static final int PAGE_ACCESS_ER = 4;

    /** Shared read/write access, no data saved */
    public static final int PAGE_ACCESS_SN = 5;

    /** Exclusive read/write access, no data saved */
    public static final int PAGE_ACCESS_EN = 6;

    /** Shared read/write access, no data saved, CP writeable pages */
    public static final int PAGE_ACCESS_SC = 7;

    /** Unspecified */
    public static final int MEMORY_ATTRIBUTES_UNSPECIFIED = 0;

    /** Restricted saved memory */
    public static final int MEMORY_ATTRIBUTES_RSTD = 0;

    /** Unrestricted saved memory */
    public static final int MEMORY_ATTRIBUTES_UNRSTD = 0;

    /**
     * Constructor does nothing.
     */ 
    public SharedMemoryCreate() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("memory_segment_name", "", "The name of the memory segment to create"),
            new Parameter("begin_page", -1, "The beginning page to be saved"),
            new Parameter("end_page", -1, "The ending page to be saved"),
            new Parameter("page_access_descriptor", 0, "The type of page access"),
            new Parameter("memory_attributes", MEMORY_ATTRIBUTES_UNRSTD, "The memory attributes"),
            new Parameter("memory_access_identifier", "", "The name of the image or list of images authorized to access the RSTD segment") } ;
    }
}
