package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.ibm.nlp.model.mimic3.EKGReport;
import com.ibm.nlp.model.mimic3.EchoReport;
import com.ibm.nlp.model.mimic3.RadiologyReport;
import com.ibm.nlp.services.ReportService;

/**
 * {@link ReportService}
 */

/**
 * The Class ReportsDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class ReportsDao extends HibernateDao implements ReportService {

	/**
	 * Instantiates a new reports dao.
	 */
	public ReportsDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see ReportService#getRadiologyReport(Integer)
	 */
	@Override
	public RadiologyReport getRadiologyReport(Integer id) {
		final Session session = openSession();
		RadiologyReport report = null;
		try {
			report = session.get(RadiologyReport.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			return null;

		}
		closeSession(session);
		return report;

	}

	/**
	 * @see ReportService#getEkgReport(Integer)
	 */
	@Override
	public EKGReport getEkgReport(Integer id) {
		final Session session = openSession();
		EKGReport report = null;
		try {
			report = session.get(EKGReport.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			return null;

		}
		closeSession(session);
		return report;
	}

	/**
	 * @see ReportService#getEchoReport(Integer)
	 */
	@Override
	public EchoReport getEchoReport(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see ReportService#getAllRadiologyReports()
	 */
	@Override
	public List<RadiologyReport> getAllRadiologyReports() {
		final Session session = openSession();
		List<RadiologyReport> noteEvents = HibernateUtil.loadAllData(RadiologyReport.class, session);
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ReportService#getAllEKGReports()
	 */
	@Override
	public List<EKGReport> getAllEKGReports() {
		final Session session = openSession();
		List<EKGReport> noteEvents = HibernateUtil.loadAllData(EKGReport.class, session);
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ReportService#getAllEchoReports()
	 */
	@Override
	public List<EchoReport> getAllEchoReports() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see ReportService#getAllEKGReportsForMimicStudy()
	 */
	@Override
	public List<EKGReport> getAllEKGReportsForMimicStudy() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EKGReport> criteriaQuery = builder.createQuery(EKGReport.class);
		Root<EKGReport> root = criteriaQuery.from(EKGReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("acdStudyNote"), true));
		List<EKGReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#getAllRadiologyReportsForMimicStudy()
	 */
	@Override
	public List<RadiologyReport> getAllRadiologyReportsForMimicStudy() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RadiologyReport> criteriaQuery = builder.createQuery(RadiologyReport.class);
		Root<RadiologyReport> root = criteriaQuery.from(RadiologyReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("acdStudyNote"), true));
		List<RadiologyReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#getAllEchoReportsForMimicStudy()
	 */
	@Override
	public List<EchoReport> getAllEchoReportsForMimicStudy() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EchoReport> criteriaQuery = builder.createQuery(EchoReport.class);
		Root<EchoReport> root = criteriaQuery.from(EchoReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("acdStudyNote"), true));
		List<EchoReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#saveRadiologyReport(RadiologyReport)
	 */
	@Override
	public RadiologyReport saveRadiologyReport(RadiologyReport radiologyReport) {
		final Session session = openSession();
		session.saveOrUpdate(radiologyReport);
		closeSession(session);
		return radiologyReport;
	}

	/**
	 * @see ReportService#saveEKGReport(EKGReport)
	 */
	@Override
	public EKGReport saveEKGReport(EKGReport ekgReport) {
		final Session session = openSession();
		session.saveOrUpdate(ekgReport);
		closeSession(session);
		return ekgReport;
	}

	/**
	 * @see ReportService#saveEchoReport(EchoReport)
	 */
	@Override
	public EchoReport saveEchoReport(EchoReport echoReport) {
		final Session session = openSession();
		session.saveOrUpdate(echoReport);
		closeSession(session);
		return echoReport;
	}

	/**
	 * @see ReportService#deleteEKGReport(Integer)
	 */
	@Override
	public void deleteEKGReport(Integer id) {
		final Session session = openSession();
		try {
			EKGReport report = session.get(EKGReport.class, id);
			session.delete(report);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
		}
		closeSession(session);
	}

	/**
	 * @see ReportService#deleteRadiologyReport(Integer)
	 */
	@Override
	public void deleteRadiologyReport(Integer id) {
		final Session session = openSession();
		try {
			RadiologyReport report = session.get(RadiologyReport.class, id);
			session.delete(report);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
		}
		closeSession(session);
	}

	/**
	 * @see ReportService#deleteEchoReport(Integer)
	 */
	@Override
	public void deleteEchoReport(Integer id) {
		final Session session = openSession();
		try {
			EchoReport report = session.get(EchoReport.class, id);
			session.delete(report);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
		}
		closeSession(session);
	}

	/**
	 * @see ReportService#searchRadiologyReportsForText(String)
	 */
	@Override
	public List<RadiologyReport> searchRadiologyReportsForText(String text) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RadiologyReport> criteriaQuery = builder.createQuery(RadiologyReport.class);
		Root<RadiologyReport> root = criteriaQuery.from(RadiologyReport.class);
		criteriaQuery.select(root).where(builder.like(root.get("text"), "%" + text + "%"));
		List<RadiologyReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#searchEKGReportReportsForText(String)
	 */
	@Override
	public List<EKGReport> searchEKGReportReportsForText(String text) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EKGReport> criteriaQuery = builder.createQuery(EKGReport.class);
		Root<EKGReport> root = criteriaQuery.from(EKGReport.class);
		criteriaQuery.select(root).where(builder.like(root.get("text"), "%" + text + "%"));
		List<EKGReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#searchEchoReportReportsForText(String)
	 */
	@Override
	public List<EchoReport> searchEchoReportReportsForText(String text) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EchoReport> criteriaQuery = builder.createQuery(EchoReport.class);
		Root<EchoReport> root = criteriaQuery.from(EchoReport.class);
		criteriaQuery.select(root).where(builder.like(root.get("text"), "%" + text + "%"));
		List<EchoReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#getEchoReportByPage(Integer, Integer)
	 */
	@Override
	public List<EchoReport> getEchoReportByPage(Integer pageNumber, Integer pageSize) {

		final Session session = openSession();

		Query<EchoReport> query = session.createQuery("from EchoReport", EchoReport.class);

		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<EchoReport> reports = query.list();
		closeSession(session);
		return reports;

	}

	/**
	 * @see ReportService#getEKGReportByPage(Integer, Integer)
	 */
	@Override
	public List<EKGReport> getEKGReportByPage(Integer pageNumber, Integer pageSize) {

		final Session session = openSession();

		Query<EKGReport> query = session.createQuery("from EKGReport", EKGReport.class);

		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<EKGReport> reports = query.list();
		closeSession(session);
		return reports;

	}

	/**
	 * @see ReportService#getRadiologyReportByPage(Integer, Integer)
	 */
	@Override
	public List<RadiologyReport> getRadiologyReportByPage(Integer pageNumber, Integer pageSize) {

		final Session session = openSession();

		Query<RadiologyReport> query = session.createQuery("from RadiologyReport", RadiologyReport.class);

		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<RadiologyReport> reports = query.list();
		closeSession(session);
		return reports;

	}

	/**
	 * @see ReportService#getNumberOfEchoReportRows()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Long getNumberOfEchoReportRows() {
		final Session session = openSession();
		Criteria criteria = session.createCriteria(EchoReport.class);
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
		Long size = (Long) criteria.uniqueResult();
		closeSession(session);
		return size;

	}

	/**
	 * @see ReportService#getNumberOfEKGReportRows()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Long getNumberOfEKGReportRows() {
		final Session session = openSession();
		Criteria criteria = session.createCriteria(EKGReport.class);
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
		Long size = (Long) criteria.uniqueResult();
		closeSession(session);
		return size;

	}

	/**
	 * @see ReportService#getNumberOfRadiologyReportRows()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Long getNumberOfRadiologyReportRows() {
		final Session session = openSession();
		Criteria criteria = session.createCriteria(RadiologyReport.class);
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
		Long size = (Long) criteria.uniqueResult();
		closeSession(session);
		return size;
	}

	/**
	 * @see ReportService#getAllEchoReportsForPatient()
	 */
	@Override
	public List<EchoReport> getAllEchoReportsForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EchoReport> criteriaQuery = builder.createQuery(EchoReport.class);
		Root<EchoReport> root = criteriaQuery.from(EchoReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<EchoReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#getAllEKGReportsForPatient(Integer)
	 */
	@Override
	public List<EKGReport> getAllEKGReportsForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EKGReport> criteriaQuery = builder.createQuery(EKGReport.class);
		Root<EKGReport> root = criteriaQuery.from(EKGReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<EKGReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	/**
	 * @see ReportService#getAllRadiologyReportsForPatient(Integer)
	 */
	@Override
	public List<RadiologyReport> getAllRadiologyReportsForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RadiologyReport> criteriaQuery = builder.createQuery(RadiologyReport.class);
		Root<RadiologyReport> root = criteriaQuery.from(RadiologyReport.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<RadiologyReport> reports = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return reports;
	}

	public List<RadiologyReport> getAllNullRadiologyReportsForPatientByPage(Integer pageNumber, Integer pageSize) {
		final Session session = openSession();
		Query<RadiologyReport> query = session.createQuery("from RadiologyReport where impression is null",
				RadiologyReport.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<RadiologyReport> reports = query.list();
		closeSession(session);
		return reports;
	}

	@SuppressWarnings("deprecation")
	public Long getNumberOfNullRadiologyReportRows() {
		final Session session = openSession();
		Criteria criteria = session.createCriteria(RadiologyReport.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.isNull("impression"));
		Long size = (Long) criteria.uniqueResult();
		closeSession(session);
		return size;
	}

	public List<EchoReport> getAllNullEchoReportsForPatientByPage(Integer pageNumber, Integer pageSize) {
		final Session session = openSession();
		Query<EchoReport> query = session.createQuery("from EchoReport where impression is null", EchoReport.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<EchoReport> reports = query.list();
		closeSession(session);
		return reports;
	}

	@SuppressWarnings("deprecation")
	public Long getNumberOfNullEchoReportRows() {
		final Session session = openSession();
		Criteria criteria = session.createCriteria(EchoReport.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.isNull("impression"));
		Long size = (Long) criteria.uniqueResult();
		closeSession(session);
		return size;
	}

}
