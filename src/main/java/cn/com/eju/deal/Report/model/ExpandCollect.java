package cn.com.eju.deal.Report.model;

public class ExpandCollect {

		//草签A模式
	  private String CrmContractInitialANumber="0";
		//草签B模式
	  private String CrmContractInitialBNumber="0";
	  //OA审批通过A模式
	   private String CRMAuditPassANumber="0";
	  //OA审批通过B模式
	   private String CRMAuditPassBNumber="0";
	   //翻牌完成
	   private String CrmDrawFinishNumber="0";
	   //翻牌验收完成
	   private String CrmDrawCheckFinishNumber="0";
		//门面店拓展→保证金→门店数实际数 
		private String CrmDepositStoreNumber="0";
		//门面店拓展→保证金→已分账金
		private String CrmDepositRoutingAmountSum="0";
		//门面店拓展→保证金→已收金额
		private String CrmDepositReceiveAmountSum="0";
		
		//拓展汇总时间
		private String calendarDate;
		public String getCrmContractInitialANumber() {
			return CrmContractInitialANumber;
		}
		public void setCrmContractInitialANumber(String crmContractInitialANumber) {
			CrmContractInitialANumber = crmContractInitialANumber;
		}
		public String getCrmContractInitialBNumber() {
			return CrmContractInitialBNumber;
		}
		public void setCrmContractInitialBNumber(String crmContractInitialBNumber) {
			CrmContractInitialBNumber = crmContractInitialBNumber;
		}
		public String getCRMAuditPassANumber() {
			return CRMAuditPassANumber;
		}
		public void setCRMAuditPassANumber(String cRMAuditPassANumber) {
			CRMAuditPassANumber = cRMAuditPassANumber;
		}
		public String getCRMAuditPassBNumber() {
			return CRMAuditPassBNumber;
		}
		public void setCRMAuditPassBNumber(String cRMAuditPassBNumber) {
			CRMAuditPassBNumber = cRMAuditPassBNumber;
		}
		public String getCrmDrawFinishNumber() {
			return CrmDrawFinishNumber;
		}
		public void setCrmDrawFinishNumber(String crmDrawFinishNumber) {
			CrmDrawFinishNumber = crmDrawFinishNumber;
		}
		public String getCrmDrawCheckFinishNumber() {
			return CrmDrawCheckFinishNumber;
		}
		public void setCrmDrawCheckFinishNumber(String crmDrawCheckFinishNumber) {
			CrmDrawCheckFinishNumber = crmDrawCheckFinishNumber;
		}
		public String getCrmDepositStoreNumber() {
			return CrmDepositStoreNumber;
		}
		public void setCrmDepositStoreNumber(String crmDepositStoreNumber) {
			CrmDepositStoreNumber = crmDepositStoreNumber;
		}
		public String getCrmDepositRoutingAmountSum() {
			return CrmDepositRoutingAmountSum;
		}
		public void setCrmDepositRoutingAmountSum(String crmDepositRoutingAmountSum) {
			CrmDepositRoutingAmountSum = crmDepositRoutingAmountSum;
		}
		public String getCrmDepositReceiveAmountSum() {
			return CrmDepositReceiveAmountSum;
		}
		public void setCrmDepositReceiveAmountSum(String crmDepositReceiveAmountSum) {
			CrmDepositReceiveAmountSum = crmDepositReceiveAmountSum;
		}
		public String getCalendarDate() {
			return calendarDate;
		}
		public void setCalendarDate(String calendarDate) {
			this.calendarDate = calendarDate;
		}
		
		
		
	   
}
