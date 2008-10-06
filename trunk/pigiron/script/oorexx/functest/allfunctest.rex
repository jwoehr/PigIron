#!/opt/ooRexx/bin/rexx
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

/*
 * Requires ObjectRexx http://sourceforge.net/projects/oorexx
 *          BSF4REXX   http://wi.wu-wien.ac.at/rgf/rexx/bsf4rexx/current/
 */

/* ***************** */
/* Invoke many tests */
/* ***************** */
/* The trick here is that there are many VSMAPI functions which use six simple parameters
 * that often can be used in simplistic testing for several different functions represented
 * by the first six parms to this ObjectRexx script:
 *
 *' my.host my.port my.userid my.password my.target my.extraparm
 *
 * After those first six (6) the arg list is parsed for name/value pairs by walking
 * the arg list in two's (2's). Any pair, e.g.,
 *
 *   imagediskcreate.imagedisknumber 1234
 *
 * is stored:
 *
 *   my.custom.imagediskcreate.imagedisknumber=1234
 *
 * These pairs are reported after parsing early in the script. Then they may
 * be used anywhere in the script as custom arguments to VSMAPI functions.
 */

-- Set all stems descending from "my." to an empty string
-- so we can easily test for "is set?"
my. = ''

-- Get the args and source
PARSE ARG my.arglist
PARSE SOURCE my.platform my.invocation my.command
if my.arglist~words < 6 then signal usage
PARSE VAR my.arglist my.host my.port my.userid my.password my.target my.extraparm my.custom.parameters
SAY "Performing" my.command my.host my.port my.userid my.password my.target my.extraparm my.custom.parameters

-- Parse the custom parameters
my.custom.parameters.list=''
-- Load defaults first so user-supplied defaults will override
CALL load_defaults "allfunctest.defaults"
-- Now examine custom parameter portion of supplied args which override defaults
IF my.custom.parameters~words > 0 THEN,
DO i = 1 to my.custom.parameters~words BY 2
	CALL set_custom my.custom.parameters~word(i) my.custom.parameters~word(i+1)
	END;,
DO i = 1 to my.custom.parameters.list~words
	INTERPRET "my.tmp =" my.custom.parameters.list~word(i)
	say my.custom.parameters.list~word(i) '==' my.tmp
	END

-- *********
-- Run tests
-- *********

-- ---------------------------------- --
-- First we'll run some easy queries. --
-- ---------------------------------- --

CALL testing 'CheckAuthentication' my.host my.port my.userid my.password my.target
CALL testing 'QueryAPIFunctionalLevel' my.host my.port my.userid my.password my.target

-- Here we activate an image in case the user specified it in the target param so the easy queries would apply to it
IF my.custom.imageactivate.targetid \= '' THEN CALL testing 'ImageActivate' my.host my.port my.userid my.password my.custom.imageactivate.targetid
ELSE CALL explain_skip "ImageActivate" "imageactivate.targetid"
	
CALL testing 'ImageActiveConfigurationQuery' my.host my.port my.userid my.password my.target

-- 5.4
CALL testing 'ImageCPUQuery' my.host my.port my.userid my.password my.target

CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
	.PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_MMDDYY
CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
	.PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_MMDDYYYY
CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
        .PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_YYMMDD
CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
        .PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_YYYYMMDD
CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
        .PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_DDMMYY
CALL testing 'ImageQueryActivateTime' my.host my.port my.userid my.password my.target,
        .PigFunc~DirectoryAt('ImageQueryActivateTime')~DATE_FORMAT_INDICATOR_DDMMYYYY

CALL testing 'ImageQueryDM' my.host my.port my.userid my.password my.target
CALL testing 'ImageStatusQuery' my.host my.port my.userid my.password my.target
CALL testing 'ImageStatusQuery' my.host my.port my.userid my.password "*"

-- Here we deactivate the image we activated
IF my.custom.imagedeactivate.targetid \= '' THEN
	IF my.custom.imagedeactivate.forcetime \= '' THEN
	CALL testing 'ImageDeactivate' my.host my.port my.userid my.password my.custom.imagedeactivate.targetid my.custom.imagedeactivate.forcetime
	ELSE CALL explain_skip "ImageDeactivate" "imagedeactivate.forcetime"
ELSE CALL explain_skip "ImageDeactivate" "imagedeactivate.targetid"

-- More easy queries
CALL testing 'ImageIPLQueryDM' my.host my.port my.userid my.password my.target
CALL testing 'ImageNameQueryDM' my.host my.port my.userid my.password my.target
CALL testing 'NameListQuery' my.host my.port my.userid my.password "*"
CALL testing 'ProfileQueryDM' my.host my.port my.userid my.password my.target
CALL testing 'PrototypeNameQueryDM' my.host my.port my.userid my.password my.target
CALL testing 'QueryDirectoryManagerLevelDM' my.host my.port my.userid my.password my.target
CALL testing 'SharedMemoryQuery' my.host my.port my.userid my.password my.target "*"
CALL testing 'SharedMemoryQuery' my.host my.port my.userid my.password my.target "CMSPIPES"
CALL testing 'VMRMMeasurementQuery' my.host my.port my.userid my.password my.target
CALL testing 'VirtualNetworkAdapterQuery' my.host my.port my.userid my.password my.target "*"

