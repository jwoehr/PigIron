#!/opt/ooRexx/bin/rexx
/*
 * Copyright (c)) 2008, Jack J. Woehr jwoehr@softwoehr.com
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

/* Utility for creating PigIron function tests */

::REQUIRES BSF.CLS
::CLASS PigFuncTest PUBLIC

  ::METHOD PigDirectory ATTRIBUTE CLASS

   ::METHOD assimilate_arguments
   	USE ARG arguments
	self~argument_array=bsf.createArray("String.class", arguments~words)
	DO i = 1 TO arguments~words
	      self~argument_array[i]=arguments~word(i)
	      END
	RETURN self~argument_array
	
   ::METHOD pigfunc_name ATTRIBUTE
   ::METHOD class_instance ATTRIBUTE
   ::METHOD function_instance ATTRIBUTE
   ::METHOD argument_array ATTRIBUTE
   ::METHOD output_array ATTRIBUTE
   
   ::METHOD INIT
   	USE ARG classname, arguments
	self~pigfunc_name=classname
	self~assimilate_arguments(arguments)
	self~class_instance=self~class~PigDirectory~at(self~pigfunc_name)

   /* This is overloaded by the subclass. */
   ::METHOD construct_instance
   	SAY "Not implemented yet"
	self~function_instance="Some invocation of self~class_instance~newStrict() using the data in argument_array"
	exit 1
	
   ::METHOD do_it
         self~output_array=self~function_instance~doIt();
	 
   ::METHOD pretty_print
   	return self~output_array~prettyPrintAll()
	
   ::METHOD INIT CLASS
   	super~init
	self~init_dictionary

   ::METHOD init_dictionary CLASS
	self~PigDirectory=.Directory~new
   	class_path='com.softwoehr.pigiron.functions'
	self~PigDirectory~put(bsf.import(class_path'.AsynchronousNotificationDisableDM'), "AsynchronousNotificationDisableDM")
	self~PigDirectory~put(bsf.import(class_path'.AsynchronousNotificationEnableDM'), "AsynchronousNotificationEnableDM")
	self~PigDirectory~put(bsf.import(class_path'.AsynchronousNotificationQueryDM'), "AsynchronousNotificationQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.AuthorizationListAdd'), "AuthorizationListAdd")
	self~PigDirectory~put(bsf.import(class_path'.AuthorizationListQuery'), "AuthorizationListQuery")
	self~PigDirectory~put(bsf.import(class_path'.AuthorizationListRemove'), "AuthorizationListRemove")
	self~PigDirectory~put(bsf.import(class_path'.CheckAuthentication'), "CheckAuthentication")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerLocalTagDefineDM'), "DirectoryManagerLocalTagDefineDM")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerLocalTagDeleteDM'), "DirectoryManagerLocalTagDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerLocalTagQueryDM'), "DirectoryManagerLocalTagQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerLocalTagSetDM'), "DirectoryManagerLocalTagSetDM")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerSearchDM'), "DirectoryManagerSearchDM")
	self~PigDirectory~put(bsf.import(class_path'.DirectoryManagerTaskCancelDM'), "DirectoryManagerTaskCancelDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageActivate'), "ImageActivate")
	self~PigDirectory~put(bsf.import(class_path'.ImageActiveConfigurationQuery'), "ImageActiveConfigurationQuery")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUDefine'), "ImageCPUDefine")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUDefineDM'), "ImageCPUDefineDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUDelete'), "ImageCPUDelete")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUDeleteDM'), "ImageCPUDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUQuery'), "ImageCPUQuery")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUQueryDM'), "ImageCPUQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageCPUSetMaximumDM'), "ImageCPUSetMaximumDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageCreateDM'), "ImageCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeactivate'), "ImageDeactivate")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeleteDM'), "ImageDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeviceDedicate'), "ImageDeviceDedicate")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeviceDedicateDM'), "ImageDeviceDedicateDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeviceReset'), "ImageDeviceReset")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeviceUndedicate'), "ImageDeviceUndedicate")
	self~PigDirectory~put(bsf.import(class_path'.ImageDeviceUndedicateDM'), "ImageDeviceUndedicateDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskCopy'), "ImageDiskCopy")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskCopyDM'), "ImageDiskCopyDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskCreate'), "ImageDiskCreate")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskCreateDM'), "ImageDiskCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskDelete'), "ImageDiskDelete")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskDeleteDM'), "ImageDiskDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskShare'), "ImageDiskShare")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskShareDM'), "ImageDiskShareDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskUnshare'), "ImageDiskUnshare")
	self~PigDirectory~put(bsf.import(class_path'.ImageDiskUnshareDM'), "ImageDiskUnshareDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageIPLDeleteDM'), "ImageIPLDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageIPLQueryDM'), "ImageIPLQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageIPLSetDM'), "ImageIPLSetDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageLockDM'), "ImageLockDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageNameQueryDM'), "ImageNameQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImagePasswordSetDM'), "ImagePasswordSetDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageQueryActivateTime'), "ImageQueryActivateTime")
	self~PigDirectory~put(bsf.import(class_path'.ImageQueryDM'), "ImageQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageRecycle'), "ImageRecycle")
	self~PigDirectory~put(bsf.import(class_path'.ImageReplaceDM'), "ImageReplaceDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageSCSICharacteristicsDefineDM'), "ImageSCSICharacteristicsDefineDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageSCSICharacteristicsQueryDM'), "ImageSCSICharacteristicsQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageStatusQuery'), "ImageStatusQuery")
	self~PigDirectory~put(bsf.import(class_path'.ImageUnlockDM'), "ImageUnlockDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageVolumeAdd'), "ImageVolumeAdd")
	self~PigDirectory~put(bsf.import(class_path'.ImageVolumeDelete'), "ImageVolumeDelete")
	self~PigDirectory~put(bsf.import(class_path'.ImageVolumeSpaceDefineDM'), "ImageVolumeSpaceDefineDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageVolumeSpaceQueryDM'), "ImageVolumeSpaceQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ImageVolumeSpaceRemoveDM'), "ImageVolumeSpaceRemoveDM")
	self~PigDirectory~put(bsf.import(class_path'.NameListAdd'), "NameListAdd")
	self~PigDirectory~put(bsf.import(class_path'.NameListDestroy'), "NameListDestroy")
	self~PigDirectory~put(bsf.import(class_path'.NameListQuery'), "NameListQuery")
	self~PigDirectory~put(bsf.import(class_path'.NameListRemove'), "NameListRemove")
	self~PigDirectory~put(bsf.import(class_path'.ProfileCreateDM'), "ProfileCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.ProfileDeleteDM'), "ProfileDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.ProfileLockDM'), "ProfileLockDM")
	self~PigDirectory~put(bsf.import(class_path'.ProfileQueryDM'), "ProfileQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.ProfileReplaceDM'), "ProfileReplaceDM")
	self~PigDirectory~put(bsf.import(class_path'.ProfileUnlockDM'), "ProfileUnlockDM")
	self~PigDirectory~put(bsf.import(class_path'.PrototypeCreateDM'), "PrototypeCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.PrototypeDeleteDM'), "PrototypeDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.PrototypeNameQueryDM'), "PrototypeNameQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.PrototypeQueryDM'), "PrototypeQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.PrototypeReplaceDM'), "PrototypeReplaceDM")
	self~PigDirectory~put(bsf.import(class_path'.QueryAPIFunctionalLevel'), "QueryAPIFunctionalLevel")
	self~PigDirectory~put(bsf.import(class_path'.QueryAsynchronousOperationDM'), "QueryAsynchronousOperationDM")
	self~PigDirectory~put(bsf.import(class_path'.QueryDirectoryManagerLevelDM'), "QueryDirectoryManagerLevelDM")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryAccessAddDM'), "SharedMemoryAccessAddDM")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryAccessQueryDM'), "SharedMemoryAccessQueryDM")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryAccessRemoveDM'), "SharedMemoryAccessRemoveDM")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryCreate'), "SharedMemoryCreate")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryDelete'), "SharedMemoryDelete")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryQuery'), "SharedMemoryQuery")
	self~PigDirectory~put(bsf.import(class_path'.SharedMemoryReplace'), "SharedMemoryReplace")
	self~PigDirectory~put(bsf.import(class_path'.StaticImageChangesActivateDM'), "StaticImageChangesActivateDM")
	self~PigDirectory~put(bsf.import(class_path'.StaticImageChangesDeactivateDM'), "StaticImageChangesDeactivateDM")
	self~PigDirectory~put(bsf.import(class_path'.StaticImageChangesImmediateDM'), "StaticImageChangesImmediateDM")
	self~PigDirectory~put(bsf.import(class_path'.VMRMConfigurationQuery'), "VMRMConfigurationQuery")
	self~PigDirectory~put(bsf.import(class_path'.VMRMConfigurationUpdate'), "VMRMConfigurationUpdate")
	self~PigDirectory~put(bsf.import(class_path'.VMRMMeasurementQuery'), "VMRMMeasurementQuery")
	self~PigDirectory~put(bsf.import(class_path'.VirtualChannelConnectionCreate'), "VirtualChannelConnectionCreate")
	self~PigDirectory~put(bsf.import(class_path'.VirtualChannelConnectionCreateDM'), "VirtualChannelConnectionCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualChannelConnectionDelete'), "VirtualChannelConnectionDelete")
	self~PigDirectory~put(bsf.import(class_path'.VirtualChannelConnectionDeleteDM'), "VirtualChannelConnectionDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterConnectLAN'), "VirtualNetworkAdapterConnectLAN")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterConnectLANDM'), "VirtualNetworkAdapterConnectLANDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterConnectVswitch'), "VirtualNetworkAdapterConnectVswitch")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterConnectVswitchDM'), "VirtualNetworkAdapterConnectVswitchDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterCreate'), "VirtualNetworkAdapterCreate")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterCreateDM'), "VirtualNetworkAdapterCreateDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterDelete'), "VirtualNetworkAdapterDelete")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterDeleteDM'), "VirtualNetworkAdapterDeleteDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterDisconnect'), "VirtualNetworkAdapterDisconnect")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterDisconnectDM'), "VirtualNetworkAdapterDisconnectDM")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkAdapterQuery'), "VirtualNetworkAdapterQuery")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkLANAccess'), "VirtualNetworkLANAccess")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkLANAccessQuery'), "VirtualNetworkLANAccessQuery")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkLANCreate'), "VirtualNetworkLANCreate")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkLANDelete'), "VirtualNetworkLANDelete")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkLANQuery'), "VirtualNetworkLANQuery")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkVswitchCreate'), "VirtualNetworkVswitchCreate")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkVswitchDelete'), "VirtualNetworkVswitchDelete")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkVswitchQuery'), "VirtualNetworkVswitchQuery")
	self~PigDirectory~put(bsf.import(class_path'.VirtualNetworkVswitchSet'), "VirtualNetworkVswitchSet")	
/* End of file */
