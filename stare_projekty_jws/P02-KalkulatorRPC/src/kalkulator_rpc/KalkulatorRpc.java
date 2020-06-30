
package kalkulator_rpc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "kalkulator_rpc", targetNamespace = "http://www.example.org/kalkulator_rpc/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface KalkulatorRpc {


    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator_rpc/add")
    @WebResult(name = "wynik", partName = "wynik")
    public int add(
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator_rpc/sub")
    @WebResult(name = "wynik", partName = "wynik")
    public int sub(
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator_rpc/mul")
    @WebResult(name = "wynik", partName = "wynik")
    public int mul(
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param reszta
     * @param iloraz
     */
    @WebMethod(action = "http://www.example.org/kalkulator_rpc/div")
    public void div(
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2,
        @WebParam(name = "iloraz", mode = WebParam.Mode.OUT, partName = "iloraz")
        Holder<Integer> iloraz,
        @WebParam(name = "reszta", mode = WebParam.Mode.OUT, partName = "reszta")
        Holder<Integer> reszta);

    /**
     * 
     * @param liczby
     * @return
     *     returns int
     */
    @WebMethod(action = "http://www.example.org/kalkulator_rpc/suma")
    @WebResult(name = "wynik", partName = "wynik")
    public int suma(
        @WebParam(name = "liczby", partName = "liczby")
        WieleLiczb liczby);

}
