package uk.gov.hmrc.ct.accounts

import uk.gov.hmrc.ct.box.{AttachmentDetails, CtBoxIdentifier, CtValue, Input}

case class UploadedAccounts(value: AttachmentDetails) extends CtBoxIdentifier("Description of file uploaded as accounts")
                                                      with CtValue[AttachmentDetails]
                                                      with Input
