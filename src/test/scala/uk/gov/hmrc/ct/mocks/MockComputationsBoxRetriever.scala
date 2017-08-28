package uk.gov.hmrc.ct.mocks

import uk.gov.hmrc.ct.{CATO02, CATO20, CATO21, CATO22}
import uk.gov.hmrc.ct.computations._
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

object MockComputationsBoxRetriever {
  def apply(lec01Param: List[Car] = List.empty,
            cpq8Param: Option[Boolean] = None,
            cp78Param: Option[Int] = None,
            cp79Param: Option[Int] = None,
            cp80Param: Option[Int] = None,
            cp82Param: Option[Int] = None,
            cp83Param: Option[Int] = None,
            cp84Param: Option[Int] = None,
            cp87InputParam: Option[Int] = None,
            cp88Param: Option[Int] = None,
            cp89Param: Option[Int] = None,
            cp666Param: Option[Int] = None,
            cp667Param: Option[Int] = None,
            cp668Param: Option[Int] = None,
            cp672Param: Option[Int] = None,
            cp673Param: Option[Int] = None,
            cp674Param: Option[Int] = None,
            cato02Param: Int = 0,
            cato20Param: Int = 0,
            cato21Param: Int = 0,
            cato22Param: Int = 0,
            cpAux1Param: Int = 0,
            cpAux2Param: Int = 0,
            cpAux3Param: Int = 0): MockComputationsBoxRetriever = {
    val baseBoxRetriever = MockFilingAttributesBoxRetriever()
    new MockComputationsBoxRetriever(lec01Param = lec01Param,
      cpq8Param = cpq8Param,
      cp78Param = cp78Param,
      cp79Param = cp79Param,
      cp80Param = cp80Param,
      cp82Param = cp82Param,
      cp83Param = cp83Param,
      cp84Param = cp84Param,
      cp87InputParam = cp87InputParam,
      cp88Param = cp88Param,
      cp89Param = cp89Param,
      cp666Param = cp666Param,
      cp667Param = cp667Param,
      cp668Param = cp668Param,
      cp672Param = cp672Param,
      cp673Param = cp673Param,
      cp674Param = cp674Param,
      cato02Param = cato02Param,
      cato20Param = cato20Param,
      cato21Param = cato21Param,
      cato22Param = cato22Param,
      cpAux1Param = cpAux1Param,
      cpAux2Param = cpAux2Param,
      cpAux3Param = cpAux3Param)(
      baseBoxRetriever,
      MockAccountsBoxRetriever(baseBoxRetriever)
    )
  }
}

class MockComputationsBoxRetriever(lec01Param: List[Car] = List.empty,
                                   cpq8Param: Option[Boolean] = None,
                                   cp78Param: Option[Int] = None,
                                   cp79Param: Option[Int] = None,
                                   cp80Param: Option[Int] = None,
                                   cp82Param: Option[Int] = None,
                                   cp83Param: Option[Int] = None,
                                   cp84Param: Option[Int] = None,
                                   cp87InputParam: Option[Int] = None,
                                   cp88Param: Option[Int] = None,
                                   cp89Param: Option[Int] = None,
                                   cp666Param: Option[Int] = None,
                                   cp667Param: Option[Int] = None,
                                   cp668Param: Option[Int] = None,
                                   cp672Param: Option[Int] = None,
                                   cp673Param: Option[Int] = None,
                                   cp674Param: Option[Int] = None,
                                   cato02Param: Int = 0,
                                   cato20Param: Int = 0,
                                   cato21Param: Int = 0,
                                   cato22Param: Int = 0,
                                   cpAux1Param: Int = 0,
                                   cpAux2Param: Int = 0,
                                   cpAux3Param: Int = 0)(mockFilingAttributesBoxRetriever: MockFilingAttributesBoxRetriever,
                                   mockAccountsBoxRetriever: MockAccountsBoxRetriever)
  extends ComputationsBoxRetriever(mockFilingAttributesBoxRetriever, mockAccountsBoxRetriever) {

  override def cato02() = CATO02(cato02Param)

  override def cato20() = CATO20(cato20Param)

  override def cato21() = CATO21(cato21Param)

  override def cato22() = CATO22(cato22Param)

  override def cpAux1() = CPAux1(cpAux1Param)

  override def cpAux2() = CPAux2(cpAux2Param)

  override def cpAux3() = CPAux3(cpAux3Param)

  override def ap1() = ???

  override def ap2() = ???

  override def ap3() = ???

  override def cp1() = ???

  override def cp2() = ???

  override def cp7() = ???

  override def cp8() = ???

  override def cp15() = ???

  override def cp16() = ???

  override def cp17() = ???

  override def cp18() = ???

  override def cp19() = ???

  override def cp20() = ???

  override def cp21() = ???

  override def cp22() = ???

  override def cp23() = ???

  override def cp24() = ???

  override def cp25() = ???

  override def cp26() = ???

  override def cp27() = ???

  override def cp28() = ???

  override def cp29() = ???

  override def cp30() = ???

  override def cp31() = ???

  override def cp32() = ???

  override def cp33() = ???

  override def cp34() = ???

  override def cp35() = ???

  override def cp36() = ???

  override def cp37() = ???

  override def cp43() = ???

  override def cp46() = ???

  override def cp47() = ???

  override def cp48() = ???

  override def cp49() = ???

  override def cp51() = ???

  override def cp52() = ???

  override def cp53() = ???

  override def cp55() = ???

  override def cp57() = ???

  override def cp78() = CP78(cp78Param)

  override def cp79() = CP79(cp79Param)

  override def cp80() = CP80(cp80Param)

  override def cp82() = CP82(cp82Param)

  override def cp83() = CP83(cp83Param)

  override def cp84() = CP84(cp84Param)

  override def cp85() = ???

  override def cp86() = ???

  override def cp87Input() = CP87Input(cp87InputParam)

  override def cp88() = CP88(cp88Param)

  override def cp89() = CP89(cp89Param)

  override def cp91Input() = ???

  override def cp281() = ???

  override def cp285() = ???

  override def cp286() = ???

  override def cp287() = ???

  override def cp301() = ???

  override def cp302() = ???

  override def cp303() = ???

  override def cp3010() = ???

  override def cp3020() = ???

  override def cp3030() = ???

  override def cp501() = ???

  override def cp502() = ???

  override def cp503() = ???

  override def cp510() = ???

  override def cp252() = ???

  override def cp666() = CP666(cp666Param)

  override def cp667() = CP667(cp667Param)

  override def cp668() = CP668(cp668Param)

  override def cp672() = CP672(cp672Param)

  override def cp673() = CP673(cp673Param)

  override def cp674() = CP674(cp674Param)

  override def cpQ1000() = ???

  override def cpQ7() = ???

  override def cpQ8() = CPQ8(cpq8Param)

  override def cpQ10() = ???

  override def cpQ17() = ???

  override def cpQ18() = ???

  override def cpQ19() = ???

  override def cpQ20() = ???

  override def cpQ21() = ???

  override def cpQ321() = ???

  override def lec01() = LEC01(lec01Param)

  override def generateValues = ???
}
