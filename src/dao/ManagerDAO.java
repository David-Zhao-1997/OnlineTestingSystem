package dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import entity.ManagerEntity;
import util.HibernateUtil;

@SuppressWarnings("ALL")
public class ManagerDAO
{
    public boolean updateManager(ManagerEntity managerEntity)
    {
        try
        {
            Transaction tx = null;
            List list = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(managerEntity);
            tx.commit();
            session.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据管理员ID查询管理员信息
     *
     * @param managerID
     * @return ManagerEntity
     */
    public ManagerEntity queryManagerById(Integer managerID)
    {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        String hql = "from ManagerEntity where mgrId = '" + managerID + "'";
        ManagerEntity entity = (ManagerEntity) session.createQuery(hql).uniqueResult();
        tx.commit();
        session.close();
        return entity;
    }

    public List<ManagerEntity> queryAllManagers()
    {
        Transaction tx = null;
        List list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        list = session.createQuery("from ManagerEntity").list();
        tx.commit();
        session.close();
        return list;
    }

    public int getManagerNum()
    {
        Transaction tx = null;
        List list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select count(*) from [OnlineTest].[dbo].Manager");
        list = query.list();
        return (int) list.get(0);
    }

    public static void main(String[] args)
    {
        System.out.println(new ManagerDAO().getManagerNum());
    }
}
