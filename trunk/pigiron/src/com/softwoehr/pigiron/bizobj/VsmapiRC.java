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

import java.util.HashMap;

/**
 * Singleton class with one public static method to interpret a VSMAPI Function
 * return code and reason code. {@code VsmapiRC} returns a {@code ReturnCode}
 * which then can be queried about the {@code ReasonCode}.
 * @author jax
 */
public class VsmapiRC {

    private static VsmapiRC vsmapiRC = null;
    private HashMap<Integer, ReturnCode> rcMap = new HashMap<Integer, ReturnCode>(50);
    private HashMap syntaxErrors = new HashMap<Integer, String>(25);

    /**
     * Singleton yields {@code ReturnCode} object for given return code.
     * Reason code can then be deref'ed using the {@code ReturnCode} object.
     * @param rc - a VSMAPI Function's return code
     * @return a {@code ReturnCode} object with text and {@code ReasonCode}
     * capability.
     */
    public static ReturnCode returnCode(int rc) {
        ReturnCode result = null;
        if (vsmapiRC == null) {
            vsmapiRC = new VsmapiRC();
        }
        result = vsmapiRC.getReturnCode(rc);
        return result;
    }

    /**
     * Return a multiline string interpreting return code and reason code.
     * @param rc return code from VSMAPI func
     * @param reason reason code from VSMAPI func
     * @return a multiline string interpreting return code and reason code
     */
    public static String prettyPrint(int rc, int reason) {
        String result = null;
        StringBuffer sb = new StringBuffer();
        ReturnCode returnCode = VsmapiRC.returnCode(rc);
        ReasonCode reasonCode = returnCode.getReasonCode(reason);
        sb.append("Return code is: ");
        sb.append(returnCode.getValue());
        sb.append(" : ");
        sb.append(returnCode.getName());
        sb.append("\nReason code is: ");
        sb.append(reasonCode.getValue());
        sb.append(" : ");
        sb.append(reasonCode.getName());
        sb.append(" : ");
        if (reasonCode instanceof ReasonCodeRC24) {
            sb.append("Parameter number " + ReasonCodeRC24.class.cast(reasonCode).getParamNumber());
            sb.append(" : ");
        }
        sb.append(reasonCode.getMessage());
        result = sb.toString();
        return result;
    }

