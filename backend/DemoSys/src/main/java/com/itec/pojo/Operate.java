package com.itec.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Operate extends OperateKey {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operEngineerNm;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operProjectNm;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer operPricePer;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer operPurchasePrice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date operStartdt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date operEnddt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operStatus;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer operExcessUnitprice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_DEDUCTION_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer operDeductionUnitprice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operSettlementUnit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operUpperLimit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operLowerLimit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operRmk;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operCrdUsr;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date operCrdDt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String operUpdUsr;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column M_OPERATE_TBL.OPER_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date operUpdDt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_ENGINEER_NM
	 *
	 * @return the value of M_OPERATE_TBL.OPER_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperEngineerNm() {
		return operEngineerNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_ENGINEER_NM
	 *
	 * @param operEngineerNm the value for M_OPERATE_TBL.OPER_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperEngineerNm(String operEngineerNm) {
		this.operEngineerNm = operEngineerNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_PROJECT_NM
	 *
	 * @return the value of M_OPERATE_TBL.OPER_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperProjectNm() {
		return operProjectNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_PROJECT_NM
	 *
	 * @param operProjectNm the value for M_OPERATE_TBL.OPER_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperProjectNm(String operProjectNm) {
		this.operProjectNm = operProjectNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_PRICE_PER
	 *
	 * @return the value of M_OPERATE_TBL.OPER_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getOperPricePer() {
		return operPricePer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_PRICE_PER
	 *
	 * @param operPricePer the value for M_OPERATE_TBL.OPER_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperPricePer(Integer operPricePer) {
		this.operPricePer = operPricePer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_PURCHASE_PRICE
	 *
	 * @return the value of M_OPERATE_TBL.OPER_PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getOperPurchasePrice() {
		return operPurchasePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_PURCHASE_PRICE
	 *
	 * @param operPurchasePrice the value for M_OPERATE_TBL.OPER_PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperPurchasePrice(Integer operPurchasePrice) {
		this.operPurchasePrice = operPurchasePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_STARTDT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getOperStartdt() {
		return operStartdt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_STARTDT
	 *
	 * @param operStartdt the value for M_OPERATE_TBL.OPER_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperStartdt(Date operStartdt) {
		this.operStartdt = operStartdt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_ENDDT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getOperEnddt() {
		return operEnddt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_ENDDT
	 *
	 * @param operEnddt the value for M_OPERATE_TBL.OPER_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperEnddt(Date operEnddt) {
		this.operEnddt = operEnddt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_STATUS
	 *
	 * @return the value of M_OPERATE_TBL.OPER_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperStatus() {
		return operStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_STATUS
	 *
	 * @param operStatus the value for M_OPERATE_TBL.OPER_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_EXCESS_UNITPRICE
	 *
	 * @return the value of M_OPERATE_TBL.OPER_EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getOperExcessUnitprice() {
		return operExcessUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_EXCESS_UNITPRICE
	 *
	 * @param operExcessUnitprice the value for M_OPERATE_TBL.OPER_EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperExcessUnitprice(Integer operExcessUnitprice) {
		this.operExcessUnitprice = operExcessUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_DEDUCTION_UNITPRICE
	 *
	 * @return the value of M_OPERATE_TBL.OPER_DEDUCTION_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getOperDeductionUnitprice() {
		return operDeductionUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_DEDUCTION_UNITPRICE
	 *
	 * @param operDeductionUnitprice the value for
	 *                               M_OPERATE_TBL.OPER_DEDUCTION_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperDeductionUnitprice(Integer operDeductionUnitprice) {
		this.operDeductionUnitprice = operDeductionUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_SETTLEMENT_UNIT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperSettlementUnit() {
		return operSettlementUnit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_SETTLEMENT_UNIT
	 *
	 * @param operSettlementUnit the value for M_OPERATE_TBL.OPER_SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperSettlementUnit(String operSettlementUnit) {
		this.operSettlementUnit = operSettlementUnit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_UPPER_LIMIT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperUpperLimit() {
		return operUpperLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_UPPER_LIMIT
	 *
	 * @param operUpperLimit the value for M_OPERATE_TBL.OPER_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperUpperLimit(String operUpperLimit) {
		this.operUpperLimit = operUpperLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_LOWER_LIMIT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperLowerLimit() {
		return operLowerLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_LOWER_LIMIT
	 *
	 * @param operLowerLimit the value for M_OPERATE_TBL.OPER_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperLowerLimit(String operLowerLimit) {
		this.operLowerLimit = operLowerLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_RMK
	 *
	 * @return the value of M_OPERATE_TBL.OPER_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperRmk() {
		return operRmk;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_RMK
	 *
	 * @param operRmk the value for M_OPERATE_TBL.OPER_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperRmk(String operRmk) {
		this.operRmk = operRmk;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_CRD_USR
	 *
	 * @return the value of M_OPERATE_TBL.OPER_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperCrdUsr() {
		return operCrdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_CRD_USR
	 *
	 * @param operCrdUsr the value for M_OPERATE_TBL.OPER_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperCrdUsr(String operCrdUsr) {
		this.operCrdUsr = operCrdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_CRD_DT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getOperCrdDt() {
		return operCrdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_CRD_DT
	 *
	 * @param operCrdDt the value for M_OPERATE_TBL.OPER_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperCrdDt(Date operCrdDt) {
		this.operCrdDt = operCrdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_UPD_USR
	 *
	 * @return the value of M_OPERATE_TBL.OPER_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getOperUpdUsr() {
		return operUpdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_UPD_USR
	 *
	 * @param operUpdUsr the value for M_OPERATE_TBL.OPER_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperUpdUsr(String operUpdUsr) {
		this.operUpdUsr = operUpdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column M_OPERATE_TBL.OPER_UPD_DT
	 *
	 * @return the value of M_OPERATE_TBL.OPER_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getOperUpdDt() {
		return operUpdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column M_OPERATE_TBL.OPER_UPD_DT
	 *
	 * @param operUpdDt the value for M_OPERATE_TBL.OPER_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setOperUpdDt(Date operUpdDt) {
		this.operUpdDt = operUpdDt;
	}

	public Operate(String operEngineerNm, String operProjectNm, Integer operPricePer, Integer operPurchasePrice,
			Date operStartdt, Date operEnddt, String operStatus, Integer operExcessUnitprice,
			Integer operDeductionUnitprice, String operSettlementUnit, String operUpperLimit, String operLowerLimit,
			String operRmk, String operCrdUsr, Date operCrdDt, String operUpdUsr, Date operUpdDt) {
		super();
		this.operEngineerNm = operEngineerNm;
		this.operProjectNm = operProjectNm;
		this.operPricePer = operPricePer;
		this.operPurchasePrice = operPurchasePrice;
		this.operStartdt = operStartdt;
		this.operEnddt = operEnddt;
		this.operStatus = operStatus;
		this.operExcessUnitprice = operExcessUnitprice;
		this.operDeductionUnitprice = operDeductionUnitprice;
		this.operSettlementUnit = operSettlementUnit;
		this.operUpperLimit = operUpperLimit;
		this.operLowerLimit = operLowerLimit;
		this.operRmk = operRmk;
		this.operCrdUsr = operCrdUsr;
		this.operCrdDt = operCrdDt;
		this.operUpdUsr = operUpdUsr;
		this.operUpdDt = operUpdDt;
	}

	public Operate() {
		super();
	}

}