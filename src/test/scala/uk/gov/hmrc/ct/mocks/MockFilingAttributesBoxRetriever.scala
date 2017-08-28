package uk.gov.hmrc.ct.mocks

import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever

case class MockFilingAttributesBoxRetriever() extends FilingAttributesBoxValueRetriever {
  override def companyType() = ???

  override def abbreviatedAccountsFiling() = ???

  override def abridgedFiling() = ???

  override def companiesHouseFiling() = ???

  override def hmrcFiling() = ???

  override def companiesHouseSubmitted() = ???

  override def hmrcSubmitted() = ???

  override def hmrcAmendment() = ???

  override def microEntityFiling() = ???

  override def statutoryAccountsFiling() = ???

  override def utr() = ???

  override def countryOfRegistration() = ???

  override def generateValues = ???
}