    /**
     * Singelton ctor populates the table of {@code ReturnCode} objects.
     */
    protected VsmapiRC() {
        ReturnCode rc = new ReturnCode("RC_OK", 0);
        rc.addReasonCode(new ReasonCode("Successful", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Segment was created or replaced, but specified userid in memory_access_identifier could not be found to give RSTD access", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Request successful; object directory offline", "RS_OFFLINE", 8));
        rc.addReasonCode(new ReasonCode("Request successful; NAMESAVE statement already exists in directory", "RS_NAMESAVE_EXISTS", 12));
        rc.addReasonCode(new ReasonCode("Request successful; new list created", "RS_NEW_LIST", 12));
        rc.addReasonCode(new ReasonCode("Image not active", "RS_NOT_ACTIVE", 12));
        rc.addReasonCode(new ReasonCode("Request successful; no more entries, list destroyed", "RS_LIST_DESTROYED", 16));
        rc.addReasonCode(new ReasonCode("Request successful; new virtual network LAN created", "RS_VMLAN_CREATED", 20));
        rc.addReasonCode(new ReasonCode("No output; user(s) not authorized for specified segment", "RS_NOT_AUTHORIZED", 20));
        rc.addReasonCode(new ReasonCode("Request successful; virtual network LAN removed", "RS_VMLAN_REMOVED", 24));
        rc.addReasonCode(new ReasonCode("No matching entries found. Return buffer is empty.", "RS_NONE_FOUND", 28));
        rc.addReasonCode(new ReasonCode("There are no SCSI characteristics for this image.", "RS_EMPTY", 28));
        rc.addReasonCode(new ReasonCode("Query request successful, but segment not found", "RS_SEGMENT_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Query request successful, but no data returned since no item was found matching the search criteria", "RS_NO_MATCH_ON_SEARCH", 28));
        rc.addReasonCode(new ReasonCode("No matching entries found", "RS_NOTIFY_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Name was not in list", "RS_NOT_IN_LIST", 32));
        rc.addReasonCode(new ReasonCode("Name is already in list", "RS_NAME_IN_LIST", 36));
        rc.addReasonCode(new ReasonCode("Request successful; new virtual switch created", "RS_VSWITCH_CREATED", 40));
        rc.addReasonCode(new ReasonCode("Request successful; virtual switch removed", "RS_VSWITCH_REMOVED", 44));
        rc.addReasonCode(new ReasonCode("Multiple DEFINE or MODIFY statements are erased in system config", "RS_DEF_MOD_MULTI_ERASED", 66));
        rc.addReasonCode(new ReasonCode("Asynchronous operation succeeded", "RS_ASYNC_OP_SUCCEEDED", 100));
        rc.addReasonCode(new ReasonCode("Asynchronous operation in progress", "RS_ASYNC_OP_IN_PROGRESS", 104));
        rc.addReasonCode(new ReasonCode("Asynchronous operation failed", "RS_ASYNC_OP_FAILED", 108));
        rcMap.put(0, rc);
        rc = new ReturnCode("RCERR_SYNTAX", 24) {

            @Override
            public ReasonCode getReasonCode(int reason) { // SPECIAL
                ReasonCode result = null;
                int pp = reason / 100; // parameter number
                int rr = reason % 100; // reason code
                if (syntaxErrors.containsKey(rr)) {
                    result = new ReasonCodeRC24(syntaxErrors.get(rr).toString(), "RCERR_SYNTAX", rr, pp);
                } else {
                    result = new ReasonCodeRC24("Unknown syntax error in function parameter", "RCERR_SYNTAX", rr, pp);
                }
                return result;
            }
        };
        rc.addReasonCode(new ReasonCode("Parameter value not recognized", "RS_UNRECOG", 19));
        rcMap.put(24, rc);
        rc = new ReturnCode("RCERR_FILE_NOT_FOUND", 28);
        rc.addReasonCode(new ReasonCode("Namelist file not found", "RS_NONE", 0));
        rcMap.put(28, rc);
        rc = new ReturnCode("RCERR_FILE_CANNOT_BE_UPDATED", 36);
        rc.addReasonCode(new ReasonCode("Namelist file cannot be updated", "RS_NONE", 0));
        rcMap.put(36, rc);
        rc = new ReturnCode("RCERR_AUTH", 100);
        rc.addReasonCode(new ReasonCode("Request is authorized", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Authorization deferred to directory manager", "RS_DEFERRED_SERVER", 4));
        rc.addReasonCode(new ReasonCode("Request not authorized by external security manager", "RS_AUTHERR_ESM", 8));
        rc.addReasonCode(new ReasonCode("Request not authorized by directory manager", "RS_AUTHERR_DM", 12));
        rc.addReasonCode(new ReasonCode("Request not authorized by server", "RS_AUTHERR_SERVER", 16));
        rc.addReasonCode(new ReasonCode("Target image not authorized for function", "RS_TARGET_IMG_NOT_AUTHORIZED", 20));
        rcMap.put(100, rc);
        rc = new ReturnCode("RCERR_NO_AUTHFILE", 104);
        rc.addReasonCode(new ReasonCode("Authorization file not found", "RS_NONE", 0));
        rcMap.put(104, rc);
        rc = new ReturnCode("RCERR_AUTHFILE_RO", 106);
        rc.addReasonCode(new ReasonCode("Authorization file cannot be updated", "RS_NONE", 0));
        rcMap.put(106, rc);
        rc = new ReturnCode("RCERR_EXISTS", 108);
        rc.addReasonCode(new ReasonCode("Authorization file entry already exists", "RS_NONE", 0));
        rcMap.put(108, rc);
        rc = new ReturnCode("RCERR_NO_ENTRY", 112);
        rc.addReasonCode(new ReasonCode("Authorization file entry does not exist", "RS_NONE", 0));
        rcMap.put(112, rc);
        rc = new ReturnCode("RCERR_USER_PW_BAD", 120);
        rc.addReasonCode(new ReasonCode("Authentication error; userid or password not valid", "RS_NONE", 0));
        rcMap.put(120, rc);
        rc = new ReturnCode("RCERR_PW_EXPIRED", 128);
        rc.addReasonCode(new ReasonCode("Authentication error; password expired", "RS_NONE", 0));
        rcMap.put(128, rc);
        rc = new ReturnCode("RCERR_ESM", 188);
        // rc.addReasonCode(new ReasonCode("Internal server error; ESM failure", "psrc2", psrc2));
        rcMap.put(188, rc);
        rc = new ReturnCode("RCERR_PW_CHECK", 192);
        //  rc.addReasonCode(new ReasonCode("Internal server error; cannot authenticate user/password", "psrc2", psrc2));
        rcMap.put(192, rc);
        rc = new ReturnCode("RCERR_IMAGEOP", 200);
        rc.addReasonCode(new ReasonCode("Image operation error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Image already active", "RS_ALREADY_ACTIVE", 8));
        rc.addReasonCode(new ReasonCode("Image not active", "RS_NOT_ACTIVE", 12));
        rc.addReasonCode(new ReasonCode("Image being deactivated", "RS_BEING_DEACT", 16));
        rc.addReasonCode(new ReasonCode("List not found", "RS_LIST_NOT_FOUND", 24));
        rc.addReasonCode(new ReasonCode("Some images in list not activated", "RS_NOT_ALL", 28));
        rc.addReasonCode(new ReasonCode("Some images in list not deactivated", "RS_SOME_NOT_DEACT", 32));
        rc.addReasonCode(new ReasonCode("Some images in list not recycled", "RS_SOME_NOT_RECYC", 36));
        rc.addReasonCode(new ReasonCode("Specified time results in interval greater than max allowed", "RS_TIME_NOT_VALID", 36));
        rcMap.put(200, rc);
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
        rcMap.put(204, rc);
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
        rcMap.put(208, rc);
        rc = new ReturnCode("RCERR_IMAGECONN", 212);
        rc.addReasonCode(new ReasonCode("Active image connectivity error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Partner image not found", "RS_NO_PARTNER", 4));
        rc.addReasonCode(new ReasonCode("Image not authorized to connect", "RS_AUTHERR_CONNECT", 8));
        rc.addReasonCode(new ReasonCode("Adapter does not exist", "RS_ADAPTER_NOT_EXIST", 8));
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
        rcMap.put(212, rc);
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
        rcMap.put(300, rc);
        rc = new ReturnCode("RCERR_INTERNAL", 396) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason) {
                ReasonCode result = null;

                if (reason == 0) {
                    result = new ReasonCode("Internal System Error", "RS_NONE", reason);
                } else {
                    result = new ReasonCode("Internal system error - error occurs in a function exec - product-specific return code", "RS_OPID", reason);
                }
                return result;
            }
        };
        rcMap.put(396, rc);
        rc = new ReturnCode("RCERR_IMAGEDEF", 400);
        rc.addReasonCode(new ReasonCode("Image definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image definition not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Image name already defined", "RS_NAME_EXISTS", 8));
        rc.addReasonCode(new ReasonCode("Image definition is locked", "RS_LOCKED", 12));
        rc.addReasonCode(new ReasonCode("Image definition cannot be deleted", "RS_CANNOT_DELETE", 16));
        rc.addReasonCode(new ReasonCode("Image prototype is not defined", "RS_NOT_DEFINED", 20));
        rc.addReasonCode(new ReasonCode("Image name is not locked", "RS_NOT_LOCKED", 24));
        rc.addReasonCode(new ReasonCode("Multiple user statements", "RS_MULTIPLE", 40));
        rcMap.put(400, rc);
        rc = new ReturnCode("RCERR_IMAGEDEVD", 404);
        rc.addReasonCode(new ReasonCode("Image device definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image device already defined", "RS_EXISTS", 4));
        rc.addReasonCode(new ReasonCode("Image device not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Image device is locked", "RS_LOCKED", 12));
        rc.addReasonCode(new ReasonCode("Image device type not same as source", "RS_TYPE_NOT_SAME", 24));
        rc.addReasonCode(new ReasonCode("Image device is not locked", "RS_NOT_LOCKED", 24));
        rc.addReasonCode(new ReasonCode("Image device size not same as source", "RS_SIZE_NOT_SAME", 28));
        rcMap.put(404, rc);
        rc = new ReturnCode("RCERR_IMAGEDISKD", 408);
        rc.addReasonCode(new ReasonCode("Image disk definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Image disk already defined", "RS_EXISTS", 4));
        rc.addReasonCode(new ReasonCode("Image disk not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Image device is locked", "RS_LOCKED", 12));
        rc.addReasonCode(new ReasonCode("Image disk sharing not allowed by target image definition", "RS_NO_SHARING", 16));
        rc.addReasonCode(new ReasonCode("Requested image disk space not available", "RS_NO_SPACE", 24));
        rc.addReasonCode(new ReasonCode("Image disk does not have required password", "RS_PW_NEEDED", 28));
        rc.addReasonCode(new ReasonCode("Incorrect password specified for image disk", "RS_BAD_PW", 32));
        rcMap.put(408, rc);
        rc = new ReturnCode("RCERR_IMAGECONND", 412);
        rc.addReasonCode(new ReasonCode("Image connectivity definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Partner image not found", "RS_NO_PARTNER", 4));
        rc.addReasonCode(new ReasonCode("Parameters do not match existing directory statement", "RS_NO_MATCH", 16));
        rc.addReasonCode(new ReasonCode("Image device not correct type for requested connection", "RS_DEV_INCOMPATIBLE", 28));
        rcMap.put(412, rc);
        rc = new ReturnCode("RCERR_PROTODEF", 416);
        rc.addReasonCode(new ReasonCode("Prototype definition error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Prototype definition not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Prototype already exists", "RS_NAME_EXISTS", 8));
        rcMap.put(416, rc);
        rc = new ReturnCode("RC_DASD_DM", 420);
        rc.addReasonCode(new ReasonCode("Group, region, or volume name is already defined", "RS_IVS_NAME_USED", 4));
        rc.addReasonCode(new ReasonCode("Group, region, or volume name is not defined", "RS_IVS_NAME_NOT_USED", 8));
        rc.addReasonCode(new ReasonCode("Region name is not included in the group", "RS_IVS_NAME_NOT_INCLUDED", 12));
        rc.addReasonCode(new ReasonCode("The requested volume is offline or is not a DASD device", "RS_IVS_NAME_NOT_DASD", 36));
        rcMap.put(420, rc);
        rc = new ReturnCode("RCERR_SEGMENT_DM", 424);
        rc.addReasonCode(new ReasonCode("Namesave statement already exists", "RS_SEG_NAME_DUPLICATE", 4));
        rc.addReasonCode(new ReasonCode("Segment name not found", "RS_SEG_NAME_NOT_FOUND", 8));
        rcMap.put(424, rc);
        rc = new ReturnCode("RCERR_NOTIFY", 428);
        rc.addReasonCode(new ReasonCode("Duplicate subscription", "RS_NOTIFY_DUP", 4));
        rc.addReasonCode(new ReasonCode("No matching entries", "RS_NOTIFY_NOT_FOUND", 8));
        rcMap.put(428, rc);
        rc = new ReturnCode("RCERR_TAG", 432);
        rc.addReasonCode(new ReasonCode("Tag name is already defined", "RS_DUP_NAME", 4));
        rc.addReasonCode(new ReasonCode("Tag name is not defined", "RS_NOT_DEFINED", 8));
        rc.addReasonCode(new ReasonCode("Tag ordinal is already defined", "RS_DUP_ORDINAL", 12));
        rc.addReasonCode(new ReasonCode("Tag is in use in one or more directory entries, can not be revoked", "RS_CANNOT_REVOKE", 16));
        rc.addReasonCode(new ReasonCode("Tag too long", "RS_LONG", 16)); /* Duplicate! What does this mean? */
        rc.addReasonCode(new ReasonCode("Use not allowed by exit routine", "RS_NOT_AUTHORIZED", 20));
        rcMap.put(432, rc);
        rc = new ReturnCode("RCERR_PROFILED", 436);
        rc.addReasonCode(new ReasonCode("Profile included not found", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Multiple profiles included", "RS_MULTIPLE", 40));
        rcMap.put(436, rc);
        rc = new ReturnCode("RCERR_POLICY_PW", 444);
        rc.addReasonCode(new ReasonCode("Password policy error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Password too long", "RS_LONG", 4));
        rc.addReasonCode(new ReasonCode("Password too short", "RS_SHORT", 8));
        rc.addReasonCode(new ReasonCode("Password content does not match policy", "RS_CONTENT", 12));
        rcMap.put(444, rc);
        rc = new ReturnCode("RCERR_POLICY_ACCT", 448);
        rc.addReasonCode(new ReasonCode("Account policy error", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Account number too long", "RS_LONG", 4));
        rc.addReasonCode(new ReasonCode("Account number too short", "RS_SHORT", 8));
        rc.addReasonCode(new ReasonCode("Account number content does not match policy", "RS_CONTENT", 12));
        rcMap.put(448, rc);
        rc = new ReturnCode("RCERR_TASK", 452);
        rc.addReasonCode(new ReasonCode("Task not found", "RS_NOT_FOUND", 4));
        rcMap.put(452, rc);
        rc = new ReturnCode("RCERR_SCSI", 456);
        rc.addReasonCode(new ReasonCode("LOADDEV statement not found", "RS_LOADDEV_NOT_FOUND", 4));
        rcMap.put(456, rc);
        rc = new ReturnCode("RCERR_DM", 500);
        rc.addReasonCode(new ReasonCode("Directory manager request could not be completed", "RS_NONE", 0));
        rc.addReasonCode(new ReasonCode("Directory manager is not accepting updates", "RS_NO_UPDATES", 4));
        rc.addReasonCode(new ReasonCode("Directory manager is not available", "RS_NOT_AVAILABLE", 8));
        rc.addReasonCode(new ReasonCode("Directory manager has been disabled", "RS_DISABLED", 12));
        rc.addReasonCode(new ReasonCode("Directory manager was interrupted", "RS_INTERRUPTED", 16));
        rc.addReasonCode(new ReasonCode("Password format not supported", "RS_PW_FORMAT_NOT_SUPPORTED", 20));
        rcMap.put(500, rc);
        rc = new ReturnCode("RCERR_LIST_DM", 504) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason) {
                return new ReasonCode("Target ID not added - Reason is product-specific return code", "RS_PSRC", reason);
            }
        };
        rcMap.put(504, rc);
        rc = new ReturnCode("RCERR_ASYNC_DM", 592) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason) {
                ReasonCode result = null;

                if (reason == 0) {
                    result = new ReasonCode("Asynchronous operation started - product-specific asynchronous operation ID is found in output_id parameter", "RS_NONE", reason);
                } else {
                    result = new ReasonCode("Asynchronous operation started - product-specific asynchronous operation ID", "RS_OPID", reason);
                }
                return result;
            }
        };
        rcMap.put(592, rc);
        rc = new ReturnCode("RCERR_INTERNAL_DM", 596) // SPECIAL
        {

            @Override
            public ReasonCode getReasonCode(int reason) {
                return new ReasonCode("Internal directory manager error - Product-specific return code", "RS_PSRC", reason);
            }
        };
        rcMap.put(596, rc);
        rc = new ReturnCode("RCERR_INTERNAL_DM", 600);
        rc.addReasonCode(new ReasonCode("Bad page range", "RS_BAD_RANGE", 8));
        rc.addReasonCode(new ReasonCode("User not logged on", "RS_NOT_LOGGED_ON", 12));
        rc.addReasonCode(new ReasonCode("Could not save segment", "RS_NOSAVE", 16));
        rc.addReasonCode(new ReasonCode("Not authorized to issue internal system command or is not authorized for RSTD segment", "RS_NOT_AUTHORIZED", 20));
        rc.addReasonCode(new ReasonCode("Conflicting parameters", "RS_CONFLICTING_PARMS", 24));
        rc.addReasonCode(new ReasonCode("Segment not found or does not exist", "RS_SEGMENT_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Class S (skeleton) segment file already exists", "RS_CLASS_S_ALREADY_DEFINED", 299));
        rcMap.put(600, rc);
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
        rcMap.put(620, rc);
        rc = new ReturnCode("RCERR_VMRM", 800);
        rc.addReasonCode(new ReasonCode("No measurement data exists", "RS_NO_MEASUREMENT_DATA", 8));
        rc.addReasonCode(new ReasonCode("Error in update buffer or processing syntax check", "RS_UPDATE_SYNTAX_ERROR", 12));
        rc.addReasonCode(new ReasonCode("Not authorized to access file", "RS_CANNOT_ACCESS_DATA", 16));
        rc.addReasonCode(new ReasonCode("Error writing file(s) to directory", "UPDATE_WRITE_ERROR", 24));
        rc.addReasonCode(new ReasonCode("Specified configuration file not found", "RS_FILE_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Internal error processing updates", "RS_UPDATE_PROCESS_ERROR", 32));
        rcMap.put(800, rc);
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
        rcMap.put(900, rc);

        syntaxErrors.put(1, "First character of listname is a colon \":\"");
        syntaxErrors.put(10, "Characters not \"0123456789\"");
        syntaxErrors.put(11, "Unsupported function");
        syntaxErrors.put(13, "Length is greater than maximum or exceeds total length");
        syntaxErrors.put(14, "Length is less than minimum");
        syntaxErrors.put(15, "Numeric value less than minimum or null value encountered");
        syntaxErrors.put(16, "Characters not \"0123456789ABCDEF\"");
        syntaxErrors.put(17, "Characters not \"0123456789ABCDEF-\"");
        syntaxErrors.put(18, "Numeric value greater than maximum");
        syntaxErrors.put(19, "Unrecognized value");
        syntaxErrors.put(23, "Conflicting parameter specified");
        syntaxErrors.put(24, "Unspecified required parameter");
        syntaxErrors.put(25, "Extraneous parameter specified");
        syntaxErrors.put(26, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"");
        syntaxErrors.put(36, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\"");
        syntaxErrors.put(37, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-\"");
        syntaxErrors.put(42, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:\"");
        syntaxErrors.put(43, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:_\"");
        syntaxErrors.put(44, "Characters not \"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$+-:_=\"");
        syntaxErrors.put(88, "Unexpected end of data");
        syntaxErrors.put(99, "Non-breaking characters: non-blank, non-null, non-delete, non-line-end, non-carriage return, non-line-feed");
    }

