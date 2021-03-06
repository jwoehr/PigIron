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
package com.softwoehr.pigiron.bizobj;

import com.softwoehr.pigiron.functions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a singleton class with public static methods usd to interpret a a
 * VSMAPIFunction return code and reason code. {@code VsmapiRC} returns a
 * {@code ReturnCode} which then can be queried about the {@code ReasonCode}
 * using the reason code integer returned by VSMAPI as an index into a sparse
 * vector associated with the return code.<br>
 * <br>
 * However, some Return Code / Result Code pairs are overloaded in the
 * specification to be context-sensitive to the calling VSMAPI function. Thus
 * there is an ReasonCodeOverload class and a sparse vector of these associated
 * with certain resason codes in conjunction with certain return codes.<br>
 * <br>
 * Then Yet Again there is one more wrinkle: return codes whose reason codes in
 * <i>some</i> instances can be indexes into a sparse vector, yet in other cases
 * the reason code is simply a numeric value.<br>
 * <br>
 * For example, for {@code ImageDeactivate}, when a Return Code 0 is associated
 * with a non-zero Result Code, the Result Code means the number of seconds
 * within which the Image was deactivated.<br>
 * <br>
 * So both ReturnCode and ReasonCode must know how to use a VSMCall reference to
 * find the right thing.
 *
 * @author jax
 */
public class VsmapiRC {

    public static class ReturnCodeMap extends HashMap<Integer, ReturnCode> {

        public ReturnCodeMap(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public ReturnCodeMap(int initialCapacity) {
            super(initialCapacity);
        }

        public ReturnCodeMap() {
        }

        public ReturnCodeMap(Map<? extends Integer, ? extends ReturnCode> m) {
            super(m);
        }
    }

    public static class SyntaxErrorMap extends HashMap<Integer, String> {

        public SyntaxErrorMap(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public SyntaxErrorMap(int initialCapacity) {
            super(initialCapacity);
        }

        public SyntaxErrorMap(Map<? extends Integer, ? extends String> m) {
            super(m);
        }

        public SyntaxErrorMap() {
        }
    }

    public static class ReasonCodeOverloadArray extends ArrayList<ReasonCodeOverload> {

        public ReasonCodeOverloadArray(int initialCapacity) {
            super(initialCapacity);
        }

        public ReasonCodeOverloadArray() {
        }

        public ReasonCodeOverloadArray(Collection<? extends ReasonCodeOverload> c) {
            super(c);
        }
    }

    private static final ReturnCodeMap RCMAP = new ReturnCodeMap(50);
    private static final SyntaxErrorMap SYNTAXERRORS = new SyntaxErrorMap(25);

