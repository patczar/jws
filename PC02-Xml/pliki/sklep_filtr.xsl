<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:param name="kategoria" />

	<xsl:template match="/*">
		<xsl:copy>
			<xsl:copy-of select="kategoria[@id-kategorii = $kategoria or not($kategoria)]"/>
			<xsl:copy-of select="towar[@id-kategorii = $kategoria or not($kategoria)]"/>
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>