/*
 *  Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 *  PO Box 51, Golden, Colorado 80402-0051 USA
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer
 *  in the documentation and/or other materials provided with the
 *  distribution.
 *  * Neither the name of the PigIron Project nor the names of its
 *  contributors may be used to endorse or promote products derived
 *  from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 *  THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.softwoehr.pigiron.webobj.topview.functions;

import java.util.Hashtable;

/**
 * FunctionTable is static representation of mapping between strings found in the function_name field
 * of the Function JSON object in the Requestor and the proxy functions we use to bind a JSON
 * request to a PigIron VSMAPI call.
 *
 */
public class FunctionTable {
     static public final Hashtable<String, Class<? extends FunctionProxy>> functions;

    static {
	functions = new Hashtable<String, Class<? extends FunctionProxy>>(200);
        // functions.put("AsynchronousNotificationDisableDM", AsynchronousNotificationDisableDM.class);
        // functions.put("AsynchronousNotificationEnableDM", AsynchronousNotificationEnableDM.class);
        // functions.put("AsynchronousNotificationQueryDM", AsynchronousNotificationQueryDM.class);
        // functions.put("AuthorizationListAdd", AuthorizationListAdd.class);
        // functions.put("AuthorizationListQuery", AuthorizationListQuery.class);
        // functions.put("AuthorizationListRemove", AuthorizationListRemove.class);
        functions.put("CheckAuthentication", CheckAuthentication.class);
        // functions.put("DirectoryManagerLocalTagDefineDM", DirectoryManagerLocalTagDefineDM.class);
        // functions.put("DirectoryManagerLocalTagDeleteDM", DirectoryManagerLocalTagDeleteDM.class);
        // functions.put("DirectoryManagerLocalTagQueryDM", DirectoryManagerLocalTagQueryDM.class);
        // functions.put("DirectoryManagerLocalTagSetDM", DirectoryManagerLocalTagSetDM.class);
        // functions.put("DirectoryManagerSearchDM", DirectoryManagerSearchDM.class);
        // functions.put("DirectoryManagerTaskCancelDM", DirectoryManagerTaskCancelDM.class);
        // functions.put("ImageActivate", ImageActivate.class);
        // functions.put("ImageActiveConfigurationQuery", ImageActiveConfigurationQuery.class);
        // functions.put("ImageCPUDefine", ImageCPUDefine.class);
        // functions.put("ImageCPUDefineDM", ImageCPUDefineDM.class);
        // functions.put("ImageCPUDelete", ImageCPUDelete.class);
        // functions.put("ImageCPUDeleteDM", ImageCPUDeleteDM.class);
        functions.put("ImageCPUQuery", ImageCPUQuery.class);
        // functions.put("ImageCPUQueryDM", ImageCPUQueryDM.class);
        // functions.put("ImageCPUSetMaximumDM", ImageCPUSetMaximumDM.class);
        // functions.put("ImageCreateDM", ImageCreateDM.class);
        // functions.put("ImageDeactivate", ImageDeactivate.class);
        // functions.put("ImageDeleteDM", ImageDeleteDM.class);
        // functions.put("ImageDeviceDedicate", ImageDeviceDedicate.class);
        // functions.put("ImageDeviceDedicateDM", ImageDeviceDedicateDM.class);
        // functions.put("ImageDeviceReset", ImageDeviceReset.class);
        // functions.put("ImageDeviceUndedicate", ImageDeviceUndedicate.class);
        // functions.put("ImageDeviceUndedicateDM", ImageDeviceUndedicateDM.class);
        // functions.put("ImageDiskCopy", ImageDiskCopy.class);
        // functions.put("ImageDiskCopyDM", ImageDiskCopyDM.class);
        // functions.put("ImageDiskCreate", ImageDiskCreate.class);
        // functions.put("ImageDiskCreateDM", ImageDiskCreateDM.class);
        // functions.put("ImageDiskDelete", ImageDiskDelete.class);
        // functions.put("ImageDiskDeleteDM", ImageDiskDeleteDM.class);
        // functions.put("ImageDiskShare", ImageDiskShare.class);
        // functions.put("ImageDiskShareDM", ImageDiskShareDM.class);
        // functions.put("ImageDiskUnshare", ImageDiskUnshare.class);
        // functions.put("ImageDiskUnshareDM", ImageDiskUnshareDM.class);
        // functions.put("ImageIPLDeleteDM", ImageIPLDeleteDM.class);
        // functions.put("ImageIPLQueryDM", ImageIPLQueryDM.class);
        // functions.put("ImageIPLSetDM", ImageIPLSetDM.class);
        // functions.put("ImageLockDM", ImageLockDM.class);
        // functions.put("ImageNameQueryDM", ImageNameQueryDM.class);
        // functions.put("ImagePasswordSetDM", ImagePasswordSetDM.class);
        // functions.put("ImageQueryActivateTime", ImageQueryActivateTime.class);
        // functions.put("ImageQueryDM", ImageQueryDM.class);
        // functions.put("ImageRecycle", ImageRecycle.class);
        // functions.put("ImageReplaceDM", ImageReplaceDM.class);
        // functions.put("ImageSCSICharacteristicsDefineDM", ImageSCSICharacteristicsDefineDM.class);
        // functions.put("ImageSCSICharacteristicsQueryDM", ImageSCSICharacteristicsQueryDM.class);
        // functions.put("ImageStatusQuery", ImageStatusQuery.class);
        // functions.put("ImageUnlockDM", ImageUnlockDM.class);
        // functions.put("ImageVolumeAdd", ImageVolumeAdd.class);
        // functions.put("ImageVolumeDelete", ImageVolumeDelete.class);
        // functions.put("ImageVolumeSpaceDefineDM", ImageVolumeSpaceDefineDM.class);
        // functions.put("ImageVolumeSpaceQueryDM", ImageVolumeSpaceQueryDM.class);
        // functions.put("ImageVolumeSpaceRemoveDM", ImageVolumeSpaceRemoveDM.class);
        // functions.put("NameListAdd", NameListAdd.class);
        // functions.put("NameListDestroy", NameListDestroy.class);
        // functions.put("NameListQuery", NameListQuery.class);
        // functions.put("NameListRemove", NameListRemove.class);
        // functions.put("ProfileCreateDM", ProfileCreateDM.class);
        // functions.put("ProfileDeleteDM", ProfileDeleteDM.class);
        // functions.put("ProfileLockDM", ProfileLockDM.class);
        // functions.put("ProfileQueryDM", ProfileQueryDM.class);
        // functions.put("ProfileReplaceDM", ProfileReplaceDM.class);
        // functions.put("ProfileUnlockDM", ProfileUnlockDM.class);
        // functions.put("PrototypeCreateDM", PrototypeCreateDM.class);
        // functions.put("PrototypeDeleteDM", PrototypeDeleteDM.class);
        // functions.put("PrototypeNameQueryDM", PrototypeNameQueryDM.class);
        // functions.put("PrototypeQueryDM", PrototypeQueryDM.class);
        // functions.put("PrototypeReplaceDM", PrototypeReplaceDM.class);
        functions.put("QueryAPIFunctionalLevel", QueryAPIFunctionalLevel.class);
        // functions.put("QueryAsynchronousOperationDM", QueryAsynchronousOperationDM.class);
        // functions.put("QueryDirectoryManagerLevelDM", QueryDirectoryManagerLevelDM.class);
        // functions.put("SharedMemoryAccessAddDM", SharedMemoryAccessAddDM.class);
        // functions.put("SharedMemoryAccessQueryDM", SharedMemoryAccessQueryDM.class);
        // functions.put("SharedMemoryAccessRemoveDM", SharedMemoryAccessRemoveDM.class);
        // functions.put("SharedMemoryCreate", SharedMemoryCreate.class);
        // functions.put("SharedMemoryDelete", SharedMemoryDelete.class);
        // functions.put("SharedMemoryQuery", SharedMemoryQuery.class);
        // functions.put("SharedMemoryReplace", SharedMemoryReplace.class);
        // functions.put("StaticImageChangesActivateDM", StaticImageChangesActivateDM.class);
        // functions.put("StaticImageChangesDeactivateDM", StaticImageChangesDeactivateDM.class);
        // functions.put("StaticImageChangesImmediateDM", StaticImageChangesImmediateDM.class);
        // functions.put("VirtualChannelConnectionCreate", VirtualChannelConnectionCreate.class);
        // functions.put("VirtualChannelConnectionCreateDM", VirtualChannelConnectionCreateDM.class);
        // functions.put("VirtualChannelConnectionDelete", VirtualChannelConnectionDelete.class);
        // functions.put("VirtualChannelConnectionDeleteDM", VirtualChannelConnectionDeleteDM.class);
        // functions.put("VirtualNetworkAdapterConnectLAN", VirtualNetworkAdapterConnectLAN.class);
        // functions.put("VirtualNetworkAdapterConnectLANDM", VirtualNetworkAdapterConnectLANDM.class);
        // functions.put("VirtualNetworkAdapterConnectVswitch", VirtualNetworkAdapterConnectVswitch.class);
        // functions.put("VirtualNetworkAdapterConnectVswitchDM", VirtualNetworkAdapterConnectVswitchDM.class);
        // functions.put("VirtualNetworkAdapterCreate", VirtualNetworkAdapterCreate.class);
        // functions.put("VirtualNetworkAdapterCreateDM", VirtualNetworkAdapterCreateDM.class);
        // functions.put("VirtualNetworkAdapterDelete", VirtualNetworkAdapterDelete.class);
        // functions.put("VirtualNetworkAdapterDeleteDM", VirtualNetworkAdapterDeleteDM.class);
        // functions.put("VirtualNetworkAdapterDisconnect", VirtualNetworkAdapterDisconnect.class);
        // functions.put("VirtualNetworkAdapterDisconnectDM", VirtualNetworkAdapterDisconnectDM.class);
        // functions.put("VirtualNetworkAdapterQuery", VirtualNetworkAdapterQuery.class);
        // functions.put("VirtualNetworkLANAccess", VirtualNetworkLANAccess.class);
        // functions.put("VirtualNetworkLANAccessQuery", VirtualNetworkLANAccessQuery.class);
        // functions.put("VirtualNetworkLANCreate", VirtualNetworkLANCreate.class);
        // functions.put("VirtualNetworkLANDelete", VirtualNetworkLANDelete.class);
        // functions.put("VirtualNetworkLANQuery", VirtualNetworkLANQuery.class);
        // functions.put("VirtualNetworkVswitchCreate", VirtualNetworkVswitchCreate.class);
        // functions.put("VirtualNetworkVswitchDelete", VirtualNetworkVswitchDelete.class);
        // functions.put("VirtualNetworkVswitchQuery", VirtualNetworkVswitchQuery.class);
        // functions.put("VirtualNetworkVswitchSet", VirtualNetworkVswitchSet.class);
        // functions.put("VMRMConfigurationQuery", VMRMConfigurationQuery.class);
        // functions.put("VMRMConfigurationUpdate", VMRMConfigurationUpdate.class);
        // functions.put("VMRMMeasurementQuery", VMRMMeasurementQuery.class);
    }
    
    /**
     * Get by JSON string name the FunctionProxy class object suitable for newInstance() by Engine
     * 
     * @param key the name of the function in PigIron as received by the Engine in a JSON string.
     * @return class object or null
     */
    public static Class<? extends FunctionProxy> get(String key) {
	return functions.get(key);
    }
}
