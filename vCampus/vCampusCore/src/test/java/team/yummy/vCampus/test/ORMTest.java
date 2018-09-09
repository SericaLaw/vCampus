package team.yummy.vCampus.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import team.yummy.vCampus.models.entity.AccountEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ORMTest {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;
    @Before
    public void init() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }
    //查找
    @Test
    public void a_getById() {
        AccountEntity user = (AccountEntity) session.get(AccountEntity.class, "213160001");
        tx.commit();
        session.close();
        System.out.println(
                "ID号：" + user.getCampusCardId() +
                        "；用户名：" + user.getNickname() +
                        "；密码：" + user.getPassword() +
                        "；角色：" + user.getRole());
    }
    //增加
    @Test
    public void b_insert() {
        AccountEntity ue = new AccountEntity();
        ue.setCampusCardId("213164320");
        ue.setNickname("Serica");
        ue.setPassword("BestSerica");
        ue.setFirstName("Luo");
        ue.setLastName("YinHong");
        ue.setRole("admin");
        session.save(ue);
        tx.commit();
    }
    //修改
    @Test
    public void c_update() {
        AccountEntity user = (AccountEntity) session.get(AccountEntity.class, "213164320");
        user.setRole("student");
        session.update(user);
        tx.commit();
        session.close();
    }
    //删除
    @Test
    public void d_delete() {
        AccountEntity user = (AccountEntity) session.get(AccountEntity.class, "213164320");
        session.delete(user);
        tx.commit();
        session.close();
    }

}