-- ------------------------------- --
-- Slightly more intricate queries
-- ------------------------------- --
IF my.custom.imagedeletedm.imagetodelete \= '' THEN -- We should also test other DATA_SECURITY_ERASE flags
	CALL testing 'ImageDeleteDM' my.host my.port my.userid my.password my.custom.imagedeletedm.imagetodelete .PigFunc~DirectoryAt('ImageDeleteDM')~DATA_SECURITY_ERASE_UNSPECIFIED
ELSE CALL explain_skip 'ImageDeleteDM' "imagedeletedm.imagetodelete"

IF my.custom.directorymanagersearchdm.searchpattern \= '' THEN CALL testing 'DirectoryManagerSearchDM' my.host my.port my.userid my.password my.target my.custom.directorymanagersearchdm.searchpattern
ELSE CALL explain_skip 'DirectoryManagerSearchDM' "directorymanagersearchdm.searchpattern"
	
IF my.custom.sharedmemoryaccessquerydm.memorysegmentname \= '' THEN CALL testing 'SharedMemoryAccessQueryDM' my.host my.port my.userid my.password my.target my.custom.sharedmemoryaccessquerydm.memorysegmentname
ELSE CALL explain_skip 'SharedMemoryAccessQueryDM' 'sharedmemoryaccessquerydm.memorysegmentname'

IF my.custom.virtualnetworklanaccessquery.lanname \= ''THEN
	IF my.custom.virtualnetworklanaccessquery.lanowner \= '' THEN
	CALL testing 'VirtualNetworkLANAccessQuery' my.host my.port my.userid my.password my.target my.custom.virtualnetworklanaccessquery.lanname my.custom.virtualnetworklanaccessquery.lanowner
	ELSE CALL explain_skip 'VirtualNetworkLANAccessQuery' 'virtualnetworklanaccessquery.lanowner'
ELSE CALL explain_skip 'VirtualNetworkLANAccessQuery' 'virtualnetworklanaccessquery.lanname'

IF my.custom.virtualnetworklanaccessquery.lanname \= ''THEN
	IF my.custom.virtualnetworklanaccessquery.lanowner \= '' THEN
	CALL testing 'VirtualNetworkLANQuery' my.host my.port my.userid my.password my.target my.custom.virtualnetworklanquery.lanname my.custom.virtualnetworklanquery.lanowner
	ELSE CALL explain_skip 'VirtualNetworkLANQuery' 'virtualnetworklanquery.lanowner'
ELSE CALL explain_skip 'VirtualNetworkLANQuery' 'virtualnetworklanquery.lanname'

IF my.custom.virtualnetworkvswitchquery.switchname \= '' THEN CALL testing 'VirtualNetworkVswitchQuery' my.host my.port my.userid my.password my.target my.custom.virtualnetworkvswitchquery.switchname
ELSE CALL explain_skip 'VirtualNetworkVswitchQuery' 'virtualnetworkvswitchquery.switchname'

-- 3 x 3 yields nine different cases for '*' ... we should also test specific entry_names
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_DEFINITION .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_VOLUME '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_DEFINITION .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_REGION '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_DEFINITION .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_GROUP '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_FREE .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_VOLUME '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_FREE .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_REGION '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_FREE .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_GROUP '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_USED .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_VOLUME '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_USED .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_REGION '*'
CALL testing 'ImageVolumeSpaceQueryDM' my.host my.port my.userid my.password my.target .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~QUERY_TYPE_USED .PigFunc~DirectoryAt('ImageVolumeSpaceQueryDM')~ENTRY_TYPE_GROUP '*'

CALL testing 'PrototypeQueryDM' my.host my.port my.userid my.password my.custom.prototypequerydm.prototypetoquery
CALL testing 'PrototypeQueryDM' my.host my.port my.userid my.password 'CMS'
CALL testing 'PrototypeQueryDM' my.host my.port my.userid my.password 'LINUX'

-- ------------ --
-- Harder stuff --
-- ------------ --
-- CALL testing 'ImageCreateDM' my.host my.port my.userid my.password my.target,
--	my.custom.imagecreatedm.prototypename my.custom.imagecreatedm.intialpassword my.custom.imagecreatedm.accountnumber my.custom.imagecreatedm.alltheotherargs


