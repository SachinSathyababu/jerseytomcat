package medbooking.dao.impl;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import medbooking.dao.BookingDao;
import medbooking.dao.entity.model.BookingEntity;
import medbooking.dao.hibernate.utils.HibernateUtils;
import medbooking.shared.dto.Bookingdto;
import medbooking.shared.dto.Bookingdto.bookingStatus;

public class Bookingdaohibernateimpl implements BookingDao{

	Session session;
	
	@Override
	public void openConnection() {

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		session= sessionFactory.openSession();
		
	}

	@Override
	public Bookingdto getBookingById(String bookingId) {
		
		Bookingdto bookingdto = null;
		
		CriteriaBuilder crb= session.getCriteriaBuilder();
		
		CriteriaQuery<BookingEntity> cquery= crb.createQuery(BookingEntity.class);
		
		Root<BookingEntity> root= cquery.from(BookingEntity.class);
		
		cquery.select(root);
		
		cquery.where(crb.equal(root.get("bookingId"), bookingId));
		
		Query<BookingEntity> query= session.createQuery(cquery);
		
		BookingEntity bookingEntity = query.uniqueResult();
		
		if(bookingEntity!=null) {
			bookingdto = new Bookingdto();
			BeanUtils.copyProperties(bookingEntity, bookingdto);
		}
		
		return bookingdto;
		
	}

	@Override
	public void closeConnection() {
		if(session!=null)
		session.close();
	}

	@Override
	public Bookingdto checkDuplicateBooking(Bookingdto bookingdto) {
		
		Bookingdto duplicatebooking=null;
		
		CriteriaBuilder crb= session.getCriteriaBuilder();
		
		CriteriaQuery<BookingEntity> cquery= crb.createQuery(BookingEntity.class);
		
		Root<BookingEntity> root= cquery.from(BookingEntity.class);
		
		cquery.select(root);
		
		cquery.where(crb.equal(root.get("patientEmail"), bookingdto.getPatientEmail())).
		where(crb.equal(root.get("doctorName"), bookingdto.getDoctorName())).
		where(crb.equal(root.get("bookingDateTime"), bookingdto.getBookingDateTime())).
		where(crb.equal(root.get("status"), bookingStatus.CONFIRMED));
		
		Query<BookingEntity> query= session.createQuery(cquery);
		
		BookingEntity bookingEntity = query.uniqueResult();
		
		if(bookingEntity!=null) {
		duplicatebooking = new Bookingdto();
		BeanUtils.copyProperties(bookingEntity, duplicatebooking);
		}
		
		return duplicatebooking;
		
	}

	@Override
	public Bookingdto saveBooking(Bookingdto bookdto) {

		Bookingdto savedBooking=null;
		BookingEntity bookingEntity= new BookingEntity();
		
		BeanUtils.copyProperties(bookdto, bookingEntity);
		
		session.beginTransaction();
		session.save(bookingEntity);
		session.getTransaction().commit();
		
		savedBooking= new Bookingdto();
		BeanUtils.copyProperties(bookingEntity, savedBooking);
		
		return savedBooking;
	}

}
