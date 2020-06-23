package kalkulator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.6
 * 2020-06-23T10:03:43.490+02:00
 * Generated source version: 3.3.6
 *
 */
@WebService(targetNamespace = "http://www.example.org/KalkulatorRPC/", name = "KalkulatorRPC")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface KalkulatorRPC {

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/sub")
    @WebResult(name = "result", targetNamespace = "http://www.example.org/KalkulatorRPC/", partName = "result")
    public int sub(

        @WebParam(partName = "arg1", name = "arg1")
        int arg1,
        @WebParam(partName = "arg2", name = "arg2")
        int arg2
    );

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/add")
    @WebResult(name = "result", targetNamespace = "http://www.example.org/KalkulatorRPC/", partName = "result")
    public int add(

        @WebParam(partName = "arg1", name = "arg1")
        int arg1,
        @WebParam(partName = "arg2", name = "arg2")
        int arg2
    );

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/div")
    public void div(

        @WebParam(partName = "arg1", name = "arg1")
        int arg1,
        @WebParam(partName = "arg2", name = "arg2")
        int arg2,
        @WebParam(partName = "quotient", mode = WebParam.Mode.OUT, name = "quotient")
        javax.xml.ws.Holder<java.lang.Integer> quotient,
        @WebParam(partName = "rest", mode = WebParam.Mode.OUT, name = "rest")
        javax.xml.ws.Holder<java.lang.Integer> rest
    );

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/avg")
    @WebResult(name = "result", targetNamespace = "http://www.example.org/KalkulatorRPC/", partName = "result")
    public float avg(

        @WebParam(partName = "args", name = "args")
        ListaIntow args
    );

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/mul")
    @WebResult(name = "result", targetNamespace = "http://www.example.org/KalkulatorRPC/", partName = "result")
    public int mul(

        @WebParam(partName = "arg1", name = "arg1")
        int arg1,
        @WebParam(partName = "arg2", name = "arg2")
        int arg2
    );

    @WebMethod(action = "http://www.example.org/KalkulatorRPC/inc")
    @WebResult(name = "result", targetNamespace = "http://www.example.org/KalkulatorRPC/", partName = "result")
    public int inc(

        @WebParam(partName = "arg", name = "arg")
        int arg
    );
}