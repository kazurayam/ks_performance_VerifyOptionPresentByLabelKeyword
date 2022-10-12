import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable

String startingPart = """<html>
<head>
<title>ks_performance_VerifyOptionPresentByLabelKeyword</title>
</head>
<body>
<div id="main">
<select id="combo_facility" name="facility">
"""

String trailingPart = """</select>
</div>
</option>
</html>
"""

Path htmlPath = Paths.get(RunConfiguration.getProjectDir()).resolve("fixture.html")

Files.write(htmlPath, startingPart.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

int rangeEnd = 0
if (GlobalVariable.RANGE_END != null) { rangeEnd = GlobalVariable.RANGE_END }

StringBuilder sb = new StringBuilder()
for (int i = 1; i <= rangeEnd; i++) {
	sb.append(String.format("    <option value=\"%d\">Label%d</option>\n", i, i))
}
Files.write(htmlPath, sb.toString().getBytes(), StandardOpenOption.APPEND)


Files.write(htmlPath, trailingPart.getBytes(), StandardOpenOption.APPEND)