package org.itstep.dao;


import org.itstep.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

    /* public abstract TeacherDao createTeacherDao();*/
    public abstract UserAccountDao createUserDao();

    public abstract BarberDao createBarberDao();

    public abstract ServiceDao createServiceDao();

    public abstract RecordDao createRecordDao();

    public abstract ClientDao createClientDao();
}