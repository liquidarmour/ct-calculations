package uk.gov.hmrc.ct.mocks

import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever

case class MockAccountsBoxRetriever(baseBoxRetriever: MockFilingAttributesBoxRetriever) extends AccountsBoxRetriever(baseBoxRetriever) {
  override def companyAddress() = ???

  override def ac1() = ???

  override def ac2() = ???

  override def ac3() = ???

  override def ac4() = ???

  override def ac12() = ???

  override def ac205() = ???

  override def ac206() = ???

  override def generateValues = ???

  override def companiesHouseAccountsApproval() = ???

  override def hmrcAccountsApproval() = ???
}
