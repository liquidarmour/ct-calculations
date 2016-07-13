/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ct.accounts.frs10x.abridged.calculations

import uk.gov.hmrc.ct.accounts.frs10x.abridged._

trait OperatingProfitOrLossCalculator {

  def calculateAC26(aC16: AC16, aC18: AC18, aC20: AC20): AC26 = {
    AC26(aC16.orZero - aC18.orZero - aC20.orZero)
  }

  def calculateAC27(aC17: AC17, aC19: AC19, aC21: AC21): AC27 = {
    AC27(aC17.orZero - aC19.orZero - aC21.orZero)
  }
}
