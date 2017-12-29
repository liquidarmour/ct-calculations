package uk.gov.hmrc.ct.accounts.retriever

import uk.gov.hmrc.ct.box.retriever.{BoxRetriever, FilingAttributesBoxValueRetriever}
import uk.gov.hmrc.ct.{CoHoAccountsApprovalRequired, HmrcAccountsApprovalRequired}

trait AccountsApprovalRequiredBoxRetriever extends BoxRetriever {

  def filingAttributesBoxValueRetriever: FilingAttributesBoxValueRetriever

  def coHoAccountsApprovalRequired(): CoHoAccountsApprovalRequired

  def hmrcAccountsApprovalRequired(): HmrcAccountsApprovalRequired
}
