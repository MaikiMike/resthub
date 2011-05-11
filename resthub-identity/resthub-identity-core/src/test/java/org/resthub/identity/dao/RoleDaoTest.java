package org.resthub.identity.dao;

import javax.inject.Inject;
import javax.inject.Named;
import org.resthub.core.test.dao.AbstractResourceDaoTest;
import org.resthub.identity.model.Role;
import static org.junit.Assert.*;

/**
 * Test class for <tt>RoleDao</tt>.
 *
 * @author "Nicolas Morel <nicolas.morel@atosorigin.com>"
 */
public class RoleDaoTest extends AbstractResourceDaoTest<Role, RoleDao> {

    /**
     * Generate a random role name based on a string and a randomized number.
     * @return A unique role name.
     */
    private String generateRandomRoleName() {
        return "RoleName" + Math.round(Math.random() * 1000);
    }

    @Inject
    @Named("roleDao")
    @Override
    public void setDao(RoleDao resourceDao) {
        super.setDao(resourceDao);
    }

    /**
     * {@InheritDoc}
     */
    @Override
    protected Role createTestEntity() {
        Role testRole = new Role(generateRandomRoleName());
        return testRole;
    }

    @Override
    public void testUpdate() {
        final String editedRoleName = generateRandomRoleName();

        Role role1 = this.dao.readByPrimaryKey(this.id);
        role1.setName(editedRoleName);
        dao.save(role1);
        Role role2 = dao.readByPrimaryKey(this.id);
        assertEquals("Role not updated!", role2.getName(), editedRoleName);
    }
}
