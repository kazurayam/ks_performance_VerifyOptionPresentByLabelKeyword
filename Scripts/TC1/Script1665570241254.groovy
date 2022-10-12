import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import com.kazurayam.timekeeper.Measurement
import com.kazurayam.timekeeper.Table
import com.kazurayam.timekeeper.Timekeeper
import java.time.LocalDateTime
import java.time.Duration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path html = projectDir.resolve("fixture.html")
Path report = projectDir.resolve("report.md")
//
Timekeeper tk = new Timekeeper()
Measurement m1 = new Measurement.Builder("How long it took",["Case"]).build()
tk.add(new Table.Builder(m1).build())

WebUI.openBrowser('')
WebUI.setViewPortSize(800,600)
WebUI.navigateToUrl(html.toFile().toURI().toURL().toExternalForm())

TestObject tObj = makeTestObject("//*[@id='combo_facility']")
WebUI.verifyElementPresent(tObj, 10)
LocalDateTime beforeAction = LocalDateTime.now()
WebUI.comment("start verifyOptionPresentByLabel")
//
WebUI.verifyOptionPresentByLabel(tObj, "Label" + GlobalVariable.RANGE_END, false, 5000)
//
LocalDateTime afterAction = LocalDateTime.now()
WebUI.comment("end verifyOptionPresentByLable")
m1.recordDuration(["Case": "verifyOptionPresentByLabel() took"],
		beforeAction, afterAction)
//
WebUI.closeBrowser()

WebUI.comment("took ${Duration.between(beforeAction, afterAction).getSeconds()} seconds")
tk.report(report)


/**
 * 
 * @param xpath
 * @return
 */
TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}