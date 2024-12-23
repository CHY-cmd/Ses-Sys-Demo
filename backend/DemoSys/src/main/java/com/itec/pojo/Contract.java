package com.itec.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Contract {
	private String custNm;
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_NO
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrNo;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_CUST_ID
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Long contrCustId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrEngineerNm;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrProjectNm;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.QUOT_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String quotDelflg;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrDelflg;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date contrStartdt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date contrEnddt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrStatus;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrRmk;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer contrPricePer;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer purchasePrice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer excessUnitprice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.DEDU_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer deduUnitprice;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String settlementUnit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrUpperLimit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrLowerLimit;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_AMOUNT_SUBTOTAL
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer contrAmountSubtotal;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONSUMPTION_TAX
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer consumptionTax;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.TOTAL_AMOUNT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Integer totalAmount;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrCrdUsr;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date contrCrdDt;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private String contrUpdUsr;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column CONTRACT_TBL.CONTR_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	private Date contrUpdDt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_NO
	 *
	 * @return the value of CONTRACT_TBL.CONTR_NO
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrNo() {
		return contrNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_NO
	 *
	 * @param contrNo the value for CONTRACT_TBL.CONTR_NO
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrNo(String contrNo) {
		this.contrNo = contrNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_CUST_ID
	 *
	 * @return the value of CONTRACT_TBL.CONTR_CUST_ID
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Long getContrCustId() {
		return contrCustId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_CUST_ID
	 *
	 * @param contrCustId the value for CONTRACT_TBL.CONTR_CUST_ID
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrCustId(Long contrCustId) {
		this.contrCustId = contrCustId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_ENGINEER_NM
	 *
	 * @return the value of CONTRACT_TBL.CONTR_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrEngineerNm() {
		return contrEngineerNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_ENGINEER_NM
	 *
	 * @param contrEngineerNm the value for CONTRACT_TBL.CONTR_ENGINEER_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrEngineerNm(String contrEngineerNm) {
		this.contrEngineerNm = contrEngineerNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_PROJECT_NM
	 *
	 * @return the value of CONTRACT_TBL.CONTR_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrProjectNm() {
		return contrProjectNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_PROJECT_NM
	 *
	 * @param contrProjectNm the value for CONTRACT_TBL.CONTR_PROJECT_NM
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrProjectNm(String contrProjectNm) {
		this.contrProjectNm = contrProjectNm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.QUOT_DELFLG
	 *
	 * @return the value of CONTRACT_TBL.QUOT_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getQuotDelflg() {
		return quotDelflg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.QUOT_DELFLG
	 *
	 * @param quotDelflg the value for CONTRACT_TBL.QUOT_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setQuotDelflg(String quotDelflg) {
		this.quotDelflg = quotDelflg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_DELFLG
	 *
	 * @return the value of CONTRACT_TBL.CONTR_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrDelflg() {
		return contrDelflg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_DELFLG
	 *
	 * @param contrDelflg the value for CONTRACT_TBL.CONTR_DELFLG
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrDelflg(String contrDelflg) {
		this.contrDelflg = contrDelflg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_STARTDT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getContrStartdt() {
		return contrStartdt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_STARTDT
	 *
	 * @param contrStartdt the value for CONTRACT_TBL.CONTR_STARTDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrStartdt(Date contrStartdt) {
		this.contrStartdt = contrStartdt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_ENDDT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getContrEnddt() {
		return contrEnddt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_ENDDT
	 *
	 * @param contrEnddt the value for CONTRACT_TBL.CONTR_ENDDT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrEnddt(Date contrEnddt) {
		this.contrEnddt = contrEnddt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_STATUS
	 *
	 * @return the value of CONTRACT_TBL.CONTR_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrStatus() {
		return contrStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_STATUS
	 *
	 * @param contrStatus the value for CONTRACT_TBL.CONTR_STATUS
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrStatus(String contrStatus) {
		this.contrStatus = contrStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_RMK
	 *
	 * @return the value of CONTRACT_TBL.CONTR_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrRmk() {
		return contrRmk;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_RMK
	 *
	 * @param contrRmk the value for CONTRACT_TBL.CONTR_RMK
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrRmk(String contrRmk) {
		this.contrRmk = contrRmk;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_PRICE_PER
	 *
	 * @return the value of CONTRACT_TBL.CONTR_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getContrPricePer() {
		return contrPricePer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_PRICE_PER
	 *
	 * @param contrPricePer the value for CONTRACT_TBL.CONTR_PRICE_PER
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrPricePer(Integer contrPricePer) {
		this.contrPricePer = contrPricePer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.PURCHASE_PRICE
	 *
	 * @return the value of CONTRACT_TBL.PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.PURCHASE_PRICE
	 *
	 * @param purchasePrice the value for CONTRACT_TBL.PURCHASE_PRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.EXCESS_UNITPRICE
	 *
	 * @return the value of CONTRACT_TBL.EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getExcessUnitprice() {
		return excessUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.EXCESS_UNITPRICE
	 *
	 * @param excessUnitprice the value for CONTRACT_TBL.EXCESS_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setExcessUnitprice(Integer excessUnitprice) {
		this.excessUnitprice = excessUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.DEDU_UNITPRICE
	 *
	 * @return the value of CONTRACT_TBL.DEDU_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getDeduUnitprice() {
		return deduUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.DEDU_UNITPRICE
	 *
	 * @param deduUnitprice the value for CONTRACT_TBL.DEDU_UNITPRICE
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setDeduUnitprice(Integer deduUnitprice) {
		this.deduUnitprice = deduUnitprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.SETTLEMENT_UNIT
	 *
	 * @return the value of CONTRACT_TBL.SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getSettlementUnit() {
		return settlementUnit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.SETTLEMENT_UNIT
	 *
	 * @param settlementUnit the value for CONTRACT_TBL.SETTLEMENT_UNIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setSettlementUnit(String settlementUnit) {
		this.settlementUnit = settlementUnit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_UPPER_LIMIT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrUpperLimit() {
		return contrUpperLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_UPPER_LIMIT
	 *
	 * @param contrUpperLimit the value for CONTRACT_TBL.CONTR_UPPER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrUpperLimit(String contrUpperLimit) {
		this.contrUpperLimit = contrUpperLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_LOWER_LIMIT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrLowerLimit() {
		return contrLowerLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_LOWER_LIMIT
	 *
	 * @param contrLowerLimit the value for CONTRACT_TBL.CONTR_LOWER_LIMIT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrLowerLimit(String contrLowerLimit) {
		this.contrLowerLimit = contrLowerLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_AMOUNT_SUBTOTAL
	 *
	 * @return the value of CONTRACT_TBL.CONTR_AMOUNT_SUBTOTAL
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getContrAmountSubtotal() {
		return contrAmountSubtotal;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_AMOUNT_SUBTOTAL
	 *
	 * @param contrAmountSubtotal the value for CONTRACT_TBL.CONTR_AMOUNT_SUBTOTAL
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrAmountSubtotal(Integer contrAmountSubtotal) {
		this.contrAmountSubtotal = contrAmountSubtotal;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONSUMPTION_TAX
	 *
	 * @return the value of CONTRACT_TBL.CONSUMPTION_TAX
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getConsumptionTax() {
		return consumptionTax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONSUMPTION_TAX
	 *
	 * @param consumptionTax the value for CONTRACT_TBL.CONSUMPTION_TAX
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setConsumptionTax(Integer consumptionTax) {
		this.consumptionTax = consumptionTax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.TOTAL_AMOUNT
	 *
	 * @return the value of CONTRACT_TBL.TOTAL_AMOUNT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.TOTAL_AMOUNT
	 *
	 * @param totalAmount the value for CONTRACT_TBL.TOTAL_AMOUNT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_CRD_USR
	 *
	 * @return the value of CONTRACT_TBL.CONTR_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrCrdUsr() {
		return contrCrdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_CRD_USR
	 *
	 * @param contrCrdUsr the value for CONTRACT_TBL.CONTR_CRD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrCrdUsr(String contrCrdUsr) {
		this.contrCrdUsr = contrCrdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_CRD_DT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getContrCrdDt() {
		return contrCrdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_CRD_DT
	 *
	 * @param contrCrdDt the value for CONTRACT_TBL.CONTR_CRD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrCrdDt(Date contrCrdDt) {
		this.contrCrdDt = contrCrdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_UPD_USR
	 *
	 * @return the value of CONTRACT_TBL.CONTR_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public String getContrUpdUsr() {
		return contrUpdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_UPD_USR
	 *
	 * @param contrUpdUsr the value for CONTRACT_TBL.CONTR_UPD_USR
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrUpdUsr(String contrUpdUsr) {
		this.contrUpdUsr = contrUpdUsr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column CONTRACT_TBL.CONTR_UPD_DT
	 *
	 * @return the value of CONTRACT_TBL.CONTR_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public Date getContrUpdDt() {
		return contrUpdDt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column CONTRACT_TBL.CONTR_UPD_DT
	 *
	 * @param contrUpdDt the value for CONTRACT_TBL.CONTR_UPD_DT
	 *
	 * @mbg.generated Fri Dec 13 14:58:51 HKT 2024
	 */
	public void setContrUpdDt(Date contrUpdDt) {
		this.contrUpdDt = contrUpdDt;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public Contract() {
		super();
	}

	public Contract(String custNm, String contrNo, Long contrCustId, String contrEngineerNm, String contrProjectNm,
			String quotDelflg, String contrDelflg, Date contrStartdt, Date contrEnddt, String contrStatus,
			String contrRmk, Integer contrPricePer, Integer purchasePrice, Integer excessUnitprice,
			Integer deduUnitprice, String settlementUnit, String contrUpperLimit, String contrLowerLimit,
			Integer contrAmountSubtotal, Integer consumptionTax, Integer totalAmount, String contrCrdUsr,
			Date contrCrdDt, String contrUpdUsr, Date contrUpdDt) {
		super();
		this.custNm = custNm;
		this.contrNo = contrNo;
		this.contrCustId = contrCustId;
		this.contrEngineerNm = contrEngineerNm;
		this.contrProjectNm = contrProjectNm;
		this.quotDelflg = quotDelflg;
		this.contrDelflg = contrDelflg;
		this.contrStartdt = contrStartdt;
		this.contrEnddt = contrEnddt;
		this.contrStatus = contrStatus;
		this.contrRmk = contrRmk;
		this.contrPricePer = contrPricePer;
		this.purchasePrice = purchasePrice;
		this.excessUnitprice = excessUnitprice;
		this.deduUnitprice = deduUnitprice;
		this.settlementUnit = settlementUnit;
		this.contrUpperLimit = contrUpperLimit;
		this.contrLowerLimit = contrLowerLimit;
		this.contrAmountSubtotal = contrAmountSubtotal;
		this.consumptionTax = consumptionTax;
		this.totalAmount = totalAmount;
		this.contrCrdUsr = contrCrdUsr;
		this.contrCrdDt = contrCrdDt;
		this.contrUpdUsr = contrUpdUsr;
		this.contrUpdDt = contrUpdDt;
	}
}