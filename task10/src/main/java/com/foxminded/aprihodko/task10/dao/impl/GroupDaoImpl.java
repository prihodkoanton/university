package com.foxminded.aprihodko.task10.dao.impl;

//public abstract class GroupDaoImpl implements GroupDao {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);
//
//    @Override
//    public Optional<Group> findByName(String name) throws SQLException {
//        TypedQuery<Group> query = entityManager.createQuery("FROM Group WHERE group_name = '" + name + "'",
//                Group.class);
//        Group group = query.getSingleResult();
//        return group != null ? Optional.of(group) : Optional.empty();
//    }
//}
