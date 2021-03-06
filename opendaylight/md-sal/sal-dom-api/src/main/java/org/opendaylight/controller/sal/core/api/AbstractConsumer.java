/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.sal.core.api;

import java.util.Collection;
import java.util.Collections;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Deprecated.
 *
 * @deprecated Use blueprint instead for code wiring.
 */
@Deprecated
public abstract class AbstractConsumer implements Consumer, BundleActivator,ServiceTrackerCustomizer<Broker, Broker> {

    private BundleContext context;
    private ServiceTracker<Broker, Broker> tracker;
    private Broker broker;

    @Override
    public final void start(final BundleContext bundleContext) throws Exception {
        this.context = bundleContext;
        this.startImpl(bundleContext);
        tracker = new ServiceTracker<>(bundleContext, Broker.class, this);
        tracker.open();
    }

    @Override
    public final void stop(final BundleContext bundleContext) throws Exception {
        stopImpl(bundleContext);
        broker = null;

        if (tracker != null) {
            tracker.close();
        }
    }

    protected void startImpl(final BundleContext bundleContext) {
        // NOOP
    }

    protected void stopImpl(final BundleContext bundleContext) {
        // NOOP
    }

    @Override
    public Collection<ConsumerFunctionality> getConsumerFunctionality() {
        return Collections.emptySet();
    }


    @Override
    public Broker addingService(final ServiceReference<Broker> reference) {
        if (broker == null && context != null) {
            broker = context.getService(reference);
            broker.registerConsumer(this, context);
            return broker;
        }

        return null;
    }

    @Override
    public void modifiedService(final ServiceReference<Broker> reference, final Broker service) {
        // NOOP
    }

    @Override
    public void removedService(final ServiceReference<Broker> reference, final Broker service) {
        stopImpl(context);
    }
}
