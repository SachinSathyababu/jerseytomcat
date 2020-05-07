package medbooking.dao.impl;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import medbooking.dao.UserDao;
import medbooking.dao.entity.model.UserEntity;
import medbooking.dao.hibernate.utils.HibernateUtils;
import medbooking.shared.dto.Userdto;


public class Userdaohibernateimpl implements UserDao{

	Session session;
	
	@Override
	public void openConnection() {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		session= sessionFactory.openSession();
		
	}
	
	@Override
	public void closeConnection() {
		if(session!=null)
		session.close();
	}

	@Override
	public Userdto getUser(Userdto userdto) {
		
		Userdto retrievedUserdto=null;
		
		CriteriaBuilder crb= session.getCriteriaBuilder();
		
		CriteriaQuery<UserEntity> cquery= crb.createQuery(UserEntity.class);
		
		Root<UserEntity> root= cquery.from(UserEntity.class);
		
		cquery.select(root);
		
		Predicate predicateForUsername
		  = crb.equal(root.get("username"), userdto.getUsername());
		Predicate predicateForPassword
		  = crb.equal(root.get("password"), userdto.getPassword());
		
		Predicate finalPredicate
		  = crb.and(predicateForUsername, predicateForPassword);
		
		cquery.where(finalPredicate);
		
		Query<UserEntity> query= session.createQuery(cquery);
		
		UserEntity userEntity = query.uniqueResult();
		
		if(userEntity!=null) {
			retrievedUserdto = new Userdto();
		BeanUtils.copyProperties(userEntity, retrievedUserdto);
		}
		
		return retrievedUserdto;
		
	}


}
