import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

Path html = Paths.get(RunConfiguration.getProjectDir()).resolve("fixture.html")

WebUI.openBrowser('')
WebUI.setViewPortSize(800,600)
WebUI.navigateToUrl(html.toFile().toURI().toURL().toExternalForm())

TestObject tObj = makeTestObject("//*[@id='combo_facility']")
WebUI.verifyElementPresent(tObj, 10)

WebUI.comment("start verifyOptionPresentByLabel")
WebUI.verifyOptionPresentByLabel(tObj, "Label" + GlobalVariable.RANGE_END, false, 5000)
WebUI.comment("end verifyOptionPresentByLable")

WebUI.delay(3)
WebUI.closeBrowser()

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}