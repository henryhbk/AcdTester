package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.hibernate.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PRESCRIPTIONS database table.
 *
 * @author henry.feldman@ibm.com
 */
@Entity
@Table(name = "PRESCRIPTIONS")
@NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p")
@JsonRootName(value = "Prescription")
public class Prescription extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The dose unit rx. */
	@Column(name = "DOSE_UNIT_RX")
	private String doseUnitRx;

	/** The dose val rx. */
	@Column(name = "DOSE_VAL_RX")
	private String doseValRx;

	/** The drug. */
	private String drug;

	/** The drug name generic. */
	@Column(name = "DRUG_NAME_GENERIC")
	private String drugNameGeneric;

	/** The drug name poe. */
	@Column(name = "DRUG_NAME_POE")
	private String drugNamePoe;

	/** The drug type. */
	@Column(name = "DRUG_TYPE")
	private String drugType;

	/** The enddate. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;

	/** The form unit disp. */
	@Column(name = "FORM_UNIT_DISP")
	private String formUnitDisp;

	/** The form val disp. */
	@Column(name = "FORM_VAL_DISP")
	private String formValDisp;

	/** The formulary drug cd. */
	@Column(name = "FORMULARY_DRUG_CD")
	private String formularyDrugCd;

	/** The gsn. */
	@Column(name = "GSN")
	private String gsn;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The icustay id. */
	@Column(name = "ICUSTAY_ID")
	private Integer icustayId;

	/** The ndc. */
	@Column(name = "NDC")
	private String ndc;

	/** The rx norm cui. */
	@Column(name = "rxNormId")
	private String rxNormCui;

	/** The prod strength. */
	@Column(name = "PROD_STRENGTH")
	private String prodStrength;

	/** The route. */
	@Column(name = "ROUTE")
	private String route;

	/** The startdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STARTDATE")
	private Date startdate;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	/** The fhir json. */
	@Column(name = "fhir_json")
	private String fhirJson;

	/** The fhir json. */
	@Column(name = "acd_study_med")
	private Boolean acdStudyMed;

	/**
	 * Instantiates a new prescription.
	 */
	public Prescription() {
	}

	/**
	 * Instantiates a new prescription.
	 *
	 * @param rowId           the row id
	 * @param doseUnitRx      the dose unit rx
	 * @param doseValRx       the dose val rx
	 * @param drug            the drug
	 * @param drugNameGeneric the drug name generic
	 * @param drugNamePoe     the drug name poe
	 * @param drugType        the drug type
	 * @param enddate         the enddate
	 * @param formUnitDisp    the form unit disp
	 * @param formValDisp     the form val disp
	 * @param formularyDrugCd the formulary drug cd
	 * @param gsn             the gsn
	 * @param hadmId          the hadm id
	 * @param icustayId       the icustay id
	 * @param ndc             the ndc
	 * @param rxNormCui       the rx norm cui
	 * @param prodStrength    the prod strength
	 * @param route           the route
	 * @param startdate       the startdate
	 * @param subjectId       the subject id
	 * @param fhirJson        the fhir json
	 * @param acdStudyMed     the acd study med
	 */
	public Prescription(int rowId, String doseUnitRx, String doseValRx, String drug, String drugNameGeneric,
			String drugNamePoe, String drugType, Date enddate, String formUnitDisp, String formValDisp,
			String formularyDrugCd, String gsn, Integer hadmId, Integer icustayId, String ndc, String rxNormCui,
			String prodStrength, String route, Date startdate, Integer subjectId, String fhirJson,
			Boolean acdStudyMed) {
		super();
		this.rowId = rowId;
		this.doseUnitRx = doseUnitRx;
		this.doseValRx = doseValRx;
		this.drug = drug;
		this.drugNameGeneric = drugNameGeneric;
		this.drugNamePoe = drugNamePoe;
		this.drugType = drugType;
		this.enddate = enddate;
		this.formUnitDisp = formUnitDisp;
		this.formValDisp = formValDisp;
		this.formularyDrugCd = formularyDrugCd;
		this.gsn = gsn;
		this.hadmId = hadmId;
		this.icustayId = icustayId;
		this.ndc = ndc;
		this.rxNormCui = rxNormCui;
		this.prodStrength = prodStrength;
		this.route = route;
		this.startdate = startdate;
		this.subjectId = subjectId;
		this.fhirJson = fhirJson;
		this.acdStudyMed = acdStudyMed;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public Integer getRowId() {
		return rowId;
	}

	/**
	 * Gets the dose unit rx.
	 *
	 * @return the dose unit rx
	 */
	public String getDoseUnitRx() {
		return doseUnitRx;
	}

	/**
	 * Gets the dose val rx.
	 *
	 * @return the dose val rx
	 */
	public String getDoseValRx() {
		return doseValRx;
	}

	/**
	 * Gets the drug.
	 *
	 * @return the drug
	 */
	public String getDrug() {
		return drug;
	}

	/**
	 * Gets the drug name generic.
	 *
	 * @return the drug name generic
	 */
	public String getDrugNameGeneric() {
		return drugNameGeneric;
	}

	/**
	 * Gets the drug name poe.
	 *
	 * @return the drug name poe
	 */
	public String getDrugNamePoe() {
		return drugNamePoe;
	}

	/**
	 * Gets the drug type.
	 *
	 * @return the drug type
	 */
	public String getDrugType() {
		return drugType;
	}

	/**
	 * Gets the enddate.
	 *
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * Gets the form unit disp.
	 *
	 * @return the form unit disp
	 */
	public String getFormUnitDisp() {
		return formUnitDisp;
	}

	/**
	 * Gets the form val disp.
	 *
	 * @return the form val disp
	 */
	public String getFormValDisp() {
		return formValDisp;
	}

	/**
	 * Gets the formulary drug cd.
	 *
	 * @return the formulary drug cd
	 */
	public String getFormularyDrugCd() {
		return formularyDrugCd;
	}

	/**
	 * Gets the gsn.
	 *
	 * @return the gsn
	 */
	public String getGsn() {
		return gsn;
	}

	/**
	 * Gets the hadm id.
	 *
	 * @return the hadm id
	 */
	public Integer getHadmId() {
		return hadmId;
	}

	/**
	 * Gets the icustay id.
	 *
	 * @return the icustay id
	 */
	public Integer getIcustayId() {
		return icustayId;
	}

	/**
	 * Gets the ndc.
	 *
	 * @return the ndc
	 */
	public String getNdc() {
		return ndc;
	}

	/**
	 * Gets the rx norm cui.
	 *
	 * @return the rx norm cui
	 */
	public String getRxNormCui() {
		return rxNormCui;
	}

	/**
	 * Gets the prod strength.
	 *
	 * @return the prod strength
	 */
	public String getProdStrength() {
		return prodStrength;
	}

	/**
	 * Gets the route.
	 *
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * Gets the startdate.
	 *
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * Gets the acd study med.
	 *
	 * @return the acd study med
	 */
	public Boolean getAcdStudyMed() {
		return acdStudyMed;
	}

	/**
	 * Sets the acd study med.
	 *
	 * @param acdStudyMed the new acd study med
	 */
	public void setAcdStudyMed(Boolean acdStudyMed) {
		this.acdStudyMed = acdStudyMed;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Gets the fhir json.
	 *
	 * @return the fhir json
	 */
	public String getFhirJson() {
		return fhirJson;
	}

	/**
	 * Sets the fhir json.
	 *
	 * @param fhirJson the new fhir json
	 */
	public void setFhirJson(String fhirJson) {
		this.fhirJson = fhirJson;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the dose unit rx.
	 *
	 * @param doseUnitRx the new dose unit rx
	 */
	public void setDoseUnitRx(String doseUnitRx) {
		this.doseUnitRx = doseUnitRx;
	}

	/**
	 * Sets the dose val rx.
	 *
	 * @param doseValRx the new dose val rx
	 */
	public void setDoseValRx(String doseValRx) {
		this.doseValRx = doseValRx;
	}

	/**
	 * Sets the drug.
	 *
	 * @param drug the new drug
	 */
	public void setDrug(String drug) {
		this.drug = drug;
	}

	/**
	 * Sets the drug name generic.
	 *
	 * @param drugNameGeneric the new drug name generic
	 */
	public void setDrugNameGeneric(String drugNameGeneric) {
		this.drugNameGeneric = drugNameGeneric;
	}

	/**
	 * Sets the drug name poe.
	 *
	 * @param drugNamePoe the new drug name poe
	 */
	public void setDrugNamePoe(String drugNamePoe) {
		this.drugNamePoe = drugNamePoe;
	}

	/**
	 * Sets the drug type.
	 *
	 * @param drugType the new drug type
	 */
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	/**
	 * Sets the enddate.
	 *
	 * @param enddate the new enddate
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	/**
	 * Sets the form unit disp.
	 *
	 * @param formUnitDisp the new form unit disp
	 */
	public void setFormUnitDisp(String formUnitDisp) {
		this.formUnitDisp = formUnitDisp;
	}

	/**
	 * Sets the form val disp.
	 *
	 * @param formValDisp the new form val disp
	 */
	public void setFormValDisp(String formValDisp) {
		this.formValDisp = formValDisp;
	}

	/**
	 * Sets the formulary drug cd.
	 *
	 * @param formularyDrugCd the new formulary drug cd
	 */
	public void setFormularyDrugCd(String formularyDrugCd) {
		this.formularyDrugCd = formularyDrugCd;
	}

	/**
	 * Sets the gsn.
	 *
	 * @param gsn the new gsn
	 */
	public void setGsn(String gsn) {
		this.gsn = gsn;
	}

	/**
	 * Sets the hadm id.
	 *
	 * @param hadmId the new hadm id
	 */
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
	}

	/**
	 * Sets the icustay id.
	 *
	 * @param icustayId the new icustay id
	 */
	public void setIcustayId(Integer icustayId) {
		this.icustayId = icustayId;
	}

	/**
	 * Sets the ndc.
	 *
	 * @param ndc the new ndc
	 */
	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	/**
	 * Sets the rx norm cui.
	 *
	 * @param rxNormCui the new rx norm cui
	 */
	public void setRxNormCui(String rxNormCui) {
		this.rxNormCui = rxNormCui;
	}

	/**
	 * Sets the prod strength.
	 *
	 * @param prodStrength the new prod strength
	 */
	public void setProdStrength(String prodStrength) {
		this.prodStrength = prodStrength;
	}

	/**
	 * Sets the route.
	 *
	 * @param route the new route
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * Sets the startdate.
	 *
	 * @param startdate the new startdate
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the sig.
	 *
	 * @return the sig
	 */
	public String getSig() {
		return drug + " " + doseValRx + doseUnitRx + " " + route;
	}

	/**
	 * Gets the search string form.
	 *
	 * @return the search string form
	 */
	public String getSearchStringForm() {
		if (drugNameGeneric != null && !drugNameGeneric.isEmpty()) {
			return StringEscapeUtils.escapeHtml4(drugNameGeneric + "+" + prodStrength);
		} else {
			return StringEscapeUtils.escapeHtml4(drug + "+" + prodStrength);
		}
	}

	/**
	 * To HTML.
	 *
	 * @return the string
	 */
	public String toHTML() {
		return "<b>" + drug + "</b> " + doseValRx + doseUnitRx + " Start:  " + FhirUtil.toIsoDate(startdate, false)
				+ " End: " + FhirUtil.toIsoDate(enddate, false);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Prescription [rowId=" + rowId + ", doseUnitRx=" + doseUnitRx + ", doseValRx=" + doseValRx + ", drug="
				+ drug + ", drugNameGeneric=" + drugNameGeneric + ", drugNamePoe=" + drugNamePoe + ", drugType="
				+ drugType + ", enddate=" + enddate + ", formUnitDisp=" + formUnitDisp + ", formValDisp=" + formValDisp
				+ ", formularyDrugCd=" + formularyDrugCd + ", gsn=" + gsn + ", hadmId=" + hadmId + ", icustayId="
				+ icustayId + ", ndc=" + ndc + ", rxNormCui=" + rxNormCui + ", prodStrength=" + prodStrength
				+ ", route=" + route + ", startdate=" + startdate + ", subjectId=" + subjectId + "]";
	}

}