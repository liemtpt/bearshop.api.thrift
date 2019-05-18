package com.bearshop.service.thrift.client;

import com.nct.framework.common.Config;
import com.nct.framework.common.LogUtil;
import com.nct.framework.util.ConvertUtils;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.cliffc.high_scale_lib.NonBlockingHashMap;

/**
 *
 * @author liemtpt
 */
public class ShopClient {

    private static Logger logger = LogUtil.getLogger(ShopClient.class);
    private static final long maxRecyleAge = 5 * 60 * 1000; // 5 minutes
    private static Map<String, ShopClient> instances = new NonBlockingHashMap();
    private static final Lock createLock = new ReentrantLock();
    private ArrayBlockingQueue<PooledClient> queue;
    private Integer maxPool;
    private Integer minPool;
    private Integer timeout;
    private String host;
    private Integer port;

    public static ShopClient getInstance(String name) {
        if (!instances.containsKey(name)) {
            try {
                createLock.lock();
                if (!instances.containsKey(name)) {
                    instances.put(name, new ShopClient(name));
                }
            } finally {
                createLock.unlock();
            }
        }
        return instances.get(name);
    }

    private ShopClient(String name) {
        host = ConvertUtils.toString(Config.getParam(name, "host"), "localhost");
        port = ConvertUtils.toInt(Config.getParam(name, "port"), 6225);
        maxPool = ConvertUtils.toInt(Config.getParam(name, "maxpool"), 1024);
        minPool = ConvertUtils.toInt(Config.getParam(name, "minpool"), 256);
        timeout = ConvertUtils.toInt(Config.getParam(name, "timeout"), 500000);

//        String opauth_key = Config.getParam(name, "opauth_key");
//        String opauth_source = Config.getParam(name, "opauth_source");
//        op_auth = new OpAuth(opauth_key, opauth_source);
        queue = new ArrayBlockingQueue(maxPool);
    }

    private PooledClient borrowClient() {
        PooledClient pooledClient = null;
        while (queue.size() > 0) {
            try {
                pooledClient = queue.take();
            } catch (InterruptedException ex) {
                logger.error(LogUtil.stackTrace(ex));
            }

            if (pooledClient != null && pooledClient.isAlive()) {
                return pooledClient;
            }
        }

        pooledClient = new PooledClient(host, port, timeout);

        return pooledClient;
    }

