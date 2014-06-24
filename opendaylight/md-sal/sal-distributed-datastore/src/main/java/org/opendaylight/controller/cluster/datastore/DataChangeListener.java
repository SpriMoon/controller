/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.controller.cluster.datastore;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

public class DataChangeListener extends UntypedActor {
    @Override public void onReceive(Object message) throws Exception {
        throw new UnsupportedOperationException("onReceive");
    }

    public static Props props() {
        return Props.create(new Creator<DataChangeListener>() {
            @Override
            public DataChangeListener create() throws Exception {
                return new DataChangeListener();
            }

        });

    }
}
