/*
 * Copyright (c) 2008, 2016, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Sun Oct 30 06:28:37 UTC 2016
 * by pigstruct.m4 Copyright *C* 2016 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * DirectoryEntryFormatNoArray implements the {@code directory_entry_format_no_array} from {@code Query_All_DM}
 Query_All_DM has two different sorts of structures it returns
 * based on the FORMAT=YES/NO optional parameter (default=NO).
 * This Array is for FORMAT=NO.
 * The corresponding array for FORMAT=YES is DirectoryEntryArray 
 * @see com.softwoehr.pigiron.functions.QueryAllDM
 * @see com.softwoehr.pigiron.access.paramstructs.DirectoryEntryFormatNoStructureCounted
 */
public class DirectoryEntryFormatNoArray extends CountedArray {

    /**
     * Create a modelled-for-read instance with a specified formal name.
     * @param formalName the formal name
     * @return the modelled instance.
     */
    public static DirectoryEntryFormatNoArray modelArray(String formalName) {
        DirectoryEntryFormatNoArray result = new DirectoryEntryFormatNoArray();
        result.add(new DirectoryEntryFormatNoStructureCounted(null, "directory_entry_format_no_structure_counted"));
        result.setFormalName(formalName);
        return result;
    }

    /**
     * Create an instance by copying the value from a like instance, and
     * assign also the formal name.
     * @param value a like instance to copy from
     * @param formalName the formal name
     */
    public DirectoryEntryFormatNoArray(CountedArray value, String formalName) {
        super(value, formalName);
    }


    /**
     * Create an instance by absorbing a CountedStruct type only if
     * that instance is the associated counted struct for this array
     * type. Assign also the formal name.
     * @param value a CountedStruct to absorb
     * @param formalName the formal name
     */
    public DirectoryEntryFormatNoArray(CountedStruct value, String formalName) throws VSMArrayCountedStructCTORException {
        super();
        if (!value.getClass().equals(DirectoryEntryFormatNoStructureCounted.class)) {
            throw new VSMArrayCountedStructCTORException(value + " is not an instance of DirectoryEntryFormatNoStructureCounted");
        }
        setValue(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance by copying the value from a like instance.
     * @param value a like instance to copy from
     */
    public DirectoryEntryFormatNoArray(CountedArray value) {
        super(value);
    }

    /**
     * Create an instance where only the formal name
     * is instanced.
     * @param formalName the formal name
     */
    public DirectoryEntryFormatNoArray(String formalName) {
        super();
        setFormalName(formalName);
    }

    /**
     * Create an instance of undefined value.
     */
    public DirectoryEntryFormatNoArray() {
    }
    /** Tests whether the Array can assimilate its proper
     * CountedStruct type and still not assimilate other CountedStruct types.
     * @param argv not used
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws VSMArrayCountedStructCTORException
     */
    public static void main(String argv[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, VSMArrayCountedStructCTORException {
        CountedStruct cS = (CountedStruct) Class.forName("com.softwoehr.pigiron.access.paramstructs.DirectoryEntryFormatNoStructureCounted").newInstance();
        DirectoryEntryFormatNoArray aC = new DirectoryEntryFormatNoArray(cS, FORMAL_TYPE);
        System.out.println("Here is the DirectoryEntryFormatNoArray instance having assimilated an DirectoryEntryFormatNoStructureCounted instance: " + aC.prettyPrint());
        cS = new PageRangeStructureCounted();
        aC = new DirectoryEntryFormatNoArray(cS, FORMAL_TYPE);
        System.out.println("You should never see this message due to a VSMArrayCountedStructCTORException thrown before: Here is the DirectoryEntryFormatNoArray instance having assimilated an PageRangeStructureCounted instance: " + aC.prettyPrint());
    }
}

/* End of autogenerated code */

