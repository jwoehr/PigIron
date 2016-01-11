/* 
 * Copyright (c)) 2008, 2016 Jack J. Woehr jwoehr@softwoehr.com
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

/* Utility for creating OORexx classes of PigIron VSMAPI Functions */

::REQUIRES BSF.CLS
::CLASS PigFunc PUBLIC

  ::METHOD PigDirectory ATTRIBUTE CLASS

   ::METHOD assimilate_arguments
   	USE ARG arguments
	self~argument_array=bsf.createArray("String.class", arguments~words)
	DO i = 1 TO self~argument_array_length
	      self~argument_array[i]=arguments~word(i)
	      END
	RETURN self~argument_array
	
   ::METHOD pigfunc_name ATTRIBUTE
   ::METHOD class_instance ATTRIBUTE
   ::METHOD function_instance ATTRIBUTE
   ::METHOD argument_array ATTRIBUTE
   ::METHOD argument_array_length
        RETURN self~argument_array~items
   ::METHOD output_array ATTRIBUTE
   
   ::METHOD INIT
   	USE ARG classname, arguments
	self~pigfunc_name=classname
	self~assimilate_arguments(arguments)
	self~class_instance=self~class~PigDirectory~at(self~pigfunc_name)

   ::METHOD DirectoryAt CLASS
        USE ARG aName
        RETURN self~PigDirectory~at(aName)

   /* Allow strings to be passed in with substitute chars
    * e.g., underscore for spaces, backslash for empty string,
    * etc., then convert to spaces or whatever is desired.
    */
   ::METHOD translate_argument
        USE ARG index, translate_to, to_translate
        self~argument_array[index]=TRANSLATE(self~argument_array[index], translate_to, to_translate)

   /* Allow strings to be passed in with substitute chars
    * using specifically:
    *   - underscore ("_") for space
    *   - backslash  ("\") for empty string (and *only* that one character for a blank arg)
    * which seems to be a pretty safe assigment of substitute
    * chars w/r/t VSMAPI.
    */
   ::METHOD translate_argument_conventionally
        USE ARG index
        self~argument_array[index]=TRANSLATE(self~argument_array[index], ' ', '_')
        self~argument_array[index]=STRIP(self~argument_array[index], , '\')

   /* This is overloaded by the subclass. */
   ::METHOD construct_instance
   	SAY "Not implemented yet"
	self~function_instance="Some invocation of self~class_instance~newStrict() using the data in argument_array"
	exit 1
	
   ::METHOD do_it
         self~output_array=self~function_instance~doIt();
	 
   ::METHOD pretty_print
   	return self~output_array~prettyPrintAll()

   ::METHOD invocation_message
        invoke_msg = "Invoking" self~pigfunc_name"("
          do i = 1 to self~argument_array_length
            invoke_msg = invoke_msg || self~argument_array[i]
            if i < self~argument_array_length then invoke_msg = invoke_msg', '
            end
        invoke_msg = invoke_msg')'
        return invoke_msg

   ::METHOD INIT CLASS
   	super~init
	self~init_dictionary

   ::METHOD init_dictionary CLASS
	self~PigDirectory=.Directory~new
   	class_path='com.softwoehr.pigiron.functions'
        /* PigIron functions */
        self~PigDirectory~put(bsf.import(class_path".AsynchronousNotificationDisableDM"), "AsynchronousNotificationDisableDM")
        self~PigDirectory~put(bsf.import(class_path".AsynchronousNotificationEnableDM"), "AsynchronousNotificationEnableDM")
        self~PigDirectory~put(bsf.import(class_path".AsynchronousNotificationQueryDM"), "AsynchronousNotificationQueryDM")
        self~PigDirectory~put(bsf.import(class_path".AuthorizationListAdd"), "AuthorizationListAdd")
        self~PigDirectory~put(bsf.import(class_path".AuthorizationListQuery"), "AuthorizationListQuery")
        self~PigDirectory~put(bsf.import(class_path".AuthorizationListRemove"), "AuthorizationListRemove")
        self~PigDirectory~put(bsf.import(class_path".CheckAuthentication"), "CheckAuthentication")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerLocalTagDefineDM"), "DirectoryManagerLocalTagDefineDM")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerLocalTagDeleteDM"), "DirectoryManagerLocalTagDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerLocalTagQueryDM"), "DirectoryManagerLocalTagQueryDM")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerLocalTagSetDM"), "DirectoryManagerLocalTagSetDM")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerSearchDM"), "DirectoryManagerSearchDM")
        self~PigDirectory~put(bsf.import(class_path".DirectoryManagerTaskCancelDM"), "DirectoryManagerTaskCancelDM")
        self~PigDirectory~put(bsf.import(class_path".EchoParameters"), "EchoParameters")
        self~PigDirectory~put(bsf.import(class_path".EventStreamAdd"), "EventStreamAdd")
        self~PigDirectory~put(bsf.import(class_path".EventSubscribe"), "EventSubscribe")
        self~PigDirectory~put(bsf.import(class_path".EventUnsubscribe"), "EventUnsubscribe")
        self~PigDirectory~put(bsf.import(class_path".ImageActivate"), "ImageActivate")
        self~PigDirectory~put(bsf.import(class_path".ImageActiveConfigurationQuery"), "ImageActiveConfigurationQuery")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUDefine"), "ImageCPUDefine")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUDefineDM"), "ImageCPUDefineDM")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUDelete"), "ImageCPUDelete")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUDeleteDM"), "ImageCPUDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUQuery"), "ImageCPUQuery")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUQueryDM"), "ImageCPUQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImageCPUSetMaximumDM"), "ImageCPUSetMaximumDM")
        self~PigDirectory~put(bsf.import(class_path".ImageCreateDM"), "ImageCreateDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDeactivate"), "ImageDeactivate")
        self~PigDirectory~put(bsf.import(class_path".ImageDeleteDM"), "ImageDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDeviceDedicate"), "ImageDeviceDedicate")
        self~PigDirectory~put(bsf.import(class_path".ImageDeviceDedicateDM"), "ImageDeviceDedicateDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDeviceReset"), "ImageDeviceReset")
        self~PigDirectory~put(bsf.import(class_path".ImageDeviceUndedicate"), "ImageDeviceUndedicate")
        self~PigDirectory~put(bsf.import(class_path".ImageDeviceUndedicateDM"), "ImageDeviceUndedicateDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskCopy"), "ImageDiskCopy")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskCopyDM"), "ImageDiskCopyDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskCreate"), "ImageDiskCreate")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskCreateDM"), "ImageDiskCreateDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskDelete"), "ImageDiskDelete")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskDeleteDM"), "ImageDiskDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskShare"), "ImageDiskShare")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskShareDM"), "ImageDiskShareDM")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskUnshare"), "ImageDiskUnshare")
        self~PigDirectory~put(bsf.import(class_path".ImageDiskUnshareDM"), "ImageDiskUnshareDM")
        self~PigDirectory~put(bsf.import(class_path".ImageIPLDeleteDM"), "ImageIPLDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".ImageIPLQueryDM"), "ImageIPLQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImageIPLSetDM"), "ImageIPLSetDM")
        self~PigDirectory~put(bsf.import(class_path".ImageLockDM"), "ImageLockDM")
        self~PigDirectory~put(bsf.import(class_path".ImageMDISKLinkQuery"), "ImageMDISKLinkQuery")
        self~PigDirectory~put(bsf.import(class_path".ImageNameQueryDM"), "ImageNameQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImagePasswordSetDM"), "ImagePasswordSetDM")
        self~PigDirectory~put(bsf.import(class_path".ImageQueryActivateTime"), "ImageQueryActivateTime")
        self~PigDirectory~put(bsf.import(class_path".ImageQueryDM"), "ImageQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImageRecycle"), "ImageRecycle")
        self~PigDirectory~put(bsf.import(class_path".ImageReplaceDM"), "ImageReplaceDM")
        self~PigDirectory~put(bsf.import(class_path".ImageSCSICharacteristicsDefineDM"), "ImageSCSICharacteristicsDefineDM")
        self~PigDirectory~put(bsf.import(class_path".ImageSCSICharacteristicsQueryDM"), "ImageSCSICharacteristicsQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImageStatusQuery"), "ImageStatusQuery")
        self~PigDirectory~put(bsf.import(class_path".ImageUnlockDM"), "ImageUnlockDM")
        self~PigDirectory~put(bsf.import(class_path".ImageVolumeAdd"), "ImageVolumeAdd")
        self~PigDirectory~put(bsf.import(class_path".ImageVolumeDelete"), "ImageVolumeDelete")
        self~PigDirectory~put(bsf.import(class_path".ImageVolumeSpaceDefineDM"), "ImageVolumeSpaceDefineDM")
        self~PigDirectory~put(bsf.import(class_path".ImageVolumeSpaceQueryDM"), "ImageVolumeSpaceQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ImageVolumeSpaceRemoveDM"), "ImageVolumeSpaceRemoveDM")
        self~PigDirectory~put(bsf.import(class_path".NameListAdd"), "NameListAdd")
        self~PigDirectory~put(bsf.import(class_path".NameListDestroy"), "NameListDestroy")
        self~PigDirectory~put(bsf.import(class_path".NameListQuery"), "NameListQuery")
        self~PigDirectory~put(bsf.import(class_path".NameListRemove"), "NameListRemove")
        self~PigDirectory~put(bsf.import(class_path".ProfileCreateDM"), "ProfileCreateDM")
        self~PigDirectory~put(bsf.import(class_path".ProfileDeleteDM"), "ProfileDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".ProfileLockDM"), "ProfileLockDM")
        self~PigDirectory~put(bsf.import(class_path".ProfileQueryDM"), "ProfileQueryDM")
        self~PigDirectory~put(bsf.import(class_path".ProfileReplaceDM"), "ProfileReplaceDM")
        self~PigDirectory~put(bsf.import(class_path".ProfileUnlockDM"), "ProfileUnlockDM")
        self~PigDirectory~put(bsf.import(class_path".PrototypeCreateDM"), "PrototypeCreateDM")
        self~PigDirectory~put(bsf.import(class_path".PrototypeDeleteDM"), "PrototypeDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".PrototypeNameQueryDM"), "PrototypeNameQueryDM")
        self~PigDirectory~put(bsf.import(class_path".PrototypeQueryDM"), "PrototypeQueryDM")
        self~PigDirectory~put(bsf.import(class_path".PrototypeReplaceDM"), "PrototypeReplaceDM")
        self~PigDirectory~put(bsf.import(class_path".QueryAPIFunctionalLevel"), "QueryAPIFunctionalLevel")
        self~PigDirectory~put(bsf.import(class_path".QueryAllDM"), "QueryAllDM")
        self~PigDirectory~put(bsf.import(class_path".QueryAsynchronousOperationDM"), "QueryAsynchronousOperationDM")
        self~PigDirectory~put(bsf.import(class_path".QueryDirectoryManagerLevelDM"), "QueryDirectoryManagerLevelDM")
        self~PigDirectory~put(bsf.import(class_path".SMAPIStatusCapture"), "SMAPIStatusCapture")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryAccessAddDM"), "SharedMemoryAccessAddDM")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryAccessQueryDM"), "SharedMemoryAccessQueryDM")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryAccessRemoveDM"), "SharedMemoryAccessRemoveDM")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryCreate"), "SharedMemoryCreate")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryDelete"), "SharedMemoryDelete")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryQuery"), "SharedMemoryQuery")
        self~PigDirectory~put(bsf.import(class_path".SharedMemoryReplace"), "SharedMemoryReplace")
        self~PigDirectory~put(bsf.import(class_path".StaticImageChangesActivateDM"), "StaticImageChangesActivateDM")
        self~PigDirectory~put(bsf.import(class_path".StaticImageChangesDeactivateDM"), "StaticImageChangesDeactivateDM")
        self~PigDirectory~put(bsf.import(class_path".StaticImageChangesImmediateDM"), "StaticImageChangesImmediateDM")
        self~PigDirectory~put(bsf.import(class_path".VMRMConfigurationQuery"), "VMRMConfigurationQuery")
        self~PigDirectory~put(bsf.import(class_path".VMRMConfigurationUpdate"), "VMRMConfigurationUpdate")
        self~PigDirectory~put(bsf.import(class_path".VMRMMeasurementQuery"), "VMRMMeasurementQuery")
        self~PigDirectory~put(bsf.import(class_path".VSMCall"), "VSMCall")
        self~PigDirectory~put(bsf.import(class_path".VirtualChannelConnectionCreate"), "VirtualChannelConnectionCreate")
        self~PigDirectory~put(bsf.import(class_path".VirtualChannelConnectionCreateDM"), "VirtualChannelConnectionCreateDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualChannelConnectionDelete"), "VirtualChannelConnectionDelete")
        self~PigDirectory~put(bsf.import(class_path".VirtualChannelConnectionDeleteDM"), "VirtualChannelConnectionDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterConnectLAN"), "VirtualNetworkAdapterConnectLAN")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterConnectLANDM"), "VirtualNetworkAdapterConnectLANDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterConnectVswitch"), "VirtualNetworkAdapterConnectVswitch")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterConnectVswitchDM"), "VirtualNetworkAdapterConnectVswitchDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterCreate"), "VirtualNetworkAdapterCreate")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterCreateDM"), "VirtualNetworkAdapterCreateDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterDelete"), "VirtualNetworkAdapterDelete")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterDeleteDM"), "VirtualNetworkAdapterDeleteDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterDisconnect"), "VirtualNetworkAdapterDisconnect")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterDisconnectDM"), "VirtualNetworkAdapterDisconnectDM")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkAdapterQuery"), "VirtualNetworkAdapterQuery")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkLANAccess"), "VirtualNetworkLANAccess")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkLANAccessQuery"), "VirtualNetworkLANAccessQuery")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkLANCreate"), "VirtualNetworkLANCreate")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkLANDelete"), "VirtualNetworkLANDelete")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkLANQuery"), "VirtualNetworkLANQuery")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkVswitchCreate"), "VirtualNetworkVswitchCreate")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkVswitchDelete"), "VirtualNetworkVswitchDelete")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkVswitchQuery"), "VirtualNetworkVswitchQuery")
        self~PigDirectory~put(bsf.import(class_path".VirtualNetworkVswitchSet"), "VirtualNetworkVswitchSet")
        /* PigIron basic SMAPI data types */
        class_path='com.softwoehr.pigiron.access'
        self~PigDirectory~put(bsf.import(class_path".CountedArray"), "CountedArray")
        self~PigDirectory~put(bsf.import(class_path".CountedString"), "CountedString")
        self~PigDirectory~put(bsf.import(class_path".CountedStringZ"), "CountedStringZ")
        self~PigDirectory~put(bsf.import(class_path".CountedStruct"), "CountedStruct")
        self~PigDirectory~put(bsf.import(class_path".ParameterArray"), "ParameterArray")
        self~PigDirectory~put(bsf.import(class_path".StringBlankSeparatedNullTerminatedStruct"), "StringBlankSeparatedNullTerminatedStruct")
        self~PigDirectory~put(bsf.import(class_path".VSMArray"), "VSMArray")
        self~PigDirectory~put(bsf.import(class_path".VSMAsciiZ"), "VSMAsciiZ")
        self~PigDirectory~put(bsf.import(class_path".VSMAsciiZArray"), "VSMAsciiZArray")
        self~PigDirectory~put(bsf.import(class_path".VSMException"), "VSMException")
        self~PigDirectory~put(bsf.import(class_path".VSMInt"), "VSMInt")
        self~PigDirectory~put(bsf.import(class_path".VSMInt1"), "VSMInt1")
        self~PigDirectory~put(bsf.import(class_path".VSMInt4"), "VSMInt4")
        self~PigDirectory~put(bsf.import(class_path".VSMInt8"), "VSMInt8")
        self~PigDirectory~put(bsf.import(class_path".VSMParm"), "VSMParm")
        self~PigDirectory~put(bsf.import(class_path".VSMString"), "VSMString")
        self~PigDirectory~put(bsf.import(class_path".VSMStruct"), "VSMStruct")
        /* PigIron SMAPI derived data types */
        class_path='com.softwoehr.pigiron.access.paramstructs'
        self~PigDirectory~put(bsf.import(class_path".AdapterArray"), "AdapterArray")
        self~PigDirectory~put(bsf.import(class_path".AdapterStructure"), "AdapterStructure")
        self~PigDirectory~put(bsf.import(class_path".AdapterStructureCounted"), "AdapterStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".AuthRecordArray"), "AuthRecordArray")
        self~PigDirectory~put(bsf.import(class_path".AuthRecordStructure"), "AuthRecordStructure")
        self~PigDirectory~put(bsf.import(class_path".AuthRecordStructureCounted"), "AuthRecordStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".AuthorizedUserArray"), "AuthorizedUserArray")
        self~PigDirectory~put(bsf.import(class_path".AuthorizedUserStructure"), "AuthorizedUserStructure")
        self~PigDirectory~put(bsf.import(class_path".AuthorizedUserStructureCounted"), "AuthorizedUserStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".AuthorizedUsersArray"), "AuthorizedUsersArray")
        self~PigDirectory~put(bsf.import(class_path".ConnectedAdapterArray"), "ConnectedAdapterArray")
        self~PigDirectory~put(bsf.import(class_path".ConnectedAdapterStructure"), "ConnectedAdapterStructure")
        self~PigDirectory~put(bsf.import(class_path".ConnectedAdapterStructureCounted"), "ConnectedAdapterStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".CpuInfo2Array"), "CpuInfo2Array")
        self~PigDirectory~put(bsf.import(class_path".CpuInfo2Structure"), "CpuInfo2Structure")
        self~PigDirectory~put(bsf.import(class_path".CpuInfo2StructureCounted"), "CpuInfo2StructureCounted")
        self~PigDirectory~put(bsf.import(class_path".CpuInfoArray"), "CpuInfoArray")
        self~PigDirectory~put(bsf.import(class_path".CpuInfoStructure"), "CpuInfoStructure")
        self~PigDirectory~put(bsf.import(class_path".CpuInfoStructureCounted"), "CpuInfoStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".DeviceInfoArray"), "DeviceInfoArray")
        self~PigDirectory~put(bsf.import(class_path".DeviceInfoStructure"), "DeviceInfoStructure")
        self~PigDirectory~put(bsf.import(class_path".DeviceInfoStructureCounted"), "DeviceInfoStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryArray"), "DirectoryEntryArray")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryDataArray"), "DirectoryEntryDataArray")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryDataStructure"), "DirectoryEntryDataStructure")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryDataStructureCounted"), "DirectoryEntryDataStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryFormatNoArray"), "DirectoryEntryFormatNoArray")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryFormatNoStructure"), "DirectoryEntryFormatNoStructure")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryFormatNoStructureCounted"), "DirectoryEntryFormatNoStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryStructure"), "DirectoryEntryStructure")
        self~PigDirectory~put(bsf.import(class_path".DirectoryEntryStructureCounted"), "DirectoryEntryStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".FailingArray"), "FailingArray")
        self~PigDirectory~put(bsf.import(class_path".FailingStructure"), "FailingStructure")
        self~PigDirectory~put(bsf.import(class_path".FailingStructureCounted"), "FailingStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".ImageNameArray"), "ImageNameArray")
        self~PigDirectory~put(bsf.import(class_path".ImageNameStructure"), "ImageNameStructure")
        self~PigDirectory~put(bsf.import(class_path".ImageNameStructureCounted"), "ImageNameStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".ImageRecordArray"), "ImageRecordArray")
        self~PigDirectory~put(bsf.import(class_path".ImageRecordStructure"), "ImageRecordStructure")
        self~PigDirectory~put(bsf.import(class_path".ImageRecordStructureCounted"), "ImageRecordStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".LanStructure"), "LanStructure")
        self~PigDirectory~put(bsf.import(class_path".LinkArray"), "LinkArray")
        self~PigDirectory~put(bsf.import(class_path".LinkStructure"), "LinkStructure")
        self~PigDirectory~put(bsf.import(class_path".MemorySegmentArray"), "MemorySegmentArray")
        self~PigDirectory~put(bsf.import(class_path".MemorySegmentStructure"), "MemorySegmentStructure")
        self~PigDirectory~put(bsf.import(class_path".MemorySegmentStructureCounted"), "MemorySegmentStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".NameArray"), "NameArray")
        self~PigDirectory~put(bsf.import(class_path".NameStructure"), "NameStructure")
        self~PigDirectory~put(bsf.import(class_path".NameStructureCounted"), "NameStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".NotificationArray"), "NotificationArray")
        self~PigDirectory~put(bsf.import(class_path".NotificationStructure"), "NotificationStructure")
        self~PigDirectory~put(bsf.import(class_path".NotificationStructureCounted"), "NotificationStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".PageRangeArray"), "PageRangeArray")
        self~PigDirectory~put(bsf.import(class_path".PageRangeStructure"), "PageRangeStructure")
        self~PigDirectory~put(bsf.import(class_path".PageRangeStructureCounted"), "PageRangeStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".ProfileRecordArray"), "ProfileRecordArray")
        self~PigDirectory~put(bsf.import(class_path".ProfileRecordStructure"), "ProfileRecordStructure")
        self~PigDirectory~put(bsf.import(class_path".ProfileRecordStructureCounted"), "ProfileRecordStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".PrototypeNameArray"), "PrototypeNameArray")
        self~PigDirectory~put(bsf.import(class_path".PrototypeNameStructure"), "PrototypeNameStructure")
        self~PigDirectory~put(bsf.import(class_path".PrototypeNameStructureCounted"), "PrototypeNameStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".PrototypeRecordArray"), "PrototypeRecordArray")
        self~PigDirectory~put(bsf.import(class_path".PrototypeRecordStructure"), "PrototypeRecordStructure")
        self~PigDirectory~put(bsf.import(class_path".PrototypeRecordStructureCounted"), "PrototypeRecordStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".RealDeviceArray"), "RealDeviceArray")
        self~PigDirectory~put(bsf.import(class_path".RealDeviceStructure"), "RealDeviceStructure")
        self~PigDirectory~put(bsf.import(class_path".RealDeviceStructureCounted"), "RealDeviceStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".RecordArray"), "RecordArray")
        self~PigDirectory~put(bsf.import(class_path".RecordStructure"), "RecordStructure")
        self~PigDirectory~put(bsf.import(class_path".RecordStructureCounted"), "RecordStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".StatementArray"), "StatementArray")
        self~PigDirectory~put(bsf.import(class_path".StatementStructure"), "StatementStructure")
        self~PigDirectory~put(bsf.import(class_path".StatementStructureCounted"), "StatementStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".VlanArray"), "VlanArray")
        self~PigDirectory~put(bsf.import(class_path".VlanStructure"), "VlanStructure")
        self~PigDirectory~put(bsf.import(class_path".VlanStructureCounted"), "VlanStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".VswitchArray"), "VswitchArray")
        self~PigDirectory~put(bsf.import(class_path".VswitchStructure"), "VswitchStructure")
        self~PigDirectory~put(bsf.import(class_path".VswitchStructureCounted"), "VswitchStructureCounted")
        self~PigDirectory~put(bsf.import(class_path".WorkloadArray"), "WorkloadArray")
        self~PigDirectory~put(bsf.import(class_path".WorkloadStructure"), "WorkloadStructure")
        self~PigDirectory~put(bsf.import(class_path".WorkloadStructureCounted"), "WorkloadStructureCounted")

/* End of file */
