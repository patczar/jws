<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" encoding="UTF-8"/>
	<xsl:param name="kategoria" />
	
	<xsl:decimal-format name="format-polski"
		decimal-separator=","
		grouping-separator=" "/>
	
	

	<xsl:template match="/">
		<html>
			<head>
				<title>Sklep</title>
				<style type="text/css">
.towar, .kategoria {
	display: block;
	margin: 1em;
	padding: 0.5em;
	border-style: solid;
	border-with: 2px;
}

.towar {
	border-color: green;
}

.kategoria {
	border-color: red;
}

h2 {
	font-size: 1.2em;
	font-family: 'Arial', sans-serif;
	margin: 0 0 0.75em 0;
}

.kategoria h2 {
	font-style: italic;
	font-weight: normal;
}

.towar h2 {
	font-style: normal;
	font-weight: bold;
}

dfn {
	font-weight: bold;
	font-style: normal;
}

span.rem {
	color: #888888;
}

				</style>
			</head>
			<body>
				Towary z kategorii: <xsl:value-of select="$kategoria"/>
				<xsl:apply-templates select="/sklep/kategoria[@id-kategorii = $kategoria or not($kategoria)]"/>
				<xsl:apply-templates select="/sklep/towar[@id-kategorii = $kategoria or not($kategoria)]"/>
			</body>
		</html>
	</xsl:template>
	
	
	<xsl:template match="kategoria">
		<div class="kategoria" id="K-{@id-kategorii}">
			<xsl:apply-templates select="nazwa"/>
			<xsl:apply-templates select="opis"/>
			
			<p>Towary:
			<xsl:for-each select="/sklep/towar[@id-kategorii = current()/@id-kategorii]">
				<a href="#T-{@id-towaru}"><xsl:value-of select="nazwa"/></a>
				<xsl:if test="position() != last()">, </xsl:if>
			</xsl:for-each>
			
			</p> 
		</div>
	</xsl:template>
	
	<xsl:template match="towar">
		<div class="towar" id="T-{@id-towaru}">
			<xsl:apply-templates select="nazwa"/>
			Kategoria: 
			<a href="#K-{@id-kategorii}">
				<xsl:value-of select="/sklep/kategoria[@id-kategorii = current()/@id-kategorii]/nazwa"/>
			</a>

			<p>
			<xsl:if test="cena-promocyjna">
				PROMOCJA<br/>
			</xsl:if>
			
			Cena netto: <xsl:value-of select="format-number(cena, '0,00', 'format-polski')"/>
			<br/>
			Cena brutto: <xsl:value-of select="format-number(cena-brutto, '0,00', 'format-polski')"/>
			</p>

			<xsl:apply-templates select="opis"/>
		</div>
	</xsl:template>
	
	<xsl:template match="nazwa">
		<h2>
			<xsl:apply-templates />
		</h2>
	</xsl:template>
	
	<xsl:template match="opis">
		<p>
			<xsl:apply-templates />
		</p>
	</xsl:template>
	
	<xsl:template match="term">
		<dfn>
			<xsl:apply-templates />
		</dfn>
	</xsl:template>
	
	<xsl:template match="em">
		<em>
			<xsl:apply-templates />
		</em>
	</xsl:template>
		
	<xsl:template match="rem">
		<span class="rem">
			<xsl:apply-templates />
		</span>
	</xsl:template>
	
	<xsl:template match="link">
		<a href="{@href}">
			<xsl:apply-templates />
		</a>
	</xsl:template>
</xsl:stylesheet>