package org.ovirt.engine.core.bll.storage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.ovirt.engine.core.bll.BaseCommandTest;
import org.ovirt.engine.core.common.action.StoragePoolManagementParameter;
import org.ovirt.engine.core.common.businessentities.StoragePool;
import org.ovirt.engine.core.common.config.ConfigValues;
import org.ovirt.engine.core.common.errors.EngineMessage;
import org.ovirt.engine.core.compat.Guid;
import org.ovirt.engine.core.dao.StoragePoolDao;
import org.ovirt.engine.core.dao.StoragePoolIsoMapDao;
import org.ovirt.engine.core.utils.MockConfigRule;

public class StorageHandlingCommandBaseTest extends BaseCommandTest {

    @InjectMocks
    private StorageHandlingCommandBase<StoragePoolManagementParameter> cmd =
            new TestStorageHandlingCommandBase(new StoragePoolManagementParameter(createStoragePool()));

    @Mock
    private StoragePoolDao storagePoolDao;

    @Mock
    private StoragePoolIsoMapDao storagePoolIsoMapDao;

    private StoragePool storagePool;

    @ClassRule
    public static MockConfigRule mcr = new MockConfigRule();

    @Before
    public void setUp() {
        storagePool = cmd.getParameters().getStoragePool();
        cmd.init();

        when(storagePoolDao.get(storagePool.getId())).thenReturn(storagePool);
        when(storagePoolIsoMapDao.getAllForStorage(any(Guid.class))).thenReturn(Collections.emptyList());
    }

    @Test
    public void storagePoolNotFound() {
        when(storagePoolDao.get(storagePool.getId())).thenReturn(null);
        checkStoragePoolFails();
    }

    @Test
    public void storagePoolNull() {
        createCommandWithNullPool();
        checkStoragePoolFails();
    }

    @Test
    public void storagePoolExists() {
        checkStoragePoolSucceeds();
    }

    @Test
    public void nameTooLong() {
        setAcceptableNameLength(10);
        checkStoragePoolNameLengthSucceeds();
    }

    @Test
    public void nameAcceptableLength() {
        setAcceptableNameLength(255);
        checkStoragePoolNameLengthFails();
    }

    private void checkStoragePoolSucceeds() {
        assertTrue(cmd.checkStoragePool());
    }

    private static StoragePool createStoragePool() {
        StoragePool pool = new StoragePool();
        pool.setName("DefaultStoragePool");
        pool.setId(Guid.newGuid());
        pool.setIsLocal(false);
        return pool;
    }

    private void checkStoragePoolFails() {
        assertFalse(cmd.checkStoragePool());
        assertTrue(cmd.getReturnValue().getValidationMessages().contains(EngineMessage
                .ACTION_TYPE_FAILED_STORAGE_POOL_NOT_EXIST.toString()));
    }

    private void createCommandWithNullPool() {
        cmd = new TestStorageHandlingCommandBase(new StoragePoolManagementParameter());
    }

    private static void setAcceptableNameLength(final int length) {
        mcr.mockConfigValue(ConfigValues.StoragePoolNameSizeLimit, length);
    }

    private void checkStoragePoolNameLengthSucceeds() {
        assertFalse(cmd.checkStoragePoolNameLengthValid());
    }

    private void checkStoragePoolNameLengthFails() {
        assertTrue(cmd.checkStoragePoolNameLengthValid());
    }

    private static class TestStorageHandlingCommandBase extends StorageHandlingCommandBase<StoragePoolManagementParameter> {
        TestStorageHandlingCommandBase(StoragePoolManagementParameter parameters) {
            super(parameters, null);
        }

        @Override
        protected void executeCommand() {
            // Intentionally empty - no behavior is required
        }
    }
}
