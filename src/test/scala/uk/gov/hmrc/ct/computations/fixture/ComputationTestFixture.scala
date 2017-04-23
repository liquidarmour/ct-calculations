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

package uk.gov.hmrc.ct.computations.fixture

import org.mockito.Mockito.when
import uk.gov.hmrc.ct.{CATO02, CATO21, CATO22}
import uk.gov.hmrc.ct.computations._
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import org.scalatest.mock.MockitoSugar

trait ComputationsTestFixture extends MockitoSugar {

  val computationsBoxRetriever = mock[ComputationsBoxRetriever]

  def withBox(cpq8: CPQ8) = when(computationsBoxRetriever.cpQ8()).thenReturn(cpq8)

  def withBox(cp78: CP78) = when(computationsBoxRetriever.cp78()).thenReturn(cp78)

  def withBox(cp79: CP79) = when(computationsBoxRetriever.cp79()).thenReturn(cp79)

  def withBox(cp80: CP80) = when(computationsBoxRetriever.cp80()).thenReturn(cp80)

  def withBox(cp82: CP82) = when(computationsBoxRetriever.cp82()).thenReturn(cp82)

  def withBox(cp83: CP83) = when(computationsBoxRetriever.cp83()).thenReturn(cp83)

  def withBox(cp87Input: CP87Input) = when(computationsBoxRetriever.cp87Input()).thenReturn(cp87Input)

  def withBox(cp666: CP666) = when(computationsBoxRetriever.cp666()).thenReturn(cp666)

  def withBox(cp667: CP667) = when(computationsBoxRetriever.cp667()).thenReturn(cp667)

  def withBox(cp672: CP672) = when(computationsBoxRetriever.cp672()).thenReturn(cp672)

  def withBox(cpAaux1: CPAux1) = when(computationsBoxRetriever.cpAux1()).thenReturn(cpAaux1)

  def withBox(cpAux2: CPAux2) = when(computationsBoxRetriever.cpAux2()).thenReturn(cpAux2)

  def withBox(cpAux3: CPAux3) = when(computationsBoxRetriever.cpAux3()).thenReturn(cpAux3)

  def withBox(cato02: CATO02) = when(computationsBoxRetriever.cato02()).thenReturn(cato02)

  def withBox(cato21: CATO21) = when(computationsBoxRetriever.cato21()).thenReturn(cato21)

  def withBox(cato22: CATO22) = when(computationsBoxRetriever.cato22()).thenReturn(cato22)

  def withBox(lec01: LEC01) = when(computationsBoxRetriever.lec01()).thenReturn(lec01)

  def withBox(cp668: CP668) = when(computationsBoxRetriever.cp668()).thenReturn(cp668)

  def withBox(cp670: CP670) = when(computationsBoxRetriever.cp670()).thenReturn(cp670)

  def withBox(cp88: CP88) = when(computationsBoxRetriever.cp88()).thenReturn(cp88)

  def withBox(cp89: CP89) = when(computationsBoxRetriever.cp89()).thenReturn(cp89)

  def withBox(cp186: CP186) = when(computationsBoxRetriever.cp186()).thenReturn(cp186)

  def withBox(cp91: CP91) = when(computationsBoxRetriever.cp91()).thenReturn(cp91)

  def withBox(cp671: CP671) = when(computationsBoxRetriever.cp671()).thenReturn(cp671)

  def withBox(cp92: CP92) = when(computationsBoxRetriever.cp92()).thenReturn(cp92)

  def withBox(cp669: CP669) = when(computationsBoxRetriever.cp669()).thenReturn(cp669)

  def withBox(cp84: CP84) = when(computationsBoxRetriever.cp84()).thenReturn(cp84)

  def withBox(cp673: CP673) = when(computationsBoxRetriever.cp673()).thenReturn(cp673)

  def withBox(cp674: CP674) = when(computationsBoxRetriever.cp674()).thenReturn(cp674)

}