-- CALL testing 'AsynchronousNotificationDisableDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'AsynchronousNotificationEnableDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'AsynchronousNotificationQueryDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'AuthorizationListAdd' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'AuthorizationListQuery' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'AuthorizationListRemove' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'DirectoryManagerLocalTagDefineDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'DirectoryManagerLocalTagDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'DirectoryManagerLocalTagQueryDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'DirectoryManagerLocalTagSetDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'DirectoryManagerTaskCancelDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageCPUDefine' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageCPUDefineDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageCPUDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageCPUDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageCPUQueryDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageCPUSetMaximumDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageDeviceDedicate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDeviceDedicateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDeviceReset' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDeviceUndedicate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDeviceUndedicateDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageDiskCopy' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskCopyDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskCreate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskCreateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskShare' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskShareDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskUnshare' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageDiskUnshareDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageIPLDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageIPLSetDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageLockDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImagePasswordSetDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageRecycle' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageReplaceDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageSCSICharacteristicsDefineDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageSCSICharacteristicsQueryDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageUnlockDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageVolumeAdd' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageVolumeDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ImageVolumeSpaceDefineDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'ImageVolumeSpaceRemoveDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'NameListAdd' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'NameListDestroy' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'NameListRemove' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ProfileCreateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ProfileDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ProfileLockDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ProfileReplaceDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'ProfileUnlockDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'PrototypeCreateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'PrototypeDeleteDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'PrototypeReplaceDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'QueryAsynchronousOperationDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'SharedMemoryAccessAddDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'SharedMemoryAccessRemoveDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'SharedMemoryCreate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'SharedMemoryDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'SharedMemoryReplace' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'StaticImageChangesActivateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'StaticImageChangesDeactivateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'StaticImageChangesImmediateDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VMRMConfigurationQuery' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VMRMConfigurationUpdate' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VirtualChannelConnectionCreate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualChannelConnectionCreateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualChannelConnectionDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualChannelConnectionDeleteDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VirtualNetworkAdapterConnectLAN' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterConnectLANDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterConnectVswitch' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterConnectVswitchDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterCreate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterCreateDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterDeleteDM' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterDisconnect' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkAdapterDisconnectDM' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VirtualNetworkLANAccess' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VirtualNetworkLANDelete' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkVswitchCreate' my.host my.port my.userid my.password my.target my.extraparm
-- CALL testing 'VirtualNetworkVswitchDelete' my.host my.port my.userid my.password my.target my.extraparm

-- CALL testing 'VirtualNetworkVswitchSet' my.host my.port my.userid my.password my.target my.extraparm

CALL draw_pig
SAY "PigIron All Function Test is complete"
exit 0

draw_pig:
PROCEDURE
SAY " /\- -/\"
SAY "( (o o) )"
SAY ""
RETURN

testing:
PROCEDURE
PARSE ARG the_function its_args
CALL draw_pig
SAY "CALLing" the_function its_args
INTERPRET 'CALL' "'"the_function"'" "'"its_args"'"
RETURN

explain_skip:
PROCEDURE EXPOSE my.
PARSE ARG funcname custom_param_name
CALL draw_pig
SAY "Skipping" funcname "since no custom value for" custom_param_name "was offered in the paired custom arguments to" my.command
SAY "Run" my.command "with no arguments for help."
RETURN

usage:
SAY "Usage:" my.command "my.host my.port my.userid my.password my.target my.extraparm [[parmid parmval] [parmid parmval] ..]"
SAY " The first six parms to this ObjectRexx script are stored as:"
SAY ""
SAY "' my.host my.port my.userid my.password my.target my.extraparm"
SAY ""
SAY " After those first six (6) the arg list is parsed for name/value pairs by walking"
SAY " the arg list in two's (2's). Any pair, e.g.,"
SAY ""
SAY "   imagediskcreate.imagedisknumber 1234"
SAY ""
SAY " is stored:"
SAY ""
SAY "   my.custom.imagediskcreate.imagedisknumber=1234"
SAY ""
SAY " These pairs are reported after parsing early in the script. Then they may"
SAY " be used anywhere in the script as custom arguments to VSMAPI functions."
SAY ""
SAY "List of recognized custom parameters follows:"
SAY "(none yet)"
EXIT 1

set_custom:
PROCEDURE EXPOSE my.
PARSE ARG custom_param_name custom_param_value
INTERPRET 'my.custom.'custom_param_name '=' custom_param_value
my.custom.parameters.list=my.custom.parameters.list 'my.custom.'custom_param_name
RETURN

load_defaults:
PROCEDURE EXPOSE my.
PARSE ARG filename
IF SysIsFile(filename) THEN,
	DO WHILE Lines(filename)
		tmp = LineIn(filename)
		CALL set_custom tmp~word(1) tmp~word(2)
	END
RETURN

::REQUIRES 'pigfunc.cls'

/* Fin */
