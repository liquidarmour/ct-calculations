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
import uk.gov.hmrc.ct.box.retriever.BoxRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v2._
import uk.gov.hmrc.ct.ct600a.v2.retriever.CT600ABoxRetriever

abstract class CT600BoxRetriever(val computationsBoxRetriever: ComputationsBoxRetriever,
                                 val ct600ABoxRetriever: Option[CT600ABoxRetriever]) extends BoxRetriever {

  def b38(): B38

  def b39(): B39

  def b42a(): B42a

  def b42b(): B42b

  def b42(): B42 = B42.calculate(this)

  def b84(): B84

  def b91(): B91

  def b155(): B155

  def b1(): B1 = B1(computationsBoxRetriever.cp7)

  def b3(): B3 = B3(computationsBoxRetriever.cp256())

  def b4(): B4 = B4(computationsBoxRetriever.cp257())

  def b5(): B5 = B5(computationsBoxRetriever.cp258())

  def b6(): B6 = B6(computationsBoxRetriever.cp259())

  def b11(): B11 = B11(computationsBoxRetriever.cp511())

  def b14(): B14 = B14(computationsBoxRetriever.cp515())

  def b21(): B21 = B21(computationsBoxRetriever.cp265())

  def b30(): B30 = B30(computationsBoxRetriever.cp264())

  def b35(): B35 = B35(computationsBoxRetriever.cp305())

  def b37(): B37 = B37(computationsBoxRetriever.cp295())

  def b43(): B43 = B43.calculate(computationsBoxRetriever)

  def b44(): B44 = B44.calculate(computationsBoxRetriever)

  def b45(): B45 = B45.calculate(this)

  def b46(): B46 = B46.calculate(this)

  def b46R(): B46R = B46R.calculate(this)

  def b53(): B53 = B53.calculate(computationsBoxRetriever)

  def b54(): B54 = B54.calculate(computationsBoxRetriever)

  def b55(): B55 = B55.calculate(this)

  def b56(): B56 = B56.calculate(this)

  def b56R(): B56R = B56R.calculate(this)

  def b63(): B63 = B63.calculate(this)

  def b64(): B64 = B64(cato04())

  def b65(): B65 = B65.calculate(this)

  def b70(): B70 = B70.calculate(this)

  def b78(): B78 = B78(b65())

  def b79(): B79 = ct600ABoxRetriever.map { retriever =>
    B79(retriever.a13())
  }.getOrElse(B79(None))

  def b80(): B80 = ct600ABoxRetriever.map { retriever =>
    B80.calculate(retriever)
  }.getOrElse(B80(None))

  def b85(): B85 = B85.calculate(this)

  def b86(): B86 = B86.calculate(this)

  def b92(): B92 = B92.calculate(this)

  def b93(): B93 = B93.calculate(this)

  def b105(): B105 = B105(computationsBoxRetriever.cp668())

  def b106(): B106 = B106(computationsBoxRetriever.cp670())

  def b107(): B107 = B107(computationsBoxRetriever.cp248())

  def b108(): B108 = B108(computationsBoxRetriever.cp247())

  def b118(): B118 = B118(computationsBoxRetriever.cp251())

  def b121(): B121 = B121(computationsBoxRetriever.cp253())

  def b122(): B122 = B122.calculate(computationsBoxRetriever)

  def b172(): B172 = B172(computationsBoxRetriever.cp88())

  def b174(): B174 = B174(computationsBoxRetriever.cp278)

  def cato04(): CATO04 = CATO04.calculate(this)

  def b1000: B1000

  def b1001: B1001 = B1001(computationsBoxRetriever.accountsBoxRetriever.ac1())
}
