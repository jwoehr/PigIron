/*
 *  Copyright (c) 2008, 2015 Jack J. Woehr jwoehr@softwoehr.com
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

import java.util.HashMap;

/**
 * FunctionTable is static representation of mapping between strings found in
 * the function_name field of the Function JSON object in the Requestor and the
 * proxy FUNCTIONS we use to bind a JSON request to a PigIron VSMAPI call.
 *
 */
public class FunctionTable {

    static public final HashMap<String, Class<? extends FunctionProxy>> FUNCTIONS;

    static {
        FUNCTIONS = new HashMap<String, Class<? extends FunctionProxy>>(200);
        FUNCTIONS.put("AsynchronousNotificationDisableDM", AsynchronousNotificationDisableDM.class);
        FUNCTIONS.put("AsynchronousNotificationEnableDM", AsynchronousNotificationEnableDM.class);
        FUNCTIONS.put("AsynchronousNotificationQueryDM", AsynchronousNotificationQueryDM.class);
        FUNCTIONS.put("AuthorizationListAdd", AuthorizationListAdd.class);
        FUNCTIONS.put("AuthorizationListQuery", AuthorizationListQuery.class);
        FUNCTIONS.put("AuthorizationListRemove", AuthorizationListRemove.class);
        FUNCTIONS.put("CheckAuthentication", CheckAuthentication.class);
        FUNCTIONS.put("DirectoryManagerLocalTagDefineDM", DirectoryManagerLocalTagDefineDM.class);
        FUNCTIONS.put("DirectoryManagerLocalTagDeleteDM", DirectoryManagerLocalTagDeleteDM.class);
        FUNCTIONS.put("DirectoryManagerLocalTagQueryDM", DirectoryManagerLocalTagQueryDM.class);
        FUNCTIONS.put("DirectoryManagerLocalTagSetDM", DirectoryManagerLocalTagSetDM.class);
        FUNCTIONS.put("DirectoryManagerSearchDM", DirectoryManagerSearchDM.class);
        FUNCTIONS.put("DirectoryManagerTaskCancelDM", DirectoryManagerTaskCancelDM.class);
        FUNCTIONS.put("EchoParameters", EchoParameters.class);
        FUNCTIONS.put("EventStream_Add", EventStreamAdd.class);
        FUNCTIONS.put("EventSubscribe", EventSubscribe.class);
        FUNCTIONS.put("EventUnsubscribe", EventUnsubscribe.class);
        FUNCTIONS.put("ImageActivate", ImageActivate.class);
        FUNCTIONS.put("ImageActiveConfigurationQuery", ImageActiveConfigurationQuery.class);
        FUNCTIONS.put("ImageCPUDefine", ImageCPUDefine.class);
        FUNCTIONS.put("ImageCPUDefineDM", ImageCPUDefineDM.class);
        FUNCTIONS.put("ImageCPUDelete", ImageCPUDelete.class);
        FUNCTIONS.put("ImageCPUDeleteDM", ImageCPUDeleteDM.class);
        FUNCTIONS.put("ImageCPUQuery", ImageCPUQuery.class);
        FUNCTIONS.put("ImageCPUQueryDM", ImageCPUQueryDM.class);
        FUNCTIONS.put("ImageCPUSetMaximumDM", ImageCPUSetMaximumDM.class);
        FUNCTIONS.put("ImageCreateDM", ImageCreateDM.class);
        FUNCTIONS.put("ImageDeactivate", ImageDeactivate.class);
        FUNCTIONS.put("ImageDeleteDM", ImageDeleteDM.class);
        FUNCTIONS.put("ImageDeviceDedicate", ImageDeviceDedicate.class);
        FUNCTIONS.put("ImageDeviceDedicateDM", ImageDeviceDedicateDM.class);
        FUNCTIONS.put("ImageDeviceReset", ImageDeviceReset.class);
        FUNCTIONS.put("ImageDeviceUndedicate", ImageDeviceUndedicate.class);
        FUNCTIONS.put("ImageDeviceUndedicateDM", ImageDeviceUndedicateDM.class);
        FUNCTIONS.put("ImageDiskCopy", ImageDiskCopy.class);
        FUNCTIONS.put("ImageDiskCopyDM", ImageDiskCopyDM.class);
        FUNCTIONS.put("ImageDiskCreate", ImageDiskCreate.class);
        FUNCTIONS.put("ImageDiskCreateDM", ImageDiskCreateDM.class);
        FUNCTIONS.put("ImageDiskDelete", ImageDiskDelete.class);
        FUNCTIONS.put("ImageDiskDeleteDM", ImageDiskDeleteDM.class);
        FUNCTIONS.put("ImageDiskShare", ImageDiskShare.class);
        FUNCTIONS.put("ImageDiskShareDM", ImageDiskShareDM.class);
        FUNCTIONS.put("ImageDiskUnshare", ImageDiskUnshare.class);
        FUNCTIONS.put("ImageDiskUnshareDM", ImageDiskUnshareDM.class);
        FUNCTIONS.put("ImageIPLDeleteDM", ImageIPLDeleteDM.class);
        FUNCTIONS.put("ImageIPLQueryDM", ImageIPLQueryDM.class);
        FUNCTIONS.put("ImageIPLSetDM", ImageIPLSetDM.class);
        FUNCTIONS.put("ImageLockDM", ImageLockDM.class);
        FUNCTIONS.put("ImageMDISKLinkQuery", ImageMDISKLinkQuery.class);
        FUNCTIONS.put("ImageNameQueryDM", ImageNameQueryDM.class);
        FUNCTIONS.put("ImagePasswordSetDM", ImagePasswordSetDM.class);
        FUNCTIONS.put("ImageQueryActivateTime", ImageQueryActivateTime.class);
        FUNCTIONS.put("ImageQueryDM", ImageQueryDM.class);
        FUNCTIONS.put("ImageRecycle", ImageRecycle.class);
        FUNCTIONS.put("ImageReplaceDM", ImageReplaceDM.class);
        FUNCTIONS.put("ImageSCSICharacteristicsDefineDM", ImageSCSICharacteristicsDefineDM.class);
        FUNCTIONS.put("ImageSCSICharacteristicsQueryDM", ImageSCSICharacteristicsQueryDM.class);
        FUNCTIONS.put("ImageStatusQuery", ImageStatusQuery.class);
        FUNCTIONS.put("ImageUnlockDM", ImageUnlockDM.class);
        FUNCTIONS.put("ImageVolumeAdd", ImageVolumeAdd.class);
        FUNCTIONS.put("ImageVolumeDelete", ImageVolumeDelete.class);
        FUNCTIONS.put("ImageVolumeSpaceDefineDM", ImageVolumeSpaceDefineDM.class);
        FUNCTIONS.put("ImageVolumeSpaceQueryDM", ImageVolumeSpaceQueryDM.class);
        FUNCTIONS.put("ImageVolumeSpaceRemoveDM", ImageVolumeSpaceRemoveDM.class);
        FUNCTIONS.put("NameListAdd", NameListAdd.class);
        FUNCTIONS.put("NameListDestroy", NameListDestroy.class);
        FUNCTIONS.put("NameListQuery", NameListQuery.class);
        FUNCTIONS.put("NameListRemove", NameListRemove.class);
        FUNCTIONS.put("ProfileCreateDM", ProfileCreateDM.class);
        FUNCTIONS.put("ProfileDeleteDM", ProfileDeleteDM.class);
        FUNCTIONS.put("ProfileLockDM", ProfileLockDM.class);
        FUNCTIONS.put("ProfileQueryDM", ProfileQueryDM.class);
        FUNCTIONS.put("ProfileReplaceDM", ProfileReplaceDM.class);
        FUNCTIONS.put("ProfileUnlockDM", ProfileUnlockDM.class);
        FUNCTIONS.put("PrototypeCreateDM", PrototypeCreateDM.class);
        FUNCTIONS.put("PrototypeDeleteDM", PrototypeDeleteDM.class);
        FUNCTIONS.put("PrototypeNameQueryDM", PrototypeNameQueryDM.class);
        FUNCTIONS.put("PrototypeQueryDM", PrototypeQueryDM.class);
        FUNCTIONS.put("PrototypeReplaceDM", PrototypeReplaceDM.class);
        FUNCTIONS.put("QueryAllDM", QueryAllDM.class);
        FUNCTIONS.put("QueryAPIFunctionalLevel", QueryAPIFunctionalLevel.class);
        FUNCTIONS.put("QueryAsynchronousOperationDM", QueryAsynchronousOperationDM.class);
        FUNCTIONS.put("QueryDirectoryManagerLevelDM", QueryDirectoryManagerLevelDM.class);
        FUNCTIONS.put("SharedMemoryAccessAddDM", SharedMemoryAccessAddDM.class);
        FUNCTIONS.put("SharedMemoryAccessQueryDM", SharedMemoryAccessQueryDM.class);
        FUNCTIONS.put("SharedMemoryAccessRemoveDM", SharedMemoryAccessRemoveDM.class);
        FUNCTIONS.put("SharedMemoryCreate", SharedMemoryCreate.class);
        FUNCTIONS.put("SharedMemoryDelete", SharedMemoryDelete.class);
        FUNCTIONS.put("SharedMemoryQuery", SharedMemoryQuery.class);
        FUNCTIONS.put("SharedMemoryReplace", SharedMemoryReplace.class);
        FUNCTIONS.put("SMAPIStatusCapture", SMAPIStatusCapture.class);
        FUNCTIONS.put("StaticImageChangesActivateDM", StaticImageChangesActivateDM.class);
        FUNCTIONS.put("StaticImageChangesDeactivateDM", StaticImageChangesDeactivateDM.class);
        FUNCTIONS.put("StaticImageChangesImmediateDM", StaticImageChangesImmediateDM.class);
        FUNCTIONS.put("VirtualChannelConnectionCreate", VirtualChannelConnectionCreate.class);
        FUNCTIONS.put("VirtualChannelConnectionCreateDM", VirtualChannelConnectionCreateDM.class);
        FUNCTIONS.put("VirtualChannelConnectionDelete", VirtualChannelConnectionDelete.class);
        FUNCTIONS.put("VirtualChannelConnectionDeleteDM", VirtualChannelConnectionDeleteDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterConnectLAN", VirtualNetworkAdapterConnectLAN.class);
        FUNCTIONS.put("VirtualNetworkAdapterConnectLANDM", VirtualNetworkAdapterConnectLANDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterConnectVswitch", VirtualNetworkAdapterConnectVswitch.class);
        FUNCTIONS.put("VirtualNetworkAdapterConnectVswitchDM", VirtualNetworkAdapterConnectVswitchDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterCreate", VirtualNetworkAdapterCreate.class);
        FUNCTIONS.put("VirtualNetworkAdapterCreateDM", VirtualNetworkAdapterCreateDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterDelete", VirtualNetworkAdapterDelete.class);
        FUNCTIONS.put("VirtualNetworkAdapterDeleteDM", VirtualNetworkAdapterDeleteDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterDisconnect", VirtualNetworkAdapterDisconnect.class);
        FUNCTIONS.put("VirtualNetworkAdapterDisconnectDM", VirtualNetworkAdapterDisconnectDM.class);
        FUNCTIONS.put("VirtualNetworkAdapterQuery", VirtualNetworkAdapterQuery.class);
        FUNCTIONS.put("VirtualNetworkLANAccess", VirtualNetworkLANAccess.class);
        FUNCTIONS.put("VirtualNetworkLANAccessQuery", VirtualNetworkLANAccessQuery.class);
        FUNCTIONS.put("VirtualNetworkLANCreate", VirtualNetworkLANCreate.class);
        FUNCTIONS.put("VirtualNetworkLANDelete", VirtualNetworkLANDelete.class);
        FUNCTIONS.put("VirtualNetworkLANQuery", VirtualNetworkLANQuery.class);
        FUNCTIONS.put("VirtualNetworkVswitchCreate", VirtualNetworkVswitchCreate.class);
        FUNCTIONS.put("VirtualNetworkVswitchDelete", VirtualNetworkVswitchDelete.class);
        FUNCTIONS.put("VirtualNetworkVswitchQuery", VirtualNetworkVswitchQuery.class);
        FUNCTIONS.put("VirtualNetworkVswitchSet", VirtualNetworkVswitchSet.class);
        FUNCTIONS.put("VMRMConfigurationQuery", VMRMConfigurationQuery.class);
        FUNCTIONS.put("VMRMConfigurationUpdate", VMRMConfigurationUpdate.class);
        FUNCTIONS.put("VMRMMeasurementQuery", VMRMMeasurementQuery.class);
    }

    /**
     * Get by JSON string name the FunctionProxy class object suitable for
     * newInstance() by Engine
     *
     * @param key the name of the function in PigIron as received by the Engine
     * in a JSON string.
     * @return class object or null
     */
    public static Class<? extends FunctionProxy> get(String key) {
        return FUNCTIONS.get(key);
    }
}
