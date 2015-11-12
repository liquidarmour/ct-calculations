package uk.gov.hmrc.ct.accounts

import uk.gov.hmrc.ct.accounts.calculations.ProfitOrLossCalculator
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.{Calculated, CtBoxIdentifier, CtOptionalInteger}

case class AC27(value: Option[Int]) extends CtBoxIdentifier(name = "Previous Operating profit or loss") with CtOptionalInteger

object AC27 extends Calculated[AC27, AccountsBoxRetriever] with ProfitOrLossCalculator {
  override def calculate(boxRetriever: AccountsBoxRetriever): AC27 = {
    calculatePreviousOperatingProfitOrLoss(ac17= boxRetriever.retrieveAC17(),
                                           ac19 = boxRetriever.retrieveAC19(),
                                           ac21 = boxRetriever.retrieveAC21(),
                                           ac23 = boxRetriever.retrieveAC23())
  }
}
