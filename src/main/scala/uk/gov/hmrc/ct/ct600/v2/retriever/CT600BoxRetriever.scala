/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.ct.ct600.v2.retriever

import uk.gov.hmrc.ct.CATO04
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.retriever.BoxRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v2._
import uk.gov.hmrc.ct.ct600a.v2.retriever.CT600ABoxRetriever

trait CT600BoxRetriever extends BoxRetriever {

  def accountsRetriever: AccountsBoxRetriever

  def computationsRetriever: ComputationsBoxRetriever

  def b38(): B38

  def b39(): B39

  def b42a(): B42a

  def b42b(): B42b

  def b42(): B42 = B42.calculate(this)

  def b84(): B84

  def b91(): B91

  def b155(): B155

  def b1(): B1 = B1(computationsRetriever.cp7)

  def b3(): B3 = B3(computationsRetriever.cp256())

  def b4(): B4 = B4(computationsRetriever.cp257())

  def b5(): B5 = B5(computationsRetriever.cp258())

  def b6(): B6 = B6(computationsRetriever.cp259())

  def b11(): B11 = B11(computationsRetriever.cp511())

  def b14(): B14 = B14(computationsRetriever.cp515())

  def b21(): B21 = B21(computationsRetriever.cp265())

  def b30(): B30 = B30(computationsRetriever.cp264())

  def b35(): B35 = B35(computationsRetriever.cp305())

  def b37(): B37 = B37(computationsRetriever.cp295())

  def b43(): B43 = B43.calculate(computationsRetriever)

  def b44(): B44 = B44.calculate(computationsRetriever)

  def b45(): B45 = B45.calculate(this)

  def b46(): B46 = B46.calculate(this)

  def b46R(): B46R = B46R.calculate(this)

  def b53(): B53 = B53.calculate(computationsRetriever)

  def b54(): B54 = B54.calculate(computationsRetriever)

  def b55(): B55 = B55.calculate(this)

  def b56(): B56 = B56.calculate(this)

  def b56R(): B56R = B56R.calculate(this)

  def b63(): B63 = B63.calculate(this)

  def b64(): B64 = B64(cato04())

  def b65(): B65 = B65.calculate(this)

  def b70(): B70 = B70.calculate(this)

  def b78(): B78 = B78(b65())

  def b79(): B79 = {
    this match {
      case r: CT600ABoxRetriever => B79(r.a13())
      case _ => B79(None)
    }
  }

  def b80(): B80 = {
    this match {
      case r: CT600ABoxRetriever => B80.calculate(r)
      case _ => B80(None)
    }

  }

  def b85(): B85 = B85.calculate(this)

  def b86(): B86 = B86.calculate(this)

  def b92(): B92 = B92.calculate(this)

  def b93(): B93 = B93.calculate(this)

  def b105(): B105 = B105(computationsRetriever.cp668())

  def b106(): B106 = B106(computationsRetriever.cp670())

  def b107(): B107 = B107(computationsRetriever.cp248())

  def b108(): B108 = B108(computationsRetriever.cp247())

  def b118(): B118 = B118(computationsRetriever.cp251())

  def b121(): B121 = B121(computationsRetriever.cp253())

  def b122(): B122 = B122.calculate(computationsRetriever)

  def b172(): B172 = B172(computationsRetriever.cp88())

  def b174(): B174 = B174(computationsRetriever.cp278)

  def cato04(): CATO04 = CATO04.calculate(this)

  def b1000: B1000

  def b1001: B1001 = B1001(accountsRetriever.ac1())
}
