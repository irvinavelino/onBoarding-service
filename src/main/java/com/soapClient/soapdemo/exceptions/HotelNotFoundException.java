package com.soapClient.soapdemo.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class HotelNotFoundException extends RuntimeException
{
    public HotelNotFoundException(String message) {
        super(message);
    }
}
