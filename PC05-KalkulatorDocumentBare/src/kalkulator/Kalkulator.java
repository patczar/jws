package kalkulator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.6
 * 2020-06-23T22:59:57.158+02:00
 * Generated source version: 3.3.6
 *
 */
@WebService(targetNamespace = "http://www.example.org/Kalkulator/", name = "Kalkulator")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Kalkulator {

    @WebMethod(action = "http://www.example.org/Kalkulator/sub")
    @WebResult(name = "subOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public SubOutput sub(

        @WebParam(partName = "parameters", name = "subInput", targetNamespace = "http://www.example.org/Kalkulator/")
        SubInput parameters
    );

    @WebMethod(action = "http://www.example.org/Kalkulator/add")
    @WebResult(name = "addOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public AddOutput add(

        @WebParam(partName = "parameters", name = "addInput", targetNamespace = "http://www.example.org/Kalkulator/")
        AddInput parameters
    );

    @WebMethod(action = "http://www.example.org/Kalkulator/div")
    @WebResult(name = "divOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public DivOutput div(

        @WebParam(partName = "parameters", name = "divInput", targetNamespace = "http://www.example.org/Kalkulator/")
        DivInput parameters
    );

    @WebMethod(action = "http://www.example.org/Kalkulator/avg")
    @WebResult(name = "avgOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public AvgOutput avg(

        @WebParam(partName = "parameters", name = "avgInput", targetNamespace = "http://www.example.org/Kalkulator/")
        AvgInput parameters
    );

    @WebMethod(action = "http://www.example.org/Kalkulator/mul")
    @WebResult(name = "mulOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public MulOutput mul(

        @WebParam(partName = "parameters", name = "mulInput", targetNamespace = "http://www.example.org/Kalkulator/")
        MulInput parameters
    );

    @WebMethod(action = "http://www.example.org/Kalkulator/inc")
    @WebResult(name = "incOutput", targetNamespace = "http://www.example.org/Kalkulator/", partName = "parameters")
    public IncOutput inc(

        @WebParam(partName = "parameters", name = "incInput", targetNamespace = "http://www.example.org/Kalkulator/")
        IncInput parameters
    );
}
