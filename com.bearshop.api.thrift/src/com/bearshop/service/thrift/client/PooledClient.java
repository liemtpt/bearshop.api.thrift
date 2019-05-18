package com.bearshop.service.thrift.client;

import com.bear.shop.service.api.thrift.models.TShopService;
import com.nct.framework.common.LogUtil;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author liemtpt
 */
public class PooledClient {

    private static Logger logger = LogUtil.getLogger(PooledClient.class);
    private TShopService.Client client;
    private long dateCreated;

    public PooledClient(String host, int port, int timeout) {

        dateCreated = System.currentTimeMillis();

        TTransport transport = new TFramedTransport(new TSocket(host, port, timeout), 1937007972);
        TProtocol protocol = new TBinaryProtocol(transport);
        try {
            transport.open();
            TServiceClientFactory factory = new TShopService.Client.Factory();
            client = (TShopService.Client) factory.getClient(protocol);
        } catch (TTransportException ex) {
            logger.error(ex);
        }
    }

    public boolean isAlive() {

        if (client == null) {
            return false;
        }

        TFramedTransport transport = (TFramedTransport) client.getOutputProtocol().getTransport();

        if (transport == null || transport.isOpen() == false) {
            return false;
        }

        try {
            client.ping();
            return true;
        } catch (TException ex) {
            return false;
        }
    }

    public void destroy() {
        if (client == null) {
            return;
        }

        TTransport itrans = client.getInputProtocol().getTransport();
        TTransport otrans = client.getOutputProtocol().getTransport();
        if (itrans != null) {
            itrans.close();
        }
        if (otrans != null && otrans != itrans) {
            otrans.close();
        }
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public TShopService.Client getClient() {
        return client;
    }
}