    private void returnClient(PooledClient client) {

        if (client == null) {
            return;
        }

        if (queue.size() > maxPool) {
            client.destroy();
            return;
        }

        long diffInSec = (System.currentTimeMillis() - client.getDateCreated());

        if ((queue.size() > minPool && diffInSec > maxRecyleAge) || !client.isAlive()) {
            client.destroy();
            return;
        }
        try {
            queue.put(client);
        } catch (InterruptedException ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
    }

    private void invalidClient(PooledClient client) {
        if (client == null) {
            return;
        }

        if (client.isAlive()) {
            returnClient(client);
        }
    }

    //ACCOUNT
//    public boolean insertAccount(TAccount value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertAccount(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateAccount(TAccount value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateAccount(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteAccount(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteAccount(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePasswordUser(String userName, String password) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePasswordUser(userName, password);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateLastLoginUser(String userName) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateLastLoginUser(userName);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusUser(String userName, int status) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusUser(userName, status);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAccountResult getLoginByUser(String userName, String password) {
//        TAccountResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getLoginByUser(userName, password);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAccountResult getAccountByUser(String userName) {
//        TAccountResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAccountByUser(userName);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAccountListResult getListAccountPaging(String whereClause) {
//        TAccountListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAccountPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAccountListResult getListAccountByFilter(TAccountFilter filter) {
//        TAccountListResult result = new TAccountListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAccountByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // PAGE
//    public boolean insertPage(TPage value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertPage(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePage(TPage value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePage(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deletePage(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deletePage(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPageResult getPageById(int id) {
//        TPageResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getPageById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPageListResult getListPagePaging(String whereClause) {
//        TPageListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPagePaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPageListResult getListPageByFilter(TPageFilter filter) {
//        TPageListResult result = new TPageListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPageByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListPageByParentId(int parentId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPageByParentId(parentId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // PERMISSION
//    public boolean insertPermission(TPermission value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertPermission(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePermission(TPermission value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePermission(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deletePermission(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deletePermission(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPermissionResult getPermissionById(int id) {
//        TPermissionResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getPermissionById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPermissionListResult getListPermissionPaging(String whereClause) {
//        TPermissionListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPermissionPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPermissionListResult getListPermissionByFilter(TPermissionFilter filter) {
//        TPermissionListResult result = new TPermissionListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPermissionByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // COPYRIGHT INFRINGEMENT
//    public boolean insertCopyrightInfringement(TCopyrightInfringement value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertCopyrightInfringement(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateCopyrightInfringement(TCopyrightInfringement value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateCopyrightInfringement(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteCopyrightInfringement(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteCopyrightInfringement(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCopyrightInfringementResult getCopyrightInfringementById(int id) {
//        TCopyrightInfringementResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCopyrightInfringementById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCopyrightInfringementListResult getListCopyrightInfringementPaging(String whereClause) {
//        TCopyrightInfringementListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCopyrightInfringementPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCopyrightInfringementListResult getListCopyrightInfringementByFilter(TCopyrightInfringementFilter filter) {
//        TCopyrightInfringementListResult result = new TCopyrightInfringementListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCopyrightInfringementByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // SONG
//    public boolean insertSong(TSong value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertSong(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateSong(TSong value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateSong(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteSong(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteSong(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusSong(int status, int userUpdate, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusSong(status, userUpdate, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongResult getSongBySongKey(String songKey) {
//        TSongResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSongBySongKey(songKey);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongResult getSongById(int id) {
//        TSongResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSongById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongListResult getListSongPaging(String whereClause) {
//        TSongListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSongPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongListResult getListSongByFilter(TSongFilter filter) {
//        TSongListResult result = new TSongListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSongByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CUSTOMER
//    public boolean insertCustomer(TCustomer value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertCustomer(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateCustomer(TCustomer value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateCustomer(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteCustomer(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteCustomer(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePasswordByCustomer(String userName, String password, int userCreated) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePasswordByCustomer(userName, password, userCreated);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateLastLoginByCustomer(String userName, int userCreated) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateLastLoginByCustomer(userName, userCreated);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusByCustomer(String userName, int status, int userCreated) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusByCustomer(userName, status, userCreated);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerResult getCustomerByUserName(String userName) {
//        TCustomerResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCustomerByUserName(userName);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerResult getCustomerById(int id) {
//        TCustomerResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCustomerById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerResult getLoginByCustomer(String userName, String password) {
//        TCustomerResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getLoginByCustomer(userName, password);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerListResult getListCustomerPaging(String whereClause) {
//        TCustomerListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCustomerPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerListResult getListCustomerByFilter(TCustomerFilter filter) {
//        TCustomerListResult result = new TCustomerListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCustomerByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // SINGER ALIAS
//    public boolean insertSingerAlias(TSingerAlias value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertSingerAlias(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateSingerAlias(TSingerAlias value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateSingerAlias(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusSingerAlias(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusSingerAlias(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteSingerAlias(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteSingerAlias(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSingerAliasResult getSingerAliasById(int id) {
//        TSingerAliasResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSingerAliasById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSingerAliasListResult getListSingerAliasPaging(String whereClause) {
//        TSingerAliasListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSingerAliasPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSingerAliasListResult getListSingerAliasByFilter(TSingerAliasFilter filter) {
//        TSingerAliasListResult result = new TSingerAliasListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSingerAliasByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CUSTOMER EXTEND
//    public boolean insertCustomerExtend(TCustomerExtend value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertCustomerExtend(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateCustomerExtend(TCustomerExtend value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateCustomerExtend(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusCustomerExtend(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusCustomerExtend(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteCustomerExtend(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteCustomerExtend(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerExtendResult getCustomerExtendById(int id) {
//        TCustomerExtendResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCustomerExtendById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerExtendListResult getListCustomerExtendPaging(String whereClause) {
//        TCustomerExtendListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCustomerExtendPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TCustomerExtendListResult getListCustomerExtendByFilter(TCustomerExtendFilter filter) {
//        TCustomerExtendListResult result = new TCustomerExtendListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCustomerExtendByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListCustomerExtendIdByCustomerId(int customerId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListCustomerExtendIdByCustomerId(customerId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // AREA
//    public boolean insertArea(TArea value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertArea(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateArea(TArea value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateArea(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusArea(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusArea(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteArea(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteArea(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAreaResult getAreaById(int id) {
//        TAreaResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAreaById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAreaListResult getListAreaPaging(String whereClause) {
//        TAreaListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAreaPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAreaListResult getListAreaByFilter(TAreaFilter filter) {
//        TAreaListResult result = new TAreaListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAreaByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListAreaIdByParentId(int parentId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAreaIdByParentId(parentId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CONTRACT
//    public int insertContract(TContract value) {
//        int result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertContract(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateContract(TContract value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateContract(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusContract(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusContract(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteContract(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteContract(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractResult getContractById(int id) {
//        TContractResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getContractById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractResult getContractByCode(String code) {
//        TContractResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getContractByCode(code);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractListResult getListContractPaging(String whereClause) {
//        TContractListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractListResult getListContractByFilter(TContractFilter filter) {
//        TContractListResult result = new TContractListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListContractIdByDate(String startDate, String endDate) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractIdByDate(startDate, endDate);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListContractIdByCustomerId(int customerId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractIdByCustomerId(customerId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListContractIdBySaleId(int saleId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractIdBySaleId(saleId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CONTRACT_V1
//    public int insertContractV1(TContractV1 value) {
//        int result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertContractV1(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateContractV1(TContractV1 value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateContractV1(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusContractV1(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusContractV1(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteContractV1(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteContractV1(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractV1Result getContractV1ById(int id) {
//        TContractV1Result result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getContractV1ById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractV1Result getContractV1ByCode(String code) {
//        TContractV1Result result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getContractV1ByCode(code);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractV1ListResult getListContractV1Paging(String whereClause) {
//        TContractV1ListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractV1Paging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractV1ListResult getListContractV1ByFilter(TContractV1Filter filter) {
//        TContractV1ListResult result = new TContractV1ListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractV1ByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListV1ContractIdByDate(String startDate, String endDate) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListV1ContractIdByDate(startDate, endDate);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListV1ContractIdByCustomerId(int customerId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListV1ContractIdByCustomerId(customerId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListV1ContractIdBySaleId(int saleId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListV1ContractIdBySaleId(saleId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // APPENDIX
//    public int insertAppendix(TAppendix value) {
//        int result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertAppendix(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateAppendix(TAppendix value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateAppendix(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusAppendix(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusAppendix(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteAppendix(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteAppendix(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixResult getAppendixById(int id) {
//        TAppendixResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAppendixById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixListResult getListAppendixPaging(String whereClause) {
//        TAppendixListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixListResult getListAppendixByFilter(TAppendixFilter filter) {
//        TAppendixListResult result = new TAppendixListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListAppendixIdByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixIdByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // APPENDIX_V1
//    public int insertAppendixV1(TAppendixV1 value) {
//        int result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertAppendixV1(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateAppendixV1(TAppendixV1 value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateAppendixV1(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusAppendixV1(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusAppendixV1(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteAppendixV1(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteAppendixV1(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixV1Result getAppendixV1ById(int id) {
//        TAppendixV1Result result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAppendixV1ById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixV1ListResult getListAppendixV1Paging(String whereClause) {
//        TAppendixV1ListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixV1Paging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAppendixV1ListResult getListAppendixV1ByFilter(TAppendixV1Filter filter) {
//        TAppendixV1ListResult result = new TAppendixV1ListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixV1ByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListAppendixV1IdByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListIdV1ByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CONTRACT DETAIL
//    public int insertContractDetail(TContractDetail value) {
//        int result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertContractDetail(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateContractDetail(TContractDetail value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateContractDetail(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusContractDetail(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusContractDetail(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteContractDetail(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteContractDetail(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractDetailResult getContractDetailById(int id) {
//        TContractDetailResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getContractDetailById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractDetailListResult getListContractDetailPaging(String whereClause) {
//        TContractDetailListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractDetailPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TContractDetailListResult getListContractDetailByFilter(TContractDetailFilter filter) {
//        TContractDetailListResult result = new TContractDetailListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractDetailByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListContractDetailIdByDate(String startDate, String endDate) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractDetailIdByDate(startDate, endDate);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListAppendixIdBySongId(int songId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAppendixIdBySongId(songId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListSongIdByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSongIdByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListContractDetailIdByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListContractDetailIdByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // OUTLET DETAIL
//    public boolean insertOutletDetail(TOutletDetail value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertOutletDetail(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateOutletDetail(TOutletDetail value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateOutletDetail(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusOutletDetail(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusOutletDetail(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteOutletDetail(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteOutletDetail(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TOutletDetailResult getOutletDetailById(int id) {
//        TOutletDetailResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getOutletDetailById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TOutletDetailListResult getListOutletDetailPaging(String whereClause) {
//        TOutletDetailListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListOutletDetailPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TOutletDetailListResult getListOutletDetailByFilter(TOutletDetailFilter filter) {
//        TOutletDetailListResult result = new TOutletDetailListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListOutletDetailByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListOutletDetailIdByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListOutletDetailIdByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // PAYMENT MASTER
//    public boolean insertPaymentMaster(TPaymentMaster value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertPaymentMaster(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePaymentMaster(TPaymentMaster value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePaymentMaster(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusPaymentMaster(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusPaymentMaster(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deletePaymentMaster(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deletePaymentMaster(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentMasterResult getPaymentMasterById(int id) {
//        TPaymentMasterResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getPaymentMasterById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentMasterListResult getListPaymentMasterPaging(String whereClause) {
//        TPaymentMasterListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentMasterPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentMasterListResult getListPaymentMasterByFilter(TPaymentMasterFilter filter) {
//        TPaymentMasterListResult result = new TPaymentMasterListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentMasterByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListPaymentMasterByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentMasterByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // PAYMENT ESTIMATE
//    public boolean insertPaymentEstimate(TPaymentEstimate value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertPaymentEstimate(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updatePaymentEstimate(TPaymentEstimate value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updatePaymentEstimate(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deletePaymentEstimate(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deletePaymentEstimate(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentEstimateResult getPaymentEstimateById(int id) {
//        TPaymentEstimateResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getPaymentEstimateById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentEstimateListResult getListPaymentEstimatePaging(String whereClause) {
//        TPaymentEstimateListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentEstimatePaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentEstimateListResult getListPaymentEstimateByFilter(TPaymentEstimateFilter filter) {
//        TPaymentEstimateListResult result = new TPaymentEstimateListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentEstimateByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public List<Integer> getListPaymentEstimateByContractId(int contractId) {
//        List<Integer> result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentEstimateByContractId(contractId);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // REPORT
//    public boolean insertReport(TReport value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertReport(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateReport(TReport value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateReport(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteReport(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteReport(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TReportResult getReportById(int id) {
//        TReportResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getReportById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TReportListResult getListReportPaging(String whereClause) {
//        TReportListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListReportPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TReportListResult getListReportByFilter(TReportFilter filter) {
//        TReportListResult result = new TReportListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListReportByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // TAG
//    public boolean insertTag(TTag value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertTag(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateTag(TTag value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateTag(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusTag(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusTag(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteTag(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteTag(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TTagResult getTagById(int id) {
//        TTagResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getTagById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TTagListResult getListTagPaging(String whereClause) {
//        TTagListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListTagPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TTagListResult getListTagByFilter(TTagFilter filter) {
//        TTagListResult result = new TTagListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListTagByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // SITE
//    public boolean insertSite(TSite value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertSite(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateSite(TSite value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateSite(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusSite(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusSite(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteSite(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteSite(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSiteResult getSiteById(int id) {
//        TSiteResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSiteById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSiteListResult getListSitePaging(String whereClause) {
//        TSiteListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSitePaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSiteListResult getListSiteByFilter(TSiteFilter filter) {
//        TSiteListResult result = new TSiteListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSiteByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // NEWS
//    public boolean insertNews(TNews value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertNews(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateNews(TNews value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateNews(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusNews(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusNews(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteNews(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteNews(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNewsResult getNewsById(int id) {
//        TNewsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getNewsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNewsListResult getListNewsPaging(String whereClause) {
//        TNewsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListNewsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNewsListResult getListNewsByFilter(TNewsFilter filter) {
//        TNewsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListNewsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // SHOWCASE
//    public boolean insertShowcase(TShowcase value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertShowcase(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateShowcase(TShowcase value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateShowcase(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusShowcase(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusShowcase(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteShowcase(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteShowcase(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TShowcaseResult getShowcaseById(int id) {
//        TShowcaseResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getShowcaseById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TShowcaseListResult getListShowcasePaging(String whereClause) {
//        TShowcaseListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListShowcasePaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TShowcaseListResult getListShowcaseByFilter(TShowcaseFilter filter) {
//        TShowcaseListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListShowcaseByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // GENRE
//    public boolean insertGenre(TGenre value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertGenre(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateGenre(TGenre value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateGenre(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusGenre(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusGenre(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteGenre(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteGenre(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TGenreResult getGenreById(int id) {
//        TGenreResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getGenreById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TGenreListResult getListGenrePaging(String whereClause) {
//        TGenreListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListGenrePaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TGenreListResult getListGenreByFilter(TGenreFilter filter) {
//        TGenreListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListGenreByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // LYRIC
//    public boolean insertLyric(TLyric value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertLyric(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateLyric(TLyric value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateLyric(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusLyric(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusLyric(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteLyric(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteLyric(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TLyricResult getLyricById(int id) {
//        TLyricResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getLyricById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TLyricListResult getListLyricPaging(String whereClause) {
//        TLyricListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListLyricPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TLyricListResult getListLyricByFilter(TLyricFilter filter) {
//        TLyricListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListLyricByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // CONTRACT PAYMENT DETAIL
//    public boolean insertPaymentDetail(TPaymentDetail value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertPaymentDetail(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deletePaymentDetail(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deletePaymentDetail(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentDetailResult getPaymentDetailById(int id) {
//        TPaymentDetailResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getPaymentDetailById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TPaymentDetailListResult getListPaymentDetailPaging(String whereClause) {
//        TPaymentDetailListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListPaymentDetailPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // REPORT
//    public boolean insertRecordUpload(TRecordUpload value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertRecordUpload(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateRecordUpload(TRecordUpload value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateRecordUpload(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteRecordUpload(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteRecordUpload(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TRecordUploadResult getRecordUploadById(int id) {
//        TRecordUploadResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getRecordUploadById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TRecordUploadListResult getListRecordUploadPaging(String whereClause) {
//        TRecordUploadListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListRecordUploadPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TRecordUploadListResult getListRecordUploadByFilter(TRecordUploadFilter filter) {
//        TRecordUploadListResult result = new TRecordUploadListResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListRecordUploadByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // LOG API AIBIZ
//    public boolean insertLogApiAibiz(TLogApiAibiz value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertLogApiAibiz(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TLogApiAibizListResult getListLogApiAibizPaging(String whereClause) {
//        TLogApiAibizListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListLogApiAibizPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TLogApiAibizListResult getListLogApiAibizByFilter(TLogApiAibizFilter filter) {
//        TLogApiAibizListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListLogApiAibizByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // ACTION LOGS
//    public boolean insertActionLogs(TActionLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertActionLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteActionLogs(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteActionLogs(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TActionLogsResult getActionLogsById(int id) {
//        TActionLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getActionLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TActionLogsListResult getListActionLogsPaging(String whereClause) {
//        TActionLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListActionLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TActionLogsListResult getListActionLogsByFilter(TActionLogsFilter filter) {
//        TActionLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListActionLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // NOTIFICATION LOGS
//    public boolean insertNotificationLogs(TNotificationLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertNotificationLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateNotificationLogs(TNotificationLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateNotificationLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean updateStatusNotificationLogs(int status, int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().updateStatusNotificationLogs(status, id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteNotificationLogs(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteNotificationLogs(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNotificationLogsResult getNotificationLogsById(int id) {
//        TNotificationLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getNotificationLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNotificationLogsListResult getListNotificationLogsPaging(String whereClause) {
//        TNotificationLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListNotificationLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TNotificationLogsListResult getListNotificationLogsByFilter(TNotificationLogsFilter filter) {
//        TNotificationLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListNotificationLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }

    // XMS PLAY LOGS
    // SONG
//    public boolean insertSongXmsPlayLogs(TSongXmsPlayLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertSongXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean incrCounterSongXmsPlayLogs(String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().incrCounterSongXmsPlayLogs(songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean decrCounterSongXmsPlayLogs(String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().decrCounterSongXmsPlayLogs(songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public long getCounterSongXmsPlayLogs(String songKey, String date) {
//        long result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCounterSongXmsPlayLogs(songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteSongXmsPlayLogsById(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteSongXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteSongXmsPlayLogsByKey(String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteSongXmsPlayLogsByKey(songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongXmsPlayLogsResult getSongXmsPlayLogsById(int id) {
//        TSongXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSongXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongXmsPlayLogsResult getSongXmsPlayLogsByKey(String songKey, String date) {
//        TSongXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getSongXmsPlayLogsByKey(songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongXmsPlayLogsListResult getListSongXmsPlayLogsPaging(String whereClause) {
//        TSongXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSongXmsPlayLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TSongXmsPlayLogsListResult getListSongXmsPlayLogsByFilter(TSongXmsPlayLogsFilter filter) {
//        TSongXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListSongXmsPlayLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // USER
//    public boolean insertUserXmsPlayLogs(TUserXmsPlayLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertUserXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean incrCounterUserXmsPlayLogs(long userId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().incrCounterUserXmsPlayLogs(userId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean decrCounterUserXmsPlayLogs(long userId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().decrCounterUserXmsPlayLogs(userId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public long getCounterUserXmsPlayLogs(long userId, String songKey, String date) {
//        long result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCounterUserXmsPlayLogs(userId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteUserXmsPlayLogsById(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteUserXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteUserXmsPlayLogsByKey(long userId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteUserXmsPlayLogsByKey(userId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TUserXmsPlayLogsResult getUserXmsPlayLogsById(int id) {
//        TUserXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getUserXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TUserXmsPlayLogsResult getUserXmsPlayLogsByKey(long userId, String songKey, String date) {
//        TUserXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getUserXmsPlayLogsByKey(userId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TUserXmsPlayLogsListResult getListUserXmsPlayLogsPaging(String whereClause) {
//        TUserXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListUserXmsPlayLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TUserXmsPlayLogsListResult getListUserXmsPlayLogsByFilter(TUserXmsPlayLogsFilter filter) {
//        TUserXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListUserXmsPlayLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // ALBUM
//    public boolean insertAlbumXmsPlayLogs(TAlbumXmsPlayLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertAlbumXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean incrCounterAlbumXmsPlayLogs(long albumId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().incrCounterAlbumXmsPlayLogs(albumId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean decrCounterAlbumXmsPlayLogs(long albumId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().decrCounterAlbumXmsPlayLogs(albumId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public long getCounterAlbumXmsPlayLogs(long albumId, String songKey, String date) {
//        long result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCounterAlbumXmsPlayLogs(albumId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteAlbumXmsPlayLogsById(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteAlbumXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteAlbumXmsPlayLogsByKey(long albumId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteAlbumXmsPlayLogsByKey(albumId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAlbumXmsPlayLogsResult getAlbumXmsPlayLogsById(int id) {
//        TAlbumXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAlbumXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAlbumXmsPlayLogsResult getAlbumXmsPlayLogsByKey(long albumId, String songKey, String date) {
//        TAlbumXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getAlbumXmsPlayLogsByKey(albumId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAlbumXmsPlayLogsListResult getListAlbumXmsPlayLogsPaging(String whereClause) {
//        TAlbumXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAlbumXmsPlayLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TAlbumXmsPlayLogsListResult getListAlbumXmsPlayLogsByFilter(TAlbumXmsPlayLogsFilter filter) {
//        TAlbumXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListAlbumXmsPlayLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // DEVICE
//    public boolean insertDeviceXmsPlayLogs(TDeviceXmsPlayLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertDeviceXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean incrCounterDeviceXmsPlayLogs(String deviceId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().incrCounterDeviceXmsPlayLogs(deviceId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean decrCounterDeviceXmsPlayLogs(String deviceId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().decrCounterDeviceXmsPlayLogs(deviceId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public long getCounterDeviceXmsPlayLogs(String deviceId, String songKey, String date) {
//        long result = 0;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getCounterDeviceXmsPlayLogs(deviceId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteDeviceXmsPlayLogsById(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteDeviceXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteDeviceXmsPlayLogsByKey(String deviceId, String songKey, String date) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteDeviceXmsPlayLogsByKey(deviceId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TDeviceXmsPlayLogsResult getDeviceXmsPlayLogsById(int id) {
//        TDeviceXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getDeviceXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TDeviceXmsPlayLogsResult getDeviceXmsPlayLogsByKey(String deviceId, String songKey, String date) {
//        TDeviceXmsPlayLogsResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getDeviceXmsPlayLogsByKey(deviceId, songKey, date);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TDeviceXmsPlayLogsListResult getListDeviceXmsPlayLogsPaging(String whereClause) {
//        TDeviceXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListDeviceXmsPlayLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TDeviceXmsPlayLogsListResult getListDeviceXmsPlayLogsByFilter(TDeviceXmsPlayLogsFilter filter) {
//        TDeviceXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListDeviceXmsPlayLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    // DETAIL
//    public boolean insertXmsPlayLogs(TXmsPlayLogs value) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().insertXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public boolean deleteXmsPlayLogsById(int id) {
//        boolean result = false;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().deleteXmsPlayLogsById(id);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TXmsPlayLogsResult getXmsPlayLogs(TXmsPlayLogs value) {
//        TXmsPlayLogsResult result = new TXmsPlayLogsResult();
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getXmsPlayLogs(value);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TXmsPlayLogsListResult getListXmsPlayLogsPaging(String whereClause) {
//        TXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListXmsPlayLogsPaging(whereClause);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }
//
//    public TXmsPlayLogsListResult getListXmsPlayLogsByFilter(TXmsPlayLogsFilter filter) {
//        TXmsPlayLogsListResult result = null;
//        PooledClient client = borrowClient();
//        try {
//            result = client.getClient().getListXmsPlayLogsByFilter(filter);
//            returnClient(client);
//        } catch (TException ex) {
//            logger.error(LogUtil.stackTrace(ex));
//            invalidClient(client);
//        }
//        return result;
//    }

}
