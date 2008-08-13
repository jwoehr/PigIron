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
package com.softwoehr.pigiron;

import com.softwoehr.pigiron.functions.ImageActiveConfigurationQuery;
import com.softwoehr.pigiron.functions.ImageQueryActivateTime;
import com.softwoehr.pigiron.access.VSMException;
import com.softwoehr.pigiron.functions.CheckAuthentication;
import com.softwoehr.pigiron.functions.ImageStatusQuery;
import java.io.IOException;

/**
 * Runs whatever tests are currently of interest. Harmless as all
 * function calls currently used are queries.
 * @author jax
 */
public class Main {

    /**
     * Currently runs ImageActiveConfigurationQuery.main and
     * ImageQueryActivateTime.main
     * @param argv five args: server_dns_name smapi_port userid_for_authentication password userid_to_query
     * @throws java.io.IOException
     * @throws com.softwoehr.pigiron.access.VSMException
     * @see com.softwoehr.pigiron.functions.ImageActiveConfigurationQuery
     * @see com.softwoehr.pigiron.functions.ImageQueryActivateTime
     */
    public static void main(String[] argv) throws IOException, VSMException {
        System.out.println("==================");
        ImageActiveConfigurationQuery.main(argv);
        System.out.println("==================");
        ImageQueryActivateTime.main(argv);
        System.out.println("==================");
        CheckAuthentication.main(argv);
        System.out.println("==================");
        ImageStatusQuery.main(argv);
        System.out.println("==================");
        String[] modifiedArgs = {argv[0], argv[1], argv[2], argv[3], "*"};
        ImageStatusQuery.main(modifiedArgs);
        System.out.println("=======END========");
    }
}
