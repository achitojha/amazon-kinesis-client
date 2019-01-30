package com.amazonaws.services.kinesis.clientlibrary.lib.worker;

import com.amazonaws.services.kinesis.clientlibrary.exceptions.internal.KinesisClientLibIOException;
import com.amazonaws.services.kinesis.clientlibrary.proxies.IKinesisProxy;
import com.amazonaws.services.kinesis.leases.exceptions.DependencyException;
import com.amazonaws.services.kinesis.leases.exceptions.InvalidStateException;
import com.amazonaws.services.kinesis.leases.exceptions.ProvisionedThroughputException;
import com.amazonaws.services.kinesis.leases.impl.KinesisClientLease;
import com.amazonaws.services.kinesis.leases.interfaces.ILeaseManager;

/**
 * Interface to sync leases with shards of the Kinesis stream.
 */
public interface ShardSyncer {

    /**
     * Check and create leases for any new shards (e.g. following a reshard operation).
     *
     * @param kinesisProxy Kinesis proxy (used to get shard list)
     * @param leaseManager Used to fetch and create leases
     * @param initialPositionInStream One of LATEST, TRIM_HORIZON or AT_TIMESTAMP. Amazon Kinesis Client Library will
     *        start processing records from this point in the stream (when an application starts up for the first time)
     *        except for shards that already have a checkpoint (and their descendant shards).
     * @param cleanupLeasesOfCompletedShards  boolean to indicate clean up the leases of completed shards
     * @param ignoreUnexpectedChildShards set this flag to ignore child shards with open parents
     * @throws DependencyException
     * @throws InvalidStateException
     * @throws ProvisionedThroughputException
     * @throws KinesisClientLibIOException
     */
    void checkAndCreateLeasesForNewShards(IKinesisProxy kinesisProxy,
                                                              ILeaseManager<KinesisClientLease> leaseManager,
                                                              InitialPositionInStreamExtended initialPositionInStream,
                                                              boolean cleanupLeasesOfCompletedShards,
                                                              boolean ignoreUnexpectedChildShards)
            throws DependencyException, InvalidStateException, ProvisionedThroughputException, KinesisClientLibIOException;


}