    static {
        /**
         * Static ctor populates the table of {@code ReturnCode} objects.
         */
        ReasonCodeOverloadArray rcos = new ReasonCodeOverloadArray();
        ReturnCode rc = new ReturnCode("RC_OK", 0) {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) { // SPECIAL 0/Seconds for ImageDeactivate
                ReasonCode result;
                if (function instanceof ImageDeactivate) {
                    result = new ReasonCode("Request successful; Image Deactivated Within " + reason + " Seconds", "secs", reason);
                } else if (function instanceof QueryAPIFunctionalLevel) {
                    String level = "unknown";
                    switch (reason) {
                        case 0:
                            level = "5.3";
                            break;
                        case 540:
                            level = "5.4";
                            break;
                    }
                    result = new ReasonCode("API Level is " + level, "decimal coded API level", reason);
                } else {
                    result = super.getReasonCode(reason, function);
                }
                return result;
            }
        };
        rc.addReasonCode(new ReasonCode("Successful", "RS_NONE", 0));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("CPU defined, but CPU affinity suppressed", "RS_AFFINITY_SUPPRESSED", 4, ImageCPUDefine.class)); // 0/4 is overloaded
        rc.addReasonCode(new ReasonCode("Segment was created or replaced, but specified userid in memory_access_identifier could not be found to give RSTD access", "RS_NOT_FOUND", 4, rcos));
        rc.addReasonCode(new ReasonCode("Request successful; object directory offline", "RS_OFFLINE", 8));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("Image not active", "RS_NOT_ACTIVE", 12, ImageStatusQuery.class)); // 0/12 is overloaded
        rcos.add(new ReasonCodeOverload("Request successful; new list created", "RS_NEW_LIST", 12, NameListAdd.class)); // 0/12 is overloaded
        rc.addReasonCode(new ReasonCode("Request successful; NAMESAVE statement already exists in directory", "RS_NAMESAVE_EXISTS", 12, rcos));
        rc.addReasonCode(new ReasonCode("Request successful; no more entries, list destroyed", "RS_LIST_DESTROYED", 16));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("Request successful; new virtual network LAN created", "RS_VMLAN_CREATED", 20, VirtualNetworkLANCreate.class)); // 0/20 is overloaded
        rc.addReasonCode(new ReasonCode("No output; user(s) not authorized for specified segment", "RS_NOT_AUTHORIZED", 20, rcos));
        rc.addReasonCode(new ReasonCode("Request successful; virtual network LAN removed", "RS_VMLAN_REMOVED", 24));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("There are no SCSI characteristics for this image.", "RS_EMPTY", 28, ImageSCSICharacteristicsQueryDM.class)); // 0/28 is very overloaded
        rcos.add(new ReasonCodeOverload("Query request successful, but segment not found", "RS_SEGMENT_NOT_FOUND", 28, SharedMemoryQuery.class)); // 0/28 is very overloaded
        // vrco.add(new ReasonCodeOverload("Query request successful, but no data returned since no item was found matching the search criteria", "RS_NO_MATCH_ON_SEARCH", 28, /* dead? */ )); // 0/28 is very overloaded
        rcos.add(new ReasonCodeOverload("No matching entries found", "RS_NOTIFY_NOT_FOUND", 28, AsynchronousNotificationQueryDM.class)); // 0/28 is very overloaded
        rc.addReasonCode(new ReasonCode("No matching entries found. Return buffer is empty.", "RS_NONE_FOUND", 28, rcos));
        rc.addReasonCode(new ReasonCode("Name was not in list", "RS_NOT_IN_LIST", 32));
        rc.addReasonCode(new ReasonCode("Name is already in list", "RS_NAME_IN_LIST", 36));
        rc.addReasonCode(new ReasonCode("Request successful; new virtual switch created", "RS_VSWITCH_CREATED", 40));
        rc.addReasonCode(new ReasonCode("Request successful; virtual switch removed", "RS_VSWITCH_REMOVED", 44));
        rc.addReasonCode(new ReasonCode("Multiple DEFINE or MODIFY statements are erased in system config", "RS_DEF_MOD_MULTI_ERASED", 66));
        rc.addReasonCode(new ReasonCode("Asynchronous operation succeeded", "RS_ASYNC_OP_SUCCEEDED", 100));
        rc.addReasonCode(new ReasonCode("Asynchronous operation in progress", "RS_ASYNC_OP_IN_PROGRESS", 104));
        rc.addReasonCode(new ReasonCode("Asynchronous operation failed", "RS_ASYNC_OP_FAILED", 108));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RC_WNG", 4); // 5.4
        rc.addReasonCode(new ReasonCode("Unrestricted LAN", "RS_UNRESTRICTED_LAN", 5));
        rc.addReasonCode(new ReasonCode("No authorized users", "RS_NO_USERS", 6));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RC_ERR", 8); // 5.4
        rc.addReasonCode(new ReasonCode("Invalid access user", "RS_INVALID_USER", 2));
        rc.addReasonCode(new ReasonCode("Invalid op value", "RS_INVALID_OP", 3));
        rc.addReasonCode(new ReasonCode("Invalid promiscuity value", "RS_INVALID_PRO", 4));
        rc.addReasonCode(new ReasonCode("Invalid LAN ID", "RS_INVALID_LANID", 2783));
        rc.addReasonCode(new ReasonCode("Invalid LAN parameter", "RS_INVALID_LAN_PARM", 2795));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_SYNTAX", 24) {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) { // SPECIAL
                ReasonCode result;
                int pp = reason / 100; // parameter number
                int rr = reason % 100; // reason code
                if (SYNTAXERRORS.containsKey(rr)) {
                    result = new ReasonCodeRC24(SYNTAXERRORS.get(rr), "RCERR_SYNTAX", rr, pp);
                } else {
                    result = new ReasonCodeRC24("Unknown syntax error in function parameter", "RCERR_SYNTAX", rr, pp);
                }
                return result;
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_FILE_NOT_FOUND", 28);
        rc.addReasonCode(new ReasonCode("Namelist file not found", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_FILE_CANNOT_BE_UPDATED", 36);
        rc.addReasonCode(new ReasonCode("Namelist file cannot be updated", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_AUTH", 100);
        rc.addReasonCode(new ReasonCode("Request is authorized", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Authorization deferred to directory manager", "RS_DEFERRED_SERVER", 4));
        rc.addReasonCode(new ReasonCode("Request not authorized by external security manager", "RS_AUTHERR_ESM", 8));
        rc.addReasonCode(new ReasonCode("Request not authorized by directory manager", "RS_AUTHERR_DM", 12));
        rc.addReasonCode(new ReasonCode("Request not authorized by server", "RS_AUTHERR_SERVER", 16));
        rc.addReasonCode(new ReasonCode("Target image not authorized for function", "RS_TARGET_IMG_NOT_AUTHORIZED", 20));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_NO_AUTHFILE", 104);
        rc.addReasonCode(new ReasonCode("Authorization file not found", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_AUTHFILE_RO", 106);
        rc.addReasonCode(new ReasonCode("Authorization file cannot be updated", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_EXISTS", 108);
        rc.addReasonCode(new ReasonCode("Authorization file entry already exists", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_NO_ENTRY", 112);
        rc.addReasonCode(new ReasonCode("Authorization file entry does not exist", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_USER_PW_BAD", 120);
        rc.addReasonCode(new ReasonCode("Authentication error; userid or password not valid", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_PW_EXPIRED", 128);
        rc.addReasonCode(new ReasonCode("Authentication error; password expired", "RS_NONE", 0));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_ESM", 188) {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) { // SPECIAL
                return new ReasonCode("Internal server error; ESM failure", "Product-specific return code " + reason, reason);
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_PW_CHECK", 192) {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) { // SPECIAL
                return new ReasonCode("Internal server error; cannot authenticate user/password", "Product-specific return code " + reason, reason);
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEOP", 200);
        rc.addReasonCode(new ReasonCode("Image operation error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Image already active", "RS_ALREADY_ACTIVE", 8));
        rc.addReasonCode(new ReasonCode("Image not active", "RS_NOT_ACTIVE", 12));
        rc.addReasonCode(new ReasonCode("Image being deactivated", "RS_BEING_DEACT", 16));
        rc.addReasonCode(new ReasonCode("List not found", "RS_LIST_NOT_FOUND", 24));
        rc.addReasonCode(new ReasonCode("Some images in list not activated", "RS_NOT_ALL", 28));
        rc.addReasonCode(new ReasonCode("Some images in list not deactivated", "RS_SOME_NOT_DEACT", 32));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("Some images in list not recycled", "RS_SOME_NOT_RECYC", 36, ImageRecycle.class)); // 200/36 is overloaded
        rc.addReasonCode(new ReasonCode("Specified time results in interval greater than max allowed", "RS_TIME_NOT_VALID", 36, rcos));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEDEVU ", 204);
        rc.addReasonCode(new ReasonCode("Image device usage error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image device already exists", "RS_EXISTS", 4));
        rc.addReasonCode(new ReasonCode("Image device does not exist", "RS_NOT_EXIST", 8));
        rc.addReasonCode(new ReasonCode("Image device is busy", "RS_BUSY", 12));
        rc.addReasonCode(new ReasonCode("Image device is not available", "RS_NOT_AVAILABLE", 16));
        rc.addReasonCode(new ReasonCode("Image device already connected", "RS_IS_CONNECTED", 20));
        rc.addReasonCode(new ReasonCode("Image device is not a tape drive, or cannot be assigned/reset", "RS_TAPE_NOT_ASSIGNED", 24));
        rc.addReasonCode(new ReasonCode("Image device is not a shared DASD", "RS_DEV_NOT_SHARED", 28));
        rc.addReasonCode(new ReasonCode("Image device is not a reserved DASD", "RS_DEV_NOT_RESERVED", 32));
        rc.addReasonCode(new ReasonCode("I/O error on image device", "RS_DEV_IO_ERROR", 36));
        rc.addReasonCode(new ReasonCode("Virtual Network Adapter not deleted", "RS_NWDEV_NOT_DETACHED", 40));
        rc.addReasonCode(new ReasonCode("DASD volume cannot be deleted", "RS_DASD_IN_USE", 44));
        rc.addReasonCode(new ReasonCode("Virtual network adapter is already disconnected", "RS_IS_DISCONNECTED", 48));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEDISKU ", 208);
        rc.addReasonCode(new ReasonCode("Image disk usage error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image disk already in use", "RS_IN_USE", 4));
        rc.addReasonCode(new ReasonCode("Image disk not in use", "RS_NOT_IN_USE", 8));
        rc.addReasonCode(new ReasonCode("Image disk not available", "RS_NOT_AVAILABLE", 12));
        rc.addReasonCode(new ReasonCode("Image disk cannot be shared as requested", "RS_CANNOT_SHARE", 16));
        rc.addReasonCode(new ReasonCode("Image disk shared in different mode", "RS_SHARE_DIFF_MODE", 20));
        rc.addReasonCode(new ReasonCode("Image disk does not have required password", "RS_PW_NEEDED", 28));
        rc.addReasonCode(new ReasonCode("Incorrect password specified for image disk", "RS_BAD_PW", 32));
        rc.addReasonCode(new ReasonCode("Image disk does not exist", "RS_NOT_EXIST", 36));
        rc.addReasonCode(new ReasonCode("MDISK DEVNO parameter requires the device to be a free volume", "RS_DEVNO_REQUIRES_FREE_DISK", 1157));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGECONN", 212);
        rc.addReasonCode(new ReasonCode("Active image connectivity error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Partner image not found", "RS_NO_PARTNER", 4));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("Adapter does not exist", "RS_ADAPTER_NOT_EXIST", 8, VirtualNetworkAdapterQuery.class)); // 212/8 is overloaded
        rc.addReasonCode(new ReasonCode("Image not authorized to connect", "RS_AUTHERR_CONNECT", 8, rcos));
        rc.addReasonCode(new ReasonCode("LAN does not exist", "RS_LAN_NOT_EXIST", 12));
        rc.addReasonCode(new ReasonCode("LAN owner LAN name does not exist", "RS_NOT_EXIST", 16));
        rc.addReasonCode(new ReasonCode("Requested LAN owner not active", "RS_OWNER_NOT_ACTIVE", 20));
        rc.addReasonCode(new ReasonCode("LAN name already exists with different attributes", "RS_LAN_NAME_EXISTS", 24));
        rc.addReasonCode(new ReasonCode("Image device not correct type for requested connection", "RS_DEV_INCOMPATIBLE", 28));
        rc.addReasonCode(new ReasonCode("Image device not connected to LAN", "RS_NOT_CONNECTED", 32));
        rc.addReasonCode(new ReasonCode("Virtual switch already exists", "RS_VSWITCH_EXISTS", 36));
        rc.addReasonCode(new ReasonCode("Virtual switch does not exist", "RS_VSWITCH_NOT_EXIST", 40));
        rc.addReasonCode(new ReasonCode("Image already authorized", "RS_ALREADY_AUTH", 44));
        rc.addReasonCode(new ReasonCode("Maximum number of connections reached", "RS_MAX_CONN", 52));
        rc.addReasonCode(new ReasonCode("Unknown value", "RS_UNKNOWN", 96));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGECPU", 216); // 5.4
        rc.addReasonCode(new ReasonCode("Input virtual CPU value out of range", "RS_INVALID_DEVICE", 2));
        rc.addReasonCode(new ReasonCode("Virtual CPU not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Image not active", "RS_NOT_ACTIVE", 12));
        rc.addReasonCode(new ReasonCode("Virtual CPU already exists", "RS_VCPU_ALREADY_EXISTS", 24));
        rc.addReasonCode(new ReasonCode("Virtual CPU address beyond allowable range defined in directory", "RS_VCPU_OUT_OF_RANGE", 28));
        rc.addReasonCode(new ReasonCode("Processor type not supported on your system", "RS_TYPE_NOT_SUPPORTED", 40));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_VOLUME", 300);
        rc.addReasonCode(new ReasonCode("Image volume operation successful", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Device not found", "RS_DEV_NOT_FOUND", 8));
        rc.addReasonCode(new ReasonCode("Device not available for attachment", "RS_DEV_NOT_AVAIL_TO_ATTACH", 10));
        rc.addReasonCode(new ReasonCode("Device not a volume", "RS_DEV_NOT_VOLUME", 12));
        rc.addReasonCode(new ReasonCode("Free modes not available", "RS_FREE_MODE_NOT_AVAIL", 14));
        rc.addReasonCode(new ReasonCode("Device vary online failed", "RS_DEV_NOT_ONLINE", 16));
        rc.addReasonCode(new ReasonCode("Volume label not found in system configuration", "RS_VOLID_NOT_FOUND", 18));
        rc.addReasonCode(new ReasonCode("Volume label already in system configuration", "RS_VOLID_IN_USE", 20));
        rc.addReasonCode(new ReasonCode("Parm disks 1 and 2 are same", "RS_PDISKS_SAME", 22));
        rc.addReasonCode(new ReasonCode("Error linking parm disk (1 or 2)", "RS_PARM_DISK_LINK_ERROR", 24));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) not RW", "RS_PARM_DISK_NOT_RW", 28));
        rc.addReasonCode(new ReasonCode("System configuration not found on parm disk 1", "RS_SYS_CONF_NOT_FOUND", 32));
        rc.addReasonCode(new ReasonCode("System configuration has bad data", "RS_SYS_CONF_BAD_DATA", 34));
        rc.addReasonCode(new ReasonCode("Syntax errors updating system configuration file", "RS_SYS_CONF_SYNTX_ERR", 36));
        rc.addReasonCode(new ReasonCode("CP disk modes not available", "RS_CPDISK_MODE_NOT_AVAIL", 38));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) is full", "RS_PARM_DISK_FULL", 40));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) access not allowed", "RS_PDISK_ACC_NOT_ALLOWED", 42));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) PW not supplied", "RS_PDISK_PW_NOT_SUPPLIED", 44));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) PW is incorrect", "RS_PDISK_PW_INCORRECT", 46));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) is not in server's user directory", "RS_PDISK_NOT_IN_SERVER_DIRECTORY", 48));
        rc.addReasonCode(new ReasonCode("Error in release of CPRELEASE parm disk (1 or 2)", "RS_CP_RELEASE_ERROR", 50));
        rc.addReasonCode(new ReasonCode("Error in access of CPACCESS parm disk (1 or 2)", "RS_CP_ACCESS_ERROR", 52));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_INTERNAL", 396) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) {
                ReasonCode result;
                if (reason == 0) {
                    result = new ReasonCode("Internal System Error", "RS_NONE", reason);
                } else {
                    result = new ReasonCode("Internal system error - error occurs in a function exec - product-specific return code", "RS_OPID", reason);
                }
                return result;
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEDEF", 400); // Descriptive strings changed in 5.4 to mention profile
        rc.addReasonCode(new ReasonCode("Image or profile definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image or profile definition not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Image or profile name already defined", "RS_NAME_EXISTS", 8));
        rc.addReasonCode(new ReasonCode("Image or profile definition is locked", "RS_LOCKED", 12));
        rc.addReasonCode(new ReasonCode("Image or profile definition cannot be deleted", "RS_CANNOT_DELETE", 16));
        rc.addReasonCode(new ReasonCode("Image prototype is not defined", "RS_NOT_DEFINED", 20));
        rc.addReasonCode(new ReasonCode("Image or profile definition is not locked", "RS_NOT_LOCKED", 24));
        rc.addReasonCode(new ReasonCode("Multiple user statements", "RS_MULTIPLE", 40));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEDEVD", 404);
        rc.addReasonCode(new ReasonCode("Image device definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image device already defined", "RS_EXISTS", 4));
        rc.addReasonCode(new ReasonCode("Image device not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Image device is locked", "RS_LOCKED", 12));
        rcos.clear();
        rcos.add(new ReasonCodeOverload("Image device type not same as source", "RS_TYPE_NOT_SAME", 24, ImageDiskCopyDM.class)); // 404/24 is overloaded
        rc.addReasonCode(new ReasonCode("Image device is not locked", "RS_NOT_LOCKED", 24, rcos));
        rc.addReasonCode(new ReasonCode("Image device size not same as source", "RS_SIZE_NOT_SAME", 28));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGEDISKD", 408);
        rc.addReasonCode(new ReasonCode("Image disk definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image disk already defined", "RS_EXISTS", 4));
        rc.addReasonCode(new ReasonCode("Image disk not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Image device is locked", "RS_LOCKED", 12));
        rc.addReasonCode(new ReasonCode("Image disk sharing not allowed by target image definition", "RS_NO_SHARING", 16));
        rc.addReasonCode(new ReasonCode("Requested image disk space not available", "RS_NO_SPACE", 24));
        rc.addReasonCode(new ReasonCode("Image disk does not have required password", "RS_PW_NEEDED", 28));
        rc.addReasonCode(new ReasonCode("Incorrect password specified for image disk", "RS_BAD_PW", 32));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_IMAGECONND", 412);
        rc.addReasonCode(new ReasonCode("Image connectivity definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Partner image not found", "RS_NO_PARTNER", 4));
        rc.addReasonCode(new ReasonCode("Parameters do not match existing directory statement", "RS_NO_MATCH", 16));
        rc.addReasonCode(new ReasonCode("Image device not correct type for requested connection", "RS_DEV_INCOMPATIBLE", 28));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_PROTODEF", 416);
        rc.addReasonCode(new ReasonCode("Prototype definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Prototype definition not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Prototype already exists", "RS_NAME_EXISTS", 8));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RC_DASD_DM", 420);
        rc.addReasonCode(new ReasonCode("Group, region, or volume name is already defined", "RS_IVS_NAME_USED", 4));
        rc.addReasonCode(new ReasonCode("Group, region, or volume name is not defined", "RS_IVS_NAME_NOT_USED", 8));
        rc.addReasonCode(new ReasonCode("Region name is not included in the group", "RS_IVS_NAME_NOT_INCLUDED", 12));
        rc.addReasonCode(new ReasonCode("The requested volume is offline or is not a DASD device", "RS_IVS_NAME_NOT_DASD", 36));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_SEGMENT_DM", 424);
        rc.addReasonCode(new ReasonCode("Namesave statement already exists", "RS_SEG_NAME_DUPLICATE", 4));
        rc.addReasonCode(new ReasonCode("Segment name not found", "RS_SEG_NAME_NOT_FOUND", 8));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_NOTIFY", 428);
        rc.addReasonCode(new ReasonCode("Duplicate subscription", "RS_NOTIFY_DUP", 4));
        rc.addReasonCode(new ReasonCode("No matching entries", "RS_NOTIFY_NOT_FOUND", 8));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_TAG", 432);
        rc.addReasonCode(new ReasonCode("Tag name is already defined", "RS_DUP_NAME", 4));
        rc.addReasonCode(new ReasonCode("Tag name is not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Tag ordinal is already defined", "RS_DUP_ORDINAL", 12));
        rc.addReasonCode(new ReasonCode("Tag is in use in one or more directory entries, can not be revoked", "RS_CANNOT_REVOKE", 16));
        rc.addReasonCode(new ReasonCode("Tag too long", "RS_LONG", 16));
        /* Duplicate! What does this mean? */
        rc.addReasonCode(new ReasonCode("Use not allowed by exit routine", "RS_NOT_AUTHORIZED", 20));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_PROFILED", 436);
        rc.addReasonCode(new ReasonCode("Profile included not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Multiple profiles included", "RS_MULTIPLE", 40));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_POLICY_PW", 444);
        rc.addReasonCode(new ReasonCode("Password policy error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Password too long", "RS_LONG", 4));
        rc.addReasonCode(new ReasonCode("Password too short", "RS_SHORT", 8));
        rc.addReasonCode(new ReasonCode("Password content does not match policy", "RS_CONTENT", 12));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_POLICY_ACCT", 448);
        rc.addReasonCode(new ReasonCode("Account policy error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Account number too long", "RS_LONG", 4));
        rc.addReasonCode(new ReasonCode("Account number too short", "RS_SHORT", 8));
        rc.addReasonCode(new ReasonCode("Account number content does not match policy", "RS_CONTENT", 12));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_TASK", 452);
        rc.addReasonCode(new ReasonCode("Task not found", "RS_NOT_FOUND", 4));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_SCSI", 456);
        rc.addReasonCode(new ReasonCode("LOADDEV statement not found", "RS_LOADDEV_NOT_FOUND", 4));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RC_IPL_DM", 460); // 5.4
        rc.addReasonCode(new ReasonCode("Image does not have an IPL statement", "RS_IPL_NOT_FOUND", 4));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_DM", 500);
        rc.addReasonCode(new ReasonCode("Directory manager request could not be completed", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Directory manager is not accepting updates", "RS_NO_UPDATES", 4));
        rc.addReasonCode(new ReasonCode("Directory manager is not available", "RS_NOT_AVAILABLE", 8));
        rc.addReasonCode(new ReasonCode("Directory manager has been disabled", "RS_DISABLED", 12));
        rc.addReasonCode(new ReasonCode("Directory manager was interrupted", "RS_INTERRUPTED", 16));
        rc.addReasonCode(new ReasonCode("Password format not supported", "RS_PW_FORMAT_NOT_SUPPORTED", 20));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_LIST_DM", 504) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) {
                return new ReasonCode("Target ID not added - Reason is product-specific return code", "RS_PSRC", reason);
            }
        };
        RCMAP.put(rc.getValue(), rc);

        rc = new ReturnCode("RCERR_CPU_DM", 520); // 5.4
        rc.addReasonCode(new ReasonCode("Only one base CPU may be defined", "RS_ONLY1_BASE_ALLOWED", 24));
        rc.addReasonCode(new ReasonCode("Input virtual CPU value out of range", "RS_CPU_OUT_OF_RANGE", 28));
        rc.addReasonCode(new ReasonCode("CPU not found", "RS_CPU_NOT_FOUND", 30));
        rc.addReasonCode(new ReasonCode("Maximum allowable number of virtual CPUs is exceeded", "RS_MAX_EXCEEDED", 32));
        rc.addReasonCode(new ReasonCode("The Cryptographic Coprocessor Facility (CCF) is not installed on this system", "RS_CRYPTO_NOT_INSTALLED", 45));
        rc.addReasonCode(new ReasonCode("SCPDATA contains invalid UTF-8 data", "RS_INVALID_UTF_DATA", 2826));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_ASYNC_DM", 592) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) {
                ReasonCode result;
                if (reason == 0) {
                    result = new ReasonCode("Asynchronous operation started - product-specific asynchronous operation ID is found in output_id parameter", "RS_NONE", reason);
                } else {
                    result = new ReasonCode("Asynchronous operation started - product-specific asynchronous operation ID", "RS_OPID", reason);
                }
                return result;
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_INTERNAL_DM", 596) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason, VSMCall function) {
                return new ReasonCode("Internal directory manager error - Product-specific return code", "RS_PSRC", reason);
            }
        };
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_INTERNAL_DM", 600);
        rc.addReasonCode(new ReasonCode("Bad page range", "RS_BAD_RANGE", 8));
        rc.addReasonCode(new ReasonCode("User not logged on", "RS_NOT_LOGGED_ON", 12));
        rc.addReasonCode(new ReasonCode("Could not save segment", "RS_NOSAVE", 16));
        rc.addReasonCode(new ReasonCode("Not authorized to issue internal system command or is not authorized for RSTD segment", "RS_NOT_AUTHORIZED", 20));
        rc.addReasonCode(new ReasonCode("Conflicting parameters", "RS_CONFLICTING_PARMS", 24));
        rc.addReasonCode(new ReasonCode("Segment not found or does not exist", "RS_SEGMENT_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Class S (skeleton) segment file already exists", "RS_CLASS_S_ALREADY_DEFINED", 299));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_VIRTUALNETWORKD", 620);
        rc.addReasonCode(new ReasonCode("Free modes not available", "RS_FREE_MODE_NOT_AVAIL", 14));
        rc.addReasonCode(new ReasonCode("System config parm disks 1 and 2 are same", "RS_PARM_DISKS_SAME", 22));
        rc.addReasonCode(new ReasonCode("Error linking parm disk (1 or 2)", "RS_PARM_DISK_LINK_ ERROR", 24));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) not RW", "RS_PARM_DISK_NOT_RW", 28));
        rc.addReasonCode(new ReasonCode("System config not found on parm disk 1", "RS_SYS_CONF_NOT_FOUND", 32));
        rc.addReasonCode(new ReasonCode("System config has bad data", "RS_SYS_CONF_BAD_DATA", 34));
        rc.addReasonCode(new ReasonCode("Syntax errors updating system config", "RS_SYS_CONF_SYNTX_ERR", 36));
        rc.addReasonCode(new ReasonCode("CP disk modes not available", "RS_CPDISK_MODE_NOT_AVAIL", 38));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) is full", "RS_PARM_DISK_FULL", 40));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) access not allowed", "RS_PARM_DISK_ACC_NOT_ALLOWED", 42));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) PW not supplied", "RS_PDISK_PW_NOT_SUPPLIED", 44));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) PW is incorrect", "RS_PDISK_PW_INCORRECT", 46));
        rc.addReasonCode(new ReasonCode("Parm disk (1 or 2) is not in server's directory", "RS_PDISK_NOT_IN_SERVER_DIRECTORY", 48));
        rc.addReasonCode(new ReasonCode("Error in release of CPRELEASE parm disk (1 or 2)", "RS_CP_RELEASE_ERROR", 50));
        rc.addReasonCode(new ReasonCode("Error in access of CPACCESS parm disk (1 or 2)", "RS_CP_ACCESS_ERROR", 52));
        rc.addReasonCode(new ReasonCode("DEFINE VSWITCH statement already exists in system config", "RS_DEF_VSWITCH_EXISTS", 54));
        rc.addReasonCode(new ReasonCode("MODIFY VSWITCH statement to userid not found in system config", "RS_REVOKE_FAILED", 58));
        rc.addReasonCode(new ReasonCode("DEFINE VSWITCH statement does not exist in system config", "RS_DEF_SWITCH_NOT_EXIST", 60));
        rc.addReasonCode(new ReasonCode("DEFINE operands conflict, cannot be updated in the system config", "RS_VSWITCH_CONFLICT", 62));
        rc.addReasonCode(new ReasonCode("Multiple DEFINE or MODIFY statements found in system config", "RS_DEF_MOD_MULTI_FOUND", 64));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_VMRM", 800);
        rc.addReasonCode(new ReasonCode("No measurement data exists", "RS_NO_MEASUREMENT_DATA", 8));
        rc.addReasonCode(new ReasonCode("Error in update buffer or processing syntax check", "RS_UPDATE_SYNTAX_ERROR", 12));
        rc.addReasonCode(new ReasonCode("Not authorized to access file", "RS_CANNOT_ACCESS_DATA", 16));
        rc.addReasonCode(new ReasonCode("Error writing file(s) to directory", "UPDATE_WRITE_ERROR", 24));
        rc.addReasonCode(new ReasonCode("Specified configuration file not found", "RS_FILE_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Internal error processing updates", "RS_UPDATE_PROCESS_ERROR", 32));
        RCMAP.put(rc.getValue(), rc);
        rc = new ReturnCode("RCERR_SERVER", 900);
        rc.addReasonCode(new ReasonCode("Custom exec not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Worker server was not found", "RS_WORKER_NOT_FOUND", 8));
        rc.addReasonCode(new ReasonCode("Specified function does not exist", "RS_FUNCTION_NOT_VALID", 12));
        rc.addReasonCode(new ReasonCode("Internal server error - DMSSIPTS entry for function is invalid", "RS_PTS_ENTRY_NOT_VALID", 16));
        rc.addReasonCode(new ReasonCode("Total length does not match the specified input data", "RS_PARM_LIST_NOT_VALID", 20));
        rc.addReasonCode(new ReasonCode("Error accessing SFS directory", "RS_SFS_ERROR", 24));
        rc.addReasonCode(new ReasonCode("Internal server error - error with format of function output", "RS_OUTPUT_NOT_VALID", 28));
        rc.addReasonCode(new ReasonCode("Internal server error - response from worker server was not valid", "RS_REQRESP_INVALID", 32));
        rc.addReasonCode(new ReasonCode("Specified length was not valid, out of valid server data range", "RS_LENGTH_NOT_VALID", 36));
        rc.addReasonCode(new ReasonCode("Internal server socket error", "RS_SOCKET", 40));
        RCMAP.put(rc.getValue(), rc);

        /* Used by Return Code 24 */
        SYNTAXERRORS.put(1, "First character of listname is a colon \":\"");
        SYNTAXERRORS.put(10, "Characters not \"0123456789\"");
        SYNTAXERRORS.put(11, "Unsupported function");
        SYNTAXERRORS.put(13, "Length is greater than maximum or exceeds total length");
        SYNTAXERRORS.put(14, "Length is less than minimum");
        SYNTAXERRORS.put(15, "Numeric value less than minimum or null value encountered");
        SYNTAXERRORS.put(16, "Characters not \"0123456789ABCDEF\"");
        SYNTAXERRORS.put(17, "Characters not \"0123456789ABCDEF-\"");
        SYNTAXERRORS.put(18, "Numeric value greater than maximum");
        SYNTAXERRORS.put(19, "Unrecognized value");
        SYNTAXERRORS.put(23, "Conflicting parameter specified");
        SYNTAXERRORS.put(24, "Unspecified required parameter");
        SYNTAXERRORS.put(25, "Extraneous parameter specified");
        SYNTAXERRORS.put(26, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"");
        SYNTAXERRORS.put(36, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\"");
        SYNTAXERRORS.put(37, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-\"");
        SYNTAXERRORS.put(42, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:\"");
        SYNTAXERRORS.put(43, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:_\"");
        SYNTAXERRORS.put(44, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:_=\"");
        SYNTAXERRORS.put(88, "Unexpected end of data");
        SYNTAXERRORS.put(99, "Non-breaking characters: non-blank, non-null, non-delete, non-line-end, non-carriage return, non-line-feed");
    }

    /**
     * This class is intended as a singleton.
     */
    protected VsmapiRC() {
    }

    /**
     * The singleton instance of VsmapiRC yields a {@code ReturnCode} object for
     * given VSMAPI integer return code. The VSMAPI reason code can then be
     * deref'ed using the {@code ReturnCode} object.
     *
     * @param rc - a VSMAPI Function's return code
     * @return a {@code ReturnCode} object with text and {@code ReasonCode}
     * capability.
     */
    public static ReturnCode returnCode(int rc) {
        ReturnCode result = RCMAP.get(rc);
        if (result == null) {
            result = new ReturnCode("RC_UNKNOWN_TO_PIGIRON", rc);
        }
        return result;
    }

    /**
     * Return a multiline string interpreting return code and reason code in the
     * context of the specific function which yielded that pair.
     *
     * @param rc return code from VSMAPI func
     * @param reason reason code from VSMAPI func
     * @param function the VSMAPI function to which the rc/reason pair pertains
     * @return a multiline string interpreting return code and reason code
     */
    public static String prettyPrint(int rc, int reason, VSMCall function) {
        String result;
        StringBuilder sb = new StringBuilder();
        ReturnCode returnCode = VsmapiRC.returnCode(rc);
        ReasonCode reasonCode = returnCode.getReasonCode(reason, function);
        sb.append("Return code is: ");
        sb.append(returnCode.getValue());
        sb.append(" : ");
        sb.append(returnCode.getName());
        sb.append("\nReason code is: ");
        sb.append(reasonCode.getValue(function));
        sb.append(" : ");
        sb.append(reasonCode.getName(function));
        sb.append(" : ");
        if (reasonCode instanceof ReasonCodeRC24) {
            sb.append("Parameter number ").append(ReasonCodeRC24.class.cast(reasonCode).getParamNumber());
            sb.append(" : ");
        }
        sb.append(reasonCode.getMessage(function));
        result = sb.toString();
        return result;
    }

    /**
     * Demonstrate/test Return Code and Reason Code explanation
     *
     * @param argv return_code reason_code
     */
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.err.println("Usage: VsmapiRC.main return_code reason_code");
            System.exit(1);
        }
        int rc = Integer.parseInt(argv[0]);
        int reason = Integer.parseInt(argv[1]);
        System.out.println(VsmapiRC.prettyPrint(rc, reason, null));
    }
}
