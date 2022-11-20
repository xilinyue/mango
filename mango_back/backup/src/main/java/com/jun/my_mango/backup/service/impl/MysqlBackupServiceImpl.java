package com.jun.my_mango.backup.service.impl;

import com.jun.my_mango.backup.service.MysqlBackupService;
import com.jun.my_mango.backup.util.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月11日16:41
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        return MySqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
    }
}