    /**
     * Interpret the VSMAPI Return Code
     * @param returnCode the VSMAPI Return Code
     * @return Object interpreting the VSMAPI Return Code
     */
    public ReturnCode getReturnCode(int returnCode) {
        ReturnCode result = rcMap.get(returnCode);
        if (result == null) {
            result = new VsmapiRC.ReturnCode("RC_UNKNOWN_TO_PIGIRON", returnCode);
        }
        return result;
    }

    /**
     * Object interpreting the VSMAPI Return Code. Contains a string
     * name and a value. The full textual interpretation comes from the associated
     * ResultCode.
     */
    public class ReturnCode {

        private final String name;
        private final int value;
        protected HashMap<Integer, ReasonCode> reasonCodes = new HashMap<Integer, ReasonCode>(10);

        /**
         * Instance with the {@code final} name and value.
         * @param name Error name, e.g., {@code RS_NONE}
         * @param value numerical return code.
         */
        public ReturnCode(String name, int value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Add a {@code ReasonCode} to the hash of {@code ReasonCode}s associated
         * witha given {@code ReturnCode}.
         * @param reason the numerical reason code
         */
        public void addReasonCode(ReasonCode reason) {
            reasonCodes.put(reason.getValue(), reason);
        }

        /**
         * Return a {@code ReasonCode} associated with {@code ReturnCode}.
         * If not found, return a special reason from PigIron
         * @param reason numerical VSMAPI Reason Code
         * @return the interpretive object representing the Reason Code
         */
        public ReasonCode getReasonCode(int reason) {
            ReasonCode result = null;
            if (reasonCodes.containsKey(reason)) {
                result = reasonCodes.get(reason);
            } else {
                result = new ReasonCode("PigIron does not know this reason code", "RS_UNKNOWN_TO_PIGIRON", reason);
            }
            return result;
        }

        /**
         * Get the name of the Return Code, e.g, {@code RS_NONE}
         * @return the name of the Return Code, e.g, {@code RS_NONE}
         */
        public String getName() {
            return name;
        }

        /**
         * Get the numerical value of the Return Code
         * @return the numerical value of the Return Code
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Object interpreting a VSMAPI Reason Code associated with a particular
     * Return Code.
     */
    public class ReasonCode {

        private final String message;
        private final String name;
        private final int value;

        /**
         * Instance providing message name and value
         * @param message interpretive message
         * @param name VSMAPI name of the reason code
         * @param value numeric value of the reason code
         */
        public ReasonCode(String message, String name, int value) {
            this.message = message;
            this.name = name;
            this.value = value;
        }

        /**
         * Return interpretive message of the reason code
         * @return interpretive message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Return VSMAPI name of the reason code
         * @return VSMAPI name of the reason code
         */
        public String getName() {
            return name;
        }

        /**
         * Return numeric value of the reason code
         * @return numeric value of the reason code
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Extends {@code ReasonCode} with one more field, the parameter number,
     * since VSMAPI Return Code 24 is for syntax errors and points to the
     * offending parameter.
     */
    public class ReasonCodeRC24 extends ReasonCode {

        private int paramNumber = -1;

        /**
         * Instance a Reason Code for VSMAPI Return Code 24
         * @param message interpretive message
         * @param name VSMAPI name of the reason code
         * @param value numeric value of the reason code
         * @param paramNumber the index of the parameter that caused RC24
         */
        public ReasonCodeRC24(String message, String name, int value, int paramNumber) {
            super(message, name, value);
            this.paramNumber = paramNumber;
        }

        /**
         * Get the index of the parameter that caused RC24
         * @return the index of the parameter that caused RC24
         */
        public int getParamNumber() {
            return paramNumber;
        }
    }

    /**
     * Demonstrate Return Code and Reason Code explanation
     * @param argv return_code reason_code
     */
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.err.println("Usage: VsmapiRC.main return_code reason_code");
            System.exit(1);
        }
        int rc = Integer.parseInt(argv[0]);
        int reason = Integer.parseInt(argv[1]);
        System.out.println(VsmapiRC.prettyPrint(rc, reason));
    }
}