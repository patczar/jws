<?xml version="1.0" encoding="utf-8"?>
<!-- Przykladowy arkusz XSL tlumaczacy dokument do XSL-FO, czyli w praktyce do PDF. -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:output method="xml" encoding="utf-8"/>

	<xsl:template match="/">
		<fo:root font-family="Arial">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4"
						page-width="210mm" page-height="297mm"
						margin-top="1cm"   margin-bottom="1cm"
						margin-left="1.5cm"  margin-right="1.5cm">
					<fo:region-body   margin="2cm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4">
				<fo:flow flow-name="xsl-region-body">
					<xsl:apply-templates />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template match="ogloszenie">
		<fo:block-container space-before.minimum="0.5em"
			page-break-inside="avoid">
			<fo:block>Ogłoszenie nr <xsl:value-of select="@id"/>.</fo:block>
			<fo:block-container margin="1em" border-style="solid" border-width="2.5pt" padding="3pt" border-color="#2233AA">
              <fo:block font-weight="bold" font-size="14pt" margin-bottom="1em" color="#004444">
                <xsl:apply-templates select="tytul"/>
              </fo:block>
              <fo:block font-weight="bold" font-size="14pt" margin-bottom="1em">
                <xsl:apply-templates select="marka"/>
              <xsl:text> </xsl:text>
                <xsl:apply-templates select="model"/>
              <xsl:text> </xsl:text>
                <xsl:apply-templates select="generacja"/>
              </fo:block>
              <fo:block>Rocznik: <xsl:apply-templates select="rocznik"/></fo:block>
              <fo:block font-weight="bold" color="green">
                <xsl:text>Cena: </xsl:text><xsl:apply-templates select="cena"/>
              </fo:block>
              <fo:block>Kolor: <xsl:apply-templates select="kolor"/></fo:block>
              <fo:block>Silnik: <xsl:apply-templates select="pojemnosc"/>
              	<xsl:text> </xsl:text><xsl:apply-templates select="moc"/>KM</fo:block>

			<xsl:apply-templates select="sprzedawca"/>
			<fo:block font-style="italic" font-size="smaller"><xsl:apply-templates select="wystawione"/></fo:block>
              
			</fo:block-container>
			
		</fo:block-container>
	</xsl:template>

	<xsl:template match="sprzedawca">
      <fo:block margin-top="2em">Sprzedawca:</fo:block>
        <fo:block border-style="solid" border-width="1.5pt" border-color="#888888" background-color="#FFFFDD" padding="1mm 4mm">
		<fo:block margin-top="0.5em">
			<xsl:apply-templates select="nazwa"/>
		</fo:block>
        <fo:block margin-top="0.5em">
            <xsl:apply-templates select="ulica"/>
        </fo:block>
        <fo:block margin-top="0.1em">
            <xsl:apply-templates select="kod-pocztowy"/>
            <xsl:text> </xsl:text>
            <xsl:apply-templates select="miasto"/>
        </fo:block>
        <xsl:apply-templates select="telefon"/>
        <xsl:apply-templates select="email"/>
		</fo:block>
	</xsl:template>

	<xsl:template match="email">
	<fo:block>email: <xsl:apply-templates/></fo:block> 
    </xsl:template>

	<xsl:template match="telefon">
	<fo:block>tel: <xsl:apply-templates/></fo:block> 
    </xsl:template>
</xsl:stylesheet>
