package uk.gov.hmrc.ct.accounts.frs10x.abridged

import uk.gov.hmrc.ct.accounts.frs10x.AccountsMoneyValidationFixture

class AC18Spec extends AccountsMoneyValidationFixture {

  testAccountsMoneyValidation("AC18", AC18.apply)
}
