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

package uk.gov.hmrc.ct.computations

import org.joda.time.LocalDate
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.mocks.MockComputationsBoxRetriever

class LowEmissionCarsAcceptanceCriteriaSpec extends WordSpec with Matchers {

  /*    CPQ8        Ceased Trading
        CP78        Written down value brought forward
        CP666       Written down value of Special Rate Pool brought forward
        CP79        First year allowance (FYA) expenditure
        CP80        Other first year allowance expenditure
        CP82        Additions Qualifying for writing down allowance
        CP83        Expenditure qualifying for Annual Investment Allowance (AIA)
        CP84        Disposal proceeds
        CP673       Market value of unsold assets
        CP667       Proceeds from disposals from special rate pool
        CP672       Proceeds from disposals from main pool
        CPaux1      FYA Rate Pool
        CPaux2      Main Rate Pool
        CPaux3      Special Rate Pool
        CP87_Input  FYA Claimed
        CP87        ""
        CP88        AIA Claimed
        CP89        Written down allowance calimed
        CP668       Written down allowance claimed from special rate pool
        CP90        Balance Allowance
        CP186       Total Allowances claimed
        CP91        Main pool balancing charge
        CP671       Balancing charge
        CP670       Special Rate pool balancing charge
        CP92        Main pool written down value carried forward
        CP669       Special Rate pool written down value carried forward
     */


