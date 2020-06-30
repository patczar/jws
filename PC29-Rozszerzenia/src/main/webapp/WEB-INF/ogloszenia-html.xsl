<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<style type="text/css">
					<xsl:call-template name="css"/>
				</style>
			</head>
			<body><xsl:apply-templates/></body>
		</html>
	</xsl:template>
	
	<xsl:template match="ogloszenia">
		<xsl:apply-templates>
			<xsl:with-param name="katalog" select="true()"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="ogloszenie">
		<xsl:param name="katalog" select="false()"/>
	<xsl:variable name="photo-path">
		<xsl:choose>
		<xsl:when test="$katalog">
			<xsl:text>ogloszenia/</xsl:text>
			<xsl:value-of select="@id" />
			<xsl:text>/foto</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:text>../</xsl:text>
			<xsl:text>ogloszenia/</xsl:text>
			<xsl:value-of select="@id" />
			<xsl:text>/foto</xsl:text>
		</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
		<div class="ogloszenie">
			<h2><xsl:apply-templates select="tytul"/></h2>
			<div class="foto">
				<img src="{$photo-path}"/>
			</div>
			<p>
				<xsl:apply-templates select="marka"/>
				<xsl:text> </xsl:text>
				<xsl:apply-templates select="model"/>
				<xsl:text> </xsl:text>
				<xsl:apply-templates select="generacja"/>
			</p>
			<p>Rocznik: <b><xsl:value-of select="rocznik"/></b></p>
			<p>Przebieg: <b><xsl:value-of select="przebieg"/></b></p>
			<p class="cena">Cena: <xsl:value-of select="cena"/></p>
			<p class="opis"><xsl:apply-templates select="opis"/></p>
			<p>Silnik: <xsl:apply-templates select="pojemnosc"/>, <xsl:apply-templates select="moc"/>KM</p>
			
		</div>
	</xsl:template>
	
	<xsl:template name="css"><![CDATA[
	
	body {
		background-color: #FFFFDD;
	}
	
	.ogloszenie {
		border: outset 4px #FF4488;
		padding: 0.5em;
		margin: 1em;
		background-color: #DDFFFF;
		min-height: 360px;
		
	}
	.cena {
		font-size: larger;
		font-weight: bold;
	}
	
	div.foto {
		float: right;
	}

	div.foto img {
		max-width: 400px;
	}
	
	]]>
	</xsl:template>
</xsl:stylesheet>