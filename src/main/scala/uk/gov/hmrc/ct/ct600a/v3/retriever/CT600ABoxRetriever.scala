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

package uk.gov.hmrc.ct.ct600a.v3.retriever

import uk.gov.hmrc.ct.box.retriever.BoxRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v3.retriever.HmrcCompanyNameRetriever
import uk.gov.hmrc.ct.ct600a.v3._

abstract class CT600ABoxRetriever(val computationsBoxRetriever: ComputationsBoxRetriever,
                                  val hmrcCompanyNameRetriever: HmrcCompanyNameRetriever) extends BoxRetriever {

  def lp04(): LP04

  def lpq01(): LPQ01 = LPQ01.calculate(this)

  def lpq03(): LPQ03

  def lpq04(): LPQ04

  def lpq07(): LPQ07

  def lpq08(): LPQ08

  def lpq10(): LPQ10

  def loansToParticipators(): LoansToParticipators

  def a1(): A1 = A1(hmrcCompanyNameRetriever.companyName)

  def a2(): A2 = A2(computationsBoxRetriever.filingAttributesBoxValueRetriever.utr())

  def a3(): A3 = A3(computationsBoxRetriever.cp1())

  def a4(): A4 = A4(computationsBoxRetriever.cp2())

  def a5(): A5

  def a15(): A15 = A15.calculate(this)
  
  def a20(): A20 = A20.calculate(this, computationsBoxRetriever)

  def a30(): A30 = A30.calculate(this, computationsBoxRetriever)

  def a35(): A35 = A35.calculate(this, computationsBoxRetriever)

  def a40(): A40 = A40.calculate(this)

  def a45(): A45 = A45.calculate(this, computationsBoxRetriever)

  def a55(): A55 = A55.calculate(this, computationsBoxRetriever)

  def a55Inverse(): A55Inverse = A55Inverse.calculate(this, computationsBoxRetriever)

  def a60(): A60 = A60.calculate(this)

  def a60Inverse(): A60Inverse = A60Inverse.calculate(this, computationsBoxRetriever)
  
  def a65(): A65 = A65.calculate(this)

  def a65Inverse(): A65Inverse = A65Inverse.calculate(this)

  def a70(): A70 = A70.calculate(this, computationsBoxRetriever)

  def a70Inverse(): A70Inverse = A70Inverse.calculate(this, computationsBoxRetriever)

  def a75(): A75 = A75.calculate(this)

  def a80(): A80 = A80.calculate(this)
}