  "Low Emmission Cars calculations" should {

    "calculate values for Companies Still Trading" when {
      val companiesStillTrading =
        Table(
          ("Scenario",
            "LEC01",
            "CPQ8", "CP78", "CP666", "CP79", "CP80", "CP82", "CP83", "CP667", "CP672", "CP87_Input","CP668", "CP670", "CP88", "CP89", "CP186", "CP91", "CP671", "CP92", "CP669"),

          ("Scenario 1 - Company still trading, some AIA can be claimed from the main pool, no disposals were made, user claims some but not all they're entitled to.",
            List(mainRatePoolCar(100)),
            Some(false), Some(50), None, Some(0), Some(0), Some(15), Some(40), None, Some(0), Some(0),Some(0), Some(0), Some(0), Some(30), Some(30), None, None, Some(175), Some(0)),

          ("Scenario 2 - Company still trading, some AIA, main pool allowance can be claimed from the main pool, there have been disposals on the main pool, but lower than the value of the pool. " +
            "User claims some of the allowance but not all they're entitled to.",
            List(mainRatePoolCar(100)),
            Some(false), Some(50), None, Some(0), Some(0), Some(15), Some(40), None, Some(48), Some(0), None, Some(0), Some(0), Some(21), Some(21), None, None, Some(136), Some(0)),

          ("Scenario 3 - Company still trading, some AIA, there have been disposals on the main pool, higher than the value of the pool " +
            "(there will be balancing charges). User can't claim anything from the main pool.",
            List(mainRatePoolCar(270)),
            Some(false), Some(50), None, Some(0), Some(0), Some(47), Some(69), None, Some(3000), Some(0),None, Some(0), Some(0), None, Some(0), Some(2564), Some(2564), Some(0), Some(0)),

          ("Scenario 4 - Company still trading, some AIA and FYA, there have been disposals on the main pool and secondary pool, " +
            "higher than the value of both pools (there will be balancing charges). User can't claim anything from the main pool.",
            List(mainRatePoolCar(270), specialRatePoolCar(594)),
            Some(false), Some(11), Some(98), Some(31), Some(0),  Some(43), Some(77), Some(2111), Some(3500), Some(0), None, Some(1419), None, None, Some(0), Some(3068), Some(3068), Some(0), Some(0)),

          ("Scenario 5 - Company still trading, some AIA and FYA (also FYA cars), there have been disposals on the main pool and secondary pool, " +
            "the main disposals higher than the value of the main pool but the special rate disposals still leave some remaining special rate allowance " +
            "to be claimed (there will be only balancing charges on the main pool). user can't claim anything from the main pool but can claim from the secondary pool.",
            List(fyaRatePoolCar(25), mainRatePoolCar(50), specialRatePoolCar(600)),
            Some(false), Some(11), Some(98), Some(30), Some(1),  Some(43), Some(77), Some(4), Some(3500), Some(21), Some(20), Some(0), Some(64), None, Some(85), Some(3348), Some(3348), Some(0), Some(674))
        )

      forAll(companiesStillTrading) {
        (scenario: String,
         lec01: List[Car],
         cpq8: Option[Boolean],
         cp78: Option[Int],
         cp666: Option[Int],
         cp79: Option[Int],
         cp80: Option[Int],
         cp82: Option[Int],
         cp83: Option[Int],
         cp667: Option[Int],
         cp672: Option[Int],
         cp87_Input: Option[Int],
         cp668: Option[Int],
         cp670: Option[Int],
         cp88: Option[Int],
         cp89: Option[Int],
         cp186: Option[Int],
         cp91: Option[Int],
         cp671: Option[Int],
         cp92: Option[Int],
         cp669: Option[Int]) => {

          s"$scenario" in {

            val retriever = MockComputationsBoxRetriever(
              lec01Param = lec01,
              cpq8Param = cpq8,
              cp78Param = cp78,
              cp79Param = cp79,
              cp80Param = cp80,
              cp82Param = cp82,
              cp83Param = cp83,
              cp87InputParam = cp87_Input,
              cp88Param = cp88,
              cp89Param = cp89,
              cp666Param = cp666,
              cp667Param = cp667,
              cp668Param = cp668,
              cp672Param = cp672
            )
            /*
writtenDownValue(cpq8 = boxRetriever.cpQ8(),
                     cp78 = boxRetriever.cp78(),
                     cp82 = boxRetriever.cp82(),
                     cp89 = boxRetriever.cp89(),
                     cp91 = boxRetriever.cp91(),
                     cp672 = boxRetriever.cp672(),
                     cato20 = boxRetriever.cato20(),
                     cpAux2 = boxRetriever.cpAux2())             */

            assert(retriever.cp91().value equals cp91, clue("CP91", retriever.cp91().value, cp91))
            assert(retriever.cp186().value equals cp186, clue("CP186", retriever.cp186().value, cp186))
            assert(retriever.cp669().value equals cp669, clue("CP669", retriever.cp669().value, cp669))
            assert(retriever.cp670().value equals cp670, clue("CP670", retriever.cp670().value, cp670))
            assert(retriever.cp671().value equals cp671, clue("CP671", retriever.cp671().value, cp671))

            assert(retriever.cp92().value equals cp92, clue("CP92", retriever.cp92().value, cp92))
          }
        }
      }
    }

    val companiesNoLongerTrading =
      Table(
        ("Scenario",
          "LEC01",
          "CPQ8", "CP78", "CP666", "CP674", "CP79", "CP80", "CP82", "CP83", "CP84", "CP673",
          "CP90", "CP186", "CP91", "CP671", "CP92", "CP669"),

        ("Scenario 6 - Company not trading, higher disposals than allowances: balancing charges.",
          List(fyaRatePoolCar(20), mainRatePoolCar(30), specialRatePoolCar(40)),
          Some(true),
          Some(20), Some(30), Some(30),Some(0), None, None, None, Some(1000), Some(300),
          Some(0), Some(0), Some(1130), Some(1130), Some(0), Some(0)),

        ("Scenario 7 - Company not trading, lower disposals than allowances: balance allowances.",
          List(fyaRatePoolCar(20), mainRatePoolCar(30), specialRatePoolCar(40)),
          Some(true),
          Some(500), Some(600), Some(1000),Some(0), None, None, None, Some(10), Some(5),
          Some(2175), Some(2175), Some(0), Some(0), Some(0), Some(0))
      )

    "calculate values for Companies No Longer Trading" when {
      forAll(companiesNoLongerTrading) {
        (scenario: String,
         lec01: List[Car],
         cpq8: Option[Boolean],
         cp78: Option[Int],
         cp666: Option[Int],
         cp674: Option[Int],
         cp79: Option[Int],
         cp80: Option[Int],
         cp82: Option[Int],
         cp83: Option[Int],
         cp84: Option[Int],
         cp673: Option[Int],
         cp90: Option[Int],
         cp186: Option[Int],
         cp91: Option[Int],
         cp671: Option[Int],
         cp92: Option[Int],
         cp669: Option[Int]) => {

          s"$scenario" in {

            val retriever = MockComputationsBoxRetriever(
              lec01Param = lec01,
              cpq8Param = cpq8,
              cp78Param = cp78,
              cp79Param = cp79,
              cp80Param = cp80,
              cp82Param = cp82,
              cp83Param = cp83,
              cp84Param = cp84,
              cp666Param = cp666,
              cp673Param = cp673,
              cp674Param = cp674
            )

            assert(retriever.cp90().value equals cp90, clue("CP90", retriever.cp90().value, cp90))
            assert(retriever.cp91().value equals cp91, clue("CP91", retriever.cp91().value, cp91))
            assert(retriever.cp92().value equals cp92, clue("CP92", retriever.cp92().value, cp92))
            assert(retriever.cp186().value equals cp186, clue("CP186", retriever.cp186().value, cp186))
            assert(retriever.cp669().value equals cp669, clue("CP669", retriever.cp669().value, cp669))
            assert(retriever.cp671().value equals cp671, clue("CP671", retriever.cp671().value, cp671))
          }
        }
      }

    }

  }

  private def clue(boxId: String, calcValue: Option[Int], expectedValue: Option[Int]) = s"Calculated value $boxId of $calcValue was not equal to expected $expectedValue"

  private def fyaRatePoolCar(value: Int) = Car(regNumber = "ABC123Z", isNew = true, price = value, emissions = 110, dateOfPurchase = new LocalDate("2013-03-31"))

  private def mainRatePoolCar(value: Int) = Car(regNumber = "XYZ123A", isNew = true, price = value, emissions = 160, dateOfPurchase = new LocalDate("2009-04-01"))

  private def specialRatePoolCar(value: Int) = Car(regNumber = "XYZ789C", isNew = true, price = value, emissions = 161, dateOfPurchase = new LocalDate("2013-03-31"))

}
