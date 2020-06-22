package przyklady.sax;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**Filtr zmienia nazwy elementow na pisane duzymi literami. */
public class FiltrDuzeLitery extends XMLFilterImpl {
  /**Zmienia nazwy elementow na pisane duzymi literami.
   * @see org.xml.sax.helpers.XMLFilterImpl#endElement(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void endElement(String aUri, String aLocalName, String aName) throws SAXException {
    if(this.getContentHandler() != null)
      this.getContentHandler().endElement(aUri, aLocalName.toUpperCase(), aName.toUpperCase());
  }

  /**Zmienia nazwy elementow na pisane duzymi literami.
   * @see org.xml.sax.helpers.XMLFilterImpl#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  @Override
  public void startElement(String aUri, String aLocalName, String aName, Attributes atts) throws SAXException {
    if(this.getContentHandler() != null)
      this.getContentHandler().startElement(aUri, aLocalName.toUpperCase(), aName.toUpperCase(), atts);
  }
}