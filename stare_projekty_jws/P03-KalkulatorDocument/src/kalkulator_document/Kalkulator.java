
package kalkulator_document;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "kalkulator", targetNamespace = "http://www.example.org/kalkulator/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Kalkulator {


    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator/add")
    @WebResult(name = "wynik", targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.DwieLiczby")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.Wynik")
    public int add(
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator/sub")
    @WebResult(name = "wynik", targetNamespace = "")
    @RequestWrapper(localName = "sub", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.DwieLiczby")
    @ResponseWrapper(localName = "subResponse", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.Wynik")
    public int sub(
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator/mul")
    @WebResult(name = "wynik", targetNamespace = "")
    @RequestWrapper(localName = "mul", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.DwieLiczby")
    @ResponseWrapper(localName = "mulResponse", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.Wynik")
    public int mul(
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param reszta
     * @param iloraz
     */
    @WebMethod(action = "http://www.example.org/kalkulator/div")
    @RequestWrapper(localName = "div", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.DwieLiczby")
    @ResponseWrapper(localName = "divResponse", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.WynikDzielenia")
    public void div(
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "iloraz", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> iloraz,
        @WebParam(name = "reszta", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> reszta);

    /**
     * 
     * @param liczba
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator/sum")
    @WebResult(name = "wynik", targetNamespace = "")
    @RequestWrapper(localName = "sum", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.WieleLiczb")
    @ResponseWrapper(localName = "sumResponse", targetNamespace = "http://www.example.org/kalkulator/", className = "kalkulator_document.Wynik")
    public int sum(
        @WebParam(name = "liczba", targetNamespace = "")
        List<Integer> liczba);

}
